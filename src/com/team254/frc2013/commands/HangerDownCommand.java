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
    drive.shift(true);
    hanger.setPto(true);
    hanger.enableController(false);
    motors.set(speed);
  }

  protected void execute() {
    System.out.println("Hanger down " + motors.getLeftEncoder().get() + " " + goal);
  }


  protected boolean isFinished() {
    boolean done = motors.getLeftEncoder().get() > goal;
    return done;
  }

  protected void end() {
    System.out.println("Hanger down done");
    motors.set(0);

  }

  protected void interrupted() {
    motors.set(0);
  }
}
