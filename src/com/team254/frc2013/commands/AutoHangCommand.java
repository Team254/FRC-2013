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
    addSequential(new HangerUpCommand(-2485));
    addSequential(new WaitForForwardSwingCommand(-5, 15));
    addSequential(new HangerUpCommand(-3180));
    // Try this tomorrow.... Delete the previous 3 commands before removing comment
    //addSequential(new HangerGrabBarCommand(-2485, -3180, -5, 15));
    addSequential(new WaitForBackwardSwingCommand(-2));
    addSequential(new HangerDownCommand(-5));
    addSequential(new WaitCommand(0.25));
    addSequential(new HangerUpCommand(-2485));
    addSequential(new WaitForForwardSwingCommand(-5, 15));
    addSequential(new HangerUpCommand(-3180));
    addSequential(new WaitForBackwardSwingCommand(-2));
    addSequential(new HangerDownCommand(-5));
  }
}
