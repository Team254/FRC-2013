package com.team254.frc2013;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * Contains the input devices making up the driver control board.
 *
 * @author richard@team254.com (Richard Lin)
*/
public class ControlBoard {
  //public Joystick gamePad = new Joystick(Constants.leftJoystickPort.getInt()); 
  public Joystick leftStick = new Joystick(Constants.leftJoystickPort.getInt());
  public Joystick rightStick = new Joystick(Constants.rightJoystickPort.getInt());
  public OperatorJoystick operatorJoystick = new OperatorJoystick(Constants.gamepadPort.getInt());
  public Stage1HangSwitch stage1HangSwitch = new Stage1HangSwitch();

  public boolean getQuickTurn() {
    return rightStick.getRawButton(2);
  }

  public boolean getHighGear() {
    return !leftStick.getRawButton(2);
  }

  public boolean getStage1Hang() {
    return !rightStick.getRawButton(3);
  }

  public class Stage1HangSwitch extends Button {
    public boolean get() {
      return getStage1Hang();
    }
  }
}
