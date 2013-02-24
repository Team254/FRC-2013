/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.frc2013.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 * @author tombot
 */
public class SevenDiskAuto extends CommandGroup {
  public SevenDiskAuto() {
    addSequential(new ShooterSpeedCommand(12000));
    addSequential(new WaitCommand(.5));
    addSequential(new ShootMultipleCommand(3));
    addSequential(new RunIntakeCommand(1.0));
    addSequential(new DriveDistanceCommand(14*12, 6, 10));
    addSequential(new DriveDistanceCommand(-3*12,6,5));
    addSequential(new ShootMultipleCommand(3));
    
  }
  
}
