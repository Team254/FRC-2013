package com.team254.frc2013.auto;

import com.team254.frc2013.commands.DriveMotorTestCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Runs each drive motor for 5 seconds.
 *
 * @author pat@team254.com (Patrick Fairbank)
 */
public class DriveMotorTestAutoMode extends CommandGroup {
  public DriveMotorTestAutoMode() {
    addSequential(new DriveMotorTestCommand());
  }
}
