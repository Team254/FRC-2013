package com.team254.frc2013.commands;

import com.team254.lib.control.MotionProfile;

/**
 * Controls the robot drivetrain to drive in a straight line to a certain distance.
 *
 * @author pat@team254.com (Patrick Fairbank)
 */
public class DriveProfiledCommand extends CommandBase {
  private double distance;
  private double speed;
  private double timeout;
  private double angle;
  MotionProfile profile = null;

  public DriveProfiledCommand(double distance, double speed, double timeout) {
    this(distance,0,speed,timeout);
  }
  
  public DriveProfiledCommand(double distance, double angle, double speed, double timeout) {
    requires(drive);
    this.distance = distance;
    this.speed = speed;
    this.timeout = timeout;
    this.angle = angle; 
  }
  
  public DriveProfiledCommand(double distance, double speed, double timeout, MotionProfile profile) {
    this(distance,speed,timeout);
    this.profile = profile;
  }

  protected void initialize() {
    if (profile != null)
      drive.setStraightProfile(profile);
    drive.setPositionGoal(distance * 12, angle, speed * 12);  // Drive controller works in inches
    setTimeout(timeout);
  }

  protected void execute() {
  }

  protected boolean isFinished() {
    return drive.onTarget() || isTimedOut();
  }

  protected void end() {
    drive.disableControllers();
    drive.resetControllers();
  }

  protected void interrupted() {
  }
}
