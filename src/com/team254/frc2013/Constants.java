package com.team254.frc2013;

import com.sun.squawk.microedition.io.FileConnection;
import com.team254.lib.util.Util;
import java.io.DataInputStream;
import java.util.Vector;
import javax.microedition.io.Connector;

/**
 * Manages constant values used everywhere in the robot code.
 * Variables can be declared here with default values and overridden with a text file uploaded to
 * the robot's file system.
 *
 * @author brandon.gonzalez.451@gmail.com (Brandon Gonzalez)
 */
public class Constants {
  private static final Vector constants = new Vector();
  private static final String CONSTANTS_FILE_PATH = "Constants.txt";

  // Declare the constants and their default values here.
  // Control board mappings
  public static final Constant leftJoystickPort = new Constant("leftJoystickPort", 1);
  public static final Constant rightJoystickPort = new Constant("rightJoystickPort", 2);
  public static final Constant gamepadPort = new Constant("gamepadPort", 3);

  // Speed controller mappings
  public static final Constant leftDrivePortA = new Constant("leftDrivePortA", 6);
  public static final Constant leftDrivePortB = new Constant("leftDrivePortB", 7);
  public static final Constant leftDrivePortC = new Constant("leftDrivePortC", 8);
  public static final Constant rightDrivePortA = new Constant("rightDrivePortA", 3);
  public static final Constant rightDrivePortB = new Constant("rightDrivePortB", 4);
  public static final Constant rightDrivePortC = new Constant("rightDrivePortC", 5);
  
  public static final Constant frontShooterPort = new Constant("frontShooterPort", 9); // TBD
  public static final Constant frontShooterPortB = new Constant("frontShooterPortB", 2); // TBD
  public static final Constant backShooterPort = new Constant("backShooterPort", 10); // TBD
  public static final Constant intakePort = new Constant("intakePort", 1); // TBD
  public static final Constant intakePivotPort = new Constant("intakePivotPort", 10); //TBD
  
  // Solenoids
  public static final Constant pressureSwitch = new Constant("pressureSwitch",  14);
  public static final Constant compressorRelay = new Constant("compressorRelay", 8);
  public static final Constant shifterPort = new Constant("shifterPort", 8);
  public static final Constant indexPortL = new Constant("indexPortL", 1); // TBD
  public static final Constant indexPortR = new Constant("indexPortR", 2); // TBD
  public static final Constant shooterLoaderPort = new Constant("shooterLoaderPort", 3); // TBD
  public static final Constant hangerPortA = new Constant("hangerPortA", 4); // TBD
  public static final Constant hangerPortB = new Constant("hangerPortB", 5); // TBD
  
  // Sensor mappings
  public static final Constant leftEncoderPortA = new Constant("leftEncoderPortA", 1);
  public static final Constant leftEncoderPortB = new Constant("leftEncoderPortB", 2);
  public static final Constant rightEncoderPortA = new Constant("rightEncoderPortA", 3);
  public static final Constant rightEncoderPortB = new Constant("rightEncoderPortB", 4);
  public static final Constant frontEncoderPortA = new Constant("frontEncoderPortA", 6); // TBD
  public static final Constant frontEncoderPortB = new Constant("frontEncoderPortB", 7); // TBD
  public static final Constant backEncoderPortA = new Constant("backEncoderPortA", 8); // TBD
  public static final Constant backEncoderPortB = new Constant("backEncoderPortB", 9); // TBD
  public static final Constant gyroPort = new Constant("gyroPort", 1);
  
  // Drive tuning
  public static final Constant sensitivityHigh = new Constant("sensitivityHigh", .6);
  public static final Constant sensitivityLow = new Constant("sensitivityLow", .5);
  
  public static final Constant driveStraightKP = new Constant("driveStraightKP", 1.0/26.0);
  public static final Constant driveStraightKI = new Constant("driveStraightKI", .0005);
  public static final Constant driveStraightKD = new Constant("driveStraightKD", 0.0);
  
  public static final Constant driveTurnKP = new Constant("driveTurnKP", 0.0);
  public static final Constant driveTurnKI = new Constant("driveTurnKI", 0.0);
  public static final Constant driveTurnKD = new Constant("driveTurnKD", 0.0);
  
  static {
    // Set any overridden constants from the file on startup.
    readConstantsFromFile();
  }

  // Prevent instantiation of this class, as it should only be used statically.
  private Constants() {
  }

  /**
   * Reads the constants file and overrides the values in this class for any constants it contains.
   */
  public static void readConstantsFromFile() {
    DataInputStream constantsStream;
    FileConnection constantsFile;
    byte[] buffer = new byte[255];
    String content = "";

    try {
      // Read everything from the file into one string.
      constantsFile = (FileConnection)Connector.open("file:///" + CONSTANTS_FILE_PATH,
                                                     Connector.READ);
      constantsStream = constantsFile.openDataInputStream();
      while (constantsStream.read(buffer) != -1) {
        content += new String(buffer);
      }
      constantsStream.close();
      constantsFile.close();

      // Extract each line separately.
      String[] lines = Util.split(content, "\n");
      for (int i = 0; i < lines.length; i++) {
        // Extract the key and value.
        String[] line = Util.split(lines[i], "=");
        if (line.length != 2) {
          System.out.println("Error: invalid constants file line: " +
                            (lines[i].length() == 0 ? "(empty line)" : lines[i]));
          continue;
        }

        boolean found = false;
        // Search through the constants until we find one with the same name.
        for (int j = 0; j < constants.size(); j++) {
          Constant constant = (Constant)constants.elementAt(j);
          if (constant.getName().compareTo(line[0]) == 0) {
            System.out.println("Setting " + constant.getName() + " to " + Double.parseDouble(line[1]));
            constant.setVal(Double.parseDouble(line[1]));
            found = true;
            break;
          }
        }

        if (!found)
          System.out.println("Error: the constant doesn't exist: " + lines[i]);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Handles an individual value used in the Constants class.
   */
  public static class Constant {
    private String name;
    private double value;

    public Constant(String name, double value) {
      this.name = name;
      this.value = value;
      constants.addElement(this);
    }

    public String getName(){
      return name;
    }

    public double getDouble() {
      return value;
    }

    public int getInt() {
      return (int)value;
    }

    public void setVal(double value){
      this.value = value;
    }

    public String toString(){
      return name + ": " + value;
    }
  } 
}
