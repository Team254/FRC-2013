package com.team254.frc2013.subsystems;

import com.team254.frc2013.Constants;
import com.team254.lib.control.ControlOutput;
import com.team254.lib.control.ControlSource;
import com.team254.lib.control.PIDGains;
import com.team254.lib.control.PeriodicSubsystem;
import com.team254.lib.control.impl.ProfiledPIDController;
import com.team254.lib.util.RelativeEncoder;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * Controls the climbing and hanging mechanism.
 *
 * @author eliwu26@gmail.com (Elias Wu)
 */

public class Hanger extends PeriodicSubsystem {
  private DriveGearbox motors;
  private DoubleSolenoid hangerSolenoid = new DoubleSolenoid(Constants.hangerExtendedPort.getInt(), Constants.hangerRetractedPort.getInt());
  private Solenoid pto = new Solenoid(Constants.ptoPort.getInt());
  private RelativeEncoder encoder;
  
  PIDGains gains = new PIDGains(Constants.hangerKP, Constants.hangerKI, Constants.hangerKD);
  ProfiledPIDController controller = new ProfiledPIDController("Hanger", gains, new HangerControlSource(), 
          new HangerControlOutput(), .5, .5);

  private class HangerControlSource implements ControlSource {
    public double get() {
      return encoder.get();
    }
    public void updateFilter() { } 
  }
  private class HangerControlOutput implements ControlOutput {
    public void set(double value) {
      //motors.set(value);
    }
  }
  
  int state;
  private static final int STATE_IDLE = 0;
  private static final int STATE_FIRST_UP = 1;
  private static final int STATE_FIRST_HANG = 2;
  
  public Hanger(DriveGearbox motors) {
    this.motors = motors;
    encoder = new RelativeEncoder(motors.getLeftEncoder());
    encoder.start();
  }
  
  public void setHookUp(Value isUp) {
    System.out.println("Setting hooks: " + isUp.toString());
    hangerSolenoid.set(isUp);
  }
  
  private void set(double power) {
    motors.set(power);
  }
  
  public void setPto(boolean on) {
    pto.set(on);
    motors.setDriveMode(!on);
  }

  protected void initDefaultCommand() {
  }
  
  public void prepareClimb() {
    if (state == STATE_IDLE) {
      state = STATE_FIRST_UP;
    }
  }
  
  public void startClimb() {
    if (state == STATE_FIRST_UP) {
      state = STATE_FIRST_HANG;
    } 
  }

  public void update() {
    switch(state) {
      case STATE_IDLE:
       // setHookUp(false);
        break;
      case STATE_FIRST_UP:
     //   setHookUp(true);
        break;
      case STATE_FIRST_HANG:
   //     setHookUp(false);
        break;
      default:
        break;
    }
  }
}
