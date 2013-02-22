package com.team254.frc2013;

import com.team254.frc2013.commands.ConveyorSpeedCommand;
import com.team254.frc2013.commands.HangerHookCommand;
import com.team254.frc2013.commands.IncrementShooterSpeedCommand;
import com.team254.frc2013.commands.IntakeSpeedCommand;
import com.team254.frc2013.commands.SetPtoCommand;
import com.team254.frc2013.commands.ShootCommand;
import com.team254.frc2013.commands.ToggleShooterAngleCommand;

/**
 * Maps operator control buttons to a specified command.
 *
 * @author tom@team254.com (Tom Bottiglieri)
 */
public class OperatorControlHelper {
  
  public static void setupOperationMap(ControlBoard c) {
    c.operatorJoystick.getKeyFarButton().whenPressed(new IncrementShooterSpeedCommand(200,0));
    c.operatorJoystick.getKeyCloseButton().whenPressed(new IncrementShooterSpeedCommand(-200,0));
    c.operatorJoystick.getFarFenderButton().whenPressed(new IncrementShooterSpeedCommand(0,100));
    c.operatorJoystick.getFenderButton().whenPressed(new IncrementShooterSpeedCommand(0,-100));
    
    c.operatorJoystick.getIncreaseButton().whenPressed(new ShootCommand());
    c.operatorJoystick.getDecreaseButton().whenPressed(new ToggleShooterAngleCommand());
    
    c.operatorJoystick.getShootButton().whenPressed(new ConveyorSpeedCommand(1.0));
    c.operatorJoystick.getShootButton().whenReleased(new ConveyorSpeedCommand(0.0));
    c.operatorJoystick.getUnjamButton().whenPressed(new ConveyorSpeedCommand(-1.0));
    c.operatorJoystick.getUnjamButton().whenReleased(new ConveyorSpeedCommand(0.0));
    
    c.operatorJoystick.getAutoShootButton().whenPressed(new IntakeSpeedCommand(1.0));
    c.operatorJoystick.getAutoShootButton().whenReleased(new IntakeSpeedCommand(0.0));
    c.operatorJoystick.getIntakeButton().whenPressed(new IntakeSpeedCommand(-1.0));
    c.operatorJoystick.getIntakeButton().whenReleased(new IntakeSpeedCommand(0.0));
    
    c.operatorJoystick.getAutonSelectButton().whenPressed(new HangerHookCommand(true));
    c.operatorJoystick.getAutonSelectButton().whenReleased(new HangerHookCommand(false));
    
    c.operatorJoystick.ptoSwitch.whenActive(new SetPtoCommand(true));
    c.operatorJoystick.ptoSwitch.whenInactive(new SetPtoCommand(false));
    
    /*
    c.gamepad.getButtonA().whenPressed(new IncrementShooterSpeedCommand(-200,0));
    c.gamepad.getButtonB().whenPressed(new IncrementShooterSpeedCommand(200,0));
    c.gamepad.getButtonX().whenPressed(new IncrementShooterSpeedCommand(0,-100));
    c.gamepad.getButtonY().whenPressed(new IncrementShooterSpeedCommand(0,100));
    
    c.gamepad.getStartButton().whenPressed(new ShootCommand());
    c.gamepad.getBackButton().whenPressed(new ToggleShooterAngleCommand());
    
    c.gamepad.getLeftShoulder().whenPressed(new ConveyorSpeedCommand(-1.0));
    c.gamepad.getLeftShoulder().whenReleased(new ConveyorSpeedCommand(0.0));
    c.gamepad.getRightShoulder().whenPressed(new ConveyorSpeedCommand(1.0));
    c.gamepad.getRightShoulder().whenReleased(new ConveyorSpeedCommand(0.0));
    
    c.gamepad.getLeftStickClick().whenPressed(new IntakeSpeedCommand(-1.0));
    c.gamepad.getLeftStickClick().whenReleased(new IntakeSpeedCommand(0.0));
    c.gamepad.getRightStickClick().whenPressed(new IntakeSpeedCommand(1.0));
    c.gamepad.getRightStickClick().whenReleased(new IntakeSpeedCommand(0.0));
    
    c.gamepad.getLeftTriggerClick().whenPressed(new HangerHookCommand(true));
    c.gamepad.getRightTriggerClick().whenPressed(new HangerHookCommand(false));
    */
  }
}
