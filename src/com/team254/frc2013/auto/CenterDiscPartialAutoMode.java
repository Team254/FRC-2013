package com.team254.frc2013.auto;

import com.team254.frc2013.commands.CheckIntakeCalibratedCommand;
import com.team254.frc2013.commands.DriveAtSpeedCommand;
import com.team254.frc2013.commands.ResetDriveEncodersCommand;
import com.team254.frc2013.commands.ResetGyroCommand;
import com.team254.frc2013.commands.RunIntakeCommand;
import com.team254.frc2013.commands.SetIntakeDownCommand;
import com.team254.frc2013.commands.SetIntakeUpCommand;
import com.team254.frc2013.commands.ShiftCommand;
import com.team254.frc2013.commands.ShootSequenceCommand;
import com.team254.frc2013.commands.ShooterOnCommand;
import com.team254.frc2013.commands.ShooterPresetCommand;
import com.team254.frc2013.commands.TurnMinAngleCommand;
import com.team254.frc2013.commands.WaitCommand;
import com.team254.frc2013.subsystems.Shooter;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Shoots 3 discs, drives to center line, then pick up discs.
 *
 * @author tom@team254.com (Tom Bottiglieri)
 */
public class CenterDiscPartialAutoMode extends CommandGroup {

  public CenterDiscPartialAutoMode() {
    // Shoot first 3
    addSequential(new ShooterOnCommand(true));
    addSequential(new ShooterPresetCommand(Shooter.PRESET_BACK_PYRAMID));
    addSequential(new SetIntakeUpCommand());
    addSequential(new ShootSequenceCommand());
    addSequential(new ShootSequenceCommand());
    addSequential(new ShootSequenceCommand(false));
    addSequential(new ShooterOnCommand(false));

    // Drive to center line
    addSequential(new WaitCommand(.5));
    addSequential(new CheckIntakeCalibratedCommand(.5));
    addSequential(new ShiftCommand(false));
    addSequential(new ResetDriveEncodersCommand());
    addSequential(new ResetGyroCommand());
    addSequential(new DriveAtSpeedCommand(-8.5, -6, 0, 5));
    addSequential(new DriveAtSpeedCommand(-8, 6, 0, 0.1));
    addSequential(new TurnMinAngleCommand(-72, 5));

    // Drive forward along center line to pick up discs
    addSequential(new SetIntakeDownCommand());
    addSequential(new ResetDriveEncodersCommand());
    addSequential(new RunIntakeCommand(1.0));
    addSequential(new DriveAtSpeedCommand(7, 4, -72, 10));
  }
}
