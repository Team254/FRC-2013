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
    addSequential(new ResetTimerCommand(CommandBase.shotTimer));
    addSequential(new LoadDiscIntoIndexerCommand());
    addSequential(new CheckAutonTimerCommand(.25));
    addSequential(new RunIntakeCommand(.7));
    addSequential(new SensedIndexerUpCommand(.3));
    addSequential(new WaitForShooterSpinUpCommand(.3));
    addSequential(new RunIntakeCommand(0));
    addSequential(new ShootCommand());
    addSequential(new RunIntakeCommand(.7));
    addSequential(new SensedIndexerDownCommand(.2));
    addSequential(new FixIndexerCommand(.5));
    if (doFeed) {
      addSequential(new LoadDiscIntoIndexerCommand());
    } else {
      addSequential(new SetIndexerDownCommand());
    }
    addSequential(new PrintTimerCommand(CommandBase.shotTimer));
  }

  public ShootSequenceCommand() {
    this(true);
  }
}
