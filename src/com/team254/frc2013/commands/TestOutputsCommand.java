/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.frc2013.commands;

/**
 *
 * @author tombot
 */
public class TestOutputsCommand extends CommandBase {
  double val;
  double diff = 0.01;

  protected void initialize() {
    requires(shooter);
    requires(motors);
    requires(intake);
  }

  protected void execute() {
    val += diff;
    if (val > .99) {
      diff = -diff;
    }
    else if (val < -.99)
      diff = -diff;
    
    for (int i = 1; i <= 6; ++i)
      motors.setMotor(i,val);
    
    intake.setIntakePower(val);
    intake.raiseIntake(val);
    
    shooter.setSpeed(10000);
  }

  protected boolean isFinished() {
    return false;
    
  }

  protected void end() {
    shooter.setSpeed(0);
  }

  protected void interrupted() {
  }
  
}
