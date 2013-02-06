package com.team254.frc2013.subsystems;

import com.team254.frc2013.Constants;
import com.team254.frc2013.commands.IntakeCommand;
import com.team254.lib.control.ControlledSubsystem;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Class designed to control the intake mechanism.
 * 
 * @author art.kalb96@gmail.com (Arthur Kalb)
 */
public class Intake extends Subsystem implements ControlledSubsystem {
  private Talon intakeMotor = new Talon(Constants.intakePort.getInt());
  
  protected void initDefaultCommand() {
    setDefaultCommand(new IntakeCommand());
  }

  public void update() {
  } 
  
  public void setIntakePower(double value){
    intakeMotor.set(value);
  }
}
