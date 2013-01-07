/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.lib.debug;

import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Richard
 */
public class ThrottledPrinter {
   
    private double period;
    private double lastPrint = 0;

    public ThrottledPrinter(double seconds) {
        period = seconds;
    }
    
    public void print(String text) {
        if (Timer.getFPGATimestamp() - lastPrint >= period) {
            System.out.println(text);
            lastPrint = Timer.getFPGATimestamp();
        }   
    }
}
