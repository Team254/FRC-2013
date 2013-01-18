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
  
  public String[] strSplit(String myString, String seperator) {
    Vector node = new Vector();
    int index = myString.indexOf(seperator);
    while (index >= 0) {
      node.addElement(myString.substring(0, index));
      myString = myString.substring(index+seperator.length());
      index = myString.indexOf(seperator);
    }
    
    String[] retString = new String[node.size()];
    for(int i = 0; i < node.size(); ++i) {
      retString[i] = node.elementAt(i).toString();
    }
    
    return retString;
  }

  public void set(String file) {
    Vector lines = new Vector(10, 2);
    InputStream in = this.getClass().getResourceAsStream(file);
    byte[] buffer = new byte[1000];
    String content = "";
    
    try {
      //Reads everything from the file into one String
      while(in.read(buffer) != -1) {
        content += new String(buffer);
      }
      
      
      String[] myLines = strSplit(content, "\n");
      for(int i = 0; i<myLines.length; ++i){
        String[] line = strSplit(myLines[i], "=");
        lines.addElement(line);
      }
      //Removes reference to the object
      myLines = null;
      
      for(int i = 0; i < constants.size(); ++i) {
        Constant myConst = (Constant)constants.elementAt(i);
        for(int f = 0; f < lines.size(); ++f){
          String[] myLine = (String[])lines.elementAt(i);
          if(myLine[0].equals(myConst.getName())){
            myConst.setVal(Double.parseDouble(myLine[0]));
            break;
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  
}  