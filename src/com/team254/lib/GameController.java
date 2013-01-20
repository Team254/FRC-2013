/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.lib;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * 
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

    /**
     * Checks whether Button A is being pressed and returns true if it is.
     */
    public boolean getButtonStateA() {
        return getRawButton(kGamepadButtonA);
    }

    /**
    * Checks whether Button B is being pressed and returns true if it is.
    */
    public boolean getButtonStateB() {
        return getRawButton(kGamepadButtonB);
    }

    /**
    * Checks whether Button X is being pressed and returns true if it is.
          */
    public boolean getButtonStateX() { 
        return getRawButton(kGamepadButtonX);
    }

    /**
    * Checks whether Button Y is being pressed and returns true if it is.
          */
    public boolean getButtonStateY() {
        return getRawButton(kGamepadButtonY);
    }

    /**
     * Returns an object of Button A.
     */
    public JoystickButton getButtonA() {
        return new JoystickButton(this, kGamepadButtonA);
    }

    /**
     * Returns an object of Button B.
     */
    public JoystickButton getButtonB() {
        return new JoystickButton(this, kGamepadButtonB);
    }

    /**
    * Returns an object of Button X.
    */
    public JoystickButton getButtonX() {
        return new JoystickButton(this, kGamepadButtonX);
    }

    /**
    * Returns an object of Button Y.
    */
    public JoystickButton getButtonY() {
        return new JoystickButton(this, kGamepadButtonY);
    }
}