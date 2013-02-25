package com.team254.frc2013.commands;

/**
 * Controls the robot drivetrain to drive a certain distance.
 *
 * @author richard@team254.com (Richard Lin)
 */
public class DriveDistanceCommand extends CommandBase {
  private double distance;
  private double speed;
  private double timeout;

  public DriveDistanceCommand(double distance, double speed, double timeout) {
    this.distance = distance;
    this.speed = speed;
    this.timeout = timeout;
    requires(drive);
  }

  protected void initialize() {
    drive.resetEncoders();
    drive.setMaxSpeed(speed);
    drive.setGoal(distance, 0);
    setTimeout(timeout);
  }

  protected void execute() {

  }

  protected boolean isFinished() {
    boolean done = (drive.onTarget() ||
            isTimedOut());
    if (done)
      System.out.println("DD DONE!");
    return done;
  }

  protected void end() {
    drive.setLeftRightPower(0, 0);
    drive.setGoal(0,0);
  }

  protected void interrupted() {
  }
}
