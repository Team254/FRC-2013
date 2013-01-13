/*
 * ScriptedAutoMode builds a sequential auto mode
 * from a script file
 */
package com.team254.frc2013.commands.auto;

import com.sun.squawk.io.BufferedReader;
import com.sun.squawk.microedition.io.FileConnection;
import com.team254.frc2013.commands.DriveDistanceCommand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.util.Vector;
import javax.microedition.io.Connector;

/**
 *
 * @author tombot
 * @author Arthur Kalb
 */
public class ScriptedAutoMode extends CommandGroup {
    /**
     * This class functions as a data structure for the parameters
     */
    private class ParamList {
        Vector list = new Vector();
        
        public void addParam(double p) {
            System.out.println("added param: " + p);
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
 
    String fileName;
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
            //Gets the file and reader set up
            FileConnection c = (FileConnection) Connector.open(url);
            BufferedReader buf = new BufferedReader(new InputStreamReader(c.openInputStream()));
            
            //Other variables for parsing initialized
            String line;
            boolean foundCmd = false;
            String cmd = "NULL";
            ParamList params = new ParamList();
            int lastSpace = 0;
            
            //Goes through the file line by line
            while ((line = buf.readLine()) != null) {
                //ensures the line is not a comment
                if (line.startsWith("#") || line.length() <= 1) {
                    continue;
                }
                //Breaks the line down into substrings
                for (int i = 0; i < line.length(); ++i) {
                    if (line.charAt(i) == ' ' || i == line.length() - 1) {
                        //Assigns the first word in the line to the command variable
                        if (!foundCmd) {
                            cmd = line.substring(0, i).trim();
                            System.out.println("cmd: " + cmd);
                            foundCmd = true;
                        }
                        //or else makes the word into a double parameter
                        else {
                            String param = line.substring(lastSpace,i).trim();
                            params.addParam(Double.parseDouble(param));
                        }
                        lastSpace = i;
                    }
                }
                System.out.println("newline");
            }
            addCommand(cmd, params);
            c.close();
            
        } catch (Exception e) {
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
    private void addCommand(String cmd, ParamList params) {
        Command c = null;
        if (checkName(cmd, "DRIVE")) {
            c = new DriveDistanceCommand(params.at(1), params.at(2), params.at(3));
        } else if (checkName(cmd, "WAIT")) {
            c = new WaitCommand(params.at(1));
        }
        
        if (c != null) {
            addSequential(c);
        }
    }
}
