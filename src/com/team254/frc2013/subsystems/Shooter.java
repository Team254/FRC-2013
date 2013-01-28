package com.team254.frc2013.subsystems;

import com.team254.frc2013.Constants;
import com.team254.frc2013.control.BangBangController;
import com.team254.frc2013.control.ControlOutput;
import com.team254.frc2013.control.ControlSource;
import com.team254.frc2013.control.ControlledSubsystem;
import com.team254.frc2013.control.OpenLoopController;
import com.team254.frc2013.control.PIDController;
import com.team254.frc2013.control.PIDGains;
import com.team254.frc2013.lib.MovingAverageFilter;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Encoder;
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
 */
public class Shooter extends Subsystem implements ControlledSubsystem {
  private Victor frontMotor = new Victor(Constants.frontShooterPort.getInt());
  
  private Counter frontEncoder = new Counter(Constants.frontEncoderPortA.getInt());
  private Counter backEncoder = new Counter(Constants.backEncoderPortA.getInt());
  
  private BangBangController wheelController;

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
      if (rpm < 7000.0) {
        curVel = filter.calculate(rpm);
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
    frontEncoder.start();
    backEncoder.start();
    wheelController = new BangBangController("ShooterWheel", new ShooterControlSource(frontEncoder),
            new ShooterControlOutput(frontMotor));
  }
  
  public void setSpeed(double speed) {
    wheelController.setGoal(speed);
  }
  
  protected void initDefaultCommand() {
  }
  
}
