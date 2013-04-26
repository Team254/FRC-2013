/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.frc2013.subsystems.controllers;

import com.team254.frc2013.subsystems.Conveyor;
import com.team254.frc2013.subsystems.Intake;
import com.team254.frc2013.subsystems.Shooter;
import com.team254.lib.control.PeriodicSubsystem;
import com.team254.lib.util.ThrottledPrinter;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author tombot
 */
public class ShootController extends PeriodicSubsystem {

  Shooter s;
  Conveyor c;
  Intake i;
  int state = IDLE;
  int oldState = state;
  Timer stateTimer = new Timer();
  // States
  static final int IDLE = 0;
  static final int INTAKE = 1;
  static final int EXHAUST = 2;
  static final int SHOOT_GO_UP = 3;
  static final int SHOOT_WAIT_FOR_SPEED = 4;
  static final int FIX_INDEXER_OUT = 5;
  static final int FIX_INDEXER_IN = 6;
  static final int SHOOT_EXTEND = 7;
  static final int SHOOT_GO_DOWN = 8;
  static final int LOAD_DISC_RAPID = 9;
  static final int FEED_DISC = 10;
  static final int MANUAL_INDEX = 11;
  static final int MANUAL_INDEX_DOWN_OUT = 12;
  static final int MANUAL_INDEX_DOWN_IN = 13;
  static final int START_FED_SHOT = 14;
  public boolean wantIntake = false;
  public boolean wantExhaust = false;
  public boolean wantRapidFire = false;
  public boolean wantShoot = false;
  public boolean wantManualIndex = false;
  public boolean wantFedShoot = false;
  public boolean wantIntakeUp = false;
  public boolean wantIntakeDown = false;
  public boolean dontTouchIntake = false;
  public boolean wantForceFloor = false;
  public int shotCount = 0;
  boolean firstRun = true;
  public boolean wantFeed = false;
  int rapidFireStopShotCount = 4;
  int stopShotCount = 1;
  private boolean firstShot = true;
  public boolean wantControlOverride = false;

  public ShootController(Shooter s, Conveyor c, Intake i) {
    this.s = s;
    this.c = c;
    this.i = i;
    stateTimer.start();
  }

  private boolean timedOut(double time) {
    return stateTimer.get() > time;
  }

  private boolean controlTimedOut(double time) {
    return wantControlOverride && stateTimer.get() > time;
  }
  public static final int FLOOR = 0;
  public static final int STOWED = 1;
  public static final int HANG = 2;
  public static final int FORCE_FLOOR = 3;
  private int wristState = FLOOR;
  public boolean wantHang = false;
  ThrottledPrinter p = new ThrottledPrinter(.25);

  private void updateAngle() {

    if (wantIntakeDown) {
      wristState = FLOOR;
    } else if (wantIntakeUp) {
      wristState = STOWED;
    } else if (wantHang) {
      wristState = HANG;
    }
    double angle = 0;
    switch (wristState) {
      case FLOOR:
        angle = 0;
        if (wantForceFloor) {
          angle = -.06;
        } else if (wantIntake) {
          angle = -.03;
        }
        break;
      case STOWED:
        if (s.isOn() && s.isHighAngle()) {
          angle = 1.65;
        } else if (s.isOn() && !s.isHighAngle()) {
          angle = 1.35;
        } else {
          angle = 2.0;
        }
        break;
      case HANG:
        angle = 1.34;
        break;

    }
    // stow 2.0
    // far 1.36
    // high 1.65
    i.setAngle(angle);
  }

  public void update() {
    boolean wantIndexerUp = false || firstShot;
    boolean wantExtend = false;
    double intake = 0;
    double angle = i.getAngle();
    if (!wantRapidFire) {
      rapidFireStopShotCount = shotCount + 4;
    }
    if (!wantShoot) {
      stopShotCount = shotCount + 1;
    }

    switch (state) {
      case IDLE:

        if (wantIntake) {
          state = INTAKE;
        }

        if (wantExhaust) {
          state = EXHAUST;
        }

        if (wantManualIndex) {
          state = MANUAL_INDEX;
        }


        if (wantShoot && s.isOn()) {
          if (wantRapidFire && shotCount <= rapidFireStopShotCount) {
            if (rapidFireStopShotCount - shotCount < 1) {
              System.out.println("setting wrist to stow");
              wristState = STOWED;
            }
            state = LOAD_DISC_RAPID;
          } else if (stopShotCount > shotCount) {
            state = SHOOT_GO_UP;
          }
        }
        if (wantFedShoot && s.isOn()) {
          state = START_FED_SHOT;
        }
        if (wantFeed) {
          state = FEED_DISC;
        }
        break;


      case INTAKE:
        intake = 1;
        wristState = FLOOR;
        if (!wantIntake) {
          state = IDLE;
        }
        if (wantManualIndex) {
          state = MANUAL_INDEX;
        }
        break;

      case EXHAUST:
        wristState = FLOOR;
        intake = -1;
        if (!wantExhaust) {
          state = IDLE;
        }
        if (wantManualIndex) {
          state = MANUAL_INDEX;
        }
        break;

      case LOAD_DISC_RAPID:
        intake = 1;
        if (s.isIndexerLoaded()) {
          state = SHOOT_GO_UP;
        }
        if (!wantRapidFire) {
          state = IDLE;
        }
        break;

      case SHOOT_GO_UP:
        wantIndexerUp = true;
        if (s.isIndexerSensedUp()) {
          state = SHOOT_WAIT_FOR_SPEED;
        } else if (controlTimedOut(1.0)) {
          state = FIX_INDEXER_OUT;
        }
        break;

      case SHOOT_WAIT_FOR_SPEED:
        wantIndexerUp = true;
        if (s.onSpeedTarget() || controlTimedOut(1.5)) {
          state = SHOOT_EXTEND;
        }
        break;

      case SHOOT_EXTEND:
        wantIndexerUp = true;
        wantExtend = true;
        wantShoot = false;
        System.out.println("r " +s.getRpm() + " " + (s.getRpm() < (s.getRpmGoal() - 2500)) + " " + (s.getRpmGoal() - 2500));
        if ((s.getRpm() < (s.getRpmGoal() - 2500)) || timedOut(.5)) {
          System.out.println("SHOT " + s.getRpmGoal() + " , " + s.getRpm() + " , " + stateTimer.get());
          shotCount++;
          state = SHOOT_GO_DOWN;
        }
        break;

      case MANUAL_INDEX_DOWN_OUT:
        intake = -.15;
        if (timedOut(.1)) {
          state = MANUAL_INDEX_DOWN_IN;
        }
        break;

      case MANUAL_INDEX_DOWN_IN:
        intake = 0;
        if (timedOut(.1) || s.isIndexerLoaded()) {
          state = IDLE;
        }
        break;
      case SHOOT_GO_DOWN:
        if (s.isIndexerSensedDown()) {
          System.out.println("sensed down;");
          state = IDLE;
        } else if (timedOut(.4)) {
          state = FIX_INDEXER_OUT;
        }
        break;

      case FIX_INDEXER_OUT:
        intake = -1;
        if (s.isIndexerSensedDown() || timedOut(.4)) {
          state = FIX_INDEXER_IN;
        }
        break;

      case FIX_INDEXER_IN:
        intake = 1;
        if (s.isIndexerLoaded() || timedOut(.5)) {
          state = IDLE;
        }
        break;

      case FEED_DISC:
        intake = 1;
        if (s.isIndexerLoaded()) {
          state = IDLE;
          if (wantShoot) {
            state = SHOOT_GO_UP;
          }
          wantFeed = false;
        }
        if (wantIntake || wantExhaust) {
          state = IDLE;
          wantFeed = false;
        }
        break;

      case MANUAL_INDEX:
        wantIndexerUp = true;
        if (!wantManualIndex) {
          state = MANUAL_INDEX_DOWN_OUT;
        }
        if (wantShoot) {
          state = SHOOT_GO_UP;
        }
        break;

      case START_FED_SHOT:

        if (firstShot) {
          System.out.println("first shot");
          wantFedShoot = false;
          state = SHOOT_GO_UP;
        } else {
          intake = 1;
          if (s.isIndexerLoaded()) {
            wantFedShoot = false;
            state = SHOOT_GO_UP;
          } else if (timedOut(1)) {
            wantFedShoot = false;
            state = IDLE;
            shotCount++;
          }
        }
        break;
    }

    if (shotCount > 0 || wantManualIndex) {
      firstShot = false;
    }

    if (wantExtend) {
      s.extend();
    } else {
      s.retract();
    }

    s.setIndexerUp(wantIndexerUp);

    if (wantIndexerUp && intake > 0) {
      intake = 0;
    }

    updateAngle();

    c.setMotor(intake);
    i.setIntakePower(intake);

    firstRun = false;
    if (state != oldState) {
      stateTimer.reset();
      firstRun = true;
    }
    oldState = state;
  }

  protected void initDefaultCommand() {
  }

  public void reset() {
    wantShoot = false;
    shotCount = 0;
    firstShot = true;
    wantManualIndex = false;
    wantFedShoot = false;
    wantIntake = false;
    wantExhaust = false;
  }
}
