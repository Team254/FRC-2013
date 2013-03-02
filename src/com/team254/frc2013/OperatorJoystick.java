package com.team254.frc2013;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * Contains the buttons and triggers for the eStop operator control joystick.
 *
 * @author richard@team254.com (Richard Lin)
 */
public class OperatorJoystick extends Joystick {
  public ShooterOnSwitch shooterOnSwitch = new ShooterOnSwitch();
  public ControlLoopsSwitch controlLoopsSwitch = new ControlLoopsSwitch();
  public PtoOnSwitch ptoOnSwitch = new PtoOnSwitch();
  public IntakeUpSwitch intakeUpSwitch = new IntakeUpSwitch();
  public IntakeDownSwitch intakeDownSwitch = new IntakeDownSwitch();

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
    return ptoOnSwitch;
  }

  public class ControlLoopsSwitch extends Button {
    public boolean get() {
      return getControlLoopsSwitchState();
    }
  }

  public class PtoOnSwitch extends Button {
    public boolean get() {
      return getPtoOnSwitchState();
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

  public boolean getHang20ButtonState() {
    return this.getRawButton(Constants.indexControlPort.getInt());
  }

  public JoystickButton getIndexButton() {
    return new JoystickButton(this, Constants.indexControlPort.getInt());
  }

  public boolean getBackPyramidButtonState() {
    return this.getRawButton(Constants.backPyramidControlPort.getInt());
  }

  public JoystickButton getBackPyramidButton() {
    return new JoystickButton(this, Constants.backPyramidControlPort.getInt());
  }

  public boolean getFrontPyramidButtonState() {
    return this.getRawButton(Constants.frontPyramidControlPort.getInt());
  }

  public JoystickButton getFrontPyramidButton() {
    return new JoystickButton(this, Constants.frontPyramidControlPort.getInt());
  }

  public boolean getPyramidGoalButtonState() {
    return this.getRawButton(Constants.pyramidGoalControlPort.getInt());
  }

  public JoystickButton getPyramidGoalButton() {
    return new JoystickButton(this, Constants.pyramidGoalControlPort.getInt());
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

  public boolean getPtoOnSwitchState() {
    return this.getY() < 0.0;
  }
}
