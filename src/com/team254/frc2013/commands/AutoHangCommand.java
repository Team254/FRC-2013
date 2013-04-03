package com.team254.frc2013.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Climbs to the third level autonomously.
 *
 * @author pat@team254.com (Patrick Fairbank)
 */
public class AutoHangCommand extends CommandGroup {
  public AutoHangCommand() {
    addSequential(new WaitForLiftCommand());
    addSequential(new EngagePtoCommand());
    addSequential(new ResetDriveEncodersCommand());
    addSequential(new HangerGrabBarCommand(-2485, -3180, -5, 15));
    addSequential(new WaitForBackwardSwingCommand(0));
    addSequential(new HangerDownCommand(-5));
    addSequential(new WaitCommand(0.25));
    addSequential(new HangerGrabBarCommand(-2485, -3180, -5, 15));
    addSequential(new WaitForBackwardSwingCommand(-1));
    addSequential(new HangerDownCommand(-2980));
    addSequential(new PtoCommand());
  }
}
