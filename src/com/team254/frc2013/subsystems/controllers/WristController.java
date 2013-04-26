/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.frc2013.subsystems.controllers;

import com.team254.lib.control.ControlOutput;
import com.team254.lib.control.ControlSource;
import com.team254.lib.control.StateSpaceController;
import com.team254.lib.control.StateSpaceGains;
import com.team254.lib.util.Matrix;
import com.team254.lib.util.ThrottledPrinter;
import edu.wpi.first.wpilibj.DriverStation;

/**
 *
 * @author tombot
 *
 * Thanks Austin.
 */
public class WristController extends StateSpaceController {

  ControlOutput output;
  ControlSource sensor;
  Matrix y;
  Matrix r;
  boolean calibrated = false;
  // Enum to store the state of the internal zeroing state machine.
  public final static int UNINITIALIZED = 0;
  public final static int ZEROING = 1;
  public final static int MOVING_ON = 2;
  public final static int READY = 3;
  public int state;
  // Offset from the raw encoder value to the absolute angle.
  double zero_offset_ = 0;
  // Position that gets incremented when zeroing the wrist to slowly move it to
  // the hall effect sensor.
  double zeroing_position_;
  // Last position at which the hall effect sensor was off.
  double last_off_position_;
  double zeroing_speed = -.2;
  double moving_on_speed = -2.0;
  final double zero_sensor_position = 0.2658 +.0137;
  final double max_zeroing_voltage = 3;
  double minGoal = -.06, maxGoal = 2.1;
  int index = 0;
  public double pos = 0;

  public WristController(String name, ControlOutput output, ControlSource sensor, StateSpaceGains gains) {
    this(name, output, sensor, gains, 1 / 100.0);
  }

  public WristController(String name, ControlOutput output, ControlSource sensor, StateSpaceGains gains, double period) {
    super(name, 1, 1, 2, gains, period);
    this.output = output;
    this.sensor = sensor;
    this.y = new Matrix(1, 1);
    this.r = new Matrix(2, 1);
    this.period = period;
  }

  public void capU() {
    for (int i = 0; i < numOutputs; i++) {
      double u_i = U.get(i);
      double u_max = Umax.get(i);
      double u_min = Umin.get(i);
      if (!calibrated) {
        u_max /= 1.0;
        u_min /= 1.0;
      }
      if (u_i > u_max) {
        u_i = u_max;
      } else if (u_i < u_min) {
        u_i = u_min;
      }
      U.set(i, u_i);
    }
  }

  double clipGoal(double g) {
    if (g > maxGoal) {
      return maxGoal;
    }
    if (g < minGoal) {
      return minGoal;
    }
    return g;
  }
  ThrottledPrinter p = new ThrottledPrinter(.25);

  public void reset() {
    state = UNINITIALIZED;
  }

  private void Debug(String s) {
    if (false) {
      System.out.println(s);
    }
  }
  boolean lastZeroSensor = false;

  public void update() {
    boolean zeroSensor = sensor.getLowerLimit();
    double cur_position = sensor.get();
    double absolute_position = cur_position;

    if (state == READY) {
      absolute_position -= zero_offset_;
    }
    y.set(0, absolute_position);

    if (!sensor.getLowerLimit()) {
      last_off_position_ = cur_position;
    }
    double limited_goal = 0;
    boolean robotEnabled = DriverStation.getInstance().isEnabled();
    switch (state) {
      case UNINITIALIZED: {
        calibrated = false;
        Debug("UNINITIALIZED");
        // Reset the zeroing goal.
        zeroing_position_ = absolute_position;
        // Clear the observer state.
        Xhat.set(0, 0, absolute_position);
        Xhat.set(1, 0, 0);
        // Set the goal to here to make it so it doesn't move when disabled.
        r.set(0, 0, absolute_position);
        r.set(1, 0, 0);
        // Only progress if we are enabled.
        if (robotEnabled) {
          if (sensor.getLowerLimit()) {
            state = ZEROING;
          } else {
            state = MOVING_ON;
          }
        }
        break;
      }

      case ZEROING: {
        Debug("Moving off");
        // Move off the hall effect sensor.
        if (!robotEnabled) {
          // Start over if disabled.
          state = UNINITIALIZED;
          break;
        } else if (!sensor.getLowerLimit()) {
          state = READY;
          // Save the zero, and then offset the observer to deal with the
          // phantom step change.
          double old_zero_offset = zero_offset_;
          zero_offset_ = absolute_position - zero_sensor_position;

          Xhat.set(0, 0, Xhat.get(0, 0) + old_zero_offset - zero_offset_);
          y.set(0, 0, y.get(0, 0) + old_zero_offset - zero_offset_);
        } else {
          // Slowly creep off the sensor.
          zeroing_position_ -= zeroing_speed * period;
          r.set(0, 0, zeroing_position_);
          r.set(1, 0, -zeroing_speed);
          break;
        }
      }
      case MOVING_ON: {
        Debug("zeroing");
        if (!robotEnabled) {
          // Start over if disabled.
          state = UNINITIALIZED;
        } else if (sensor.getLowerLimit()) {
          state = ZEROING;
        } else {
          // Slowly creep towards the sensor.
          zeroing_position_ += moving_on_speed * period;
          r.set(0, 0, zeroing_position_);
          r.set(1, 0, moving_on_speed);
        }
        break;
      }
      case READY: {
        calibrated = true;
        Debug("ready");
        limited_goal = clipGoal(goal);
        r.set(0, 0, limited_goal);
        r.set(1, 0, 0);

        if (zeroSensor != lastZeroSensor) {
          System.out.println("EDGE " + zeroSensor + " " + absolute_position + "  " + cur_position + " " + zero_offset_ + " " + DriverStation.getInstance().getBatteryVoltage());
          if (Math.abs(absolute_position - zero_sensor_position) > 0.15) {
            System.out.println("Would be REZEROING");
          }

        }
        lastZeroSensor = zeroSensor;
        break;
      }
    }

    // Update the observer
    super.update(r, y);

    // Verify that the zeroing goal hasn't run away.
    switch (state) {
      case UNINITIALIZED:
      case READY:
        break;
      case ZEROING:

      case MOVING_ON:
        // Check if we have cliped and adjust the goal.
        double uncapped_voltage = Uuncapped.get(0, 0);
        if (uncapped_voltage > max_zeroing_voltage) {
          Debug("--fixing zero up");
          double dx = (uncapped_voltage - max_zeroing_voltage) / K.get(0, 0);
          zeroing_position_ -= dx;
        } else if (uncapped_voltage < -max_zeroing_voltage) {
          Debug("--fixing zero down");
          double dx = (uncapped_voltage + max_zeroing_voltage) / K.get(0, 0);
          zeroing_position_ -= dx;
        }
        break;
    }

    // System.out.println("D:" + index + ", "+ Timer.getFPGATimestamp() + ", " + U.get(0, 0) + ", " + cur_position + ", " + absolute_position +  ":D");
    index++;
    output.set(U.get(0, 0) / 12.0);
    pos = absolute_position;
  }

  public int getState() {
    return state;
  }
}
