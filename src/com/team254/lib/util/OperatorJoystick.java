package com.team254.lib.util;

import com.team254.frc2013.Constants;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 * Contains the buttons and triggers for the eStop operator control joystick.
 *
 * @author richard@team254.com (Richard Lin)
 */
public class OperatorJoystick extends Joystick {
  public PtoSwitch ptoSwitch = new PtoSwitch();
  public ControlLoopsSwitch controlLoopsSwitch = new ControlLoopsSwitch();
  public BrakeSwitch brakeSwitch = new BrakeSwitch();
  public IntakeUpSwitch intakeUpSwitch = new IntakeUpSwitch();
  public IntakeDownSwitch intakeDownSwitch = new IntakeDownSwitch();
  
  public OperatorJoystick(int port) {
    super(port);
  }
  
  public class PtoSwitch extends Trigger {    
    public boolean get() {
      return getShooterSwitch();
    }
  }
  
  public class ControlLoopsSwitch extends Trigger {
    public boolean get() {
      return getControlLoopsSwitch();
    }
  }
  
  public class BrakeSwitch extends Trigger {
    public boolean get() {
      return getBrakeSwitch();
    }
  }
  
  public class IntakeUpSwitch extends Trigger {
    public boolean get() {
      return getIntakePositionSwitch() == 1;
    }
  }
  
  public class IntakeDownSwitch extends Trigger {
    public boolean get() {
      return getIntakePositionSwitch() == -1;
    }
  }
  
  public int getIntakePositionSwitch() {
    boolean axis = (this.getRawAxis(Constants.intakeUpPort.getInt()) < 0.0);
    boolean button = this.getRawButton(Constants.intakeDownPort.getInt());
    if (axis) {
      return 1; // up
    }
    if (button) {
      return -1; // down
    }
    return 0; // floating
  }
  
  public boolean getAutonSelectButtonState() {
    return this.getRawButton(Constants.autonSelectControlPort.getInt());
  }

  public JoystickButton getAutonSelectButton() {
    return new JoystickButton(this, Constants.autonSelectControlPort.getInt());
  }
  
  public boolean getUnjamButtonState() {
    return this.getRawButton(Constants.unjamControlPort.getInt());
  }

  public JoystickButton getUnjamButton() {
    return new JoystickButton(this, Constants.unjamControlPort.getInt());
  }
  
  public boolean getShootButtonState() {
    return this.getRawButton(Constants.shootControlPort.getInt());
  }

  public JoystickButton getShootButton() {
    return new JoystickButton(this, Constants.shootControlPort.getInt());
  }
  
  public boolean getAutoShootButtonState() {
    return this.getRawButton(Constants.autoShootControlPort.getInt());
  }

  public JoystickButton getAutoShootButton() {
    return new JoystickButton(this, Constants.autoShootControlPort.getInt());
  }

  public boolean getIntakeButtonState() {
    return this.getRawButton(Constants.intakeControlPort.getInt());
  }

  public JoystickButton getIntakeButton() {
    return new JoystickButton(this, Constants.intakeControlPort.getInt());
  }

  public boolean getIncreaseButtonState() {
    return this.getRawButton(Constants.increaseControlPort.getInt());
  }

  public JoystickButton getIncreaseButton() {
    return new JoystickButton(this, Constants.increaseControlPort.getInt());
  }

  public boolean getDecreaseButtonState() {
    return this.getRawButton(Constants.decreaseControlPort.getInt());
  }

  public JoystickButton getDecreaseButton() {
    return new JoystickButton(this, Constants.decreaseControlPort.getInt());
  }
  
  public boolean getKeyFarButtonState() {
    return this.getRawButton(Constants.keyFarControlPort.getInt());
  }

  public JoystickButton getKeyFarButton() {
    return new JoystickButton(this, Constants.keyFarControlPort.getInt());
  }
  
  public boolean getKeyCloseButtonState() {
    return this.getRawButton(Constants.keyCloseControlPort.getInt());
  }

  public JoystickButton getKeyCloseButton() {
    return new JoystickButton(this, Constants.keyCloseControlPort.getInt());
  }
  
  public boolean getFarFenderButtonState() {
    return this.getRawButton(Constants.farFenderControlPort.getInt());
  }

  public JoystickButton getFarFenderButton() {
    return new JoystickButton(this, Constants.farFenderControlPort.getInt());
  }
  
  public boolean getFenderButtonState() {
    return this.getRawButton(Constants.fenderControlPort.getInt());
  }

  public JoystickButton getFenderButton() {
    return new JoystickButton(this, Constants.fenderControlPort.getInt());
  }
  
  public boolean getShooterSwitch() {
    return this.getRawAxis(Constants.shooterOnPort.getInt()) < 0.0;
  }
  
  public boolean getControlLoopsSwitch() {
    return this.getX() < -0.75;
  }

  public boolean getBrakeSwitch() {
    return this.getY() < 0.0;
  }
}
