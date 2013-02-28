package com.team254.frc2013.commands;

/**
 * @author tombot
 */
public class RunIntakeCommand extends CommandBase {
  double speed;
  public RunIntakeCommand(double speed) {
    this.speed = speed;
    requires(intake);
    requires(conveyor);
  }
  protected void initialize() {
  }

  protected void execute() {
    intake.setIntakePower(speed);
    conveyor.setMotor(speed);
  }

  protected boolean isFinished() {
    return true;
  }

  protected void end() {
  }

  protected void interrupted() {
  }

}
