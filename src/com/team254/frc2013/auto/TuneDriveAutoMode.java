package com.team254.frc2013.auto;

import com.team254.frc2013.commands.TuneHangCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drives back and forth in a square wave to allow for PID tuning.
 *
 * @author pat@team254.com (Patrick Fairbank)
 */
public class TuneDriveAutoMode extends CommandGroup {
  public TuneDriveAutoMode() {
    //addSequential(new ShiftCommand(true));
    //addSequential(new TuneDriveCommand(10, 2));
    //addSequential(new TuneTurnCommand(10, 45));
    addSequential(new TuneHangCommand(6, 500)); // 2485 3180
  }
}
