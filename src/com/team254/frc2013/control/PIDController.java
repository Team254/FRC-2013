/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.frc2013.control;

/**
 *
 * @author Travus
 */
public class PIDController extends Controller {
  PIDGains gains;
  ControlSource source;
  ControlOutput output;
  boolean enabled;
  double goal;
  double errorSum;
  double lastError;

  public PIDController(PIDGains gains, ControlSource source, ControlOutput output, String name) {
    super(name);
    this.gains = gains;
    this.source = source;
    this.output = output;
    enabled = true;
    errorSum = 0.0;
    lastError = 0.0;
  }
  
  public void enable() {
    enabled = true;
  }

  public void disable() {
    enabled = false;
  }

  public void update() {
    double error = goal - source.get();
    double p = gains.getP() * error;
    errorSum += error;
    double i = gains.getI() * errorSum;
    double dError = error - lastError;
    double d = gains.getD() * dError;
    lastError = error;
    output.set(p + i + d);
  }

  public void setGoal(double goal) {
    this.goal = goal;
  }
}
