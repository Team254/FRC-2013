package com.team254.lib.util;

import java.util.Hashtable;
import java.util.Vector;

/**
 * Controls all message subscriptions for Listeners.
 * Also allows Listeners to subscribe and unsubscribe from messages.
 *
 * @author stephen@team254.com (Stephen Pinkerton)
 * @author art.kalb96@gmail.com (Arthur Kalb)
 */
public class Notifier {

  private Hashtable listeners;
  private static Notifier instance;
  
  private Notifier() {
    listeners = new Hashtable();
  }

  /**
   * Ensures Notifier is a singleton and returns the instance
   * @return: instance of Notifier
   */
  public static Notifier getInstance() {
    if(instance == null) {
      instance = new Notifier();
    }
    return instance;
  }

  /**
   * Sends a message to all of the subscribed listeners.
   * @param key: the hash table key that points to a vector of listeners
   * @param value: the value of the message
   */
  public static void publish(Integer key, double value) {
    Vector v = (Vector)getInstance().listeners.get(key);
    if(v != null){
      for (int i = 0; i < v.size(); i++) {
        ((Listener)(v.elementAt(i))).receive(key.intValue(),value);
      }
    }
  }
  
  /**
   * Subscribes a listener to a specific message.
   * @param key: message key to subscribe the listener to
   * @param listener: the listener to subscribe to the message key
   */
  public static void subscribe(Integer key, Listener listener) {
    if (getInstance().listeners.get(key) == null) {
      getInstance().listeners.put(key, new Vector());
    }
    ((Vector)getInstance().listeners.get(key)).addElement(listener);
  }

  /**
   * Unsubscribes a listener from a specified message (if it exists).
   * @param key: message key to unsubscribe the listener from
   * @param listener: the listener being unsubscribed from the message key
   */
  public static void unsubscribe(Integer key, Listener listener){
    Vector v = (Vector)getInstance().listeners.get(key);
    if(v != null) {
      v.removeElement(listener);
    }
  }
}
