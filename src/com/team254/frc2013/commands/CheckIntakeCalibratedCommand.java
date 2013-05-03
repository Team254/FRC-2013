package com.team254.frc2013.commands;

/**
 * Checks to see if intake is ready
 *
 * @author tom@team254.com (Tom Bottiglieri)
 */
public class CheckIntakeCalibratedCommand extends CommandBase {
  private double timeRequiredSec;

  public CheckIntakeCalibratedCommand(double timeout) {
    setTimeout(timeout);
  }

  protected void initialize() {
  }

  protected void execute() {
  }

  protected boolean isFinished() {
    return isTimedOut() || intake.ready();
  }

  protected void end() {
  }

  protected void interrupted() {
  }
}
