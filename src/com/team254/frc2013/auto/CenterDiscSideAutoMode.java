package com.team254.frc2013.auto;

import com.team254.frc2013.commands.DriveProfiledCommand;
import com.team254.frc2013.commands.ResetDriveEncodersCommand;
import com.team254.frc2013.commands.RunIntakeCommand;
import com.team254.frc2013.commands.ShiftCommand;
import com.team254.frc2013.commands.ShootSequenceCommand;
import com.team254.frc2013.commands.ShooterOnCommand;
import com.team254.frc2013.commands.ShooterPresetCommand;
import com.team254.frc2013.commands.TurnAngleCommand;
import com.team254.frc2013.commands.TurnMinAngleCommand;
import com.team254.frc2013.commands.WaitCommand;
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
    addSequential(new CenterDiscPartialAutoMode());

    // Return to starting position
    addSequential(new TurnMinAngleCommand(-112, .5));
    addSequential(new RunIntakeCommand(0));
    addSequential(new ShiftCommand(true));
    addSequential(new ResetDriveEncodersCommand());
    addSequential(new DriveProfiledCommand(-8.75, 10, -112, 1.75));
   // addSequential(new DriveAtSpeedCommand(-11, -15, -112, 10));
    addSequential(new ShiftCommand(false));
    addSequential(new ResetDriveEncodersCommand());
    //addSequential(new DriveAtSpeedCommand(.25, 5, -112, .35));
    addSequential(new ShooterOnCommand(true));
    addSequential(new ShooterPresetCommand(Shooter.PRESET_BACK_PYRAMID));
    addSequential(new TurnAngleCommand(10, .5));
    addParallel(new DriveProfiledCommand(8, 7, 10, 10));
    addSequential(new WaitCommand(.6));

    // Shoot remaining discs
    addSequential(new ShootSequenceCommand());
    addSequential(new ShootSequenceCommand());
    addSequential(new ShootSequenceCommand());
    addSequential(new ShootSequenceCommand());

    addSequential(new ShooterOnCommand(false));
    addSequential(new RunIntakeCommand(0.0));
  }
}
