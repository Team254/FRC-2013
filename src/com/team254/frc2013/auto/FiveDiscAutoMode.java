package com.team254.frc2013.auto;

import com.team254.frc2013.commands.DriveAtSpeedCommand;
import com.team254.frc2013.commands.DriveProfiledCommand;
import com.team254.frc2013.commands.LoadAndShootCommand;
import com.team254.frc2013.commands.ResetDriveEncodersCommand;
import com.team254.frc2013.commands.ResetGyroCommand;
import com.team254.frc2013.commands.RunIntakeCommand;
import com.team254.frc2013.commands.ShootCommand;
import com.team254.frc2013.commands.ShooterOnCommand;
import com.team254.frc2013.commands.ShooterPresetCommand;
import com.team254.frc2013.commands.WaitCommand;
import com.team254.frc2013.subsystems.Shooter;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Scores three starting discs from the back of the pyramid, picks up two more, then scores them
 * again from the back of the pyramid.
 *
 * @author pat@team254.com (Patrick Fairbank)
 */
public class FiveDiscAutoMode extends CommandGroup {
  public FiveDiscAutoMode() {
    addSequential(new ResetDriveEncodersCommand());
    addSequential(new ResetGyroCommand());
    addSequential(new ShooterPresetCommand(Shooter.PRESET_BACK_PYRAMID));
    addSequential(new ShooterOnCommand(true));
    addSequential(new WaitCommand(1.0));
    addSequential(new ShootCommand());
    addSequential(new LoadAndShootCommand());
    addSequential(new LoadAndShootCommand());
    addSequential(new RunIntakeCommand(0.8));
    addSequential(new DriveAtSpeedCommand(3.5, 8, 5));
    addSequential(new DriveProfiledCommand(1.0, 7, 2.3)); // TODO(patrick): Fix; should be 0.
    addSequential(new RunIntakeCommand(0.0));
    addSequential(new LoadAndShootCommand());
    addSequential(new LoadAndShootCommand());
    addSequential(new ShooterOnCommand(false));
  }
}
