/*
 * FIRST Team 254 - The Cheesy Poofs
 * Team 254 Lib
 * Control
 * PeriodicController
 *
 * This class defines a controller which us updated on a periodic basis.
 * It spawns a thread to handle the timing of these updates.
 * 
 * This code was based mostly on WPILib's implementation of PIDController
 */

package com.team254.lib.control;

/**
 *
 * @author Tom Bottiglieri
 */
import java.util.TimerTask;

abstract class PeriodicController {

    java.util.Timer controlLoop_;
    double period_;
    protected boolean enabled = true;

    private class ControllerTask extends TimerTask {

      private PeriodicController m_controller;

      public ControllerTask(PeriodicController controller) {
          if (controller == null) {
              throw new NullPointerException("Given PeriodicController was null");
          }
          m_controller = controller;
      }

      public void run() {
          m_controller.update();
      }
  }

  public PeriodicController(double period) {
     controlLoop_ = new java.util.Timer();
     period_ = period;
     controlLoop_.schedule(new ControllerTask(this), 0L, (long) (period_ * 1000));
  }

  public abstract void update();
  public abstract void enable();
  public abstract void disable();
}
