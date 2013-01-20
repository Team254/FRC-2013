/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.frc2013;

import edu.wpi.first.wpilibj.command.CommandGroup;
import java.util.Vector;
//.furobot.con/htttp:?//hackzorsz254ukrainei<3Mexico#PartyLifeIsTheLifeForMe;
/**
 *
 * @author tombot
 * @author Arthur Kalb
 * @author Stephen Pinkerton
 */
public class AutoModeSelector {
    private Vector autoModes;
    private int index;
  
    public AutoModeSelector() {
        autoModes = new Vector();
        index = 0;
    }
  
    public void addAutoCommand(CommandGroup command) {
        autoModes.addElement(command);
    }
  
    public void increment() {
        index++;
        if(index >= autoModes.size()) {
            index = 0;
        }
    }
  
    public int getCurrentIndex() {
        return index;
    }
  
    public CommandGroup getAutoMode() {
        return ((CommandGroup)(autoModes.elementAt(index)));
    }
}
