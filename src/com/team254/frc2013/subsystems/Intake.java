package com.team254.frc2013.subsystems;

import com.team254.frc2013.Constants;
import com.team254.frc2013.WristGains;
import com.team254.lib.control.ControlSource;
import com.team254.lib.control.StateSpaceController;
import com.team254.lib.util.Matrix;
import com.team254.lib.util.Util;
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
  /*
  class WristSource implements ControlSource {

    public double get() {
    }

    public void updateFilter() {
    }

    public boolean getLowerLimit() {
    }

    public boolean getUpperLimit() {
    }

  }

  WristController controller =  new WristController("wrist", source, output, WristGains.getGains()[0], 1.0 / 100.0);*/

  protected void initDefaultCommand() {
  }

  public void setIntakePower(double power){
    double output = Util.limit(power, 1.0);
    intakeMotor.set(output);
  }

  public void setRawPivot(double power) {
    double output = Util.limit(power, 1.0);
    intakePivotMotor.set(output);
  }
}
