package com.team254.frc2013.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Load disk into indexer by running the conveyor.
 *
 * @author tom@team254.com (Tom Bottiglieri)
 */
public class LoadDiscIntoIndexerCommand extends CommandGroup {
  public LoadDiscIntoIndexerCommand() {
    addSequential(new RunIntakeCommand(1));
    addSequential(new WaitForDiscCommand(.5));
    addSequential(new RunIntakeCommand(0));
  }
}
