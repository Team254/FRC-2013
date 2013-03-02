package com.team254.frc2013.commands;

/**
 * Resets the drive encoders.
 *
 * @author pat@team254.com (Patrick Fairbank)
 */
public class ResetDriveEncodersCommand extends CommandBase {
  protected void initialize() {
    drive.resetEncoders();
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
