/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.frc2013.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author tombot
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
