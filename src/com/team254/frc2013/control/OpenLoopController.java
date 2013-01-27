/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.frc2013.control;

/**
 *
 * @author tombot
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
    if (enabled)
      output.set(value);
    else
      output.set(0);
  }

  public void setGoal(double goal) {
    value = goal;
  }
  
  
}
