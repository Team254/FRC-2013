package com.team254.frc2013.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Wait for a specified amount of time.
 *
 * @author tom@team254.com (Tom Bottiglieri)
 */
public class WaitCommand extends Command {
  private double timeout;

  public WaitCommand(double seconds) {
    timeout = seconds;
  }
  protected void initialize() {
    setTimeout(timeout);
  }

  protected void execute() {
  }

  protected boolean isFinished() {
    return isTimedOut();
  }

  protected void end() {
  }

  protected void interrupted() {
  }
}
