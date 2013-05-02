package com.team254.frc2013;

import com.team254.lib.control.StateSpaceGains;

public class ShooterGains {
  static public StateSpaceGains[] getGains() {
    return new StateSpaceGains[] {
      new StateSpaceGains(
        new double[] {1.000000, 0.000000, 3.577929, 0.963936},  // A
        new double[] {1.000000, 0.000000},                      // B
        new double[] {0.000000, 1.000000},                      // C
        new double[] {0.000000},                                // D
        new double[] {0.013975, 0.413936},                      // L
        new double[] {0.943936, 0.047502},                      // K
        new double[] {12.000000},                               // Umax
        new double[] {-2.000000}),                              // Umin
      };
  }
}
