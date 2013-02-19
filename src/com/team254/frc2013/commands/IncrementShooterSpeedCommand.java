/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.frc2013.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Will update both shooters to increase or decrease values
 * @author tombot
 */
public class IncrementShooterSpeedCommand extends CommandBase {
  double f,b;
  public IncrementShooterSpeedCommand(double frontDelta, double backDelta) {
    f = frontDelta;
    b = backDelta;
    requires(shooter);
  }

  protected void initialize() {
  }
  /*
   * Does the incrementing
   */
  protected void execute() {
    double fg = shooter.getFrontGoal() + f;
    double bg = shooter.getBackGoal() + b;
    shooter.setSpeeds(fg, bg);
    System.out.println("Shooter goals " + fg + " " + bg );
  }

  protected boolean isFinished() {
    return true;
  }

  protected void end() {
  }

  protected void interrupted() {
  }
  
}
