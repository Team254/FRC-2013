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
  
  protected void initialize() {
    super.initialize();
  }

  protected void end() {
    super.end();
    CommandBase.rapidFireShots++;
    if (CommandBase.controlBoard.operatorJoystick.getRapidFireButtonState() && CommandBase.rapidFireShots < 4) {
      start();
    }
  }
}
