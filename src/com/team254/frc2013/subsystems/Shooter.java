package com.team254.frc2013.subsystems;

import com.team254.frc2013.Constants;
import com.team254.lib.control.impl.BangBangController;
import com.team254.lib.control.ControlOutput;
import com.team254.lib.control.ControlSource;
import com.team254.lib.control.ControlledSubsystem;
import com.team254.lib.control.OpenLoopController;
import com.team254.lib.control.impl.PIDController;
import com.team254.lib.control.PIDGains;
import com.team254.lib.util.MovingAverageFilter;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Class representing the shooter wheels, managing its motors and sensors.
 *
 * @author richard@team254.com (Richard Lin)
 * @author tom@team254.com (Tom Bottiglieri)
 * @author eric.vanlare14@bcp.org (Eric van Lare)
 * @author eliwu26@gmail.com (Elias Wu)
 */
public class Shooter extends Subsystem implements ControlledSubsystem {
  private Talon frontMotor = new Talon(Constants.frontShooterPort.getInt());
  private Talon backMotor = new Talon(Constants.backShooterPort.getInt());
  private Solenoid loader = new Solenoid(Constants.shooterLoaderPort.getInt());
  private Solenoid angle = new Solenoid(Constants.shooterAnglePort.getInt());
  
  private Counter frontSensor = new Counter(Constants.frontEncoderPortA.getInt());
  private Counter backSensor = new Counter(Constants.backEncoderPortA.getInt());
  
  private BangBangController frontController;
  private BangBangController backController;
  
  double fspeed, bspeed;

  // Load a frisbee into shooter by retracting the piston
  public void load() {
    loader.set(false);
  }
  
  // Extend piston to prepare for loading in another frisbee
  public void extend() {
    loader.set(true);
  }
  
  public void setHighAngle(boolean high) {
    angle.set(high);
  }
  
  public void setState(boolean isEnabled) {
    loader.set(isEnabled);
    //insert shooter logic here
    if (isEnabled) {
      frontController.enable();
      backController.enable();
    } else {
      frontController.disable();
      backController.disable();
    }
  }

  public boolean getLoaderState() {
    return loader.get();
  }
  
  public void update() {
  }
  
  private class ShooterControlSource implements ControlSource {
    Counter counter;
    double curVel;
    MovingAverageFilter filter = new MovingAverageFilter(6);
    public ShooterControlSource(Counter c) {
      this.counter = c;
    }
    
    public double get() {
      return curVel;
    }

    public void updateFilter() {
      int kCountsPerRev = 32;
      double rpm = 60.0 / (counter.getPeriod() * (double)kCountsPerRev);
      if (rpm < 14000.0) {
       // curVel = filter.calculate(rpm); // probably dont want to filter bang bang
      }
    }
  }
  
  private class ShooterControlOutput implements ControlOutput {
    SpeedController sc;
    
    public ShooterControlOutput(SpeedController sc) {
      this.sc = sc;
    }
    
    public void set(double value) {
      value = -value; // XXX remove for 2013
      sc.set(value);
    } 
  }
  
  public Shooter() {
    super();
    frontSensor.start();
    backSensor.start();
    frontController = new BangBangController("FrontShooter", new ShooterControlSource(frontSensor),
            new ShooterControlOutput(frontMotor));
    backController = new BangBangController("FrontShooter", new ShooterControlSource(frontSensor),
            new ShooterControlOutput(backMotor));
  }
  
  public void setSpeed(double speed) {
    frontController.setGoal(speed);
    backController.setGoal(speed/4.0);
  }
  
    
  public void setSpeeds(double fspeed, double bspeed) {
    //frontController.setGoal(fspeed);
    //backController.setGoal(bspeed);
    this.fspeed = fspeed;
    this.bspeed = bspeed;
    frontMotor.set(fspeed);
    backMotor.set(bspeed);
  }
  
  public void setRawPwm(double val) {
    frontController.disable();
    backController.disable();
    frontMotor.set(val);
    backMotor.set(val);
  }
  
  public double getFrontGoal() {
    return fspeed; //frontController.getGoal();
  }
    
  public double getBackGoal() {
    return bspeed; //backController.getGoal();
  }
  
  
  
  protected void initDefaultCommand() {
  }
}
