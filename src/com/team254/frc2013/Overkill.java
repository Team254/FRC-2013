package com.team254.frc2013;

import com.team254.frc2013.auto.CenterDiscMiddleAutoMode;
import com.team254.frc2013.auto.CenterDiscSideAutoMode;
import com.team254.frc2013.auto.DriveMotorTestAutoMode;
import com.team254.frc2013.auto.FiveDiscAutoMode;
import com.team254.frc2013.auto.FourDiscAutoMode;
import com.team254.frc2013.auto.SevenDiscAutoMode;
import com.team254.frc2013.auto.TestAutoMode;
import com.team254.frc2013.auto.ThreeDiscAutoMode;
import com.team254.frc2013.auto.TuneDriveAutoMode;
import com.team254.frc2013.auto.TwoDiscAutoMode;
import com.team254.frc2013.commands.AutoHangCommand;
import com.team254.frc2013.commands.CommandBase;
import com.team254.frc2013.commands.PtoCommand;
import com.team254.frc2013.subsystems.Shooter;
import com.team254.lib.control.ControlUpdater;
import com.team254.lib.util.Debouncer;
import com.team254.lib.util.Latch;
import com.team254.lib.util.PIDTuner;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * Main class of the robot.
 *
 * @author richard@team254.com (Richard Lin)
 */
public class Overkill extends IterativeRobot {

  private AutoModeSelector autoModeSelector;
  private CommandGroup currentAutoMode;
  private AutoHangCommand autoHangCommand;
  private boolean autoHangStarted;
  private boolean lastStage1HangButton;
  private Latch autonSelectLatch = new Latch();
  private Latch reinitGyroLatch = new Latch();
  Debouncer gyroDriftDetector = new Debouncer(1.0);
  double lastAngle = 0;
  int gyroReinits = 0;

  /**
   * Called when the robot is first started up and should be used for any
   * initialization code.
   */
  public void robotInit() {
    // Initialize all subsystems.
    PIDTuner.getInstance().start();
    CommandBase.init();
    ControlUpdater.getInstance().start();

    // Set up autonomous modes.
    autoModeSelector = new AutoModeSelector();
    autoModeSelector.addAutoCommand("7 Disc", SevenDiscAutoMode.class);
    autoModeSelector.addAutoCommand("3 Disc", ThreeDiscAutoMode.class);
    autoModeSelector.addAutoCommand("5 Disc", FiveDiscAutoMode.class);
    autoModeSelector.addAutoCommand("4 Disc", FourDiscAutoMode.class);
    autoModeSelector.addAutoCommand("2 Disc", TwoDiscAutoMode.class);
    autoModeSelector.addAutoCommand("Center (Side)", CenterDiscSideAutoMode.class);
    autoModeSelector.addAutoCommand("Center (Middle)", CenterDiscMiddleAutoMode.class);
    autoModeSelector.addAutoCommand("Test Auto", TestAutoMode.class);
    autoModeSelector.addAutoCommand("Drive Test", DriveMotorTestAutoMode.class);
    autoModeSelector.addAutoCommand("Tune Drive", TuneDriveAutoMode.class);

    // Choose the first non-none autonomous.
    autoModeSelector.increment();
  }

  public void disabledInit() {
    System.out.println("Disabled init.. reloading constants...");
    Constants.readConstantsFromFile();
    CommandBase.drive.disableControllers();
    CommandBase.shooter.setIndexerUp(true); // Disabled is default up
    // Make sure that the autonomous stops running.
    if (currentAutoMode != null) {
      currentAutoMode.cancel();
      currentAutoMode = null;
    }

    // Reset all things to their default positions, so that they're not left on from autonomous.
    CommandBase.shooter.setShooterOn(false);
    CommandBase.shooter.setPreset(Shooter.PRESET_FRONT_PYRAMID);
    CommandBase.intake.setIntakePower(0);
    CommandBase.conveyor.setMotor(0);
    CommandBase.compressor.start();
    CommandBase.autonTimer.reset();
    CommandBase.drive.setLeftRightPower(0, 0);
    CommandBase.motors.set(0);
    CommandBase.sc.wantHang = false;

    lastAngle = CommandBase.drive.getGyroAngle();
  }

  public void disabledPeriodic() {
    //System.out.println("HE Up: " + CommandBase.shooter.isIndexerSensedUp() + ", Down: " + CommandBase.shooter.isIndexerSensedDown());

    boolean autonSelectButton =
            CommandBase.controlBoard.operatorJoystick.getAutonSelectButtonState();
    if (autonSelectLatch.update(autonSelectButton)) {
      autoModeSelector.increment();
    }

    boolean reinitGyroButton =
            CommandBase.controlBoard.operatorJoystick.getClimbButtonState();
    if (reinitGyroLatch.update(reinitGyroButton)) {
      System.out.println("About to reinit gyro");
      CommandBase.drive.reinitGyro();
      CommandBase.drive.resetGyro();
      System.out.println("Finished reinit gyro");
    }

    lastStage1HangButton = CommandBase.controlBoard.getStage1Hang();
    updateLCD();
    CommandBase.shooter.setSpeedLimit(1);
    CommandBase.shooter.retract();

    double curAngle = CommandBase.drive.getGyroAngle();
    if (gyroDriftDetector.update(Math.abs(curAngle - lastAngle) > (.75 / 50.0)) && gyroReinits < 3) {
      gyroReinits++;
      System.out.println("!!! Sensed drift, about to auto-reinit gyro (" + gyroReinits + ")");
      CommandBase.drive.reinitGyro();
      CommandBase.drive.resetGyro();
      gyroDriftDetector.reset();
      curAngle = CommandBase.drive.getGyroAngle();
      System.out.println("Finished auto-reinit gyro");

    }
    lastAngle = curAngle;
  }

  /**
   * Called once at the start of the autonomous period.
   */
  public void autonomousInit() {
    // Make sure that the autonomous stops running.
    if (currentAutoMode != null) {
      currentAutoMode.cancel();
      currentAutoMode = null;
    }

    CommandBase.sc.reset();

    CommandBase.drive.resetEncoders();
    CommandBase.drive.resetGyro();
    currentAutoMode = autoModeSelector.getCurrentAutoModeNewInstance();
    currentAutoMode.start();
    CommandBase.autonTimer.start();
  }

  /**
   * Called periodically during the autonomous period.
   */
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
    updateLCD();
  }

  /**
   * Called once at the start of the teleoperated period.
   */
  public void teleopInit() {
    // Make sure that the autonomous stops running when teleop begins.
    if (currentAutoMode != null) {
      currentAutoMode.cancel();
      currentAutoMode = null;
    }

    // Set up the one-shot autonomous 30-point climbing routine.
    autoHangCommand = new AutoHangCommand();
    autoHangStarted = false;
    CommandBase.shooter.retract();
    CommandBase.sc.wantForceFloor = false;
    CommandBase.sc.wantHang = false;
  }

  /**
   * Called periodically during the teleoperated period.
   */
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    updateLCD();

    // Run shooter slowly
    if (CommandBase.controlBoard.operatorJoystick.getAutonSelectButtonState()) {
      CommandBase.shooter.setSpeedLimit(.35);
    } else {
      CommandBase.shooter.setSpeedLimit(1);
    }

    // Shooter presets
    if (CommandBase.controlBoard.operatorJoystick.getBackPyramidButtonState()) {
      CommandBase.shooter.setPreset(Shooter.PRESET_BACK_PYRAMID);
    } else if (CommandBase.controlBoard.operatorJoystick.getFrontPyramidButtonState()) {
      CommandBase.shooter.setPreset(Shooter.PRESET_FRONT_PYRAMID);
    }

    // Set shooter on/off.
    CommandBase.shooter.setShooterOn(CommandBase.controlBoard.operatorJoystick.getShooterSwitch());

    // Do we want to shoot?
    CommandBase.sc.wantRapidFire = CommandBase.controlBoard.operatorJoystick.getRapidFireButtonState();
    CommandBase.sc.wantShoot = CommandBase.controlBoard.operatorJoystick.getShootButtonState() || CommandBase.controlBoard.operatorJoystick.getRapidFireButtonState();
    if (!CommandBase.controlBoard.operatorJoystick.getRapidFireButtonState()) {
      CommandBase.rapidFireShots = 0;
    }

    if (CommandBase.controlBoard.operatorJoystick.getIntakeUpButtonState()) {
      CommandBase.intake.rezero();
    }

    // Intake
    CommandBase.sc.wantIntake = CommandBase.controlBoard.operatorJoystick.getIntakeButtonState()
            || CommandBase.controlBoard.leftStick.getRawButton(2);
    CommandBase.sc.wantExhaust = CommandBase.controlBoard.operatorJoystick.getIntakeOutButtonState()
            || CommandBase.controlBoard.leftStick.getTrigger();
    CommandBase.sc.wantManualIndex = CommandBase.controlBoard.operatorJoystick.getIndexButtonState();

    CommandBase.sc.wantIntakeUp = CommandBase.controlBoard.operatorJoystick.getIntakePositionSwitch() == 1;
    CommandBase.sc.wantIntakeDown = CommandBase.controlBoard.operatorJoystick.getIntakePositionSwitch() == -1;
    // System.out.println(CommandBase.controlBoard.operatorJoystick.getIntakePositionSwitch());
    // Set 10pt hang up/down.
    CommandBase.hanger.setHookUp(CommandBase.controlBoard.getStage1Hang());

    // Handle triggering the autonomous 30-point climbing routine.
    if (CommandBase.controlBoard.getStage1Hang() && !lastStage1HangButton) {
      CommandBase.hanger.resetPitchGyro();
    } else if (!CommandBase.controlBoard.getStage1Hang() && lastStage1HangButton
            && CommandBase.controlBoard.operatorJoystick.getClimbButtonState()
            && autoHangCommand != null) {
      autoHangStarted = true;
      Scheduler.getInstance().add(autoHangCommand);
    }

    if (autoHangStarted && autoHangCommand != null && CommandBase.startedAutoHang && Math.abs(CommandBase.controlBoard.leftStick.getY()) > .5) {
      System.out.println("Canceling climb due to joystick.");
      autoHangCommand.cancel();
      autoHangCommand = null;
      Scheduler.getInstance().add(new PtoCommand());
    }

    // Control override
    CommandBase.sc.wantControlOverride = CommandBase.controlBoard.operatorJoystick.getControlLoopsSwitchState();

    // Kill the climb if the dead man switch is released.
    if (autoHangStarted && !CommandBase.controlBoard.operatorJoystick.getClimbButtonState()
            && autoHangCommand != null) {
      System.out.println("Canceling climb.");
      autoHangCommand.cancel();
      autoHangCommand = null;
    }

    lastStage1HangButton = CommandBase.controlBoard.getStage1Hang();
  }

  private void updateLCD() {
    String driveEncoders = "U" + (CommandBase.shooter.isIndexerSensedUp() ? 1 : 0) + " D" + (CommandBase.shooter.isIndexerSensedDown() ? 1 : 0);
    driveEncoders += " L: " + (Math.floor(CommandBase.motors.getLeftEncoder().get()) * 10) / 10.0;
    driveEncoders += " R: " + (Math.floor(CommandBase.drive.getRightEncoderDistance()) * 10) / 10.0;
    DriverStationLCD lcd = DriverStationLCD.getInstance();
    lcd.println(DriverStationLCD.Line.kUser2, 1, driveEncoders + "     ");
    lcd.println(DriverStationLCD.Line.kUser3, 1,
            "Gy: " + Math.floor(CommandBase.drive.getGyroAngle() * 100) / 100
            + " Pitch: " + Math.floor(CommandBase.hanger.getPitchAngle() * 10) / 10);
    lcd.println(DriverStationLCD.Line.kUser4, 1,
            "D:" + (CommandBase.shooter.isIndexerLoaded() ? 1 : 0) + " | "
            + (Math.floor(CommandBase.controlBoard.leftStick.getY() * 100) / 100.0) + "|"
            + (Math.floor(CommandBase.controlBoard.rightStick.getX() * 100) / 100.0) + "    ");
    lcd.println(DriverStationLCD.Line.kUser5, 1,
            "?: " + CommandBase.shooter.onSpeedTarget() + " RPM: "
            + Math.floor(CommandBase.shooter.lastRpm * 10) / 10 + "     ");
    lcd.println(DriverStationLCD.Line.kUser6, 1,
            "Pr: " + Math.floor(CommandBase.pressureTransducer.getPsi()) + " Z:" + (CommandBase.intake.getZeroSensor() ? 1 : 0)
            + " W:" + CommandBase.intake.controller.pos);
    lcd.updateLCD();
  }
}
