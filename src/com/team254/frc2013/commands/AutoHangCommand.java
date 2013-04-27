package com.team254.frc2013.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Climbs to the third level autonomously.
 *
 * @author pat@team254.com (Patrick Fairbank)
 */
public class AutoHangCommand extends CommandGroup {

  public AutoHangCommand() {
    addSequential(new ShiftCommand(true));
    addSequential(new StopDriveCommand());
    addSequential(new WaitForLiftCommand());
    addSequential(new StowIntakeForHangCommand());
    addSequential(new EngagePtoCommand());
    addSequential(new ResetDriveEncodersCommand());
    addSequential(new HangerGrabBarCommand(-2485, -3190, -2, 5));
    addSequential(new WaitCommand(.5));
    addSequential(new WaitForBackwardSwingCommand(-0.5));
    addSequential(new HangerDownCommand(-2980, 0.75));
    addSequential(new WaitForButtonCommand(CommandBase.controlBoard.operatorJoystick.getIntakeButton()));
    addSequential(new HangerDownCommand(-5, 1));
    addSequential(new WaitCommand(0.25));
    addSequential(new HangerGrabBarCommand(-2485, -3190, -5, 15));
    addSequential(new WaitCommand(.5));
    addSequential(new WaitForBackwardSwingCommand(-0.5));
    addSequential(new HangerDownCommand(-2980, 0.75));
    addSequential(new PtoCommand());
  }
}
