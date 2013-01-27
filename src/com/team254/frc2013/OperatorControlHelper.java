/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.frc2013;

import com.team254.frc2013.commands.ShooterSpeedCommand;

/**
 *
 * @author tombot
 */
public class OperatorControlHelper {
  
  public static void setupOperationMap(ControlBoard c) {
    c.gamepad.getButtonA().whenPressed(new ShooterSpeedCommand(0));
    c.gamepad.getButtonX().whenPressed(new ShooterSpeedCommand(.25));
    c.gamepad.getButtonY().whenPressed(new ShooterSpeedCommand(.6));
    c.gamepad.getButtonB().whenPressed(new ShooterSpeedCommand(1.0));
  }
  
}
