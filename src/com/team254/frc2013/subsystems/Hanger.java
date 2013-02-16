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
  private DriveMotors motors;
  private Solenoid hangerSolenoidA = new Solenoid(Constants.hangerPortA.getInt());
  private Solenoid hangerSolenoidB = new Solenoid(Constants.hangerPortB.getInt());
  
  public Hanger(DriveMotors motors) {
    this.motors = motors;
  }
  
  public void toggleHook(boolean isToggled) {
    hangerSolenoidA.set(isToggled);
    hangerSolenoidB.set(isToggled);
  }
  public void climb(double power) {
    motors.set(power);
  }

  protected void initDefaultCommand() {
  }
}
