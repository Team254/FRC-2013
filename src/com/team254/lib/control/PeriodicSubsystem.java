package com.team254.lib.control;

import com.team254.frc2013.ControlUpdater;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Represents a subsystem that can be updated periodically.
 *
 * @author Richard
 */
public abstract class PeriodicSubsystem extends Subsystem implements Updatable {
  
  public PeriodicSubsystem() {
    ControlUpdater.getInstance().add(this);
  }
  
  public abstract void update();
}
