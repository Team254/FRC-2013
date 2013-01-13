/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.frc2013.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author tombot
 */
public class WaitCommand extends Command {
    Timer t = new Timer();
    double timeout;
    
    public WaitCommand(double seconds) {
        timeout = seconds;
    }
    protected void initialize() {
        t.start();
    }

    protected void execute() {
        System.out.println("Waiting...curr: " + t.get() + ", max: " + timeout);
    }

    protected boolean isFinished() {
        return t.get() >= timeout;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
    
}
