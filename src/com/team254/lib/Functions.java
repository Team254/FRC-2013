package com.team254.lib;

public class Functions {
    // Sine Wave
    public static double SineWave(double timeSec, double periodSec, double amplitude) {
        return Math.sin(timeSec * 2 * Math.PI / periodSec) * amplitude;
    }
    
    // Square Wave
    public static double SquareWave(double timeSec, double periodSec, double amplitude) {
        int periodMs = (int)(periodSec * 1000);
        int incrementTimeMs = (int)(timeSec * 1000) % periodMs;
        if (incrementTimeMs < periodMs / 2) {
            return amplitude;
        } 
        else {
            return -amplitude;
        }
    }
}