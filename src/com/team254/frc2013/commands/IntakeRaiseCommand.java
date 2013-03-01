package com.team254.frc2013.commands;

import edu.wpi.first.wpilibj.Timer;

/**
 * Raises the intake.
 * TODO: Modify for autonomous usage.
 *
 * @author richard@team254.com (Richard Lin)
 */
public class IntakeRaiseCommand extends CommandBase {
  public static final int INTAKE_OFF = 0;
  public static final int INTAKE_UP = 1;
  public static final int INTAKE_DOWN = 2;

  private int intakePosition;
  private Timer intakeTimer;
  private double timeLimit;

  public IntakeRaiseCommand(int intakePosition) {
    requires(intake);
    this.intakePosition = intakePosition;
    intakeTimer = new Timer();
    if (intakePosition == INTAKE_DOWN) {
      timeLimit = 0.25;
    } else {
      timeLimit = 0;
    }
  }

  protected void initialize() {
    intakeTimer.reset();
    intakeTimer.start();
    if (intakePosition == INTAKE_UP) {
      intake.enablePivotController(true);
      intake.setIntakeAngle(105);
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
    if (intakePosition == INTAKE_UP) {
      return false;
    }
    return intakeTimer.get() > timeLimit;
  }

  protected void end() {
    intake.enablePivotController(false);
    intake.setRawPivot(0);
  }

  protected void interrupted() {
    intake.enablePivotController(false);
    intake.setRawPivot(0);
  }
}
