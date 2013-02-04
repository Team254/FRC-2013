package com.team254.frc2013.commands;

/**
 * Controls the intake to run with a certain power for a specified amount of time.
 *
 * @author art.kalb96@gmail.com (Arthur Kalb)
 */
public class IntakeTimedCommand extends CommandBase {
  private double power = 0.0;
  private double timeout = 0.0;
  
  public IntakeTimedCommand(double power, double timeout) {
    this.timeout = timeout;
    this.power = power;
    requires(intake);
  }

  protected void initialize() {
    setTimeout(timeout);
  }

  protected void execute() {
    intake.setIntakePower(power);
  }

  protected boolean isFinished() {
    return isTimedOut();
  }

  protected void end() {
    intake.setIntakePower(0.0);
  }

  protected void interrupted() {
  }
}
