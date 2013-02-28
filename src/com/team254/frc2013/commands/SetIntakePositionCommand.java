package com.team254.frc2013.commands;

/**
 *
 * @author tombot
 */
public class SetIntakePositionCommand extends CommandBase{
  double angle;
  public SetIntakePositionCommand(double angle) {
    this.angle = angle;
  }

  protected void initialize() {
    intake.setIntakeAngle(angle);
  }

  protected void execute() {
  }

  protected boolean isFinished() {
     return true;
  }

  protected void end() {
  }

  protected void interrupted() {
  }
}
