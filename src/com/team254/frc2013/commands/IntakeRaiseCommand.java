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
      timeLimit = 1.0;
    } else if (intakePosition == INTAKE_UP) {
      timeLimit = .9;
    } else {
      timeLimit = 0;
    }
  }

  protected void initialize() {
    intakeTimer.reset();
    intakeTimer.start();
    if (intakePosition == INTAKE_UP) {
      intake.setRawPivot(.5);
    } else if (intakePosition == INTAKE_DOWN) {
      intake.setRawPivot(-0.33);
    } else {
      intake.setRawPivot(0);
    }
  }

  protected void execute() {
    if (intakePosition == INTAKE_DOWN && intakeTimer.get() > .5)
      intake.setRawPivot(-.1);
  }

  protected boolean isFinished() {
    //if (intakePosition == INTAKE_UP) {
    //  return false;
    //}
    return intakeTimer.get() > timeLimit;
  }

  protected void end() {
    intake.setRawPivot(0);
  }

  protected void interrupted() {
    intake.setRawPivot(0);
  }
}
