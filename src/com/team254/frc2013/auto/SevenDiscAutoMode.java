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
import com.team254.lib.control.impl.CustomProfile;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Scores three starting discs from the back of the pyramid, picks up four more, then scores them
 * from the front of the pyramid.

 * @author tom@team254.com (Tom Bottiglieri)
 * @author pat@team254.com (Patrick Fairbank)
 */
public class SevenDiscAutoMode extends CommandGroup {
  CustomProfile profile = new CustomProfile();

  public SevenDiscAutoMode() {
    // Shoot first discs
    addSequential(new ShooterOnCommand(true));
    addSequential(new ShooterPresetCommand(Shooter.PRESET_BACK_PYRAMID));
    addSequential(new IntakeRaiseCommand(IntakeRaiseCommand.INTAKE_DOWN));
    addSequential(new ShiftCommand(false));
    addSequential(new ShootSequenceCommand());
    addSequential(new ShootSequenceCommand());
    addSequential(new ShootSequenceCommand(false));

    // Pick up 2 middle discs
    addSequential(new ResetDriveEncodersCommand());
    addSequential(new ResetGyroCommand());
    addSequential(new RunIntakeCommand(1));
    addSequential(new ShooterPresetCommand(Shooter.PRESET_FRONT_PYRAMID));

    // Drive to front of pyramid and shoot 2
    addSequential(new DriveProfiledCommand(6.35, 3.5, 0, 5));
    addSequential(new RunIntakeCommand(0.0));
    addSequential(new ShootSequenceCommand());
    addSequential(new ShootSequenceCommand(false));

    // Pick up 2 far discs
    addSequential(new RunIntakeCommand(1.0));
    addSequential(new DriveAtSpeedCommand(10.5, 2.5, 0, 4.6));

    // Drive to front of pyramid and shoot 2
    addSequential(new DriveProfiledCommand(6.35, 5, 0, 2.5));
    addSequential(new RunIntakeCommand(0.0));
    addSequential(new ShootSequenceCommand());
    addSequential(new ShootSequenceCommand());
    addSequential(new ShootSequenceCommand(false));

    addSequential(new ShooterOnCommand(false));
    addSequential(new RunIntakeCommand(0.0));
  }
}
