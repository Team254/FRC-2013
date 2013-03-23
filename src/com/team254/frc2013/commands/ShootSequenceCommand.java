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
public class ShootSequenceCommand extends CommandGroup {
  
  public ShootSequenceCommand() {
    addSequential(new IndexerUpCommand());
    addSequential(new ShootCommand());
    addSequential(new IndexerDownCommand());
    addSequential(new RunIntakeCommand(1));
    addSequential(new WaitCommand(.25));
    addSequential(new RunIntakeCommand(0));
  }
}
