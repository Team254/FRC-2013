package com.team254.frc2013.commands;

/**
 * Pushes up the angle piston.
 *
 * @author tom@team254.com (Tom Bottiglieri)
 */
public class ShooterAngleCommand extends CommandBase {
  boolean up;
  public ShooterAngleCommand(boolean up) {
    this.up = up;
    requires(shooter);
  }
  
  public ShooterAngleCommand(double up) {
    this.up = !(up == 0.0);
    requires(shooter);
  }
  
  protected void initialize() {
  }

  protected void execute() {
    shooter.setHighAngle(up);
  }

  protected boolean isFinished() {
    return true;
  }

  protected void end() {
  }

  protected void interrupted() {
  }
  
  
}
