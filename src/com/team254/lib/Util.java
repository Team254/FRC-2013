/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.lib;

/**
 *
 * @author Richard
 */
public class Util {
    
    public static double limit(double v, double limit) {
        return Math.abs(v) < limit ? v : limit * (v < 0? -1 : 1);
    }
    
}
