package com.team254.lib;

/**
 * Contains basic functions that are used often.
 * @author Richard
 */
public class Util {
  /**
   * Checks whether a given number is within the given limit.
   */
  public static double limit(double v, double limit) {
    return Math.abs(v) < limit ? v : limit * (v < 0? -1 : 1);
  }
    
}
