/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.frc2013.commands;

/**
 *
 * @author Richard
 */
public class TankDriveCommand extends CommandBase {

    public TankDriveCommand() {
        requires(drive);
    }
    protected void initialize() {
        drive.setMaxSpeed(1.0);
        drive.startEncoders();
    }

    protected void execute() {
        //XXX Joystick.getY() returns negative for forward motion
        drive.driveLR(-oi.leftStick.getY(), -oi.rightStick.getY());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
        drive.setMaxSpeed(1.0);
    }

    protected void interrupted() {
    }
    
}
