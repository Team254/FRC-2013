package com.team254.frc2013.commands;

/**
 * Raises the intake.
 *
 * @author richard@team254.com (Richard Lin)
 */
public class IntakeRaiseCommand extends CommandBase {

  public IntakeRaiseCommand() {
    requires(intake);
  }
  
  protected void initialize() {
  }

  protected void execute() {
    intake.raiseIntake(controlBoard.gamepad.getRightY() / 2.0);
  }

  protected boolean isFinished() {
    return false;
  }

  protected void end() {
  }

  protected void interrupted() {
  }
  
}
