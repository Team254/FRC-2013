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
  
  public PIDGains(double p, double i, double d) {
    set(p,i,d);
  }
  
  public final void set(double p, double i, double d) {
    kP = p;
    kI = i;
    kD = d;
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
}
