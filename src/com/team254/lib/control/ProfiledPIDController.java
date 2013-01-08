/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.lib.control;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.Timer;
import java.util.TimerTask;

/**
 *
 * @author tombot
 */
public class ProfiledPIDController {
    int i = 0;
    
    public PIDController controller;
    java.util.Timer m_controlLoop;
    double m_period;
    double accel;
    double vel;
    double t_to_max_v;
    double t_from_max_v;
    double t_total;
    double m_setpoint;
    Timer timer = new Timer();
    PIDSource m_source;

    private class PIDTask extends TimerTask {

        private ProfiledPIDController m_controller;

        public PIDTask(ProfiledPIDController controller) {
            if (controller == null) {
                throw new NullPointerException("Given ProfiledPIDController was null");
            }
            m_controller = controller;
        }

        public void run() {
            m_controller.calculate();
        }
    }

    public ProfiledPIDController(double Kp, double Ki, double Kd, double Kf,
            PIDSource source, PIDOutput output,
            double period) {
        m_period = period;
        controller = new PIDController(Kp, Ki,Kd, Kf, source, output,period);
        m_controlLoop.schedule(new PIDTask(this), 0L, (long) (m_period * 1000));
        m_source = source;
    }
    
    private void calculate() {
        double t = timer.get();
        double setpoint = controller.getSetpoint();
        if (t < t_to_max_v) { // accelerate up
            setpoint += (accel * t) * m_period;
        }
        else if (t < t_from_max_v) { // max v
            setpoint += vel * m_period;
        }
        else if (t < t_total) { // accelerate down
            double m_t = t - t_from_max_v;
            double v = vel + (-accel * m_t);
            setpoint += v * m_period;
        }
        controller.setSetpoint(setpoint);
    }
    
    public synchronized void setSetpoint(double setpoint) {
      m_setpoint = setpoint;
      t_to_max_v = vel / accel;
      double delta_s_max_v = setpoint - (t_to_max_v * vel);
      double t_at_max_v = delta_s_max_v / vel;
      t_from_max_v = t_to_max_v + t_at_max_v;
      t_total = t_from_max_v + t_to_max_v;    
      timer.reset();
      
      // Set setpoint to current value of PIDSource
      controller.setSetpoint(m_source.pidGet());
    }
    
}
