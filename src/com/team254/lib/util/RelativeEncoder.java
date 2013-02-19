/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.lib.util;

import edu.wpi.first.wpilibj.Encoder;

/**
 *
 * @author tombot
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
