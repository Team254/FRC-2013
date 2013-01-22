package com.team254.frc2013.commands;

/**
 * Controls the robot drivetrain using the left and right joystick Y axes in a tank scheme.
 *
 * @author richard@team254.com (Richard Lin)
 */
public class TankDriveCommand extends CommandBase {
  public TankDriveCommand() {
    requires(drive);
  }

  protected void initialize() {
  }

  protected void execute() {
    drive.setLeftRightPower(-controlBoard.leftStick.getY(), -controlBoard.rightStick.getY());
  }

  protected boolean isFinished() {
    return false;
  }

  protected void end() {
    drive.setMaxSpeed(1.0);
  }

  protected void interrupted() {
  }
}
