package com.team254.frc2013.auto;

import com.team254.frc2013.commands.DriveAtSpeedCommand;
import com.team254.frc2013.commands.DriveProfiledCommand;
import com.team254.frc2013.commands.IntakeRaiseCommand;
import com.team254.frc2013.commands.ResetDriveEncodersCommand;
import com.team254.frc2013.commands.ResetGyroCommand;
import com.team254.frc2013.commands.RunIntakeCommand;
import com.team254.frc2013.commands.ShiftCommand;
import com.team254.frc2013.commands.ShootSequenceCommand;
import com.team254.frc2013.commands.ShooterOnCommand;
import com.team254.frc2013.commands.ShooterPresetCommand;
import com.team254.frc2013.commands.WaitCommand;
import com.team254.frc2013.subsystems.Shooter;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Scores three starting discs from the back of the pyramid, picks up two more,
 * then scores them again from the back of the pyramid.
 *
 * @author pat@team254.com (Patrick Fairbank)
 */
public class FiveDiscAutoMode extends CommandGroup {

  public FiveDiscAutoMode() {
    // Shoot first set of discs
    addSequential(new ShooterOnCommand(true));
    addSequential(new IntakeRaiseCommand(IntakeRaiseCommand.INTAKE_DOWN));
    addSequential(new ShooterPresetCommand(Shooter.PRESET_BACK_PYRAMID));
    addSequential(new ShiftCommand(false));
    addSequential(new ShootSequenceCommand());
    addSequential(new ShootSequenceCommand());
    addSequential(new ShootSequenceCommand());

    // Drive to get second set
    addSequential(new ResetDriveEncodersCommand());
    addSequential(new ResetGyroCommand());
    addSequential(new RunIntakeCommand(1));
    addSequential(new DriveAtSpeedCommand(2, 2, 0, 5));
    addSequential(new WaitCommand(1));

    // Drive back and shoot
    addSequential(new DriveProfiledCommand(0, 5, 5));
    addSequential(new RunIntakeCommand(0.0));
    addSequential(new ShootSequenceCommand());
    addSequential(new ShootSequenceCommand());

    addSequential(new ShooterOnCommand(false));
    addSequential(new RunIntakeCommand(0.0));
  }
}
