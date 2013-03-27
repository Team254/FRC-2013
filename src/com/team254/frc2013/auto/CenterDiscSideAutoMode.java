package com.team254.frc2013.auto;

import com.team254.frc2013.commands.DriveProfiledCommand;
import com.team254.frc2013.commands.ResetDriveEncodersCommand;
import com.team254.frc2013.commands.ResetGyroCommand;
import com.team254.frc2013.commands.RunIntakeCommand;
import com.team254.frc2013.commands.ShiftCommand;
import com.team254.frc2013.commands.ShootSequenceCommand;
import com.team254.frc2013.commands.ShooterOnCommand;
import com.team254.frc2013.commands.ShooterPresetCommand;
import com.team254.frc2013.commands.TurnAngleCommand;
import com.team254.frc2013.commands.TurnMinAngleCommand;
import com.team254.frc2013.subsystems.Shooter;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Scores three starting discs from the back of the pyramid, picks up four more,
 * then scores them from the front of the pyramid.
 *
 * @author tom@team254.com (Tom Bottiglieri)
 * @author pat@team254.com (Patrick Fairbank)
 */
public class CenterDiscSideAutoMode extends CommandGroup {

  public CenterDiscSideAutoMode(int numLastDiscs) {
    // Shoot first 3
    addSequential(new ShooterOnCommand(true));
    addSequential(new ShooterPresetCommand(Shooter.PRESET_BACK_PYRAMID));
    addSequential(new ShootSequenceCommand());
    addSequential(new ShootSequenceCommand());
    addSequential(new ShootSequenceCommand());

    // Drive to center line and intake
    addSequential(new ShiftCommand(false));
    addSequential(new ResetDriveEncodersCommand());
    addSequential(new ResetGyroCommand());
    addSequential(new TurnMinAngleCommand(17, 0.5));
    addSequential(new DriveProfiledCommand(-100 / 12.0, 17, 8, 2));
    addSequential(new TurnMinAngleCommand(-73, .5));
    addSequential(new RunIntakeCommand(1.0));
    addSequential(new ResetDriveEncodersCommand());
    addSequential(new DriveProfiledCommand(6, -73, 5, 1.75));


    // Drive back to side of pyramid
    addSequential(new ResetDriveEncodersCommand());
    addSequential(new RunIntakeCommand(.5));
    addSequential(new DriveProfiledCommand(-10, -73 - 45, 9, 2));
    addSequential(new TurnAngleCommand(-5, .25));
    addSequential(new ResetDriveEncodersCommand());
    addSequential(new DriveProfiledCommand(2.3, -7, 8, 2));

    // Shoot last discs
    for (int i = 0; i < numLastDiscs; ++i) {
      addSequential(new ShootSequenceCommand());
    }
    addSequential(new ShooterOnCommand(false));
    addSequential(new RunIntakeCommand(0.0));
  }

  public CenterDiscSideAutoMode() {
    this(3);
  }
}
