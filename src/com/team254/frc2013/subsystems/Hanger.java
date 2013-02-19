package com.team254.frc2013.subsystems;

import com.team254.frc2013.Constants;
import com.team254.lib.control.PeriodicSubsystem;
import com.team254.lib.util.RelativeEncoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Controls the climbing and hanging mechanism.
 *
 * @author eliwu26@gmail.com (Elias Wu)
 */

public class Hanger extends PeriodicSubsystem {
  private DriveGearbox motors;
  private Solenoid hangerSolenoid = new Solenoid(Constants.hangerPort.getInt());

  private Solenoid pto = new Solenoid(Constants.ptoPort.getInt());
  RelativeEncoder encoder = new RelativeEncoder(motors.getLeftEncoder());
  int state;
  private static final int STATE_IDLE = 0;
  private static final int STATE_FIRST_UP = 1;
  private static final int STATE_FIRST_HANG = 2;
  
  public Hanger(DriveGearbox motors) {
    this.motors = motors;
  }
  
  public void setHookUp(boolean isUp) {
    hangerSolenoid.set(isUp);
  }
  
  private void set(double power) {
    motors.set(power);
  }
  
  public void setPto(boolean on) {
    pto.set(on);
  }

  protected void initDefaultCommand() {
  }
  
  public void prepareClimb() {
    if (state == STATE_IDLE)
      state = STATE_FIRST_UP;
  }
  
  public void startClimb() {
    if (state == STATE_FIRST_UP)
      state = STATE_FIRST_HANG; 
  }

  public void update() {
    switch(state) {
      case STATE_IDLE:
        setHookUp(false);
        break;
      case STATE_FIRST_UP:
        setHookUp(true);
        break;
      case STATE_FIRST_HANG:
        setHookUp(false);
        break;
      default:
        break;
    }
  }
}
