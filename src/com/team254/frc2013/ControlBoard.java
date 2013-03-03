package com.team254.frc2013;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Contains the input devices making up the driver control board.
 *
 * @author richard@team254.com (Richard Lin)
*/
public class ControlBoard {
  public Joystick leftStick = new Joystick(Constants.leftJoystickPort.getInt());
  public Joystick rightStick = new Joystick(Constants.rightJoystickPort.getInt());
  public OperatorJoystick operatorJoystick = new OperatorJoystick(Constants.gamepadPort.getInt());

  public boolean getQuickTurn() {
    return rightStick.getRawButton(2);
  }

  public boolean getHighGear() {
    return leftStick.getRawButton(3);
  }
  
  public boolean getStage1Hang() {
    return rightStick.getRawButton(3);
  }
}
