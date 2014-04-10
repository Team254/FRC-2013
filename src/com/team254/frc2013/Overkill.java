package com.team254.frc2013;


import com.team254.frc2013.commands.CommandBase;
import com.team254.lib.control.ControlUpdater;
import com.team254.lib.util.Debouncer;
import com.team254.lib.util.Latch;
import com.team254.lib.util.PIDTuner;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
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

    // Reset all things to their default positions, so that they're not left on from autonomous.

    CommandBase.intake.setIntakePower(0);

    CommandBase.compressor.start();
    CommandBase.autonTimer.reset();
    CommandBase.drive.setLeftRightPower(0, 0);
    CommandBase.motors.set(0);


    lastAngle = CommandBase.drive.getGyroAngle();
  }

  public void disabledPeriodic() {
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

  }

  /**
   * Called periodically during the teleoperated period.
   */
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    updateLCD();
    
    double roller=  0;
    if (CommandBase.controlBoard.gamePad.getRawButton(8)) {
      roller = 1;
    } else if( CommandBase.controlBoard.gamePad.getRawButton(7)) {
      roller = -1;
    }
    CommandBase.intake.setIntakePower(roller);
    
 

 
    lastStage1HangButton = CommandBase.controlBoard.getStage1Hang();
  }

  private void updateLCD() {
    
    DriverStationLCD lcd = DriverStationLCD.getInstance();

    lcd.println(DriverStationLCD.Line.kUser2, 1, Timer.getFPGATimestamp() + "          ");

    lcd.updateLCD();
  }
}
