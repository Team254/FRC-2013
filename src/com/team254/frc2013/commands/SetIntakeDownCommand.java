package com.team254.frc2013.commands;

/**
 * Raises the intake.
 *
 * @author richard@team254.com (Richard Lin)
 */
public class SetIntakeDownCommand extends CommandBase {

  public SetIntakeDownCommand() {
    requires(intake);
  }

  protected void initialize() {
    sc.wantIntakeDown = true;
    sc.wantIntakeUp = false;
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
