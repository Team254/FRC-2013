package com.team254.frc2013;

import com.team254.frc2013.commands.IncrementShooterSpeedCommand;
import com.team254.frc2013.commands.ShooterSpeedCommand;

/**
 * Maps operator control buttons to a specified command.
 *
 * @author tom@team254.com (Tom Bottiglieri)
 */
public class OperatorControlHelper {
  
  public static void setupOperationMap(ControlBoard c) {
    c.gamepad.getButtonA().whenPressed(new IncrementShooterSpeedCommand(.1,0));
    c.gamepad.getButtonB().whenPressed(new IncrementShooterSpeedCommand(-.1,0));
    c.gamepad.getButtonX().whenPressed(new IncrementShooterSpeedCommand(0,.1));
    c.gamepad.getButtonY().whenPressed(new IncrementShooterSpeedCommand(0,-.1));
  }
}
