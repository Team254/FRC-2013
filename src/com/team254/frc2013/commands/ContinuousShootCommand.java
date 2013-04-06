package com.team254.frc2013.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Continuously runs through the shoot cycle until stopped.
 *
 * @author richard@team254.com (Richard Lin)
 */
public class ContinuousShootCommand extends CommandGroup {
  public ContinuousShootCommand() {
    addSequential(new ShootSequenceCommand(false));
  }

  protected void end() {
    super.end();
    if (CommandBase.controlBoard.operatorJoystick.getRapidFireButtonState()) {
      start();
    }
  }
}
