package com.team254.frc2013.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Picks up a disc from the indexer and loads it into the shooter.
 *
 * @author pat@team254.com (Patrick Fairbank)
 */
public class ManualIndexerCommand extends CommandGroup {
  public ManualIndexerCommand() {
    addSequential(new SensedIndexerDownCommand());
    addSequential(new SensedIndexerUpCommand());
  }
}
