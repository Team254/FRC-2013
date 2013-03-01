package com.team254.frc2013.subsystems;

import com.team254.frc2013.Constants;
import com.team254.lib.control.ControlOutput;
import com.team254.lib.control.ControlSource;
import com.team254.lib.control.ControlledSubsystem;
import com.team254.lib.control.PIDGains;
import com.team254.lib.control.PeriodicSubsystem;
import com.team254.lib.control.impl.ProfiledPIDController;
import com.team254.lib.util.Debouncer;
import com.team254.lib.util.ThrottledPrinter;
import com.team254.lib.util.Util;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Class designed to control the intake mechanism.
 * TODO: add potentiometer; two PID gains for up/down pivot of intake.
 *
 * @author art.kalb96@gmail.com (Arthur Kalb)
 * @author maskoken@gmail.com (Matthew Koken)
 */
public class Intake extends PeriodicSubsystem implements ControlledSubsystem {
  private Talon intakeMotor = new Talon(Constants.intakePort.getInt());
  private Talon intakePivotMotor = new Talon(Constants.intakePivotPort.getInt());
  private Encoder encoder = new Encoder(Constants.intakeEncoderPortA.getInt(),
          Constants.intakeEncoderPortB.getInt(), false, Encoder.EncodingType.k4X); // encoder or counter?

  PIDGains gains = new PIDGains(Constants.intakeKP, Constants.intakeKI, Constants.intakeKD);
  ProfiledPIDController controller = new ProfiledPIDController("Intake", gains, new IntakeControlSource(), new IntakeControlOutput(),90.0,.25);

  boolean foundHome = false;
  double lastSensor = 100;
  Timer homeDriveTimer = new Timer();
  boolean firstTimeHoming = true;
  Debouncer encoderReset = new Debouncer(.5);

  ThrottledPrinter p = new ThrottledPrinter(.5);

  private class IntakeControlSource implements ControlSource {
    public double get() {
      return encoder.getDistance();
    }
    public void updateFilter() { }
  }
  private class IntakeControlOutput implements ControlOutput {
    public void set(double value) {
      if (foundHome) {
        //value = (value < -.4) ? -.4 : value; // lets not drive down
        if (encoder.getDistance() > 90.0 && value > 0)
          value /= 2.0;
        else if (encoder.getDistance() < 90 && value < 0)
          value /= 2.0;
        setPivot(value);
      }
    }
  }

  public Intake() {
    encoder.setDistancePerPulse(360.0/256.0);
    encoder.start();
  }

  protected void initDefaultCommand() {
//    setDefaultCommand(new IntakeRaiseCommand());
  }

  public void update() {
    //System.out.println(encoder.get() + " " + homeDriveTimer.get() + " ");

    SmartDashboard.putData("intake encoder", encoder);
    double s = encoder.getDistance();
    //p.println("e: " + s);
    if (!foundHome && DriverStation.getInstance().isEnabled()) {
      if (firstTimeHoming) {
        homeDriveTimer.start();
        firstTimeHoming = false;
      }
      System.out.println(homeDriveTimer.get() + " ");
      if (homeDriveTimer.get() < .4) {
        setRawPivot(-.4);
      } else {
        setRawPivot(0);
        if (encoderReset.update(Math.abs(s - lastSensor) < 1.5)) {
          System.out.println("Reset intake encoder");
          foundHome = true;
          encoder.reset();
        }
      }
    } else {
      homeDriveTimer.reset();
    }
    lastSensor = s;
  }

  public void setIntakePower(double power){
    double output = Util.limit(power, 1.0);
    intakeMotor.set(output);
  }

  private void setPivot(double power){
    //if (foundHome) {
    //  setRawPivot(power);
    //}
  }

  public void setRawPivot(double power) {
    double output = Util.limit(power, 1.0);
    intakePivotMotor.set(output);
  }

  public void setIntakeAngle(double angle) {
    controller.setGoal(angle);
  }

  public int getEncoderCount() {
    return encoder.get();
  }

  public void enablePivotController(boolean on) {
    if (on) {
      controller.enable();
    } else {
      controller.disable();
    }
  }
}
