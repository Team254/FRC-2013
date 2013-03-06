package com.team254.frc2013.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Continuously runs through the shoot cycle until stopped.
 *
 * @author richard@team254.com (Richard Lin)
 */
public class ContinuousShootCommand extends CommandGroup {
  private boolean isOn;

  public ContinuousShootCommand(boolean isOn) {
    requires(CommandBase.shooter);
    this.isOn = isOn;
    if (isOn) {
      addSequential(new RunIntakeCommand(0.5));
      addSequential(new LoadAndShootCommand());
    } else {
      addSequential(new RunIntakeCommand(0));
      addSequential(new RetractShooterCommand());
    }
 }

  protected void end() {
    super.end();
    if (isOn) {
      start();
    }
  }
}
