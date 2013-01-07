/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.frc2013.commands;

import com.team254.lib.Util;
import com.team254.lib.debug.ThrottledPrinter;

/**
 *
 * @author Richard
 */
public class CheesyDriveCommand extends CommandBase {
    
    private double old_wheel_ = 0.0;
    private double quickStopAccumulator_;
    private ThrottledPrinter printer = new ThrottledPrinter(0.1);
    
    public CheesyDriveCommand() {
        requires(drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        boolean isQuickTurn = true;
        boolean isHighGear = true;

        double wheelNonLinearity;
        
        // XXX - getY() returns negative for forward
        // getX()...not sure yet (untested)
        double wheel = oi.rightStick.getX();
        double throttle = -oi.leftStick.getY();
        
        double neg_inertia = wheel - old_wheel_;
        old_wheel_ = wheel; //get wheel

        double M_PI = 3.141592;

        if (isHighGear) {
          wheelNonLinearity = 0.9;
          // Apply a sin function that's scaled to make it feel better.
          wheel = Math.sin(M_PI / 2.0 * wheelNonLinearity * wheel) / Math.sin(M_PI / 2.0 * wheelNonLinearity);
          wheel = Math.sin(M_PI / 2.0 * wheelNonLinearity * wheel) / Math.sin(M_PI / 2.0 * wheelNonLinearity);
        } else {
          wheelNonLinearity = 0.8;
          // Apply a sin function that's scaled to make it feel better.
          wheel = Math.sin(M_PI / 2.0 * wheelNonLinearity * wheel) / Math.sin(M_PI / 2.0 * wheelNonLinearity);
          wheel = Math.sin(M_PI / 2.0 * wheelNonLinearity * wheel) / Math.sin(M_PI / 2.0 * wheelNonLinearity);
          wheel = Math.sin(M_PI / 2.0 * wheelNonLinearity * wheel) / Math.sin(M_PI / 2.0 * wheelNonLinearity);
        }

        double left_pwm, right_pwm, overPower;
        double sensitivity = 1.7;

        double angular_power;
        double linear_power;

        // Negative inertia!
        double neg_inertia_accumulator = 0.0;
        double neg_inertia_scalar;
        if (isHighGear) {
          neg_inertia_scalar = 5.0;
          sensitivity = 1.2;
        } else {
          if (wheel * neg_inertia > 0) {
            neg_inertia_scalar = 2.5;
          } else {
            if (Math.abs(wheel) > 0.65) {
              neg_inertia_scalar = 5.0;
            } else {
              neg_inertia_scalar = 3.0;
            }
          }
          sensitivity = 1.10;

          if (Math.abs(throttle) > 0.1) {
            sensitivity = 1 - (1 - sensitivity) / Math.abs(throttle);
          }
        }
        double neg_inertia_power = neg_inertia * neg_inertia_scalar;
        neg_inertia_accumulator += neg_inertia_power;

        wheel = wheel + neg_inertia_accumulator;
        if(neg_inertia_accumulator > 1)
          neg_inertia_accumulator -= 1;
        else if (neg_inertia_accumulator < -1)
          neg_inertia_accumulator += 1;
        else
          neg_inertia_accumulator = 0;

        linear_power = throttle;

        // Quickturn!
        if (isQuickTurn) {
          if (Math.abs(linear_power) < 0.2) {
            double alpha = 0.1;
            quickStopAccumulator_ = (1 - alpha) * quickStopAccumulator_ + alpha * Util.limit(wheel, 1.0) * 5;
          }
          overPower = 1.0;
          if (isHighGear) {
            sensitivity = 1.0;
          } else {
            sensitivity = 1.0;
          }
          angular_power = wheel;
        } else {
          overPower = 0.0;
          angular_power = Math.abs(throttle) * wheel * sensitivity - quickStopAccumulator_;
          if (quickStopAccumulator_ > 1) {
            quickStopAccumulator_ -= 1;
          } else if (quickStopAccumulator_ < -1) {
            quickStopAccumulator_ += 1;
          } else {
            quickStopAccumulator_ = 0.0;
          }
        }

        right_pwm = left_pwm = linear_power;
        left_pwm += angular_power;
        right_pwm -= angular_power;

        if (left_pwm > 1.0) {
          right_pwm -= overPower * (left_pwm - 1.0);
          left_pwm = 1.0;
        } else if (right_pwm > 1.0) {
          left_pwm -= overPower * (right_pwm - 1.0);
          right_pwm = 1.0;
        } else if (left_pwm < -1.0) {
          right_pwm += overPower * (-1.0 - left_pwm);
          left_pwm = -1.0;
        } else if (right_pwm < -1.0) {
          left_pwm += overPower * (-1.0 - right_pwm);
          right_pwm = -1.0;
        }

        drive.driveLR(left_pwm, right_pwm);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
