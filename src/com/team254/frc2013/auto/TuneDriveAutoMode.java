package com.team254.frc2013.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import com.team254.frc2013.commands.ShiftCommand;
import com.team254.frc2013.commands.TuneDriveCommand;

/**
 * Drives back and forth in a square wave to allow for PID tuning.
 *
 * @author pat@team254.com (Patrick Fairbank)
 */
public class TuneDriveAutoMode extends CommandGroup {
  public TuneDriveAutoMode() {
    addSequential(new ShiftCommand(true));
    addSequential(new TuneDriveCommand(10, 2));
  }
}
