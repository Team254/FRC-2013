package com.team254.frc2013;

import com.team254.frc2013.auto.FiveDiscAutoMode;
import com.team254.frc2013.auto.SevenDiscAutoMode;
import com.team254.frc2013.commands.CommandBase;
import com.team254.lib.util.PIDTuner;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * Main class of the robot.
 *
 * @author richard@team254.com (Richard Lin)
 */
public class Overkill extends IterativeRobot {
  private AutoModeSelector autoModeSelector;
  private boolean lastAutonSelectButton;

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
    autoModeSelector.addAutoCommand("7 Disc", new SevenDiscAutoMode());
    autoModeSelector.addAutoCommand("5 Disc", new FiveDiscAutoMode());
  }

  public void disabledInit() {
    System.out.println("Disabled init.. reloading constants...");
    Constants.readConstantsFromFile();
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
    autoModeSelector.getCurrentAutoMode().start();
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
    autoModeSelector.getCurrentAutoMode().cancel();
  }

  /**
   * Called periodically during the teleoperated period.
   */
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    updateLCD();
  }

  private void updateLCD(){
    String driveEncoders = "LE: " + Math.floor(CommandBase.drive.getLeftEncoderDistance());
    driveEncoders += " RE: " + Math.floor(CommandBase.drive.getRightEncoderDistance());
    DriverStationLCD lcd = DriverStationLCD.getInstance();
    lcd.println(DriverStationLCD.Line.kUser2, 1, driveEncoders + "     ");
    lcd.println(DriverStationLCD.Line.kUser3, 1,
                "Gyro: " + Math.floor(CommandBase.drive.getGyroAngle() * 100) / 100);
    lcd.println(DriverStationLCD.Line.kUser4, 1, "IE: " + CommandBase.intake.getEncoderCount());
    lcd.println(DriverStationLCD.Line.kUser5, 1,
                "FS: " + CommandBase.shooter.getFrontCounter() + " BS: " +
                    CommandBase.shooter.getBackCounter());
    lcd.updateLCD();
  }
}
