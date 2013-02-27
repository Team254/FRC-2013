package com.team254.frc2013.commands;

/**
 * Controls the shooter wheel to spin at a certain speed.
 * 
 * @author tom@team254.com (Tom Bottiglieri)
 */
public class ShooterSpeedCommand extends CommandBase {
  private double speed;
  private boolean isUp;
  private boolean keepAngle;
  
  public ShooterSpeedCommand(double speed, boolean isUp) {
    requires(shooter);
    this.speed = speed;
    this.isUp = isUp;
    keepAngle = false;
  }
  
  // Constructor that only takes in and changes speed.
  public ShooterSpeedCommand(double speed) {
    requires(shooter);
    this.speed = speed;
    keepAngle = true;
  }
    
  protected void initialize() {
  }

  protected void execute() {
    if(!keepAngle) {
      shooter.setHighAngle(isUp);
    }
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
