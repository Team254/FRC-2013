package com.team254.frc2013.subsystems;

import com.team254.frc2013.Constants;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Class representing the indexing mechanism for the shooter.
 *
 * @author calebnelson@gmail.com (Caleb Nelson)
 */
public class Indexer extends Subsystem {
  private Solenoid pistonL = new Solenoid(Constants.indexPortL.getInt());
  private Solenoid pistonR = new Solenoid(Constants.indexPortR.getInt());
  
  protected void initDefaultCommand() {    
  }
  
  public void togglePistons() {
    pistonL.set(!pistonL.get());
    pistonR.set(!pistonR.get());
  }
  
  public boolean setPistons(boolean target) {
    pistonL.set(target);
    pistonR.set(target);
    return target;
  }
  
  public boolean getPistons(){
    if (pistonL.get() != pistonR.get()) {
      if (pistonL.get()) {
        pistonL.set(false);
      }
      if (pistonR.get()) {
        pistonR.set(false);
      }
      return false;
    }
    else {
      return pistonL.get();
    }
  }
}
