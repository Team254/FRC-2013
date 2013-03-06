package com.team254.frc2013.auto;

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
 * Shoots two preload discs from the front of the pyramid, intakes 2 discs in front of the
 * pyramid, and returns to the start point to shoot them
 *
 * @author jonathan.chang13@gmail.com (Jonathan Chang)
 */
public class FourDiscAutoMode extends CommandGroup {
  public FourDiscAutoMode() {
    addSequential(new ResetDriveEncodersCommand());
    addSequential(new ResetGyroCommand());
    addSequential(new ShooterOnCommand(true));
    addSequential(new IntakeRaiseCommand(IntakeRaiseCommand.INTAKE_DOWN));
    addSequential(new ShooterPresetCommand(Shooter.PRESET_FRONT_PYRAMID));
    addSequential(new WaitCommand(0.25));
    addSequential(new RunIntakeCommand(0.5));
    addSequential(new ShootCommand());
    addSequential(new LoadAndShootCommand());
    addSequential(new RunIntakeCommand(1));
    addSequential(new DriveAtSpeedCommand(3.5, 0.3, 0, 5));
    addSequential(new WaitCommand(1));
    addSequential(new DriveProfiledCommand(0.3, 5, 2.3));
    addSequential(new RunIntakeCommand(0.5));
    addSequential(new LoadAndShootCommand());
    addSequential(new LoadAndShootCommand());
    addSequential(new ShooterOnCommand(false));
    addSequential(new RunIntakeCommand(0.0));
  }
}
