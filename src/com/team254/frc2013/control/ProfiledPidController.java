package com.team254.frc2013.control;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Timer;

/**
 * PID controller which uses a trapezoidal velocity profile.
 *
 * @author tom@team254.com (Tom Bottiglieri)
 */
public class ProfiledPidController {
  private int i = 0;
  private PIDController controller;
  private double period;
  private double acceleration;
  private double velocity;
  private double timeToMaxVelocity;
  private double timeFromMaxVelocity;
  private double timeTotal;
  private double setpoint;
  private Timer timer = new Timer();
  private ProfiledPidSource pidSource;

  public ProfiledPidController(double Kp, double Ki, double Kd, double Kf,
                               PIDSource source, PIDOutput output, double period) {
    this.period = period;
    pidSource = new ProfiledPidSource(source, this);
    controller = new PIDController(Kp, Ki, Kd, Kf, source, output, period);
  }

  protected void calculate() {
    double t = timer.get();
    double setpoint = controller.getSetpoint();
    if (t < timeToMaxVelocity) {
      // Accelerate up.
      setpoint += (acceleration * t) * period;
    } else if (t < timeFromMaxVelocity) {
      // Maintain max velocity.
      setpoint += velocity * period;
    } else if (t < timeTotal) {
       // Accelerate down.
      double decelTime = t - timeFromMaxVelocity;
      double v = velocity + (-acceleration * decelTime);
      setpoint += v * period;
    }
    controller.setSetpoint(setpoint);
  }

  public synchronized void setSetpoint(double setpoint) {
    this.setpoint = setpoint;
    timeToMaxVelocity = velocity / acceleration;
    double deltaPosMaxV = setpoint - (timeToMaxVelocity * velocity);
    double timeAtMaxV = deltaPosMaxV / velocity;
    timeFromMaxVelocity = timeToMaxVelocity + timeAtMaxV;
    timeTotal = timeFromMaxVelocity + timeToMaxVelocity;
    timer.reset();

    // Set setpoint to current value of PIDSource.
    controller.setSetpoint(pidSource.pidGetRaw());
  }
}
