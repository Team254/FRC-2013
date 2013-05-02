package com.team254.frc2013.commands;

/**
 * Stop driving.
 *
 * @author tom@team254.com (Tom Bottiglieri)
 */
public class StopDriveCommand extends CommandBase {

  protected void initialize() {
    drive.setLeftRightPower(0, 0);
  }

  protected void execute() {
  }

  protected boolean isFinished() {
    return true;
  }

  protected void end() {
  }

  protected void interrupted() {
  }

}
