package com.team254.frc2013.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Runs the shooting sequence once.
 * Raises the indexer to prepare for shooting, shoots the loaded disc, sets
 * indexer back down, and runs intake to move a disc into the indexer.
 *
 * @author tom@team254.com (Tom Bottiglieri)
 */
public class ShootSequenceCommand extends CommandGroup {

  public ShootSequenceCommand() {
    addSequential(new RunIntakeCommand(1));
    addSequential(new WaitCommand(.13));
    addSequential(new RunIntakeCommand(-.1));
    addSequential(new WaitCommand(.08));
    addSequential(new RunIntakeCommand(0));
    addSequential(new WaitCommand(.09));
   // addSequential(new LoadDiscIntoIndexerCommand());
    addSequential(new CheckAutonTimerCommand(.5));
    addSequential(new IndexerUpCommand());
    addSequential(new WaitForShooterSpinUpCommand(.5));
    addSequential(new ShootCommand());
    addSequential(new IndexerDownCommand());
    addSequential(new RunIntakeCommand(1));
    addSequential(new WaitCommand(.26));
    addSequential(new RunIntakeCommand(0));
  }
}
