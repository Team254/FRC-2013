package com.team254.frc2013.commands;

/**
 * Controls the robot drivetrain to drive a certain minimum distance, ending once the distance has
 * been traveled while the robot is still moving.
 *
 * @author pat@team254.com (Patrick Fairbank)
 */
public class DriveAtSpeedCommand extends CommandBase {
  private double distance;
  private double speed;
  private double timeout;
  private double angle;

  public DriveAtSpeedCommand(double distance, double speed, double angle, double timeout) {
    this.distance = distance;
    this.speed = speed;
    this.angle = angle;
    this.timeout = timeout;
    requires(drive);
  }

  protected void initialize() {
    drive.setSpeedGoal(speed * 12, angle);
    setTimeout(timeout);
  }

  protected void execute() {

  }

  protected boolean isFinished() {
    return (drive.getLeftEncoderDistance() / 12.0 > distance ||
                drive.getRightEncoderDistance() / 12.0 > distance || isTimedOut());
  }

  protected void end() {
    drive.setLeftRightPower(0, 0);
    drive.setSpeedGoal(0, 0);
    drive.disableControllers();
  }

  protected void interrupted() {
  }
}
