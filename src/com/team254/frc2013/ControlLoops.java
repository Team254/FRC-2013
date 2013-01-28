package com.team254.frc2013;

import com.team254.frc2013.control.Controller;
import java.util.TimerTask;

/**
 *
 * @author richard@team254.com (Richard Lin)
 */
public class ControlLoops {
  java.util.Timer controlLoop;
  double period;
  protected boolean enabled = true;
  
  private class ControllerTask extends TimerTask {
    private ControlLoops loop;
    
    public ControllerTask(ControlLoops loop) {
      if (loop == null) {
        throw new NullPointerException("Given ControlLoops was null");
      }
      this.loop = loop;
    }
    
    public void run() {
      loop.update();
    }
  }
  
  public ControlLoops(double period) {
    controlLoop = new java.util.Timer();
    this.period = period;
    controlLoop.schedule(new ControllerTask(this), 0L, (long) (this.period * 1000));
  }
  
  public void update() {
    Controller.updateAll();
  }
}
