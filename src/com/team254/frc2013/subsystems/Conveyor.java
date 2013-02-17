/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.frc2013.subsystems;

import com.team254.frc2013.Constants;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author Richard
 */
public class Conveyor extends Subsystem {
  private Talon conveyorMotor = new Talon(Constants.conveyorPort.getInt());
  
  public void setMotor(double power) {
    conveyorMotor.set(power);
  }
  
  protected void initDefaultCommand() {
  }
  
}
