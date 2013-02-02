package com.team254.frc2013.commands;

import com.team254.frc2013.Constants;
import com.team254.frc2013.ControlBoard;
import com.team254.frc2013.OperatorControlHelper;
import com.team254.frc2013.subsystems.Drive;
import com.team254.frc2013.subsystems.Intake;
import com.team254.frc2013.subsystems.Shooter;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Base class for all commands. All atomic commands should subclass CommandBase.
 *
 * @author richard@team254.com (Richard Lin)
 */
public abstract class CommandBase extends Command {
  public static ControlBoard controlBoard;

  // Declare a single static instance of each subsystem here.
  public static Drive drive = new Drive();
  public static Shooter shooter = new Shooter();
  public static Compressor compressor = new Compressor(Constants.pressureSwitch.getInt(), 
                                                       Constants.compressorRelay.getInt());
  public static Intake intake = new Intake();
  
  public static void init() {
    // This MUST be here. If the OI creates Commands (which it very likely will), constructing it
    // during the construction of CommandBase (from which commands extend), subsystems are not
    // guaranteed to be yet. Thus, their requires() statements may grab null pointers.

    // Set up operator controls
    controlBoard = new ControlBoard();
    OperatorControlHelper.setupOperationMap(controlBoard);
  
    compressor.start();
  }
}
