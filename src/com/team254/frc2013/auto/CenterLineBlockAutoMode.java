package com.team254.frc2013.auto;

import com.team254.frc2013.commands.CheckIntakeCalibratedCommand;
import com.team254.frc2013.commands.DriveAtSpeedCommand;
import com.team254.frc2013.commands.DriveProfiledCommand;
import com.team254.frc2013.commands.ResetDriveEncodersCommand;
import com.team254.frc2013.commands.ResetGyroCommand;
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
 * Sit on the center line to block opponents in auto mode.
 *
 * @author tom@team254.com (Tom Bottiglieri)
 */
public class CenterLineBlockAutoMode extends CommandGroup {

  public CenterLineBlockAutoMode() {
    // Shoot first 3
    addSequential(new ShooterOnCommand(true));
    addSequential(new ShooterPresetCommand(Shooter.PRESET_BACK_PYRAMID));
    addSequential(new SetIntakeUpCommand());
    addSequential(new ShootSequenceCommand());
    addSequential(new ShootSequenceCommand());
    addSequential(new ShootSequenceCommand(false));
    addSequential(new ShooterOnCommand(false));

    // Drive to center line
    addSequential(new WaitCommand(.25));
    addSequential(new CheckIntakeCalibratedCommand(.5));
    addSequential(new ShiftCommand(false));
    addSequential(new ResetDriveEncodersCommand());
    addSequential(new ResetGyroCommand());
    addSequential(new DriveAtSpeedCommand(-8.5, -6, 0, 5));
    addSequential(new DriveAtSpeedCommand(-8, 6, 0, 0.1));
    addSequential(new TurnMinAngleCommand(95, 5));

    // Drive forward along center line to pick up discs
    addSequential(new ResetDriveEncodersCommand());
    addSequential(new DriveProfiledCommand(-8.5, 6, 100, 10, false));
  }
}
