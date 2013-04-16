package com.team254.frc2013.subsystems;

import com.team254.frc2013.Constants;
import com.team254.frc2013.WristGains;
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

  private  StateSpaceController wrist = new StateSpaceController("wrist", 1, 1, 2, WristGains.getGains()[0], 1.0/100.0) {
    Matrix R = new Matrix(2,1);
    Matrix Y = new Matrix(1,1);
    public void update() {
      double wristAngleRadians = 0; // TODO: change me
      R.flash(new double[]{goal, 0});
      Y.set(0,0, wristAngleRadians);
      super.update(R, Y);
      double output = U.get(0, 0) / 12.0;
    }
  };

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
