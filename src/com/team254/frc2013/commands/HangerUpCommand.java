package com.team254.frc2013.commands;

/**
 * Drives hanger arms up to extend them.
 *
 * @author pat@team254.com (Patrick Fairbank)
 */
public class HangerUpCommand extends CommandBase {
  private double goal;

  public HangerUpCommand(double goal) {
    requires(drive);
    this.goal = goal;
  }

  protected void initialize() {
    drive.shift(true);
    hanger.setPto(true);
    hanger.setGoal(goal);
    hanger.enableController(true);
  }

  protected void execute() {
  }

  protected boolean isFinished() {
    return hanger.onTarget();
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
