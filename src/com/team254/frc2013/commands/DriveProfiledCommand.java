package com.team254.frc2013.commands;

/**
 * Controls the robot drivetrain to drive in a straight line to a certain distance.
 *
 * @author pat@team254.com (Patrick Fairbank)
 */
public class DriveProfiledCommand extends CommandBase {
  private double distance;
  private double speed;
  private double timeout;

  public DriveProfiledCommand(double distance, double speed, double timeout) {
    requires(drive);
    this.distance = distance;
    this.speed = speed;
    this.timeout = timeout;
  }

  protected void initialize() {
    drive.setMaxSpeed(speed * 12);  // Drive controller works in inches
    drive.setPositionGoal(distance * 12, 0);  // Drive controller works in inches
    setTimeout(timeout);
  }

  protected void execute() {
  }

  protected boolean isFinished() {
    return drive.onTarget() || isTimedOut();
  }

  protected void end() {
    drive.setLeftRightPower(0, 0);
    drive.setPositionGoal(0, 0);
  }

  protected void interrupted() {
  }
}
