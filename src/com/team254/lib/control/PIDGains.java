package com.team254.lib.control;

/**
 * Keeps track of the proportional, integral, and derivative
 * terms of a PID controller.
 *
 * @author richard@team254.com (Richard Lin)
 */
public class PIDGains {
  private double kP;
  private double kI;
  private double kD;
  private double kF;

  public PIDGains(double p, double i, double d, double f) {
    set(p, i, d, f);
  }

  public PIDGains(double p, double i, double d) {
    set(p, i, d);
  }

  public final void set(double p, double i, double d) {
    set(p, i, d, 0);
  }

  public final void set(double p, double i, double d, double f) {
    kP = p;
    kI = i;
    kD = d;
    kF = f;
  }

  public double getP() {
    return kP;
  }

  public double getI() {
    return kI;
  }
  
  public double getD() {
    return kD;
  }

  public double getF() {
    return kF;
  }
}
