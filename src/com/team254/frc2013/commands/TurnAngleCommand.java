/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.frc2013.commands;

/**
 *
 * @author tombot
 */
public class TurnAngleCommand extends CommandBase {
  
  private double angle;
  private double timeout;

  public TurnAngleCommand(double angle, double timeout) {
    this.angle = angle;
    this.timeout = timeout;
    requires(drive);
  }

  protected void initialize() {
    setTimeout(timeout);
     drive.setGoal(0, angle);
  }
  

  protected void execute() {
   
  }

  protected boolean isFinished() {
    return drive.onTarget();
  }

  protected void end() {
    drive.setLeftRightPower(0, 0);
  }

  protected void interrupted() {
  }
  
}
