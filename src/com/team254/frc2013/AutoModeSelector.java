/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.frc2013;

import edu.wpi.first.wpilibj.command.CommandGroup;
import java.util.Vector;

/**
 *
 * @author tombot
 * @author Arthur Kalb
 * @author Stephen Pinkerton
 */
public class AutoModeSelector {
  Vector autoModes;
  int index;
  /**
   * Adds all the autonomous modes 
   */
  public AutoModeSelector(){
    index = 0;
    //Add all the possible automodes
  }
  /**
   * When the button is hit, the select mode will increment, or reset to 0
   */
  public void increment(){
    index++;
    if(index >= autoModes.size()){
      index = 0;
    }
  }
  /**
   * gets the autnomous mode 
   */
  public CommandGroup getAutoMode(){
    return ((CommandGroup)(autoModes.elementAt(index)));
  }
}
