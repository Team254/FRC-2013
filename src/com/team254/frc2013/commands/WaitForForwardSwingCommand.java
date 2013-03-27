package com.team254.frc2013.commands;

/**
 * Checks the pitch of the robot and waits for it to be swinging forward past a given angle.
 *
 * @author pat@team254.com (Patrick Fairbank)
 */
public class WaitForForwardSwingCommand extends CommandBase {
  private double minForeswingAngle;
  private double minBackswingAngle;

  public WaitForForwardSwingCommand(double minForeswingAngle, double minBackswingAngle) {
    this.minForeswingAngle = minForeswingAngle;
    this.minBackswingAngle = minBackswingAngle;
  }
  protected void initialize() {
  }

  protected void execute() {
  }

  protected boolean isFinished() {
    return hanger.getPitchAngle() > minForeswingAngle && hanger.getPitchRate() > 0 ||
        hanger.getPitchAngle() > minBackswingAngle;
  }

  protected void end() {
  }

  protected void interrupted() {
  }
}
