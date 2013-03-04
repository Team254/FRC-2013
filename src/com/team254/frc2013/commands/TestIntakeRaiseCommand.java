package com.team254.frc2013.commands;

/**
 * Raises the intake.
 * TODO: Modify for autonomous usage.
 *
 * @author richard@team254.com (Richard Lin)
 */
public class TestIntakeRaiseCommand extends CommandBase {
  public static final int INTAKE_OFF = 0;
  public static final int INTAKE_UP = 1;
  public static final int INTAKE_DOWN = 2;

  private int intakePosition;

  public TestIntakeRaiseCommand(int intakePosition) {
    requires(intake);
    this.intakePosition = intakePosition;
  }

  protected void initialize() {
    if (intakePosition == INTAKE_UP) {
      intake.enablePivotController(false);
      intake.setRawPivot(0.5);
    } else if (intakePosition == INTAKE_DOWN) {
      intake.enablePivotController(false);
      intake.setRawPivot(-0.5);
    } else {
      intake.enablePivotController(false);
      intake.setRawPivot(0);
    }
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
