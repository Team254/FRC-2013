package com.team254.frc2013.auto;

import com.team254.frc2013.commands.DriveProfiledCommand;
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
 * Scores three starting discs from the back of the pyramid, picks up four more,
 * then scores them from the front of the pyramid.
 *
 * @author tom@team254.com (Tom Bottiglieri)
 * @author pat@team254.com (Patrick Fairbank)
 */
public class CenterDiscMiddleAutoMode extends CommandGroup {

  public CenterDiscMiddleAutoMode(int numLastDiscs) {
     addSequential(new CenterDiscPartialAutoMode());
 
    // Drive to back of pyramid
    addSequential(new TurnMinAngleCommand(15, 1.25));
    addSequential(new ResetDriveEncodersCommand());
    addSequential(new ShooterOnCommand(true));
    addSequential(new ShooterPresetCommand(Shooter.PRESET_BACK_PYRAMID));
    addSequential(new DriveProfiledCommand(8.9, 6.4, 15, 3));

    // Shoot last discs
    for (int i = 0; i < numLastDiscs; ++i) {
      addSequential(new ShootSequenceCommand());
    }
    addSequential(new ShooterOnCommand(false));
    addSequential(new RunIntakeCommand(0.0));
  }

  public CenterDiscMiddleAutoMode() {
    this(5);
  }
}
