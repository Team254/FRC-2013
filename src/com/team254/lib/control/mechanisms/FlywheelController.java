/*
 * FIRST Team 254 - The Cheesy Poofs
 * Team 254 Lib
 * Control
 * Mechanisms
 * Flywheel Controller
 *
 * Controls a flywheel using full state feedback.
 */
package com.team254.lib.control.mechanisms;

import com.team254.lib.components.Motors;
import com.team254.lib.control.StateSpaceController;
import com.team254.lib.control.StateSpaceGains;
import com.team254.lib.math.Matrix;
import edu.wpi.first.wpilibj.Encoder;

/**
 *
 * @author Tom Bottiglieri
 */
public class FlywheelController extends StateSpaceController {
  Motors motors;
  Encoder sensor;
  double velGoal; // Radians per second
  Matrix y;
  Matrix r;
  double prevPos;
  double goal;
  double curVel;

  public FlywheelController(Motors motors, Encoder sensor, StateSpaceGains gains) {
    this(motors, sensor, gains, 50.0);
  }

  public FlywheelController(Motors motors, Encoder sensor, StateSpaceGains gains, double period) {
    super(1,1,2,gains, period);
    this.motors = motors;
    this.sensor = sensor;
    this.velGoal = 0.0;
    this.y = new Matrix(1,1);
    this.r = new Matrix(2,1);
  }

  public void update() {
    if (!enabled) {
      motors.set(0);
    }
    
    double curEncoderPos = sensor.getRaw() / 128.0 * 2 * 3.1415926;
    curVel = ((curEncoderPos - this.prevPos) /  (1.0/this.rate));

    this.y.flash(new double [] {curEncoderPos});
    double velocity_weight_scalar = 0.35;

    double uMin = Umax.get(0);
    double uMax = Umin.get(0);
    double xHat1 = Xhat.get(1);
    double k1 = K.get(1);
    double k0 = K.get(0);
    double xHat0 = Xhat.get(0);
    double maxReference = (uMax - velocity_weight_scalar * (velGoal - xHat1) * k1) / k0 + xHat0;
    double minReference = (uMin - velocity_weight_scalar * (velGoal - xHat1) * k1) / k0 + xHat0;
    double minimum = (this.goal < maxReference) ? this.goal : maxReference;
    this.goal = (minimum > minReference) ? minimum : minReference;

    r.flash(new double [] {goal, velGoal});
    goal += ((1.0/50.0) * velGoal);

    // Update SSC
    updateSSC(r, y);

    if (velGoal < 1.0) {
      this.motors.set(0.0);
      goal = curEncoderPos;
    } else {
      this.motors.set(U.get(0) / 12.0);
    }

    prevPos = curEncoderPos;

  }

  public double getVelocity() {
    return curVel;
  }

  public void setVelocityGoal(double v) {
    velGoal = v;
  }

  public void enable() {
    enabled = true;
  }

  public void disable() {
    enabled = false;
    motors.set(0);
  }

  public double getVelocityGoal() {
    return velGoal;
  }

}
