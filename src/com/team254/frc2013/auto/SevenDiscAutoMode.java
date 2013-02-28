package com.team254.frc2013.auto;

import com.team254.frc2013.commands.DriveDistanceCommand;
import com.team254.frc2013.commands.RunIntakeCommand;
import com.team254.frc2013.commands.ShootMultipleCommand;
import com.team254.frc2013.commands.ShooterPresetCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * @author tombot
 */
public class SevenDiscAutoMode extends CommandGroup {
  public SevenDiscAutoMode() {
    addSequential(new ShooterPresetCommand(12000, false));
    addSequential(new WaitCommand(.5));
    addSequential(new ShootMultipleCommand(3));
    addSequential(new RunIntakeCommand(1.0));
    addSequential(new DriveDistanceCommand(11.3, 5.5 , 4));
    addSequential(new ShooterPresetCommand(12000, true));
    addSequential(new DriveDistanceCommand(-3.0, 8, 2.3));
    addSequential(new WaitCommand(.5));
    addSequential(new ShootMultipleCommand(3));
  }
}
