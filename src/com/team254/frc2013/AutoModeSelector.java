package com.team254.frc2013;

import edu.wpi.first.wpilibj.command.CommandGroup;
import java.util.Vector;
/**
 *
 * @author tom@team254.com (Tom Bottiglieri)
 * @author art.kalb96@gmail.com (Arthur Kalb)
 * @author stephen@team254.com (Stephen Pinkerton)
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
