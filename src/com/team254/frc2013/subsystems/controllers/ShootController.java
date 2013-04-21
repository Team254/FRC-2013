/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team254.frc2013.subsystems.controllers;

import com.team254.frc2013.subsystems.Conveyor;
import com.team254.frc2013.subsystems.Intake;
import com.team254.frc2013.subsystems.Shooter;
import com.team254.lib.control.PeriodicSubsystem;
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
  public boolean wantIntake = false;
  public boolean wantExhaust = false;
  public boolean wantRapidFire = false;
  public boolean wantShoot = false;
  public boolean wantManualIndex = false;
  public int shotCount = 0;
  boolean firstRun = true;

  public ShootController(Shooter s, Conveyor c, Intake i) {
    this.s = s;
    this.c = c;
    this.i = i;
    stateTimer.start();
  }

  private boolean timedOut(double time) {
    return stateTimer.get() > time;
  }

  public void update() {
    boolean wantIndexerUp = wantManualIndex;
    boolean wantExtend = false;
    double intake = 0;
    switch (state) {
      case IDLE:
        if (wantShoot && s.isOn()) {
          if (wantRapidFire) {
            state = LOAD_DISC_RAPID;
          } else {
            state = SHOOT_GO_UP;
          }
        }

        if (wantIntake) {
          state = INTAKE;
        }
        if (wantExhaust) {
          state = EXHAUST;
        }

        break;


      case INTAKE:
        intake = 1;
        if (!wantIntake) {
          state = IDLE;
        }
        break;

      case EXHAUST:
        intake = -1;
        if (!wantExhaust) {
          state = IDLE;
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
        } else if (timedOut(1.0)) {
          state = FIX_INDEXER_OUT;
        }
        break;

      case SHOOT_WAIT_FOR_SPEED:
        wantIndexerUp = true;
        if (s.onSpeedTarget() || timedOut(.5)) {
          state = SHOOT_EXTEND;
        }
        break;

      case SHOOT_EXTEND:
        if (firstRun)
          shotCount++;
        wantIndexerUp = true;
        wantExtend = true;
        if (timedOut(.25)) {
          state = SHOOT_GO_DOWN;
        }
        break;

      case SHOOT_GO_DOWN:
        if (s.isIndexerSensedDown()) {
          state = IDLE;
        } else if (timedOut(.5)) {
          state = FIX_INDEXER_OUT;
        }
        break;

      case FIX_INDEXER_OUT:
        intake = -1;
        s.setIndexerUp(false);
        if (s.isIndexerSensedDown() || timedOut(.4)) {
          state = FIX_INDEXER_IN;
        }
        break;

      case FIX_INDEXER_IN:
        intake = 1;
        s.setIndexerUp(false);
        if (s.isIndexerLoaded() || timedOut(.5)) {
          state = IDLE;
        }
        break;
    }

    if (wantExtend) {
      s.extend();
    } else {
      s.retract();
    }

    s.setIndexerUp(wantIndexerUp);

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
}
