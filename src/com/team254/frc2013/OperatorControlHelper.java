package com.team254.frc2013;

import com.team254.frc2013.commands.IncrementShooterSpeedCommand;
import com.team254.frc2013.commands.IntakeSpeedCommand;
import com.team254.frc2013.commands.SetConveyorCommand;
import com.team254.frc2013.commands.ShooterSpeedCommand;

/**
 * Maps operator control buttons to a specified command.
 *
 * @author tom@team254.com (Tom Bottiglieri)
 */
public class OperatorControlHelper {
  
  public static void setupOperationMap(ControlBoard c) {
    c.gamepad.getButtonA().whenPressed(new IncrementShooterSpeedCommand(-100,0));
    c.gamepad.getButtonB().whenPressed(new IncrementShooterSpeedCommand(100,0));
    c.gamepad.getButtonX().whenPressed(new IncrementShooterSpeedCommand(0,-100));
    c.gamepad.getButtonY().whenPressed(new IncrementShooterSpeedCommand(0,100));
    
    c.gamepad.getLeftShoulder().whenPressed(new SetConveyorCommand(-1.0));
    c.gamepad.getLeftShoulder().whenReleased(new SetConveyorCommand(0.0));
    c.gamepad.getRightShoulder().whenPressed(new SetConveyorCommand(1.0));
    c.gamepad.getRightShoulder().whenReleased(new SetConveyorCommand(0.0));
    
    c.gamepad.getLeftStickClick().whenPressed(new IntakeSpeedCommand(1.0));
    c.gamepad.getLeftStickClick().whenReleased(new IntakeSpeedCommand(0.0));
    c.gamepad.getRightStickClick().whenPressed(new IntakeSpeedCommand(-1.0));
    c.gamepad.getRightStickClick().whenReleased(new IntakeSpeedCommand(0.0));
  }
}
