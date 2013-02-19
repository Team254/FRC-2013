package com.team254.lib.util;

import java.util.Hashtable;
import java.util.Vector;

/**
 *
 * @author stephen@team254.com (Stephen Pinkerton)
 * @author art.kalb96@gmail.com (Arthur Kalb)
 */
public class Notifier {

  private Hashtable listeners;

  public Notifier() {
    listeners = new Hashtable();
  }
  /**
   * Sends a message to all of the listeners in a specific key of the table
   * @param key the key to the reference vector
   * @param value the value of the message
   */
  public void publish(Integer key, double value) {
    Vector v = (Vector)listeners.get(key);
    for (int i = 0; i < v.size(); i++) {
      ((Listener)(v.elementAt(i))).receive(key.intValue(),value);
    }
  }
  /**
   * Adds a listener to a specific key
   * @param key the key which will have the Listener added
   * @param listener the implanting listener
   */
  public void subscribe(Integer key, Listener listener) {
    if (listeners.get(key) == null) {
      listeners.put(key, new Vector());
    }
    ((Vector)listeners.get(key)).addElement(listener);
  }
  /**
   * Removes a listener from a specific key, if it exists
   * @param keythe key which will have the Listener remove
   * @param listener the leaving listener
   */
  public void unsubscribe(Integer key, Listener listener){
    Vector v = (Vector)listeners.get(key);
    if(v != null)
      v.removeElement(listener);
  }
}
