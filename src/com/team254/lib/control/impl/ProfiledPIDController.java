package com.team254.lib.control.impl;

import com.team254.lib.control.ControlOutput;
import com.team254.lib.control.ControlSource;
import com.team254.lib.control.MotionProfile;
import com.team254.lib.control.PIDGains;
import com.team254.lib.util.ThrottledPrinter;
import edu.wpi.first.wpilibj.Timer;

/**
 * Represents trapezoidal velocity control.
 * Constant acceleration until target (max) velocity is reached, sets acceleration to zero
 * for a calculated time, then decelerates at a constant deceleration with a slope equal to
 * the negative slope of the initial acceleration.
 *     _____
 *    /     \
 *   /       \
 *  /         \
 * /___________\
 *
 * @author tom@team254.com (Tom Bottiglieri)
 */
public class ProfiledPIDController extends PIDController {
  private double acceleration;
  private double velocity;
  private double timeToMaxVelocity;
  private double timeFromMaxVelocity;
  private double timeTotal;
  private double setpoint;
  private Timer timer = new Timer();
  private double lastTime = 0;
  double origGoal;
  double sign = 1;
  ThrottledPrinter printer = new ThrottledPrinter(.1);
  MotionProfile profile;

  public ProfiledPIDController(String name, PIDGains gains, ControlSource source,
                               ControlOutput output, MotionProfile profile) {
    super(name, gains, source, output);
    this.profile = profile;
  }

  public void update() {
    if (enabled) {
      double t = timer.get();
      super.setGoal(profile.updateSetpoint(getGoal(), source.get(), t));
      //if (name.equalsIgnoreCase("straightController"))
        //System.out.println(t + " " + getGoal() + " " + source.get());
    }

    super.update();
  }

  public void setGoal(double goal) {
    origGoal = goal;
    timer.reset();
    timer.start();
    profile.setGoal(goal, source.get(), timer.get());
    super.setGoal(source.get());

  }

  public boolean onTarget() {
    boolean done = !enabled || (Math.abs(origGoal - lastSource) < onTargetError) && (Math.abs(lastDeltaError) < onTargetDeltaError);
    if (done)
      System.out.println(name + " DONE");
    return done;
  }
  
  public void setProfile(MotionProfile profile) {
    this.profile = profile;
    setGoal(source.get());
  }
}
