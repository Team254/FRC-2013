package com.team254.frc2013.commands;

/**
 * Controls the robot drivetrain to drive a certain minimum distance, ending once the distance has
 * been travelled while the robot is still moving.
 *
 * @author pat@team254.com (Patrick Fairbank)
 */
public class DriveMinDistanceCommand extends CommandBase {
  private double distance;
  private double speed;
  private double timeout;

  public DriveMinDistanceCommand(double distance, double speed, double timeout) {
    this.distance = distance;
    this.speed = speed;
    this.timeout = timeout;
    requires(drive);
  }

  protected void initialize() {
    drive.setMaxSpeed(speed * 12);  // Drive controller works in inches
    drive.setGoal(distance * 12, 0);  // Drive controller works in inches
    setTimeout(timeout);
  }

  protected void execute() {
  }

  protected boolean isFinished() {
    return (drive.getLeftEncoderDistance() / 12 > distance ||
                drive.getRightEncoderDistance() / 12 > distance ||
                isTimedOut());
  }

  protected void end() {
    drive.setLeftRightPower(0, 0);
    drive.setGoal(0,0);
  }

  protected void interrupted() {
  }
}
