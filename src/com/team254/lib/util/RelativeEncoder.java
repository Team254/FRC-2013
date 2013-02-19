package com.team254.lib.util;

import edu.wpi.first.wpilibj.Encoder;

/**
 * Allows for a encoder to be measured relative to a certain encoder point.
 * 
 * @author tom@team254.com (Tom Bottiglieri)
 */
public class RelativeEncoder {
  private Encoder e;
  int val;
  
  public RelativeEncoder(Encoder e) {
    this.e = e;
    val = 0;
  }
  
  public void reset() {
    val = e.get();
  }
  
  public void resetAbsolute() {
    e.reset();
    reset();
  }
  
  public int get() {
    return e.get() - val;
  }
  
  public void start() {
    e.start();
  }
}
