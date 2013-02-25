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
    addAutoCommand("None", new CommandGroup());
    index = -1;
    lcd = DriverStationLCD.getInstance();
    increment();
  }

  public void addAutoCommand(String name, CommandGroup command) {
    autoModes.addElement(new AutoMode(name, command));
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
    return ((AutoMode)autoModes.elementAt(index)).command;
  }

  private static class AutoMode {
    public String name;
    public CommandGroup command;

    public AutoMode(String name, CommandGroup command) {
      this.name = name;
      this.command = command;
    }
  }
}
