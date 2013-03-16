package com.team254.lib.control;

/**
 *
 * @author tom@team254.com
 */
public interface MotionProfile {
  public double updateSetpoint(double curSetpoint, double curSource, double curTime);
  public double setGoal(double goal, double curSource, double t);
}
