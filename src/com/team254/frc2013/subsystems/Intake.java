package com.team254.frc2013.subsystems;

import com.team254.frc2013.Constants;
import com.team254.lib.util.ThrottledPrinter;
import com.team254.lib.util.Util;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Class designed to control the intake mechanism.
 * TODO: add potentiometer; two PID gains for up/down pivot of intake.
 *
 * @author art.kalb96@gmail.com (Arthur Kalb)
 * @author maskoken@gmail.com (Matthew Koken)
 */
public class Intake extends Subsystem {
  private Talon intakeMotor = new Talon(Constants.intakePort.getInt());
  private Talon intakePivotMotor = new Talon(Constants.intakePivotPort.getInt());

  ThrottledPrinter p = new ThrottledPrinter(.5);

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
