package com.team254.frc2013;


/**
 *
 * @author bg
 */
import com.team254.lib.Constant;
import java.io.InputStream;
import java.util.Vector;
public class Constants {
  private Vector constants = new Vector(10,1);

  public static Constant leftMotorPwm = new Constant("leftMotorPwm", 0.0);
  public static Constant rightMotorPwm = new Constant("rightMotorPwm", 0.0);
  public static Constant kI = new Constant("kI", 0.0);
  public static Constant kP = new Constant("kP", 0.0);
  public static Constant kD = new Constant("kD", 0.0);

  public void init() {
    //Will need to add more variables eventually
    constants.addElement(leftMotorPwm);
    constants.addElement(rightMotorPwm);
    constants.addElement(kI);
    constants.addElement(kP);
    constants.addElement(kD);
  }

  public void set(String file) {
    InputStream in = this.getClass().getResourceAsStream(file);
    byte[] buffer = new byte[1000];
    String content = "";
    
    try {
      while(in.read(buffer) != -1) {
        content += new String(buffer);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  
}


   /*String[] modified = myFile.nextLine().split("\\="); //
      
      for(int i = 0; i < constants.size(); ++i) {
        Constant myConst = constants.get(i);
        if(myConst.getName().equals(modified[0].trim())) {
          
          myConst.setVal(Double.parseDouble(modified[1].trim()));
          break;
        } 
      } */

