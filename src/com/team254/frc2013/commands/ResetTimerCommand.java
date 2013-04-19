package com.team254.frc2013.commands;

import edu.wpi.first.wpilibj.Timer;

/**
 * Resets and restarts a given timer.
 *
 * @author Richard
 */
public class ResetTimerCommand extends CommandBase {
  private Timer timer;

  public ResetTimerCommand(Timer timer) {
    this.timer = timer;
  }
  protected void initialize() {
    timer.reset();
    timer.start();
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
