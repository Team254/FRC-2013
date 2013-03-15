package com.team254.frc2013.commands;

import com.team254.lib.util.Functions;
import edu.wpi.first.wpilibj.Timer;

/**
 * Drives back and forth in a square wave to allow for PID tuning.
 *
 * @author pat@team254.com (Patrick Fairbank)
 */
public class TuneDriveCommand extends CommandBase {
  private Timer timer;
  private double periodSec;
  private double amplitudeFeet;

  public TuneDriveCommand(double periodSec, double amplitudeFeet) {
    requires(drive);
    timer = new Timer();
    this.periodSec = periodSec;
    this.amplitudeFeet = amplitudeFeet;
  }

  protected void initialize() {
    timer.reset();
    timer.start();
    drive.resetEncoders();
    drive.resetGyro();
    drive.setPositionGoal(0, 0, 5);
  }

  protected void execute() {
    drive.updatePositionGoal(Functions.squareWave(timer.get(), periodSec, amplitudeFeet * 12));
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
