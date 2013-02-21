package com.team254.frc2013.subsystems;

import com.team254.frc2013.Constants;
import com.team254.frc2013.Messages;
import com.team254.lib.control.PeriodicSubsystem;
import com.team254.lib.util.Debouncer;
import com.team254.lib.util.Listener;
import com.team254.lib.util.Notifier;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Class representing the indexing mechanism for the shooter.
 *
 * @author calebnelson@gmail.com (Caleb Nelson)
 */
public class Indexer extends PeriodicSubsystem implements Listener {
  private Solenoid piston = new Solenoid(Constants.indexerPort.getInt());
  Debouncer debouncer = new Debouncer(.125);
  AnalogChannel discSensor = new AnalogChannel(Constants.discSensorPort.getInt());
  
  public Indexer() {
    Notifier.subscribe(Messages.SHOT_TAKEN, this);
  }
  
  protected void initDefaultCommand() {    
  }
  
  public void togglePistons() {
    piston.set(!piston.get());
  }
  
  public boolean setPistonDown(boolean target) {
    piston.set(target);
    return target;
  }
  
  public boolean getPistons(){
    return piston.get();
  }

  public void update() {
    boolean hasDisk = debouncer.update(discSensor.getValue() > 150);
    if (piston.get() &&  hasDisk) {
      setPistonDown(false);
    }
  }

  public void receive(int key, double value) {
    if (key == Messages.SHOT_TAKEN) {
      setPistonDown(true);
    }
  }
}
