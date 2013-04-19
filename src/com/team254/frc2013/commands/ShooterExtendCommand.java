package com.team254.frc2013.commands;

/**
 * Extends the shooter.
 *
 * @author pat@team254.com (Patrick Fairbank)
 */
public class ShooterExtendCommand extends CommandBase {
  public ShooterExtendCommand() {
  }

  protected void initialize() {
    shooter.extend();
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
