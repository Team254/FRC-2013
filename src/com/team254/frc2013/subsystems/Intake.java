package com.team254.frc2013.subsystems;

import com.team254.frc2013.Constants;
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

  protected void initDefaultCommand() {
  }
  
  public void setIntakePower(double power) {
    intakeMotor.set(power);
  }



}
