/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package com.team254.frc2013;


import com.sun.squawk.io.BufferedReader;
import com.team254.frc2013.commands.CommandBase;
import com.team254.frc2013.commands.ExampleCommand;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import java.util.Hashtable;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Travus extends IterativeRobot {

    Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        // instantiate the command used for the autonomous period
        autonomousCommand = new ExampleCommand();

        // Initialize all subsystems
        CommandBase.init();
        
        // Java ME doesn't like BufferedReader, FileReader, File, & Scanner :(
        // TODO: figure a way to read files in Java ME; commented out for now
        /*
	BufferedReader inputStream = new BufferedReader(new FileReader("constants.txt"));
        File file = new File("constants.txt");
        Scanner in = new Scanner(file);
  	String constantLine, name;
  	double value;
  	Hashtable moddedConstants = new Hashtable();
  	while ((constantLine = inputStream.readLine()) != null) {
     		String[] splitted = constantLine.split("=");
      		name = splitted[0].trim();
      		value = Double.parseDouble(splitted[1].trim());
      		moddedConstants.put(name, value);
  	}
  	RobotMap.set(moddedConstants);
        */
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
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
