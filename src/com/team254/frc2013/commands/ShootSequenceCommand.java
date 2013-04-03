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
    addSequential(new CheckAutonTimerCommand(1));
    addSequential(new IndexerUpCommand());
    addSequential(new WaitForShooterSpinUpCommand(.5));
    addSequential(new ShootCommand());
    addSequential(new IndexerDownCommand());
    addSequential(new RunIntakeCommand(1));
    addSequential(new WaitCommand(.275));
    addSequential(new RunIntakeCommand(0));
  }
}
