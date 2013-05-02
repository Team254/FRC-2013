package com.team254.frc2013;

import com.team254.lib.control.StateSpaceGains;

public class WristGains {
  static public StateSpaceGains[] getGains() {
    return new StateSpaceGains[] {
      new StateSpaceGains(
        new double[] {1.000000, 0.008884, 0.000000, 0.785511},  // A
        new double[] {0.000467, 0.089853},                      // B
        new double[] {1.000000, 0.000000},                      // C
        new double[] {0.000000},                                // D
        new double[] {1.645511, 57.625693},                     // L
        new double[] {43.404005, 2.172731},                     // K
        new double[] {12.000000},                               // Umax
        new double[] {-12.000000}),                             // Umin
      };
  }
}
