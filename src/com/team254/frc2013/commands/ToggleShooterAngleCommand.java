package com.team254.frc2013.commands;

/**
 * Changes the state of the shooter angle
 * 
 * @author tom@team254.com (Tom Bottiglieri)
 */

public class ToggleShooterAngleCommand extends CommandBase {

  public ToggleShooterAngleCommand() {
    requires(shooter);
  }
  
  protected void initialize() {
  }

  protected void execute() {
    shooter.setHighAngle(!shooter.isHighAngle());
  }

  protected boolean isFinished() {
    return true;
  }

  protected void end() {
  }

  protected void interrupted() {
  }
  
}
