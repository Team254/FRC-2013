package com.team254.frc2013.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Continuously runs through the shoot cycle until stopped.
 *
 * @author richard@team254.com (Richard Lin)
 */
public class ContinuousShootCommand extends CommandGroup {
  public ContinuousShootCommand() {
    addSequential(new ShootSequenceCommand(true));
  }

  protected void initialize() {
    System.out.println("*** INIT");
    super.initialize();
  }

  protected void end() {
    super.end();
    CommandBase.rapidFireShots++;
    System.out.println("********In ContinuousShoot end " + CommandBase.rapidFireShots + " \n");
    if (CommandBase.controlBoard.operatorJoystick.getRapidFireButtonState() && CommandBase.rapidFireShots < 4) {
      System.out.println("************Restarting....\n");
      start();
    }
  }
}
