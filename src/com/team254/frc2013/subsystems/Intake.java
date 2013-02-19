package com.team254.frc2013.subsystems;

import com.team254.frc2013.Constants;
import com.team254.frc2013.commands.IntakeCommand;
import com.team254.frc2013.commands.IntakeRaiseCommand;
import com.team254.lib.control.ControlOutput;
import com.team254.lib.control.ControlSource;
import com.team254.lib.control.ControlledSubsystem;
import com.team254.lib.control.PIDGains;
import com.team254.lib.control.impl.PIDController;
import com.team254.lib.util.Util;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Class designed to control the intake mechanism.
 * TODO: add potentiometer; two PID gains for up/down pivot of intake.
 * 
 * @author art.kalb96@gmail.com (Arthur Kalb)
 * @author maskoken@gmail.com (Matthew Koken)
 */
public class Intake extends Subsystem implements ControlledSubsystem {
  private Talon intakeMotor = new Talon(Constants.intakePort.getInt());
  private Talon intakePivotMotor = new Talon(Constants.intakePivotPort.getInt());
  private Encoder encoder = new Encoder(Constants.intakeEncoderPortA.getInt(),
          Constants.intakeEncoderPortB.getInt()); // encoder or counter?
  
  PIDGains gains = new PIDGains(Constants.intakeKP, Constants.intakeKI, Constants.intakeKD);
  PIDController controller = new PIDController("Intake", gains, new IntakeControlSource(), new IntakeControlOutput());

  private class IntakeControlSource implements ControlSource {
    public double get() {
      return encoder.get();
    }
    public void updateFilter() { } 
  }
  private class IntakeControlOutput implements ControlOutput {
    public void set(double value) {
      //intakePivotMotor.set(value);
    }
  }
  
  public Intake() {
    encoder.start();
  }
  
  protected void initDefaultCommand() {
    setDefaultCommand(new IntakeRaiseCommand());
  }

  public void update() {
  } 
  
  public void setIntakePower(double power){
    double output = Util.limit(power, 1.0);
    System.out.println("Setting intake power: " + output);
    intakeMotor.set(output);
  }
  
  public void raiseIntake(double power){
    double output = Util.limit(power, 1.0);
    intakePivotMotor.set(output);
  }
  
  public int getEncoderCount() {
    return encoder.get();
  }
}
