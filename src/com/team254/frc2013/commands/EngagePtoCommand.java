package com.team254.frc2013.commands;

import edu.wpi.first.wpilibj.Timer;

/**
 * Engages the PTO.
 *
 * @author richard@team254.com (Richard Lin)
 */
public class EngagePtoCommand extends CommandBase {
  private Timer driveTimer;

  public EngagePtoCommand() {
    requires(drive);
    driveTimer = new Timer();
  }

  protected void initialize() {
    drive.shift(true);
    hanger.setPto(true);
    driveTimer.reset();
    driveTimer.start();
    motors.set(0.2);
  }

  protected void execute() {
  }

  protected boolean isFinished() {
    return driveTimer.get() > 0.25;
  }

  protected void end() {
    System.out.println("Engaged PTO.");
    motors.set(0);
    driveTimer.stop();
  }

  protected void interrupted() {
    motors.set(0);
    driveTimer.stop();
  }
}
