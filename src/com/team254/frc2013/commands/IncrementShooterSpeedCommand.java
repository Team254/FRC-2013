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
public class IncrementShooterSpeedCommand extends CommandBase {
  double f,b;
  public IncrementShooterSpeedCommand(double frontDelta, double backDelta) {
    f = frontDelta;
    b = backDelta;   
  }

  protected void initialize() {
    requires(shooter);
  }

  protected void execute() {
    shooter.setSpeeds(shooter.getFrontGoal() + f, shooter.getBackGoal() + b);
  }

  protected boolean isFinished() {
    return true;
  }

  protected void end() {
  }

  protected void interrupted() {
  }
  
}
