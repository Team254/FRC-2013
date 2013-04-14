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

  public ShootSequenceCommand(boolean doFeed) {
    requires(CommandBase.shooter);
    requires(CommandBase.intake);
    requires(CommandBase.conveyor);
    addSequential(new LoadDiscIntoIndexerCommand());
   // addSequential(new CheckAutonTimerCommand(.25));
    addSequential(new IndexerUpCommand());
    addSequential(new WaitForShooterSpinUpCommand(.5));
    addSequential(new ShootCommand());
    if (doFeed) {
      addSequential(new IndexerDownCommand());
      addSequential(new LoadDiscIntoIndexerCommand());
    } else {
      addSequential(new SetIndexerDownCommand());
    }
  }

  public ShootSequenceCommand() {
    this(true);
  }
}
