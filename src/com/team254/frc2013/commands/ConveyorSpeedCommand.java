
package com.team254.frc2013.commands;

import com.team254.lib.util.Util;

/**
 * Runs the conveyor a specified power.
 * 
 * @author richard@team254.com (Richard Lin)
 */
public class ConveyorSpeedCommand extends CommandBase {
  private double speed;
  
  public ConveyorSpeedCommand(double speed) {
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
