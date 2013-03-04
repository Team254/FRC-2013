package com.team254.frc2013.auto;

import com.team254.frc2013.commands.AutonIndexerCommand;
import com.team254.frc2013.commands.DriveAtSpeedCommand;
import com.team254.frc2013.commands.DriveProfiledCommand;
import com.team254.frc2013.commands.LoadAndShootCommand;
import com.team254.frc2013.commands.ResetDriveEncodersCommand;
import com.team254.frc2013.commands.ResetGyroCommand;
import com.team254.frc2013.commands.RunIntakeCommand;
import com.team254.frc2013.commands.ShootCommand;
import com.team254.frc2013.commands.ShooterOnCommand;
import com.team254.frc2013.commands.ShooterPresetCommand;
import com.team254.frc2013.commands.TurnAngleCommand;
import com.team254.frc2013.commands.WaitCommand;
import com.team254.frc2013.subsystems.Shooter;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Scores three starting discs from the back of the pyramid, picks up four more, then scores them
 * from the front of the pyramid.

 * @author tom@team254.com (Tom Bottiglieri)
 * @author pat@team254.com (Patrick Fairbank)
 */
public class CenterDiscAutoMode extends CommandGroup {
  public CenterDiscAutoMode() {
    addSequential(new ResetDriveEncodersCommand());
    addSequential(new ResetGyroCommand());
    addSequential(new ShooterPresetCommand(Shooter.PRESET_BACK_PYRAMID));
    addSequential(new ShooterOnCommand(true));
    addSequential(new WaitCommand(1.0));
    addSequential(new RunIntakeCommand(0.5));
    addSequential(new ShootCommand());
    addSequential(new LoadAndShootCommand());
    addSequential(new LoadAndShootCommand());
    addSequential(new DriveProfiledCommand(-10.0, 7, 3));
    addSequential(new TurnAngleCommand(-70, 1.25));
    addSequential(new ResetDriveEncodersCommand());
    addSequential(new RunIntakeCommand(1));
    addSequential(new DriveAtSpeedCommand(2, 0.8, -75, 5));
    addSequential(new DriveAtSpeedCommand(7, 0.3, -75, 5));
    addSequential(new DriveAtSpeedCommand(6.5, 0.1, -75, 0.2));
    addSequential(new RunIntakeCommand(0.5));
    addSequential(new TurnAngleCommand(17, 1.25));
    addSequential(new AutonIndexerCommand());
    addSequential(new ResetDriveEncodersCommand());
    addSequential(new DriveProfiledCommand(10, 6, 5));
    addSequential(new ShootCommand());
    addSequential(new LoadAndShootCommand());
    addSequential(new LoadAndShootCommand());
    addSequential(new LoadAndShootCommand());
    addSequential(new ShooterOnCommand(false));
    addSequential(new RunIntakeCommand(0.0));
 }
}
