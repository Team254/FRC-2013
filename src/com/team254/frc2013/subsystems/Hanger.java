package com.team254.frc2013.subsystems;

import com.team254.frc2013.Constants;
import com.team254.lib.control.ControlOutput;
import com.team254.lib.control.ControlSource;
import com.team254.lib.control.PIDGains;
import com.team254.lib.control.PeriodicSubsystem;
import com.team254.lib.control.impl.PIDController;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;

/**
 * Controls the climbing and hanging mechanism.
 *
 * @author eliwu26@gmail.com (Elias Wu)
 */

public class Hanger extends PeriodicSubsystem {
  private DriveGearbox motors;
  private Solenoid hangerExtend = new Solenoid(Constants.hangerExtendedPort.getInt());
  private Solenoid pto = new Solenoid(Constants.ptoPort.getInt());
  private Gyro pitchGyro = new Gyro(Constants.pitchGyroPort.getInt());
  private Timer pitchRateTimer = new Timer();
  private double pitchRate = 0;
  private double lastPitchAngle = 0;

  private PIDController controller;

  private class HangControlSource implements ControlSource {
    public double get() {
      return motors.getLeftEncoder().get();
    }

    public void updateFilter() {
    }

    public boolean getLowerLimit() {
      return false;
    }

    public boolean getUpperLimit() {
      return false;
    }
  }

  private class HangControlOutput implements ControlOutput {
    public void set(double value) {
      motors.set(value);
    }
  }

  public Hanger(DriveGearbox motors) {
    this.motors = motors;
    PIDGains gains = new PIDGains(Constants.hangerUpKP, Constants.hangerUpKI, Constants.hangerUpKD);
    controller = new PIDController("hangerUpController", gains, new HangControlSource(),
                                   new HangControlOutput());
    controller.setEpsilons(15, 1);
    controller.disable();
  }

  public void setGoal(double goal) {
    controller.setGoal(goal);
  }

  public void setGoalRaw(double goal) {
    controller.setGoalRaw(goal);
  }

  public void setHookUp(boolean isUp) {
    hangerExtend.set(isUp);
  }

  public void setPto(boolean on) {
    pto.set(on);
    motors.setDriveMode(!on);
  }

  public void enableController(boolean on) {
    if (on) {
      controller.enable();
    } else {
      controller.disable();
    }
  }

  protected void initDefaultCommand() {
  }

  public double getPitchAngle() {
    return pitchGyro.getAngle();
  }

  public double getPitchRate() {
    return pitchRate;
  }

  public void resetPitchGyro() {
    pitchRate = 0;
    pitchGyro.reset();
  }

  public void update() {
    double pitchAngle = pitchGyro.getAngle();
    pitchRate = (pitchAngle - lastPitchAngle) / pitchRateTimer.get();
    lastPitchAngle = pitchAngle;
    pitchRateTimer.reset();
    pitchRateTimer.start();
  }

  public boolean onTarget() {
    return controller.onTarget();
  }
}
