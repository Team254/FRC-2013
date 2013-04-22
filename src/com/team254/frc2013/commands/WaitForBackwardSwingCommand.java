package com.team254.frc2013.commands;

/**
 * Checks the pitch of the robot and waits for it to be swinging backward past a given angle.
 *
 * @author pat@team254.com (Patrick Fairbank)
 */
public class WaitForBackwardSwingCommand extends CommandBase {
  double angle;

  public WaitForBackwardSwingCommand(double angle) {
    this.angle = angle;
  }
  protected void initialize() {
    startedAutoHang = true;
  }

  protected void execute() {
  }

  protected boolean isFinished() {
    return hanger.getPitchAngle() < angle;
  }

  protected void end() {
  }

  protected void interrupted() {
  }
}
