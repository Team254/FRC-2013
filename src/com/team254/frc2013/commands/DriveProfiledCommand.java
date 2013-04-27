package com.team254.frc2013.commands;

import com.team254.lib.control.MotionProfile;
import edu.wpi.first.wpilibj.Timer;

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
  boolean doEnd = true;

  public DriveProfiledCommand(double distance, double speed, double angle, double timeout, boolean doEnd) {
    requires(drive);
    this.distance = distance;
    this.speed = speed;
    this.timeout = timeout;
    this.angle = angle;
    this.doEnd = doEnd;
  }

   public DriveProfiledCommand(double distance, double speed, double angle, double timeout) {
     this(distance, speed, angle, timeout, true);
   }

  public DriveProfiledCommand(double distance, double speed, double angle, double timeout,
                              MotionProfile profile) {
    this(distance, speed, angle, timeout);
    this.profile = profile;
  }

  protected void initialize() {
    if (profile != null) {
      drive.setStraightProfile(profile);
    }
    drive.setPositionGoal(distance * 12, angle, speed * 12);  // Drive controller works in inches
    setTimeout(timeout);
  }

  protected void execute() {
  }

  protected boolean isFinished() {
    return doEnd && (drive.onTarget() || isTimedOut());
  }

  protected void end() {
    drive.disableControllers();
    drive.resetControllers();
  }

  protected void interrupted() {
  }
}
