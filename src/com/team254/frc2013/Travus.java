package com.team254.frc2013;

import com.team254.frc2013.commands.CommandBase;
import com.team254.frc2013.commands.DriveDistanceCommand;
import com.team254.frc2013.commands.IntakeTimedCommand;
import com.team254.frc2013.commands.auto.DriveMotorTest;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Main class of the robot.
 *
 * @author richard@team254.com (Richard Lin)
 */
public class Travus extends IterativeRobot {
  private Command autonomousCommand;
  private ControlLoops loops = new ControlLoops(1.0 / 100.0);

  /**
   * Called when the robot is first started up and should be used for any initialization code.
   */
  public void robotInit() {
    // Initialize all subsystems.
    CommandBase.init();
    //autonomousCommand = new DriveDistanceCommand(12, 1, 10);
    autonomousCommand = new DriveMotorTest();
    /*
    autonomousCommand = new CommandGroup();
    ((CommandGroup)autonomousCommand).addParallel(new DriveDistanceCommand(1200, 1, 5));
    ((CommandGroup)autonomousCommand).addSequential(new WaitCommand(1));
    ((CommandGroup)autonomousCommand).addSequential(new IntakeTimedCommand(1, 2));
    */
  }

  /**
   * Called once at the start of the autonomous period.
   */
  public void autonomousInit() {
    //autonomousCommand = new DriveDistanceCommand(12, 1, 10);
    autonomousCommand.start();
  }

  /**
   * Called periodically during the autonomous period.
   */
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * Called once at the start of the teleoperated period.
   */
  public void teleopInit() {
    // Make sure that the autonomous stops running when teleop begins.
    autonomousCommand.cancel();
  }

  /**
   * Called periodically during the teleoperated period.
   */
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }
}
