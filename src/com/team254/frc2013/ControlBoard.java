package com.team254.frc2013;

import com.team254.lib.util.Gamepad;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * Contains the input devices making up the driver control board.
 *
 * @author richard@team254.com (Richard Lin)
*/
public class ControlBoard {
  public Joystick leftStick = new Joystick(Constants.leftJoystickPort.getInt());
  public Joystick rightStick = new Joystick(Constants.rightJoystickPort.getInt());
  public Gamepad gamepad = new Gamepad(Constants.gamepadPort.getInt());
  //public Joystick operatorJoystick = new Joystick(Constants.gamepadPort.getInt()); // may break
  
  public boolean getQuickTurn() {
    return rightStick.getRawButton(2);
  }
  
  public boolean getHighGear() {
    return !rightStick.getRawButton(3);
  }
  
/*
  public int getIntakePositionSwitch() {
    boolean axis = (operatorJoystick.getRawAxis(4) < 0.0);
    boolean button = operatorJoystick.getRawButton(12);
    if (axis) {
      return 1; // up
    }
    if (button) {
      return -1; // down
    }
    return 0; // floating
  }
  
  public boolean getAutonSelectButtonState() {
    return operatorJoystick.getRawButton(Constants.autonSelectControlPort.getInt());
  }

  public JoystickButton getAutonSelectButton() {
    return new JoystickButton(operatorJoystick, Constants.autonSelectControlPort.getInt());
  }
  
  public boolean getUnjamButtonState() {
    return operatorJoystick.getRawButton(Constants.unjamControlPort.getInt());
  }

  public JoystickButton getUnjamButton() {
    return new JoystickButton(operatorJoystick, Constants.unjamControlPort.getInt());
  }
  
  public boolean getShootButtonState() {
    return operatorJoystick.getRawButton(Constants.shootControlPort.getInt());
  }

  public JoystickButton getShootButton() {
    return new JoystickButton(operatorJoystick, Constants.shootControlPort.getInt());
  }
  
  public boolean getAutoShootButtonState() {
    return operatorJoystick.getRawButton(Constants.autoShootControlPort.getInt());
  }

  public JoystickButton getAutoShootButton() {
    return new JoystickButton(operatorJoystick, Constants.autoShootControlPort.getInt());
  }

  public boolean getIntakeButtonState() {
    return operatorJoystick.getRawButton(Constants.intakeControlPort.getInt());
  }

  public JoystickButton getIntakeButton() {
    return new JoystickButton(operatorJoystick, Constants.intakeControlPort.getInt());
  }

  public boolean getIncreaseButtonState() {
    return operatorJoystick.getRawButton(Constants.increaseControlPort.getInt());
  }

  public JoystickButton getIncreaseButton() {
    return new JoystickButton(operatorJoystick, Constants.increaseControlPort.getInt());
  }

  public boolean getDecreaseButtonState() {
    return operatorJoystick.getRawButton(Constants.decreaseControlPort.getInt());
  }

  public JoystickButton getDecreaseButton() {
    return new JoystickButton(operatorJoystick, Constants.decreaseControlPort.getInt());
  }
  
  public boolean getKeyFarButtonState() {
    return operatorJoystick.getRawButton(Constants.keyFarControlPort.getInt());
  }

  public JoystickButton getKeyFarButton() {
    return new JoystickButton(operatorJoystick, Constants.keyFarControlPort.getInt());
  }
  
  public boolean getKeyCloseButtonState() {
    return operatorJoystick.getRawButton(Constants.keyCloseControlPort.getInt());
  }

  public JoystickButton getKeyCloseButton() {
    return new JoystickButton(operatorJoystick, Constants.keyCloseControlPort.getInt());
  }
  
  public boolean getFarFenderButtonState() {
    return operatorJoystick.getRawButton(Constants.farFenderControlPort.getInt());
  }

  public JoystickButton getFarFenderButton() {
    return new JoystickButton(operatorJoystick, Constants.farFenderControlPort.getInt());
  }
  
  public boolean getFenderButtonState() {
    return operatorJoystick.getRawButton(Constants.fenderControlPort.getInt());
  }

  public JoystickButton getFenderButton() {
    return new JoystickButton(operatorJoystick, Constants.fenderControlPort.getInt());
  }
  */
}