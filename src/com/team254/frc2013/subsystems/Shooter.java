/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.frc2013.subsystems;

import com.team254.frc2013.Constants;
import com.team254.frc2013.control.ControlOutput;
import com.team254.frc2013.control.ControlSource;
import com.team254.frc2013.control.ControlledSubsystem;
import com.team254.frc2013.control.OpenLoopController;
import com.team254.frc2013.control.PIDController;
import com.team254.frc2013.control.PIDGains;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author Travus
 */
public class Shooter extends Subsystem implements ControlledSubsystem {
  private Victor frontMotor = new Victor(Constants.frontShooterPort.getInt());
  private Victor frontMotorB = new Victor(Constants.frontShooterPortB.getInt());
  private Talon backMotor = new Talon(Constants.backShooterPort.getInt());
  
  private Encoder frontEncoder = new Encoder(Constants.frontEncoderPortA.getInt(),
          Constants.frontEncoderPortB.getInt());
  private Encoder backEncoder = new Encoder(Constants.backEncoderPortA.getInt(),
          Constants.backEncoderPortB.getInt());
  
  //PIDController frontController;
  PIDController backController;
  OpenLoopController wheelController;

  public void update() {
    //frontController.update();
    backController.update();
  }
  
  private class ShooterControlSource implements ControlSource {
    
    boolean isFront;
    
    public ShooterControlSource(boolean isFront) {
      this.isFront = isFront;
    }
    
    public double get() {
      if(isFront) {
        return frontEncoder.get();
      }
      else {
        return backEncoder.get();
      }
    }
  }
  
  private class ShooterControlOutput implements ControlOutput {
    boolean isFront;
    
    public ShooterControlOutput(boolean isFront) {
      this.isFront = isFront;
    }
    
    public void set(double value) {
      if (isFront) {
        frontMotor.set(value);
        frontMotorB.set(value); //XXX remove me for 2013
      }
      else {
        backMotor.set(value);
      }
    } 
  }
  
  public Shooter() {
    super();
    frontEncoder.start();
    backEncoder.start();
    PIDGains frontGains = new PIDGains(0,0,0);
    PIDGains backGains = new PIDGains(0,0,0);
    //frontController = new PIDController("FrontShooter", frontGains, new ShooterControlSource(true),
           // new ShooterControlOutput(true));
    backController = new PIDController("BackShooter", backGains, new ShooterControlSource(false),
            new ShooterControlOutput(false));
    wheelController = new OpenLoopController("ShooterWheel", new ShooterControlOutput(true));
  }
  
  public void setSpeed(double speed) {
    wheelController.setGoal(speed);
  }
  
  protected void initDefaultCommand() {
  }
  
}
