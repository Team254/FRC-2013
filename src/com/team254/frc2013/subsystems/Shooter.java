package com.team254.frc2013.subsystems;

import com.team254.frc2013.Constants;
import com.team254.frc2013.Messages;
import com.team254.lib.control.impl.BangBangController;
import com.team254.lib.control.ControlOutput;
import com.team254.lib.control.ControlSource;
import com.team254.lib.control.ControlledSubsystem;
import com.team254.lib.control.impl.OpenLoopController;
import com.team254.lib.control.impl.PIDController;
import com.team254.lib.control.PIDGains;
import com.team254.lib.control.PeriodicSubsystem;
import com.team254.lib.util.Debouncer;
import com.team254.lib.util.MovingAverageFilter;
import com.team254.lib.util.Notifier;
import com.team254.lib.util.ThrottledPrinter;
import edu.wpi.first.wpilibj.AnalogChannel;
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
public class Shooter extends PeriodicSubsystem implements ControlledSubsystem {
  private Talon frontMotor = new Talon(Constants.frontShooterPort.getInt());
  private Talon backMotor = new Talon(Constants.backShooterPort.getInt());
  private Solenoid loader = new Solenoid(Constants.shooterLoaderPort.getInt());
  private Solenoid angle = new Solenoid(Constants.shooterAnglePort.getInt());

  public Counter frontSensor = new Counter(Constants.frontEncoderPort.getInt());
  public Counter backSensor = new Counter(Constants.backEncoderPort.getInt());

  private BangBangController frontController;
  private BangBangController backController;

  private Solenoid indexer = new Solenoid(Constants.indexerPort.getInt());
  Debouncer debouncer = new Debouncer(.125);
  AnalogChannel discSensor = new AnalogChannel(Constants.discSensorPort.getInt());
  ThrottledPrinter p = new ThrottledPrinter(.1);

  double fspeed, bspeed;
  int loadState = 0;
  boolean wantShoot = false;
  Timer stateTimer = new Timer();

  public void setIndexerUp(boolean up) {
    indexer.set(!up);
  }

  // Load a frisbee into shooter by retracting the piston
  public void load() {
    loader.set(false);
  }

  // Extend piston to prepare for loading in another frisbee
  public void extend() {
    loader.set(true);
  }

  public boolean tryShoot() {
    if (loadState == 0) {
      wantShoot = true;
    }
    return loadState == 0;
  }

  public void setHighAngle(boolean high) {
    angle.set(high);
  }

  public boolean isHighAngle() {
    return angle.get();
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

  // State machine
  public void update() {
    //p.println(loadState + " " + discSensor.getValue());
    int nextState = loadState;
    boolean hasDisk = debouncer.update(discSensor.getValue() > 400);
    switch (loadState) {
      case 0: // frisbee loaded
        setIndexerUp(true);
        if (wantShoot) {
          System.out.println("shooting in here");
          wantShoot = false;
          nextState++;
        }
        break;
      case 1: // Shooting
        extend();
        if (stateTimer.get() > .2) {
          nextState++;
          Notifier.publish(Messages.SHOT_TAKEN);
        }
        break;
      case 2:
        setIndexerUp(false);
        if (stateTimer.get() > .2)
          nextState++;
        break;
      case 3:
        load();
        if (stateTimer.get() > .9)
          nextState++;
        break;
      case 4:
        if (hasDisk)
          nextState = 0;
        break;
      default:
        nextState = 0;
        break;
    }
    if (nextState != loadState) {
      stateTimer.reset();
      loadState = nextState;
    }
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
      curVel = rpm;
    }
  }

  private class ShooterControlOutput implements ControlOutput {
    SpeedController sc;

    public ShooterControlOutput(SpeedController sc) {
      this.sc = sc;
    }

    public void set(double value) {
      sc.set(-value);
    }
  }

  public Shooter() {
    super();
    frontSensor.start();
    backSensor.start();
    frontController = new BangBangController("FrontShooter", new ShooterControlSource(frontSensor),
            new ShooterControlOutput(frontMotor));
    backController = new BangBangController("BackShooter", new ShooterControlSource(backSensor),
            new ShooterControlOutput(backMotor));

    frontController.enable();
    backController.enable();
    stateTimer.start();
  }

  public void setSpeed(double speed) {
    setSpeeds(speed,speed);
  }


  public void setSpeeds(double fspeed, double bspeed) {
    fspeed = (fspeed < 0) ? 0 : fspeed;
    bspeed = (bspeed < 0) ? 0 : bspeed;
    frontController.setGoal(fspeed);
    backController.setGoal(bspeed);
  }

  public void setRawPwm(double val) {
    frontController.disable();
    backController.disable();
    frontMotor.set(val);
    backMotor.set(val);
  }

  public double getFrontGoal() {
    return frontController.getGoal();
  }

  public double getBackGoal() {
    return backController.getGoal();
  }

  protected void initDefaultCommand() {
  }

  public int getFrontCounter() {
    return frontSensor.get();
  }

  public int getBackCounter() {
    return backSensor.get();
  }
}
