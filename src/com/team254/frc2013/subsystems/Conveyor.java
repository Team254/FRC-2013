package com.team254.frc2013.subsystems;

import com.team254.frc2013.Constants;
import com.team254.frc2013.Messages;
import com.team254.lib.control.PeriodicSubsystem;
import com.team254.lib.util.Listener;
import com.team254.lib.util.Notifier;
import com.team254.lib.util.Util;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

/**
 * Controls the conveyor mechanism.
 *
 * @author richard@team254.com (Richard Lin)
 */
public class Conveyor extends PeriodicSubsystem implements Listener {
  private Talon conveyorMotor = new Talon(Constants.conveyorPort.getInt());
  Timer autoTimer = new Timer();
  boolean doAutoLoad = false;

  public Conveyor() {
    autoTimer.start();
    Notifier.subscribe(Messages.SHOT_TAKEN, this);
  }

  public void setMotor(double power) {
    conveyorMotor.set(Util.limit(power, 1.0));
  }

  protected void initDefaultCommand() {
  }

  public void update() {
    if (autoTimer.get() < 1.0 && doAutoLoad) {
      setMotor(1.0);
    } else if (doAutoLoad) {
      setMotor(0);
      doAutoLoad = false;
    }
  }

  public void receive(int key, double value) {
    if (key == Messages.SHOT_TAKEN) {
      doAutoLoad = true;
      autoTimer.reset();
    }
  }
}
