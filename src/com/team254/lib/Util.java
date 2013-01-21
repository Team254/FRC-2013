package com.team254.lib;

import java.util.Vector;

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
  public static String[] split(String input, String delimiter) {
     Vector node = new Vector();
    int index = input.indexOf(delimiter);
    while (index >= 0) {
      node.addElement(input.substring(0, index));
      input = input.substring(index+delimiter.length());
      index = input.indexOf(delimiter);
    }
    node.addElement(input);
    
    String[] retString = new String[node.size()];
    for(int i = 0; i < node.size(); ++i) {
      retString[i] = (String)node.elementAt(i);
    }
    
    return retString;
  }
}
