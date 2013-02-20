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
  public static final Constant leftDrivePortA = new Constant("leftDrivePortA", 3);
  public static final Constant leftDrivePortB = new Constant("leftDrivePortB", 4);
  public static final Constant leftDrivePortC = new Constant("leftDrivePortC", 5);
  public static final Constant rightDrivePortA = new Constant("rightDrivePortA", 6);
  public static final Constant rightDrivePortBC = new Constant("rightDrivePortBC", 7);
  
  public static final Constant intakePivotPort = new Constant("intakePivotPort", 1);
  public static final Constant intakePort = new Constant("intakePort", 2);
  public static final Constant conveyorPort = new Constant("conveyorPort", 8);
  public static final Constant frontShooterPort = new Constant("frontShooterPort", 9);
  public static final Constant backShooterPort = new Constant("backShooterPort", 10);
  
  // Solenoids
  public static final Constant pressureSwitch = new Constant("pressureSwitch",  9);
  public static final Constant compressorRelay = new Constant("compressorRelay", 8);

  public static final Constant shifterPort = new Constant("shifterPort", 8);
  public static final Constant ptoPort = new Constant("ptoPort", 7);
  public static final Constant indexerPort = new Constant("indexerPort", 3); // TBD
  public static final Constant shooterLoaderPort = new Constant("shooterLoaderPort", 5); // TBD
  public static final Constant shooterAnglePort = new Constant("shooterAnglePort", 6);
  public static final Constant hangerPort = new Constant("hangerPort", 4); // TBD
  
  // ControlBboard mappings
  public static final Constant autonSelectControlPort = new Constant("autonSelectControlPort",11);
  public static final Constant unjamControlPort = new Constant("unjamControlPort", 10);
  public static final Constant shootControlPort = new Constant("shootControlPort", 9);
  public static final Constant autoShootControlPort = new Constant("autoShootControlPort", 8);
  public static final Constant intakeControlPort = new Constant("intakeControlPort", 7);
  public static final Constant increaseControlPort = new Constant("increaseControlPort", 6);
  public static final Constant decreaseControlPort = new Constant("decreaseControlPort", 5);
  public static final Constant keyFarControlPort = new Constant("keyFarControlPort", 4);
  public static final Constant keyCloseControlPort = new Constant("keyCloseControlPort", 3);
  public static final Constant farFenderControlPort = new Constant("farFenderControlPort", 2);
  public static final Constant fenderControlPort= new Constant("fenderControlPort", 1);
  
  // Sensor mappings
  // Drive encoders
  public static final Constant leftEncoderPortA = new Constant("leftEncoderPortA", 1);
  public static final Constant leftEncoderPortB = new Constant("leftEncoderPortB", 2);
  public static final Constant rightEncoderPortA = new Constant("rightEncoderPortA", 3);
  public static final Constant rightEncoderPortB = new Constant("rightEncoderPortB", 4);
  
  // Shooter encoders
  public static final Constant frontEncoderPort = new Constant("frontEncoderPort", 5); // TBD
  public static final Constant backEncoderPort = new Constant("backEncoderPort", 6); // TBD
  public static final Constant gyroPort = new Constant("gyroPort", 1);
  
  // Intake encoder
  public static final Constant intakeEncoderPortA = new Constant("intakeEncoderPortA", 7); // TBD
  public static final Constant intakeEncoderPortB = new Constant("intakeEncoderPortB", 8);
  
  // Drive tuning
  public static final Constant sensitivityHigh = new Constant("sensitivityHigh", .6);
  public static final Constant sensitivityLow = new Constant("sensitivityLow", .5);
  
  public static final Constant driveStraightKP = new Constant("driveStraightKP", 1.0/26.0);
  public static final Constant driveStraightKI = new Constant("driveStraightKI", .0005);
  public static final Constant driveStraightKD = new Constant("driveStraightKD", 0.0);
  
  public static final Constant driveTurnKP = new Constant("driveTurnKP", 0.0);
  public static final Constant driveTurnKI = new Constant("driveTurnKI", 0.0);
  public static final Constant driveTurnKD = new Constant("driveTurnKD", 0.0);
  
  public static final Constant hangerKP = new Constant("hangerKP", 0.0);
  public static final Constant hangerKI = new Constant("hangerKI", 0.0);
  public static final Constant hangerKD = new Constant("hangerKD", 0.0);
  
  public static final Constant intakeKP = new Constant("intakeKP", 0.0);
  public static final Constant intakeKI = new Constant("intakeKI", 0.0);
  public static final Constant intakeKD = new Constant("intakeKD", 0.0);
  
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
