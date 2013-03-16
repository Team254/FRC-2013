package com.team254.frc2013.auto;

import com.team254.frc2013.commands.DriveAtSpeedCommand;
import com.team254.frc2013.commands.DriveProfiledCommand;
import com.team254.frc2013.commands.ResetDriveEncodersCommand;
import com.team254.frc2013.commands.ResetGyroCommand;
import com.team254.frc2013.commands.ShiftCommand;
import com.team254.frc2013.commands.WaitCommand;
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
    profile.addWaypoint(0, 1.5*12);
    profile.addWaypoint(1*12, 2.5*12);
    profile.addWaypoint(3*12, 2.5*12);
    profile.addWaypoint(4*12, 5.5*12);
    profile.addWaypoint(6.5*12, 5.5*12);
    profile.addWaypoint(7.0*12, 2.0*12);
    profile.addWaypoint(10*12, 2.0*12);
  
    addSequential(new ResetDriveEncodersCommand());
    addSequential(new ResetGyroCommand());
    addSequential(new ShiftCommand(false));
    addSequential(new DriveProfiledCommand(10, 10, 10, profile));
    addSequential(new WaitCommand(1));
    addSequential(new DriveProfiledCommand(6, 6, 2.3));
//    addSequential(new ShooterOnCommand(true));
//    addSequential(new IntakeRaiseCommand(IntakeRaiseCommand.INTAKE_DOWN));
//    addSequential(new ShooterPresetCommand(Shooter.PRESET_BACK_PYRAMID));
//    addSequential(new WaitCommand(0.2));
//    addSequential(new RunIntakeCommand(0.5));
//    addSequential(new ShootCommand());
//    addSequential(new LoadAndShootCommand());
//    addSequential(new LoadAndShootCommand());
//    addSequential(new RunIntakeCommand(1));
    /*
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
//    addSequential(new ShooterOnCommand(false));
//    addSequential(new RunIntakeCommand(0.0));
* */
 }
}
