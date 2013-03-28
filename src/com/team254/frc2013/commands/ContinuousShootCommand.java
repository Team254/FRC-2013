package com.team254.frc2013.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Continuously runs through the shoot cycle until stopped.
 *
 * @author richard@team254.com (Richard Lin)
 */
public class ContinuousShootCommand extends CommandGroup {
  private boolean isOn;
  private static boolean cancel;

  public ContinuousShootCommand(boolean isOn) {
    this.isOn = isOn;
    if (isOn) {
      addSequential(new ShootSequenceCommand());
    }
 }
  
  protected void initialize() {
    super.initialize();
    cancel = !isOn;
  }

  protected void end() {
    super.end();
    if (isOn && !cancel) {
      start();
    }
  }
}
