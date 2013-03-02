package com.team254.frc2013.commands;

/**
 * Rotates the drivebase a specific angle in specific amount of time
 *
 * @author tom@team254.com (Tom Bottiglieri)
 */
public class TurnAngleCommand extends CommandBase {
  private double angle;
  private double timeout;

  public TurnAngleCommand(double angle, double timeout) {
    this.angle = angle;
    this.timeout = timeout;
    requires(drive);
  }

  protected void initialize() {
    setTimeout(timeout);
     drive.setTurnGoal(angle);
  }

  protected void execute() {
  }

  protected boolean isFinished() {
    return drive.onTarget();
  }

  protected void end() {
    drive.setLeftRightPower(0, 0);
  }

  protected void interrupted() {
  }
}
