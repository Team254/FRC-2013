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
  }

  protected void execute() {
    double tmpSpeed = speed;
    if (!shooter.isIndexerSetDown() && tmpSpeed > 0) {
    }
    intake.setIntakePower(tmpSpeed);
    if (runConveyor) {
      conveyor.setMotor(tmpSpeed);
    }
    else {
      conveyor.setMotor(0);
    }
  }

  protected boolean isFinished() {
    return true;
  }

  protected void end() {
  }

  protected void interrupted() {
  }
}
