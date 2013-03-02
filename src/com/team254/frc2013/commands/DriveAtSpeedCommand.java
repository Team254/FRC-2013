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

  public DriveAtSpeedCommand(double distance, double power, double timeout) {
    this.distance = distance;
    this.power = power;
    this.timeout = timeout;
    requires(drive);
  }

  protected void initialize() {
    drive.setPowerGoal(power, 0);
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
    drive.setPowerGoal(0,0);
  }

  protected void interrupted() {
  }
}
