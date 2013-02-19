
package com.team254.lib.util;

/**
 * Interface for subsystems to listen for Messages.
 *
 * @author art.kalb96@gmail.com (Art Kalb)
 * @author stephen@team254.com (Stephen Pinkerton)
 */
public interface Listener {
  /*
   * Receives and handle messages.
   */
  public void receive(int key, double value);

}
