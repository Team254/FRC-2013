package com.team254.frc2013.commands;

/**
 * Rotates the drivebase a specific angle in specific amount of time
 *
 * @author tom@team254.com (Tom Bottiglieri)
 */
public class TurnMinAngleCommand extends CommandBase {
  private double angle;
  private double timeout;
  boolean goingClockwise;

  public TurnMinAngleCommand(double angle, double timeout) {
    this.angle = angle;
    this.timeout = timeout;
    requires(drive);
  }

  protected void initialize() {
    setTimeout(timeout);
    drive.setTurnGoal(angle);
    goingClockwise = (angle - drive.getGyroAngle()) > 0;
  }

  protected void execute() {
  }

  protected boolean isFinished() {
    boolean done = goingClockwise ? drive.getGyroAngle() > angle : drive.getGyroAngle() < angle;
    return done || drive.onTarget() || isTimedOut();
  }

  protected void end() {
    drive.setLeftRightPower(0, 0);
  }

  protected void interrupted() {
  }
}
