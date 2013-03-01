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
    if (intakePosition == INTAKE_UP) {
      timeLimit = 0.9;
    } else if (intakePosition == INTAKE_DOWN) {
      timeLimit = 0.25;
    } else {
      timeLimit = 0;
    }
  }

  protected void initialize() {
    intakeTimer.reset();
    intakeTimer.start();
    if (intakePosition == INTAKE_UP) {
      intake.setRawPivot(0.65);
    } else if (intakePosition == INTAKE_DOWN) {
      intake.setRawPivot(-0.5);
    } else {
      intake.setRawPivot(0);
    }
  }

  protected void execute() {
    // Slow down the raise once it has become vertial and the gravity torque on it is less.
    if (intakePosition == INTAKE_UP && intakeTimer.get() > 0.5) {
      intake.setRawPivot(0.4);
    }
  }

  protected boolean isFinished() {
    return intakeTimer.get() > timeLimit;
  }

  protected void end() {
    if (intakePosition == INTAKE_UP) {
      // Stall the motor with low voltage to keep the intake from falling down.
      intake.setRawPivot(0.07);
    } else {
      intake.setRawPivot(0);
    }
  }

  protected void interrupted() {
  }
}
