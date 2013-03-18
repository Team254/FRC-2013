package com.team254.frc2013.commands;

/**
 * Checks the pitch of the robot and waits for it to be swinging, indicating that the robot is off
 * the ground.
 *
 * @author pat@team254.com (Patrick Fairbank)
 */
public class WaitForLiftCommand extends CommandBase {
  public WaitForLiftCommand() {
  }
  protected void initialize() {
  }

  protected void execute() {
  }

  protected boolean isFinished() {
    return Math.abs(hanger.getPitchAngle()) > 2;
  }

  protected void end() {
  }

  protected void interrupted() {
  }
}
