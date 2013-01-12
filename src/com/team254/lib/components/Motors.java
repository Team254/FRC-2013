/*
 * FIRST Team 254 - The Cheesy Poofs
 * Team 254 Lib
 * Components
 * Motors
 *
 * Holds a set of motors as a group
 */

package com.team254.lib.components;

import edu.wpi.first.wpilibj.SpeedController;

/**
 *
 * @author Tom Bottiglieri
 */
public class Motors {
  SpeedController [] controllers;
  public Motors(SpeedController [] controllers) {
    this.controllers = controllers;
  }

  public void set(double value) {
    for (int i = 0; i < controllers.length; i++) {
      controllers[i].set(value);
    }
  }

}
