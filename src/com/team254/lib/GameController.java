/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.lib;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 *
 * @author Richard
 */
public class GameController extends Joystick {
    // Gamepad axis
    private static final int kGamepadAxisLeftStickX = 1;
    private static final int kGamepadAxisLeftStickY = 2;
    private static final int kGamepadAxisShoulder = 3;
    private static final int kGamepadAxisRightStickX = 4;
    private static final int kGamepadAxisRightStickY = 5;
    private static final int kGamepadAxisDpad = 6;

    // Gamepad buttons
    private static final int kGamepadButtonA = 1; // Bottom Button
    private static final int kGamepadButtonB = 2; // Right Button
    private static final int kGamepadButtonX = 3; // Left Button
    private static final int kGamepadButtonY = 4; // Top Button
    private static final int kGamepadButtonShoulderL = 5;
    private static final int kGamepadButtonShoulderR = 6;
    private static final int kGamepadButtonBack = 7;
    private static final int kGamepadButtonStart = 8;
    private static final int kGamepadButtonLeftStick = 9;
    private static final int kGamepadButtonRightStick = 10;
    private static final int kGamepadButtonMode = -1;
    private static final int kGamepadButtonLogitech = -1;
    
    //Gamepad Itself!
    public GameController(int gamepadPort) {
        super(gamepadPort);
    }

    //Sticks
    public double getLeftX() {
        return getRawAxis(kGamepadAxisLeftStickX);
    }

    public double getRightX() {
        return getRawAxis(kGamepadAxisRightStickX);
    }

    public double getLeftY() {
        return getRawAxis(kGamepadAxisLeftStickY);
    }

    public double getRightY(){
        return getRawAxis(kGamepadAxisRightStickY);
    }

    //Buttons
    public boolean getButtonStateA() {
        return getRawButton(kGamepadButtonA);
    }
    public boolean getButtonStateB() {
        return getRawButton(kGamepadButtonB);
    }
    public boolean getButtonStateX() { 
        return getRawButton(kGamepadButtonX);
    }
    public boolean getButtonStateY() {
        return getRawButton(kGamepadButtonY);
    }

    //Buttons
    public JoystickButton getButtonA() {
        return new JoystickButton(this, kGamepadButtonA);
    }
    public JoystickButton getButtonB() {
        return new JoystickButton(this, kGamepadButtonB);
    }
    public JoystickButton getButtonX() {
        return new JoystickButton(this, kGamepadButtonX);
    }
    public JoystickButton getButtonY() {
        return new JoystickButton(this, kGamepadButtonY);
    }
}