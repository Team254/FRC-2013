package com.team254.frc2013.commands;

/**
 * Retracts the shooter.
 *
 * @author pat@team254.com (Patrick Fairbank)
 */
public class ShooterRetractCommand extends CommandBase {
  public ShooterRetractCommand() {
    requires(shooter);
  }

  protected void initialize() {
    shooter.retract();
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
