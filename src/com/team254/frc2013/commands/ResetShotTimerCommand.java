package com.team254.frc2013.commands;

/**
 * Resets the shot timer to measure the time of each shoot sequence.
 *
 * @author Richard
 */
public class ResetShotTimerCommand extends CommandBase {

  protected void initialize() {
    shotTimer.reset();
    shotTimer.start();
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
