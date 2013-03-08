package com.team254.frc2013.auto;

import com.team254.frc2013.commands.AutonIndexerCommand;
import com.team254.frc2013.commands.DriveAtSpeedCommand;
import com.team254.frc2013.commands.DriveProfiledCommand;
import com.team254.frc2013.commands.IntakeRaiseCommand;
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
 * Scores three starting discs from the back of the pyramid, picks up four more, then scores them
 * from the front of the pyramid.

 * @author tom@team254.com (Tom Bottiglieri)
 * @author pat@team254.com (Patrick Fairbank)
 */
public class SevenDiscAutoMode extends CommandGroup {
  public SevenDiscAutoMode() {
    addSequential(new ResetDriveEncodersCommand());
    addSequential(new ResetGyroCommand());
    addSequential(new ShooterOnCommand(true));
    addSequential(new IntakeRaiseCommand(IntakeRaiseCommand.INTAKE_DOWN));
    addSequential(new ShooterPresetCommand(Shooter.PRESET_BACK_PYRAMID));
    addSequential(new WaitCommand(0.2));
    addSequential(new RunIntakeCommand(0.5));
    addSequential(new ShootCommand());
    addSequential(new LoadAndShootCommand());
    addSequential(new LoadAndShootCommand());
    addSequential(new RunIntakeCommand(1));
    addSequential(new ResetDriveEncodersCommand());
    addSequential(new ResetGyroCommand());
    addSequential(new DriveAtSpeedCommand(0.2, 0.4, 0, 5));
    addSequential(new DriveAtSpeedCommand(1.5, 0.21, 0, 5));
    addSequential(new ShooterPresetCommand(Shooter.PRESET_FRONT_PYRAMID));
    addSequential(new DriveAtSpeedCommand(6, 0.4, 0, 5));
    addSequential(new DriveAtSpeedCommand(7, 0.21, 0, 5));
    addSequential(new AutonIndexerCommand());
    addSequential(new DriveAtSpeedCommand(11.5, 0.21, 0, 5));
    addSequential(new RunIntakeCommand(1.0));
    addSequential(new DriveProfiledCommand(7, 8, 2.3));
    addSequential(new RunIntakeCommand(0.5));
    addSequential(new ShootCommand());
    addSequential(new LoadAndShootCommand());
    addSequential(new LoadAndShootCommand());
    addSequential(new LoadAndShootCommand());
    addSequential(new ShooterOnCommand(false));
    addSequential(new RunIntakeCommand(0.0));
 }
}
