package com.team254.frc2013.commands;

import com.team254.lib.util.Functions;
import edu.wpi.first.wpilibj.Timer;

/**
 * Drives hanger arms up and down in a square wave to allow for PID tuning.
 *
 * @author pat@team254.com (Patrick Fairbank)
 */
public class TuneHangCommand extends CommandBase {
  private Timer timer;
  private double periodSec;
  private double amplitudeTicks;

  public TuneHangCommand(double periodSec, double amplitudeTicks) {
    requires(drive);
    timer = new Timer();
    this.periodSec = periodSec;
    this.amplitudeTicks = amplitudeTicks;
  }

  protected void initialize() {
    timer.reset();
    timer.start();
    motors.getLeftEncoder().reset();
    motors.getRightEncoder().reset();
    hanger.setPto(true);
    hanger.setGoal(0);
    hanger.enableController(true);
  }

  protected void execute() {
    hanger.setGoalRaw(Functions.squareWave(timer.get(), periodSec, amplitudeTicks));
  }

  protected boolean isFinished() {
    return false;
  }

  protected void end() {
    hanger.enableController(false);
    motors.set(0);
  }

  protected void interrupted() {
    hanger.enableController(false);
    motors.set(0);
  }
}
