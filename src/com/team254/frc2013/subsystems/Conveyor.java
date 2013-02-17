package com.team254.frc2013.subsystems;

import com.team254.frc2013.Constants;
import com.team254.lib.util.Util;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Controls the conveyor mechanism.
 *
 * @author richard@team254.com (Richard Lin)
 */
public class Conveyor extends Subsystem {
  private Talon conveyorMotor = new Talon(Constants.conveyorPort.getInt());
  
  public void setMotor(double power) {
    conveyorMotor.set(Util.limit(power, 1.0));
  }
  
  protected void initDefaultCommand() {
  }
}
