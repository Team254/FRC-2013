/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.frc2013.commands;

/**
 *
 * @author tombot
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
