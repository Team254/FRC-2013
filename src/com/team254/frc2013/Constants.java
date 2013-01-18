package com.team254.frc2013;


/**
 *
 * @author bg
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import com.team254.lib.Constant;
public class Constants {
  private ArrayList<Constant> constants = new ArrayList<Constant>();

  public static Constant leftMotorPwm = new Constant("leftMotorPwm", 0.0);
  public static Constant rightMotorPwm = new Constant("rightMotorPwm", 0.0);
  public static Constant kI = new Constant("kI", 0.0);
  public static Constant kP = new Constant("kP", 0.0);
  public static Constant kD = new Constant("kD", 0.0);

  public void init() {
    //Will need to add more variables eventually
    constants.add(leftMotorPwm);
    constants.add(rightMotorPwm);
    constants.add(kI);
    constants.add(kP);
    constants.add(kD);
  }

  public void set(String file) {
    Scanner myFile = new Scanner(new File(file));
    while(myFile.hasNextLine()) {
      String[] modified = myFile.nextLine().split("\\="); //
      
      for(int i = 0; i < constants.size(); ++i) {
        Constant myConst = constants.get(i);
        if(myConst.getName().equals(modified[0].trim())) {
          
          myConst.setVal(Double.parseDouble(modified[1].trim()));
          break;
        } 
      }

    }    
  }
  
}

