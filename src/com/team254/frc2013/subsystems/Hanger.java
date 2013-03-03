package com.team254.frc2013.subsystems;

import com.team254.frc2013.Constants;
import com.team254.lib.control.ControlOutput;
import com.team254.lib.control.ControlSource;
import com.team254.lib.control.PIDGains;
import com.team254.lib.control.PeriodicSubsystem;
import com.team254.lib.control.impl.ProfiledPIDController;
import com.team254.lib.util.RelativeEncoder;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * Controls the climbing and hanging mechanism.
 *
 * @author eliwu26@gmail.com (Elias Wu)
 */

public class Hanger extends PeriodicSubsystem {
  private DriveGearbox motors;
  private Solenoid hangerExtend = new Solenoid(Constants.hangerExtendedPort.getInt());
  private Solenoid hangerRetract = new Solenoid(Constants.hangerRetractedPort.getInt());
  private Solenoid pto = new Solenoid(Constants.ptoPort.getInt());
  private RelativeEncoder encoder;
  
  public final int HANGER_HOOK_EXTENDED = 0;
  public final int HANGER_HOOK_FLOATING = 1;
  public final int HANGER_HOOK_RETRACTED = 2;
  
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
  
  public void setHookUp(int isUp) {
    switch(isUp) {
      case HANGER_HOOK_EXTENDED:
        hangerExtend.set(true);
        hangerRetract.set(false);
      case HANGER_HOOK_RETRACTED:
        hangerExtend.set(false);
        hangerRetract.set(true);
      case HANGER_HOOK_FLOATING: default:
        hangerExtend.set(false);
        hangerRetract.set(false);
    }
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
        //setHookUp(false);
        break;
      case STATE_FIRST_UP:
        //setHookUp(true);
        break;
      case STATE_FIRST_HANG:
        //setHookUp(false);
        break;
      default:
        break;
    }
  }
}
