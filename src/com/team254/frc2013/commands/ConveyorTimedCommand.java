package com.team254.frc2013.commands;

/**
 * Runs the intake for a set duration and power.
 * 
 * @author jonathan.chang13@gmail.com (Jonathan Chang)
 */
public class ConveyorTimedCommand extends CommandBase {
  private double power;
  private double time;
  
  public ConveyorTimedCommand(double power, double time) {
    this.power = power;
    this.time = time;
    requires(conveyor);
  }
  
  protected void initialize() {
    setTimeout(time);
  }

  protected void execute() {
    conveyor.setMotor(power);
  }

  protected boolean isFinished() {
    return isTimedOut();
  }

  protected void end() {
    conveyor.setMotor(0);
  }

  protected void interrupted() {
  }
  
}
