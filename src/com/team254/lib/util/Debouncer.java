/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.lib.util;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author tombot
 */
public class Debouncer {
  Timer t = new Timer();
  double time;
 
  public Debouncer(double time) {
    this.time = time;
  }
 
  public boolean update(boolean val) {
    t.start();
    if(!val) {
      t.reset();
    }
    return t.get() > time;
  } 
}
