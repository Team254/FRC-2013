package com.team254.frc2013.commands;

/**
 * Drives hanger arms down to lift the entire robot weight.
 *
 * @author pat@team254.com (Patrick Fairbank)
 */
public class HangerDownCommand extends CommandBase {
  private double goal;

  public HangerDownCommand(double goal) {
    requires(drive);
    this.goal = goal;
  }

  protected void initialize() {
    hanger.setPto(true);
    hanger.enableController(false);
    motors.set(1);
  }

  protected void execute() {
  }

  protected boolean isFinished() {
    return motors.getLeftEncoder().get() > goal;
  }

  protected void end() {
    motors.set(0);
  }

  protected void interrupted() {
    motors.set(0);
  }
}
