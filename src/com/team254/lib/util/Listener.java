/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.lib.util;

/**
 * @author art.kalb96@gmail.com
 * @author stephen@team254.com (Stephen Pinkerton)
 */
public interface Listener {
  /*
   * Should receive and handle message
   */
  public void receive(int key, double value);

}
