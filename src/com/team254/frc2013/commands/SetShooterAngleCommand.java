/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.frc2013.commands;

/**
 * Pushes up the angle piston
 * @author tombot
 */
public class SetShooterAngleCommand extends CommandBase {
  boolean up;
  public SetShooterAngleCommand(boolean up) {
    this.up = up;
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
