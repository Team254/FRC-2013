package com.team254.frc2013.commands;

/**
 * Runs the intake and conveyor motors.
 *
 * @author tom@team254.com (Tom Bottiglieri)
 */
public class RunIntakeCommand extends CommandBase {

  private double speed;
  private boolean runConveyor = true;

  public RunIntakeCommand(double speed, boolean runConveyor) {
    this.speed = speed;
    this.runConveyor = runConveyor;
  }

  public RunIntakeCommand(double speed) {
    this(speed, true);
  }

  protected void initialize() {
    if (speed > 0) {
      sc.wantIntake = true;
      sc.wantExhaust = false;
    } else if (speed < 0) {
      sc.wantExhaust = true;
      sc.wantIntake = false;
    } else {
      sc.wantIntake = sc.wantExhaust = false;
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
