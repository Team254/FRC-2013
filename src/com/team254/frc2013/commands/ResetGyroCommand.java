package com.team254.frc2013.commands;

/**
 * Resets the gyro.
 *
 * @author pat@team254.com (Patrick Fairbank)
 */
public class ResetGyroCommand extends CommandBase {
  protected void initialize() {
    drive.resetGyro();
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
