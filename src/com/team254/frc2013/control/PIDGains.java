/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.frc2013.control;

/**
 *
 * @author Richard
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
