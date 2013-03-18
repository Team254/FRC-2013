package com.team254.frc2013.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Shoots a disc and then loads another one.
 *
 * @author chris@team254.com (Chris Sides) :D
 */
public class ShootAndLoadCommand extends CommandGroup {
  public ShootAndLoadCommand() {
    addSequential(new ShooterExtendCommand());
    addSequential(new WaitCommand(0.5));
    addSequential(new ShooterRetractCommand());
    addSequential(new IndexerDownCommand());
    addSequential(new IndexerUpCommand());
    addSequential(new RunIntakeCommand(1));
  }
}
