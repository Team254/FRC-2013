package com.team254.lib.control.impl;

import com.team254.lib.control.ControlOutput;
import com.team254.lib.control.ControlSource;
import com.team254.lib.control.PIDGains;
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

  public ProfiledPIDController(String name, PIDGains gains, ControlSource source, ControlOutput output, double maxV, double timeToMaxV) {
    super(name, gains, source, output);
    velocity = maxV;
    acceleration = maxV / timeToMaxV;
  }

  public void update() {
    if (enabled) {
      double t = timer.get();
      setpoint = getGoal();
      System.out.println(getGoal() + " " + source.get());
      double period = t - lastTime;
      if (t < timeToMaxVelocity) {
        // Accelerate up.
        setpoint += ((acceleration * t) * period * sign);
      } else if (t < timeFromMaxVelocity) {
        // Maintain max velocity.
        setpoint += (velocity * period * sign);
      } else if (t < timeTotal) {
         // Accelerate down.
        double decelTime = t - timeFromMaxVelocity;
        double v = velocity + (-acceleration * decelTime);
        setpoint += (v * period  * sign);
      }
      super.setGoalRaw(setpoint);
      lastTime = t;
    }
    super.update();
  }

  public void setGoal(double goal) {
    setpoint = goal;
    origGoal = goal;
    sign = (goal < source.get()) ? -1.0 : 1.0;
    timeToMaxVelocity = velocity / acceleration;
    double deltaPosMaxV = (sign*setpoint) - (timeToMaxVelocity * velocity);
    double timeAtMaxV = deltaPosMaxV / velocity;
    timeFromMaxVelocity = timeToMaxVelocity + timeAtMaxV;
    timeTotal = timeFromMaxVelocity + timeToMaxVelocity;
    timer.reset();
    timer.start();
    lastTime = timer.get();
    // Set setpoint to current value of PIDSource.
    super.setGoal(source.get());

  }

  public void setMaxVelocity(double v) {
    velocity = v;
  }

  public boolean onTarget() {
    boolean done = !enabled || (Math.abs(origGoal - lastSource) < onTargetError) && (Math.abs(lastDeltaError) < onTargetDeltaError);
    if (done)
      System.out.println(name + " DONE");
    return done;
  }
}
