package com.team254.frc2013.commands;

import com.team254.lib.util.Functions;
import edu.wpi.first.wpilibj.Timer;

/**
 * Drives back and forth in a square wave to allow for PID tuning.
 *
 * @author pat@team254.com (Patrick Fairbank)
 */
public class TuneTurnCommand extends CommandBase {
  private Timer timer;
  private double periodSec;
  private double angle;

  public TuneTurnCommand(double periodSec, double angle) {
    requires(drive);
    timer = new Timer();
    this.periodSec = periodSec;
    this.angle = angle;
  }

  protected void initialize() {
    drive.disableControllers();
    timer.reset();
    timer.start();
    drive.resetEncoders();
    drive.resetGyro();
    drive.setTurnGoal(0);
  }

  protected void execute() {
    drive.updateTurnGoal(Functions.squareWave(timer.get(), periodSec, angle));
  }

  protected boolean isFinished() {
    return false;
  }

  protected void end() {
    drive.disableControllers();
  }

  protected void interrupted() {
    drive.disableControllers();
  }
}
