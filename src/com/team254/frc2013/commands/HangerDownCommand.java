package com.team254.frc2013.commands;

/**
 * Drives hanger arms down to lift the entire robot weight.
 *
 * @author pat@team254.com (Patrick Fairbank)
 */
public class HangerDownCommand extends CommandBase {
  private double goal;
  private double speed;

  public HangerDownCommand(double goal, double speed) {
    requires(drive);
    this.goal = goal;
    this.speed = speed;
  }

  protected void initialize() {
    hanger.setPto(true);
    hanger.enableController(false);
    motors.set(speed);
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
