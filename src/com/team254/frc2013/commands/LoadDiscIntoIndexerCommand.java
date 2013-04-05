/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.frc2013.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author tombot
 */
public class LoadDiscIntoIndexerCommand extends CommandGroup {
  public LoadDiscIntoIndexerCommand() {
    addSequential(new RunIntakeCommand(1));
    addSequential(new WaitForDiscCommand(1.5));
    addSequential(new RunIntakeCommand(-.1));
    addSequential(new WaitCommand(.08));
    addSequential(new RunIntakeCommand(0));
    addSequential(new WaitCommand(.05));
  }
}
