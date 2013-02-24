package com.team254.frc2013.auto;

import com.team254.frc2013.commands.DriveDistanceCommand;
import com.team254.frc2013.commands.RunIntakeCommand;
import com.team254.frc2013.commands.ShootMultipleCommand;
import com.team254.frc2013.commands.ShooterSpeedCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 * @author tombot
 */
public class SevenDiscAutoMode extends CommandGroup {
  public SevenDiscAutoMode() {
    addSequential(new ShooterSpeedCommand(12000));
    addSequential(new WaitCommand(.5));
    addSequential(new ShootMultipleCommand(3));
    addSequential(new RunIntakeCommand(1.0));
    addSequential(new DriveDistanceCommand(14 * 12, 6, 10));
    addSequential(new DriveDistanceCommand(-3 * 12, 6, 5));
    addSequential(new ShootMultipleCommand(3));
  }
}
