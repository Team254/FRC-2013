package com.team254.frc2013.commands;

/**
 * Controls the shooter wheel to spin at a certain speed.
 * 
 * @author tom@team254.com (Tom Bottiglieri)
 */
public class ShooterSpeedCommand extends CommandBase {
  private double speed;
  
  public ShooterSpeedCommand(double speed) {
    requires(shooter);
    this.speed = speed;
  }
  
  protected void initialize() {
  }

  protected void execute() {
    shooter.setSpeed(speed);
  }

  protected boolean isFinished() {
    return true;
  }

  protected void end() {
  }

  protected void interrupted() {
  }
}
