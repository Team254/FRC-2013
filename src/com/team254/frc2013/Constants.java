package com.team254.frc2013;

import com.sun.squawk.microedition.io.FileConnection;
import com.team254.lib.Util;
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
  public static final Constant leftDrivePortA = new Constant("leftDrivePortA", 5);
  public static final Constant leftDrivePortB = new Constant("leftDrivePortB", 4);
  public static final Constant rightDrivePortA = new Constant("rightDrivePortA", 6);
  public static final Constant rightDrivePortB = new Constant("rightDrivePortB", 7);

  // Sensor mappings
  public static final Constant leftEncoderPortA = new Constant("leftEncoderPortA", 2);
  public static final Constant leftEncoderPortB = new Constant("leftEncoderPortB", 3);
  public static final Constant rightEncoderPortA = new Constant("rightEncoderPortA", 4);
  public static final Constant rightEncoderPortB = new Constant("rightEncoderPortB", 5);

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
          System.out.println("Error: invalid constants file line: " + lines[i]);
          continue;
        }

        // Search through the constants until we find one with the same name.
        for (int j = 0; j < constants.size(); j++) {
          Constant constant = (Constant)constants.elementAt(j);
          if (constant.getName().compareTo(line[0]) == 0) {
            constant.setVal(Double.parseDouble(line[1]));
            break;
          }
        }

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
