package com.team254.frc2013.auto;

import com.team254.frc2013.commands.DriveDistanceCommand;
import com.team254.frc2013.commands.RunIntakeCommand;
import com.team254.frc2013.commands.ShootMultipleCommand;
import com.team254.frc2013.commands.ShooterPresetCommand;
import com.team254.frc2013.commands.WaitCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * @author richard@team254.com (Richard Lin)
 */
public class FiveDiscAutoMode extends CommandGroup {
  public FiveDiscAutoMode() {
    addSequential(new ShooterPresetCommand(12000, false));
    addSequential(new WaitCommand(.5));
    addSequential(new ShootMultipleCommand(3));
    addSequential(new RunIntakeCommand(1.0));
    addSequential(new DriveDistanceCommand(3, 4, 4));
    addSequential(new ShootMultipleCommand(3));
  }
}
