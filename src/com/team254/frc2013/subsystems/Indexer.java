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
  private Solenoid piston = new Solenoid(Constants.indexerPort.getInt());
  
  protected void initDefaultCommand() {    
  }
  
  public void togglePistons() {
    piston.set(!piston.get());
  }
  
  public boolean setPistons(boolean target) {
    piston.set(target);
    return target;
  }
  
  public boolean getPistons(){
    return piston.get();
  }
}
