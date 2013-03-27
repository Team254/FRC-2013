package com.team254.frc2013.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Tells the robot to wait for a specified amount of time.
 *
 * @author tom@team254.com (Tom Bottiglieri)
 */
public class WaitCommand extends Command {
  double timeout;

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
