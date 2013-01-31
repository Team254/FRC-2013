package com.team254.lib.control;

import edu.wpi.first.wpilibj.PIDSource;

/**
 * PID source which uses a trapezoidal velocity profile.
 *
 * @author tom@team254.com (Tom Bottiglieri)
 */
public class ProfiledPIDSource implements PIDSource {
  private PIDSource pidSource;
  private ProfiledPIDController pidController;
  
  public ProfiledPIDSource() {
  }

  public ProfiledPIDSource(PIDSource sensor, ProfiledPIDController controller) {
    pidSource = sensor;
    pidController = controller;
  }

  public double pidGet() {
    pidController.calculate();
    return pidSource.pidGet();
  }

  public double pidGetRaw() {
    return pidSource.pidGet();
  }
}
