package com.team254.frc2013;

import com.team254.frc2013.auto.CenterDiscMiddleAutoMode;
import com.team254.frc2013.auto.CenterDiscSideAutoMode;
import com.team254.frc2013.auto.DriveMotorTestAutoMode;
import com.team254.lib.control.ControlUpdater;
import com.team254.frc2013.auto.FiveDiscAutoMode;
import com.team254.frc2013.auto.FourDiscAutoMode;
import com.team254.frc2013.auto.SevenDiscAutoMode;
import com.team254.frc2013.auto.ThreeDiscAutoMode;
import com.team254.frc2013.auto.TuneDriveAutoMode;
import com.team254.frc2013.auto.TwoDiscAutoMode;
import com.team254.frc2013.commands.CommandBase;
import com.team254.frc2013.subsystems.Shooter;
import com.team254.lib.util.PIDTuner;
import edu.wpi.first.wpilibj.DigitalInput;
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
  private boolean lastAutonSelectButton;
  private CommandGroup currentAutoMode;
  DigitalInput thing = new DigitalInput(14);

  /**
   * Called when the robot is first started up and should be used for any initialization code.
   */
  public void robotInit() {
    // Initialize all subsystems.
    PIDTuner.getInstance().start();
    CommandBase.init();
    ControlUpdater.getInstance().start();

    // Set up autonomous modes.
    autoModeSelector = new AutoModeSelector();
    autoModeSelector.addAutoCommand("3 Disc", ThreeDiscAutoMode.class);
    autoModeSelector.addAutoCommand("7 Disc", SevenDiscAutoMode.class);
    autoModeSelector.addAutoCommand("5 Disc", FiveDiscAutoMode.class);
    autoModeSelector.addAutoCommand("4 Disc", FourDiscAutoMode.class);
    autoModeSelector.addAutoCommand("2 Disc", TwoDiscAutoMode.class);
    autoModeSelector.addAutoCommand("Center (Middle)", CenterDiscMiddleAutoMode.class);
    autoModeSelector.addAutoCommand("Center (Side)", CenterDiscSideAutoMode.class);
    autoModeSelector.addAutoCommand("Drive Test", DriveMotorTestAutoMode.class);
    autoModeSelector.addAutoCommand("Tune Drive", TuneDriveAutoMode.class);

    // Choose the first non-none autonomous.
    autoModeSelector.increment();
  }

  public void disabledInit() {
    System.out.println("Disabled init.. reloading constants...");
    Constants.readConstantsFromFile();
    CommandBase.drive.disableControllers();
    // Make sure that the autonomous stops running.
    if (currentAutoMode != null) {
      currentAutoMode.cancel();
      currentAutoMode = null;
    }
  }

  public void disabledPeriodic() {
    boolean autonSelectButton =
        CommandBase.controlBoard.operatorJoystick.getAutonSelectButtonState();
    if (autonSelectButton && !lastAutonSelectButton) {
      autoModeSelector.increment();
    }
    lastAutonSelectButton = autonSelectButton;
    updateLCD();
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

    CommandBase.drive.resetEncoders();
    CommandBase.drive.resetGyro();
    currentAutoMode = autoModeSelector.getCurrentAutoModeNewInstance();
    currentAutoMode.start();
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

    // Reset all things to their default positions, so that they're not left on from autonomous.
    CommandBase.shooter.setShooterOn(false);
    CommandBase.shooter.setPreset(Shooter.PRESET_FRONT_PYRAMID);
    CommandBase.intake.setIntakePower(0);
    CommandBase.conveyor.setMotor(0);
    CommandBase.compressor.start();
  }

  /**
   * Called periodically during the teleoperated period.
   */
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    updateLCD();

    // Set shooter presets.
    if (CommandBase.controlBoard.operatorJoystick.getBackPyramidButtonState()) {
      CommandBase.shooter.setPreset(Shooter.PRESET_BACK_PYRAMID);
    } else if (CommandBase.controlBoard.operatorJoystick.getFrontPyramidButtonState()) {
      CommandBase.shooter.setPreset(Shooter.PRESET_FRONT_PYRAMID);
    }

    // Set shooter on/off.
    CommandBase.shooter.setShooterOn(CommandBase.controlBoard.operatorJoystick.getShooterSwitch());

    // Set 10pt hang up/down.
    CommandBase.hanger.setHookUp(CommandBase.controlBoard.getStage1Hang());
  }

  private void updateLCD(){
    String driveEncoders = "LE: " + Math.floor(CommandBase.drive.getLeftEncoderDistance());
    driveEncoders += " RE: " + Math.floor(CommandBase.drive.getRightEncoderDistance());
    DriverStationLCD lcd = DriverStationLCD.getInstance();
    lcd.println(DriverStationLCD.Line.kUser2, 1, driveEncoders + "     ");
    lcd.println(DriverStationLCD.Line.kUser3, 1,
                "Gyro: " + Math.floor(CommandBase.drive.getGyroAngle() * 100) / 100);
    lcd.println(DriverStationLCD.Line.kUser4, 1,
                "PSI: " + Math.floor(CommandBase.pressureTransducer.getPsi()) + "     ");
    lcd.println(DriverStationLCD.Line.kUser5, 1,
                "HE: " + !CommandBase.shooter.indexerDownSensorA.get() + ", " +
                !CommandBase.shooter.indexerDownSensorB.get() + "    ");
    lcd.updateLCD();
  }
}
