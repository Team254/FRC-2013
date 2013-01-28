package com.team254.frc2013.control;

/**
 * Interface for subsystem inputs.
 *
 * @author richard@team254.com (Richard Lin)
 */
public interface ControlSource {
  public double get();
  public void updateFilter();
}
