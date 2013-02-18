/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.frc2013.commands;

import com.team254.lib.util.Util;

/**
 *
 * @author Richard
 */
public class SetConveyorCommand extends CommandBase {
  private double speed;
  
  public SetConveyorCommand(double speed) {
    this.speed = Util.limit(speed, 1.0);
    requires(conveyor);
  }
  
  protected void initialize() {
  }

  protected void execute() {
    System.out.println("Conveyor: " + speed);
    conveyor.setMotor(speed);
  }

  protected boolean isFinished() {
    return true;
  }

  protected void end() {
  }

  protected void interrupted() {
  }
  
}
