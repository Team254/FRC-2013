package com.team254.frc2013.auto;

import com.team254.frc2013.commands.DriveMinDistanceCommand;
import com.team254.frc2013.commands.DriveToDistanceCommand;
import com.team254.frc2013.commands.IndexerCommand;
import com.team254.frc2013.commands.LoadAndShootCommand;
import com.team254.frc2013.commands.RunIntakeCommand;
import com.team254.frc2013.commands.ShootCommand;
import com.team254.frc2013.commands.ShooterOnCommand;
import com.team254.frc2013.commands.ShooterPresetCommand;
import com.team254.frc2013.commands.WaitCommand;
import com.team254.frc2013.subsystems.Shooter;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Scores three starting discs from the back of the pyramid, picks up four more, then scores them
 * from the front of the pyramid.

 * @author tom@team254.com (Tom Bottiglieri)
 * @author pat@team254.com (Patrick Fairbank)
 */
public class SevenDiscAutoMode extends CommandGroup {
  public SevenDiscAutoMode() {
    addSequential(new ShooterPresetCommand(Shooter.PRESET_BACK_PYRAMID));
    addSequential(new ShooterOnCommand(true));
    addSequential(new WaitCommand(1.0));
    addSequential(new ShootCommand());
    addSequential(new LoadAndShootCommand());
    addSequential(new LoadAndShootCommand());
    addSequential(new RunIntakeCommand(0.7));
    addSequential(new DriveMinDistanceCommand(7, 6, 5));
    addSequential(new IndexerCommand(false));
    addSequential(new WaitCommand(0.4));
    addSequential(new IndexerCommand(true));
    addSequential(new RunIntakeCommand(0.9));
    addSequential(new DriveMinDistanceCommand(13, 6, 5));
    addSequential(new RunIntakeCommand(0.4));
    addSequential(new DriveToDistanceCommand(9, 5, 2.3)); // TODO(patrick): Fix; should be 0.
    addSequential(new RunIntakeCommand(0.0));
    addSequential(new ShooterPresetCommand(Shooter.PRESET_FRONT_PYRAMID));
    addSequential(new ShootCommand());
    addSequential(new LoadAndShootCommand());
    addSequential(new LoadAndShootCommand());
    addSequential(new LoadAndShootCommand());
    addSequential(new ShooterOnCommand(false));
    addSequential(new RunIntakeCommand(0.0));
  }
}
