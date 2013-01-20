/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team254.frc2013;


import com.team254.frc2013.commands.CommandBase;
import com.team254.frc2013.commands.DriveDistanceCommand;
import com.team254.frc2013.commands.auto.ScriptedAutoMode;
import com.team254.frc2013.commands.auto.WaitCommand;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Travus extends IterativeRobot {

    Command autonomousCommand;
    AutoModeSelector selector;
    OI oi;
    boolean lastState = false;
    int num = 0;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        selector = new AutoModeSelector();
        oi = new OI();
        
        // Read from file, currently not in use
        Command scriptedCommand = new ScriptedAutoMode("test.txt");
        
        // A bunch of test auto commands to cycle through
        CommandGroup test1 = new CommandGroup("Drive 3s");
        test1.addSequential(new DriveDistanceCommand(1000, 0.5, 3));
        
        CommandGroup test2 = new CommandGroup("Drive 2s");
        test2.addSequential(new DriveDistanceCommand(1000, 0.5, 2));
        
        CommandGroup test3 = new CommandGroup("Drive 1s");
        test3.addSequential(new DriveDistanceCommand(1000, 0.5, 1));
        
        CommandGroup test4 = new CommandGroup("Wait 1s Drive 3s");
        test4.addSequential(new WaitCommand(1));
        test4.addSequential(new DriveDistanceCommand(1000, 0.5, 3));
        
        CommandGroup test5 = new CommandGroup("Drive 1s Wait 1s Drive 1s");
        test5.addSequential(new DriveDistanceCommand(1000, 0.5, 1));
        test5.addSequential(new WaitCommand(1));
        test5.addSequential(new DriveDistanceCommand(1000, 0.5, 1));
        
        selector.addAutoCommand(test1);
        selector.addAutoCommand(test2);
        selector.addAutoCommand(test3);
        selector.addAutoCommand(test4);
        selector.addAutoCommand(test5);

        // Initialize all subsystems
        CommandBase.init();
        
        DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser2, 1, "#Travus");
        DriverStationLCD.getInstance().updateLCD();
    }
    
    public void disabledPeriodic() {
        if(oi.gamepad.getButtonStateY() && lastState == false) {
            System.out.println("Y button pressed!");
            selector.increment();
            // Sketchy way of clearing line
            DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser1, 1, "                                        ");
            DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser1, 1, selector.getAutoMode().getName());
            DriverStationLCD.getInstance().updateLCD();
        }
        lastState = oi.gamepad.getButtonStateY();
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
        autonomousCommand = selector.getAutoMode();
        autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
	// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        autonomousCommand.cancel();
        System.out.println("I am in Teleop, broseph.");
        System.out.println("Robot Init! testing talons...");
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
}
