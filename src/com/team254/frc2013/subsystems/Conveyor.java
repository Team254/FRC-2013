package com.team254.frc2013.subsystems;

import com.team254.frc2013.Constants;
import com.team254.lib.util.Util;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Controls the conveyor mechanism.
 *
 * @author richard@team254.com (Richard Lin)
 */
public class Conveyor extends Subsystem {
  private Talon conveyorMotor = new Talon(Constants.conveyorPort.getInt());
  private Solenoid conveyorSolenoid = new Solenoid(Constants.conveyorSolenoidPort.getInt());
  
  public void setMotor(double power) {
    conveyorMotor.set(Util.limit(power, 1.0));
  }
  
  public void setSolenoidState(boolean on) {
    conveyorSolenoid.set(on);
  }
  
  public boolean getSolenoidState() {
    return conveyorSolenoid.get();
  }
  
  public void toggleSolenoid() {
    setSolenoidState(!getSolenoidState());
  }
  
  protected void initDefaultCommand() {
  }
}
