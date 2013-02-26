package com.team254.frc2013;

import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.command.CommandGroup;
import java.util.Vector;

/**
 * Cycles through all available autonomous modes and returns the one chosen.
 * Increment should be called when a button is pressed to cycle through.
 *
 * @author tom@team254.com (Tom Bottiglieri)
 * @author art.kalb96@gmail.com (Arthur Kalb)
 * @author stephen@team254.com (Stephen Pinkerton)
 */
public final class AutoModeSelector {
  private Vector autoModes;
  private int index;
  private DriverStationLCD lcd;

  public AutoModeSelector() {
    autoModes = new Vector();
    addAutoCommand("None", CommandGroup.class);
    index = -1;
    lcd = DriverStationLCD.getInstance();
    increment();
  }
  
  void addAutoCommand(String name, Class cmd) {
    autoModes.addElement(new AutoMode(name, cmd));
    if (autoModes.size() == 1)
      increment(); // Don't start on none!
  }


  public void increment() {
    index++;
    if (index >= autoModes.size()) {
      index = 0;
    }
    lcd.println(DriverStationLCD.Line.kUser1, 1, "                                       ");
    lcd.println(DriverStationLCD.Line.kUser1, 1, "Auto: " + getCurrentName());
    lcd.updateLCD();
  }

  public String getCurrentName() {
    return ((AutoMode)autoModes.elementAt(index)).name;
  }

  public CommandGroup getCurrentAutoMode() {
    CommandGroup ret;
    try {
      AutoMode m = (AutoMode)autoModes.elementAt(index);
      ret = (CommandGroup) m.command.newInstance();
    } catch (InstantiationException ex) {
      ret = new CommandGroup();
    } catch (IllegalAccessException ex) {
      ret = new CommandGroup();
    }
    return ret;
  }


  private static class AutoMode {
    public String name;
    public Class command;

    public AutoMode(String name, Class command) {
      this.name = name;
      this.command = command;
    }
  }
}
