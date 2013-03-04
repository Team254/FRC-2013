package com.team254.frc2013.commands;

/**
 * Controls the robot drivetrain to drive a certain minimum distance, ending once the distance has
 * been travelled while the robot is still moving.
 *
 * @author pat@team254.com (Patrick Fairbank)
 */
public class DriveAtSpeedCommand extends CommandBase {
  private double distance;
  private double power;
  private double timeout;
  private double angle;

  public DriveAtSpeedCommand(double distance, double power, double angle, double timeout) {
    this.distance = distance;
    this.power = power;
    this.angle = angle;
    this.timeout = timeout;
    requires(drive);
  }

  protected void initialize() {
    drive.setPowerGoal(power, angle);
    setTimeout(timeout);
  }

  protected void execute() {
  }

  protected boolean isFinished() {
    return (drive.getLeftEncoderDistance() / 12 > distance ||
                drive.getRightEncoderDistance() / 12 > distance || isTimedOut());
  }

  protected void end() {
    drive.setLeftRightPower(0, 0);
    drive.setPowerGoal(0, 0);
  }

  protected void interrupted() {
  }
}
