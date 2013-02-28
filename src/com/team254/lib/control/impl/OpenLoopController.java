package com.team254.lib.control.impl;

import com.team254.lib.control.ControlOutput;
import com.team254.lib.control.Controller;

/**
 * Controller that outputs indefinitely at a certain goal while enabled.
 *
 * @author tom@team254.com (Tom Bottiglieri)
 */
public class OpenLoopController extends Controller {
  ControlOutput output;
  private double value;
  private boolean enabled = true;
  
  public OpenLoopController(String name, ControlOutput output) {
    super(name);
    this.output = output;
  }
  
  public void enable() {
    enabled = true;
  }

  public void disable() {
    enabled = false;
  }

  public void update() {
    if (enabled) {
      output.set(value);
    }
    else {
      output.set(0);
    }
  }

  public void setGoal(double goal) {
    value = goal;
  }

  public double getGoal() {
    return value;
  }
}
