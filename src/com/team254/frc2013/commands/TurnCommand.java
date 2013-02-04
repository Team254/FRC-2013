package com.team254.frc2013.commands;

/**
 * Controls the robot drivetrain to spin in place with a specified angle.
 *
 * @author richard@team254.com (Richard Lin)
 */
public class TurnCommand extends CommandBase {
  double oldAngle;
  double angle;
  double timeout;
  boolean isRight;

  public TurnCommand(double angle, double timeout) {
    requires(drive);
    this.angle = angle;
    this.timeout = timeout;
  }

  protected void initialize() {
    drive.resetGyro();
    oldAngle = drive.getGyroAngle();
    setTimeout(timeout);
    isRight = angle > 0 ? true : false;
  }

  protected void execute() {
    if(isRight) {
      drive.setLeftRightPower(1.0, -1.0);
    }
    else {
      drive.setLeftRightPower(-1.0, 1.0);
    }
    //System.out.println("Current angle: " + drive.getGyroAngle() + ", goal: " + angle);
  }

  protected boolean isFinished() {    
    return (drive.getGyroAngle() > angle) || isTimedOut();
  }

  protected void end() {
  }

  protected void interrupted() {
  }
}
