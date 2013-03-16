package com.team254.frc2013.auto;

import com.team254.frc2013.commands.DriveProfiledCommand;
import com.team254.frc2013.commands.ResetDriveEncodersCommand;
import com.team254.frc2013.commands.ResetGyroCommand;
import com.team254.frc2013.commands.ShiftCommand;
import com.team254.frc2013.commands.TurnAngleCommand;
import com.team254.frc2013.commands.TurnMinAngleCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Scores three starting discs from the back of the pyramid, picks up four more, then scores them
 * from the front of the pyramid.

 * @author tom@team254.com (Tom Bottiglieri)
 * @author pat@team254.com (Patrick Fairbank)
 */
public class CenterDiscSideAutoMode extends CommandGroup {
  public CenterDiscSideAutoMode() {
    addSequential(new ResetDriveEncodersCommand());
    addSequential(new ResetGyroCommand());
   /* addSequential(new ShooterPresetCommand(Shooter.PRESET_BACK_PYRAMID));
    addSequential(new ShooterOnCommand(true));
    addSequential(new WaitCommand(1.0));
    addSequential(new RunIntakeCommand(0.5));
    addSequential(new ShootCommand());
    addSequential(new LoadAndShootCommand());
    addSequential(new LoadAndShootCommand());*/
    addSequential(new ShiftCommand(true));
    addSequential(new TurnMinAngleCommand(17, 0.2));
    addSequential(new DriveProfiledCommand(-100/12.0, 17, 13, 2));
    addSequential(new TurnMinAngleCommand(-73, .5));
    addSequential(new ResetDriveEncodersCommand());
    addSequential(new DriveProfiledCommand(6, -73, 5, 1.75));
    //addSequential(new ResetDriveEncodersCommand());
   // addSequential(new TurnMinAngleCommand(-73-45, .25));
    addSequential(new ResetDriveEncodersCommand());
    addSequential(new DriveProfiledCommand(-10, -73-45, 9, 2));
    addSequential(new TurnAngleCommand(-5, .25));
    addSequential(new ResetDriveEncodersCommand());
    addSequential(new DriveProfiledCommand(2.3, -7, 8, 2));

   // addSequential(new DriveProfiledCommand(5, 15, 6, 3));
    /*addSequential(new ResetDriveEncodersCommand());
    addSequential(new RunIntakeCommand(1));
    addSequential(new DriveAtSpeedCommand(2, 0.8, -75, 5));
    addSequential(new DriveAtSpeedCommand(7, 0.3, -75, 5));
    addSequential(new DriveAtSpeedCommand(6.5, 0.1, -75, 0.2));
    addSequential(new RunIntakeCommand(0.5));
    addSequential(new TurnAngleCommand(17, 1.25));
    addSequential(new AutonIndexerCommand());
    addSequential(new ResetDriveEncodersCommand());
    addSequential(new DriveProfiledCommand(10, 6, 5));
    addSequential(new ShootCommand());
    addSequential(new LoadAndShootCommand());
    addSequential(new LoadAndShootCommand());
    addSequential(new LoadAndShootCommand());
    addSequential(new ShooterOnCommand(false));
    addSequential(new RunIntakeCommand(0.0));*/
 }
}
