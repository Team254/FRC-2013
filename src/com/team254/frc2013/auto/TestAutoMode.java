package com.team254.frc2013.auto;

import com.team254.frc2013.commands.DriveAtSpeedCommand;
import com.team254.frc2013.commands.DriveProfiledCommand;
import com.team254.frc2013.commands.IndexerDownCommand;
import com.team254.frc2013.commands.ResetDriveEncodersCommand;
import com.team254.frc2013.commands.ResetGyroCommand;
import com.team254.frc2013.commands.RunIntakeCommand;
import com.team254.frc2013.commands.ShiftCommand;
import com.team254.frc2013.commands.TurnMinAngleCommand;
import com.team254.frc2013.commands.WaitCommand;
import com.team254.lib.control.impl.CustomProfile;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Scores three starting discs from the back of the pyramid, picks up four more, then scores them
 * from the front of the pyramid.

 * @author tom@team254.com (Tom Bottiglieri)
 * @author pat@team254.com (Patrick Fairbank)
 */
public class TestAutoMode extends CommandGroup {
  CustomProfile profile = new CustomProfile();

  public TestAutoMode() {

    addSequential(new ResetDriveEncodersCommand());
    addSequential(new ResetGyroCommand());
    addSequential(new IndexerDownCommand());
    addSequential(new ShiftCommand(false));
    addSequential(new RunIntakeCommand(1));
    addSequential(new DriveAtSpeedCommand(5, 1.5, 0, 5));
        addSequential(new RunIntakeCommand(1));
    addSequential(new WaitCommand(.25));
        addSequential(new RunIntakeCommand(1));
    addSequential(new DriveProfiledCommand(0, 8, 2.3));
    /*addSequaential(new ShooterPresetCommand(Shooter.PRESET_FRONT_PYRAMID));
    addSequential(new DriveProfiledCommand(6, 6, 2.3));

    addSequential(new ShootAndLoadCommand());
    addSequential(new ShootAndLoadCommand());
    addSequential(new ShootAndLoadCommand());
    addSequential(new ShootAndLoadCommand());
    * */
    /*
    *
    addSequential(new ResetDriveEncodersCommand());
    addSequential(new ResetGyroCommand());
    addSequential(new DriveAtSpeedCommand(2.5, 2, 0, 20));
    //addSequential(new TurnAngleCommand(90, 5));
//    addSequential(new ShooterPresetCommand(Shooter.PRESET_FRONT_PYRAMID));
    addSequential(new DriveAtSpeedCommand(7, 6, 0, 5));
//    addSequential(new AutonIndexerCommand());
//    addSequential(new RunIntakeCommand(0.8));
    addSequential(new DriveAtSpeedCommand(9.5, 1.5, 0, 5));
//    addSequential(new RunIntakeCommand(0.8));
    addSequential(new DriveProfiledCommand(6, 6, 2.3));
    addSequential(new WaitCommand(3));
    addSequential(new DriveProfiledCommand(-5, 10, 5));
//    addSequential(new RunIntakeCommand(0.6));
//    addSequential(new ShootCommand());
//    addSequential(new LoadAndShootCommand());
//    addSequential(new LoadAndShootCommand());
//    addSequential(new LoadAndShootCommand());
**/
   // addSequential(new ShooterOnCommand(false));
    addSequential(new RunIntakeCommand(0.0));
 }
}
