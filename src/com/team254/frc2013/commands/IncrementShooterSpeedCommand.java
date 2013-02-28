package com.team254.frc2013.commands;

/**
 * Updates both shooters to increase or decrease values.
 *
 * @author tom@team254.com (Tom Bottiglieri)
 */
public class IncrementShooterSpeedCommand extends CommandBase {
  double f,b;
  public IncrementShooterSpeedCommand(double frontDelta, double backDelta) {
    f = frontDelta;
    b = backDelta;
    requires(shooter);
  }

  protected void initialize() {
  }
  /*
   * Does the incrementing
   */
  protected void execute() {
    double fg = shooter.getFrontGoal() + f;
    double bg = shooter.getBackGoal() + b;
    shooter.setSpeeds(fg, bg);
    System.out.println("Shooter goals " + fg + " " + bg );
  }

  protected boolean isFinished() {
    return true;
  }

  protected void end() {
  }

  protected void interrupted() {
  }

}
