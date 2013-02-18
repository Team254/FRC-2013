package com.team254.frc2013.subsystems;

import com.team254.frc2013.Constants;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Controls the climbing and hanging mechanism.
 *
 * @author eliwu26@gmail.com (Elias Wu)
 */
public class Hanger extends Subsystem {
  private DriveGearbox motors;
  private Solenoid hangerSolenoidA = new Solenoid(Constants.hangerPort.getInt());
  private Solenoid pto = new Solenoid(Constants.ptoPort.getInt());
  
  public Hanger(DriveGearbox motors) {
    this.motors = motors;
  }
  
  public void setHookUp(boolean isUp) {
    hangerSolenoidA.set(isUp);
  }
  
  public void climb(double power) {
    motors.set(power);
  }
  
  public void setPto(boolean on) {
    pto.set(on);
  }

  protected void initDefaultCommand() {
  }
}
