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
    requires(intake);
    requires(conveyor);
  }

  public RunIntakeCommand(double speed) {
    this(speed, true);
  }
  protected void initialize() {
  }

  protected void execute() {
    double tmpSpeed = speed;
    if (!shooter.isIndexerDown() && tmpSpeed > 0) {
      tmpSpeed = 0; // Don't run the conveyor with the indexer up AND conveyor running inwards
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
