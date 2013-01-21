package com.team254.frc2013;


/**
 *
 * @author bg
 */
import com.sun.squawk.microedition.io.FileConnection;
import com.team254.lib.Constant;
import com.team254.lib.Util;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Vector;
import javax.microedition.io.Connector;
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
  
  public void update() {
    Vector lines = new Vector();
    DataInputStream theFile;
    FileConnection fc;
    byte[] buffer = new byte[255];
    String content = "";
    
    try { 
      //Reads everything from the file into one String
      fc = (FileConnection)Connector.open("file:///Constants.txt", Connector.READ);
        
      theFile = fc.openDataInputStream();
      while(theFile.read(buffer) != -1) {
        content += new String(buffer);
      }
      
      String[] myLines = Util.split(content, "\n");
      for(int i = 0; i < myLines.length; ++i){
        String[] line = Util.split(myLines[i], "=");
        lines.addElement(line);
      }
      //Removes reference to the object
      myLines = null;
  
      for(int i = 0; i < constants.size(); ++i) { //For every Constant in constants...

        Constant myConst = (Constant)constants.elementAt(i); //Sets a temp variable to the current constant
        for(int f = 0; f < lines.size(); ++f){ //For each character
          String[] myLine = (String[])lines.elementAt(f);
          if(myLine[0].equals(myConst.getName())){
            myConst.setVal(Double.parseDouble(myLine[1]));
            break;
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  } 
  
  
}  