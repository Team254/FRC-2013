package com.team254.frc2013.commands;

import edu.wpi.first.wpilibj.DriverStation;

/**
 * Checks the auton timer and blocks if there is not enough time left.
 *
 * @author pat@team254.com (Patrick Fairbank)
 */
public class CheckAutonTimerCommand extends CommandBase {
  private double timeRequiredSec;

  public CheckAutonTimerCommand(double timeRequiredSec) {
    this.timeRequiredSec = timeRequiredSec;
  }

  protected void initialize() {
  }

  protected void execute() {
  }

  protected boolean isFinished() {
    return !DriverStation.getInstance().isAutonomous() || (15 - autonTimer.get() > timeRequiredSec);
  }

  protected void end() {
  }

  protected void interrupted() {
  }
}
