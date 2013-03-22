package com.team254.frc2013.commands;

/**
 * @author tombot
 */
public class RunIntakeCommand extends CommandBase {
  double speed;
  boolean runConveyor = true;
  
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
    intake.setIntakePower(speed);
    if (runConveyor)
      conveyor.setMotor(speed);
    else
      conveyor.setMotor(0);
  }

  protected boolean isFinished() {
    return true;
  }

  protected void end() {
  }

  protected void interrupted() {
  }

}
