package com.team254.frc2013;

import com.team254.frc2013.Constants;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 * Contains the buttons and triggers for the eStop operator control joystick.
 *
 * @author richard@team254.com (Richard Lin)
 */
public class OperatorJoystick extends Joystick {
  public ShooterOnSwitch shooterOnSwitch = new ShooterOnSwitch();
  public ControlLoopsSwitch controlLoopsSwitch = new ControlLoopsSwitch();
  public StartHangSwitch startHangSwitch = new StartHangSwitch();
  public IntakeUpSwitch intakeUpSwitch = new IntakeUpSwitch();
  public IntakeDownSwitch intakeDownSwitch = new IntakeDownSwitch();
  public Hang20Switch hang20Switch = new Hang20Switch();
  public Hang30Switch hang30Switch = new Hang30Switch();
  
  public OperatorJoystick(int port) {
    super(port);
  }
  
  public class ShooterOnSwitch extends Button {    
    public boolean get() {
      return getShooterSwitch();
    }
  }
  
  public Button getShooterOnSwitch() {
    return shooterOnSwitch;
  }
  
  public Button getControlLoopsSwitch() {
    return controlLoopsSwitch;
  }
  
  public Button getStartHangSwitch() {
    return startHangSwitch;
  }
  public Button getIntakeUpSwitch() {
    return intakeUpSwitch;
  }
  
  public Button getIntakeDownSwitch() {
    return intakeDownSwitch;
  }
  
  public class ControlLoopsSwitch extends Button {
    public boolean get() {
      return getControlLoopsSwitchState();
    }
  }
  
  public class StartHangSwitch extends Button {
    public boolean get() {
      return getStartHangSwitchState();
    }
  }
  
  public class IntakeUpSwitch extends Button {
    public boolean get() {
      return getIntakePositionSwitch() == 1;
    }
  }
  
  public class IntakeDownSwitch extends Button {
    public boolean get() {
      return getIntakePositionSwitch() == -1;
    }
  }
    
  public class Hang20Switch extends Button {
    public boolean get() {
      return getHang20ButtonState() && !getHang30ButtonState();
    }
  }
  
  public class Hang30Switch extends Button {
    public boolean get() {
      return getHang20ButtonState() && getHang30ButtonState();
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
  
  public boolean getConveyOutButtonState() {
    return this.getRawButton(Constants.conveyOutControlPort.getInt());
  }

  public JoystickButton getConveyOutButton() {
    return new JoystickButton(this, Constants.conveyOutControlPort.getInt());
  }
  
  public boolean getConveyorButtonState() {
    return this.getRawButton(Constants.conveyorControlPort.getInt());
  }

  public JoystickButton getConveyorButton() {
    return new JoystickButton(this, Constants.conveyorControlPort.getInt());
  }
  
  public boolean getIntakeOutButtonState() {
    return this.getRawButton(Constants.intakeOutControlPort.getInt());
  }

  public JoystickButton getIntakeOutButton() {
    return new JoystickButton(this, Constants.intakeOutControlPort.getInt());
  }

  public boolean getIntakeButtonState() {
    return this.getRawButton(Constants.intakeControlPort.getInt());
  }

  public JoystickButton getIntakeButton() {
    return new JoystickButton(this, Constants.intakeControlPort.getInt());
  }

  public boolean getHang30ButtonState() {
    return this.getRawButton(Constants.hang30ControlPort.getInt());
  }

  public JoystickButton getHang30Button() {
    return new JoystickButton(this, Constants.hang30ControlPort.getInt());
  }

  public boolean getHang20ButtonState() {
    return this.getRawButton(Constants.hang10ControlPort.getInt());
  }

  public JoystickButton getHang20Button() {
    return new JoystickButton(this, Constants.hang10ControlPort.getInt());
  }
  
  public boolean getFarButtonState() {
    return this.getRawButton(Constants.farControlPort.getInt());
  }

  public JoystickButton getFarButton() {
    return new JoystickButton(this, Constants.farControlPort.getInt());
  }
  
  public boolean getMiddleButtonState() {
    return this.getRawButton(Constants.middleControlPort.getInt());
  }

  public JoystickButton getMiddleButton() {
    return new JoystickButton(this, Constants.middleControlPort.getInt());
  }
  
  public boolean getCloseButtonState() {
    return this.getRawButton(Constants.closeControlPort.getInt());
  }

  public JoystickButton getCloseButton() {
    return new JoystickButton(this, Constants.closeControlPort.getInt());
  }
  
  public boolean getShootButtonState() {
    return this.getRawButton(Constants.shootControlPort.getInt());
  }

  public JoystickButton getShootButton() {
    return new JoystickButton(this, Constants.shootControlPort.getInt());
  }
  
  public boolean getShooterSwitch() {
    return this.getRawAxis(Constants.shooterOnPort.getInt()) < 0.0;
  }
  
  public boolean getControlLoopsSwitchState() {
    return this.getX() < -0.75;
  }

  public boolean getStartHangSwitchState() {
    return this.getY() < 0.0;
  }
}
