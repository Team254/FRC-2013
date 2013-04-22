package com.team254.frc2013.subsystems;

import com.team254.frc2013.subsystems.controllers.WristController;
import com.team254.frc2013.Constants;
import com.team254.frc2013.WristGains;
import com.team254.lib.control.ControlOutput;
import com.team254.lib.control.ControlSource;
import com.team254.lib.util.Util;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Class designed to control the intake mechanism.
 *
 * @author art.kalb96@gmail.com (Arthur Kalb)
 * @author maskoken@gmail.com (Matthew Koken)
 */
public class Intake extends Subsystem {

  private Talon intakeMotor = new Talon(Constants.intakePort.getInt());
  private Talon intakePivotMotor = new Talon(Constants.intakePivotPort.getInt());
  private Encoder encoder = new Encoder(Constants.intakeEncoderPortA.getInt(), Constants.intakeEncoderPortB.getInt());
  private DigitalInput zeroSensor = new DigitalInput(Constants.intakeZeroSensorPort.getInt());

  public Intake() {
    encoder.start();
    controller.setGoal(0);
  }

  class WristSource implements ControlSource {

    public double get() {
      double v = -encoder.get() * (2 * Math.PI / 921.6);
      //  System.out.println("v : " + v);

      return v;
    }

    public void updateFilter() {
    }

    public boolean getLowerLimit() {
      return !zeroSensor.get();
    }

    public boolean getUpperLimit() {
      return false;
    }
  }

  class WristOutput implements ControlOutput {

    public void set(double value) {
      // System.out.println("out: " + value);
      intakePivotMotor.set(-value);
    }
  }
  WristController controller = new WristController("wrist", new WristOutput(), new WristSource(), WristGains.getGains()[0], 1.0 / 100.0);

  protected void initDefaultCommand() {
  }

  public void setIntakePower(double power) {
    double output = Util.limit(power, 1.0);
    intakeMotor.set(output);
  }

  public void setRawPivot(double power) {
  }

  public void setAngle(double angle) {
    controller.setGoal(angle);
  }

  public double getAngle() {
    return controller.getGoal();
  }

  public boolean getZeroSensor() {
    return !zeroSensor.get();
  }

  public double getEncoder() {
    return -encoder.get() * (2 * Math.PI / 921.6);
  }

  public void rezero() {
    controller.reset();
  }

  public boolean ready() {
    return controller.getState() == WristController.READY;
  }
}
