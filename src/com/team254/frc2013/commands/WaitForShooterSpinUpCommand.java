package com.team254.frc2013.commands;

/**
 * Waits until the shooter wheels have gotten up to a certain speed.
 *
 * @author tom@team254.com (Tom Bottiglieri)
 */
public class WaitForShooterSpinUpCommand extends CommandBase {

  public WaitForShooterSpinUpCommand(double timeout) {
    setTimeout(timeout);
  }
  protected void initialize() {
  }

  protected void execute() {

  }

  protected boolean isFinished() {
    return shooter.onSpeedTarget() || isTimedOut();
  }

  protected void end() {
  }

  protected void interrupted() {
  }
}
