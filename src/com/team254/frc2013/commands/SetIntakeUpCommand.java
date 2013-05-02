package com.team254.frc2013.commands;

/**
 * Raises the intake.
 *
 * @author richard@team254.com (Richard Lin)
 */
public class SetIntakeUpCommand extends CommandBase {
  public static final int INTAKE_OFF = 0;
  public static final int INTAKE_UP = 1;
  public static final int INTAKE_DOWN = 2;


  public SetIntakeUpCommand() {
    requires(intake);
  }

  protected void initialize() {
    sc.wantIntakeDown = false;
    sc.wantIntakeUp = true;
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
