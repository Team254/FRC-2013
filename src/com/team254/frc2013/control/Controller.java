/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.frc2013.control;

import java.util.Vector;

/**
 *
 * @author Richard
 */
public abstract class Controller {
  private static Vector controllers = new Vector();
  private String name;
  
  public Controller(String name) {
    controllers.addElement(this);
    this.name = name;
  }
  
  public String getName(){
    return name;
  }
  public static void updateAll() {
    for(int i = 0; i < controllers.size(); i++) {
      Controller c = (Controller) controllers.elementAt(i);
      c.update();
    }
  }
  
  public abstract void enable();
  
  public abstract void disable();
  
  public abstract void update();
  
  public abstract void setGoal(double goal);
}
