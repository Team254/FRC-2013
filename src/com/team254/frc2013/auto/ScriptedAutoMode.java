/*
 * ScriptedAutoMode builds a sequential auto mode from a script file
 */
package com.team254.frc2013.auto;

import com.team254.frc2013.commands.WaitCommand;
import com.sun.squawk.io.BufferedReader;
import com.sun.squawk.microedition.io.FileConnection;
import com.team254.frc2013.commands.ConveyorTimedCommand;
import com.team254.frc2013.commands.DriveDistanceCommand;
import com.team254.frc2013.commands.IndexerCommand;
import com.team254.frc2013.commands.IntakeTimedCommand;
import com.team254.frc2013.commands.ShooterAngleCommand;
import com.team254.frc2013.commands.ShootCommand;
import com.team254.frc2013.commands.ShooterSpeedCommand;
import com.team254.frc2013.commands.TurnAngleCommand;
import com.team254.lib.util.Util;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.util.Vector;
import javax.microedition.io.Connector;

/**
 * Reads commands from a text file and adds them to the autonomous queue
 *
 * @author tom@team254.com (Tom Bottiglieri)
 * @author art.kalb96@gmail.com (Arthur Kalb)
 * @author stephen@team254.com (Stephen Pinkerton)
 * @author jonathanc@team254.com (Jonathan Chang)
 */
public class ScriptedAutoMode extends CommandGroup {

  /**
  * This class functions as a data structure for the parameters
  */
  private class ParamList {
    Vector list = new Vector();

    public void addParam(double p) {
      list.addElement(new Double(p));
    }

    public int length() {
      return list.size();
    }

    /*
    * Accesses a specific element of the ParamList
    * @param i the index to be accessed
    * @return The value in the specific index, or 0.0 if the index does not exist
    */
    public double at(int i) {
      if (i < 0 || i >= list.size())
        return 0.0;
      return ((Double) list.elementAt(i)).doubleValue();
    }
  }

  private String fileName;

  /*
  * Creates the autnomous mode
  * @param filePath the file name which contains the script which will be run
  */
  public ScriptedAutoMode(String filePath) {
    fileName = filePath;
    parse();
  }

  /*
  * Goes through the file line by line and breaks the lines down into runnable commands
  */
  private void parse() {
    DataInputStream file;
    FileConnection fc;
    String url = "file:///" + fileName;

    try {
      //Get the file and reader set up
      FileConnection c = (FileConnection) Connector.open(url);
      BufferedReader buf = new BufferedReader(new InputStreamReader(c.openInputStream()));

      //Initialize other variables for parsing
      String line;

      boolean isParallel = false;
      String cmd = "NULL";

      //Go through the file line by line
      while ((line = buf.readLine()) != null) {
        ParamList params = new ParamList();

        //Detect command type (comment / parallel / sequential)
        String[] cmdParams = Util.split(line, " ");
        if (line.length() <= 1) {
          continue;
        } else if (cmdParams[0].trim().equals("#")) {
          continue;
        } else if (cmdParams[0].trim().equals("P")) {
          isParallel = true;
        } else if (cmdParams[0].trim().equals("S")) {
          isParallel = false;
        }
        System.out.println("Line: " + line);
        cmd = cmdParams[1].trim();
        for (int i = 2; i < cmdParams.length; i++) {
          params.addParam(Double.parseDouble(cmdParams[i].trim()));
        }
        addCommand(cmd, params, isParallel);
        //end processing the line
      }

      c.close();
      System.out.println("DONE!");

    } catch (Exception e) {
      System.out.println("EXCEPTION!");
    }
  }

  /*
  * Simple check for equality
  * @param cmd The suggested command name
  * @param name A possible command
  */
  private boolean checkName(String cmd, String name) {
    return cmd.equalsIgnoreCase(name);
  }

  /*
  * Adds a command to the autonomous queue
  * @param cmd The command which the robot will run
  * @param params The List of parameters
  */
  private void addCommand(String cmd, ParamList params, boolean isParallel) {
    Command c = null;
    //Process command
    System.out.println("Command: " + cmd);
    if (checkName(cmd, "DRIVE")) {
      System.out.println("Params: " + params.at(0) + " "  + params.at(1) + " " + params.at(2));
      c = new DriveDistanceCommand(params.at(0), params.at(1), params.at(2));
    } else if (checkName(cmd, "WAIT")) {
      System.out.println("Params: " + params.at(0));
      c = new WaitCommand(params.at(0));
    } else if (checkName(cmd, "TURN")) {
      System.out.println("Params: " + params.at(0) + " " + params.at(1));
      c = new TurnAngleCommand(params.at(0), params.at(1));
    } else if(checkName(cmd, "INTAKE_TIMED")) {
      System.out.println("Params: " + params.at(0) + " " + params.at(1));
      c = new IntakeTimedCommand(params.at(0), params.at(1));
    } else if(checkName(cmd, "CONVEYOR_TIMED")) {
      System.out.println("Params: " + params.at(0) + " " + params.at(1));
      c = new ConveyorTimedCommand(params.at(0), params.at(1));
    } else if(checkName(cmd, "SHOOTER_ANGLE")) {
      System.out.println("Params: " + params.at(0));
      c = new ShooterAngleCommand(params.at(0));
    } else if(checkName(cmd, "SHOOTER_SPEED")) {
      System.out.println("Params: " + params.at(0));
      c = new ShooterSpeedCommand(params.at(0),true);
    } else if(checkName(cmd, "SHOOT")) {
      System.out.println("Params: N/A");
      c = new ShootCommand();
    } else if(checkName(cmd, "INDEX")) {
      System.out.println("Params: N/A");
//      c = new IndexerCommand();
    }

    //Process command type
    if (c != null) {
      if(!isParallel) {
        addSequential(c);
        System.out.println("Type: " + c.getName());
      } else {
        addParallel(c);
        System.out.println("Type: " + c.getName());
      }
      System.out.println("---");
    }
  }
}
