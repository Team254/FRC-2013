package com.team254.frc2013.auto;

import com.team254.frc2013.commands.DriveAtSpeedCommand;
import com.team254.frc2013.commands.IntakeRaiseCommand;
import com.team254.frc2013.commands.ResetDriveEncodersCommand;
import com.team254.frc2013.commands.ResetGyroCommand;
import com.team254.frc2013.commands.RunIntakeCommand;
import com.team254.frc2013.commands.ShiftCommand;
import com.team254.frc2013.commands.ShootSequenceCommand;
import com.team254.frc2013.commands.ShooterOnCommand;
import com.team254.frc2013.commands.ShooterPresetCommand;
import com.team254.frc2013.commands.TurnMinAngleCommand;
import com.team254.frc2013.subsystems.Shooter;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Scores three starting discs from the back of the pyramid, picks up more from the center line,
 * then scores them from the side of the pyramid.
 *
 * @author tom@team254.com (Tom Bottiglieri)
 * @author pat@team254.com (Patrick Fairbank)
 */
public class CenterDiscSideAutoMode extends CommandGroup {

  public CenterDiscSideAutoMode() {
    // Shoot first 3
    addSequential(new ShooterOnCommand(true));
    addSequential(new ShooterPresetCommand(Shooter.PRESET_BACK_PYRAMID));
    addSequential(new IntakeRaiseCommand(IntakeRaiseCommand.INTAKE_DOWN));
    addSequential(new ShootSequenceCommand());
    addSequential(new ShootSequenceCommand());
    addSequential(new ShootSequenceCommand());
    addSequential(new ShooterOnCommand(false));

    // Drive to center line
    addSequential(new ResetDriveEncodersCommand());
    addSequential(new ResetGyroCommand());
    addSequential(new DriveAtSpeedCommand(-9, -6, 0, 5));
    addSequential(new DriveAtSpeedCommand(-8.5, 6, 0, 0.1));
    addSequential(new ShiftCommand(false));
    addSequential(new TurnMinAngleCommand(-72, 5));

    // Drive forward and backward along center line to pick up discs
    addSequential(new ResetDriveEncodersCommand());
    addSequential(new RunIntakeCommand(1.0));
    addSequential(new DriveAtSpeedCommand(8, 4, -72, 10));
    addSequential(new RunIntakeCommand(0));
    addSequential(new ShiftCommand(true));
    addSequential(new DriveAtSpeedCommand(-1, -6, -72, 5));
    addSequential(new DriveAtSpeedCommand(-0.5, 6, -72, 0.1));

    // Return to starting position
    addSequential(new TurnMinAngleCommand(0, 5));
    addSequential(new ShooterOnCommand(true));
    addSequential(new ResetDriveEncodersCommand());
    addSequential(new DriveAtSpeedCommand(10, 6, 0, 5));
    addSequential(new DriveAtSpeedCommand(9.5, -6, 0, 0.1));
    addSequential(new TurnMinAngleCommand(-5, 1));

    // Shoot remaining discs
    addSequential(new ShootSequenceCommand());
    addSequential(new ShootSequenceCommand());
    addSequential(new ShootSequenceCommand());
    addSequential(new ShootSequenceCommand());

    addSequential(new ShooterOnCommand(false));
    addSequential(new RunIntakeCommand(0.0));
  }
}
