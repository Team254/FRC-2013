package com.team254.lib.control;

import edu.wpi.first.wpilibj.PIDSource;

/**
 * PID source which uses a trapezoidal velocity profile.
 *
 * @author tom@team254.com (Tom Bottiglieri)
 */
public class ProfiledPidSource implements PIDSource {
  private PIDSource pidSource;
  private ProfiledPidController pidController;

  public ProfiledPidSource(PIDSource sensor, ProfiledPidController controller) {
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
