/*
 * Logs strings to a file
 */
package com.team254.lib.util;

import com.sun.squawk.microedition.io.FileConnection;
import java.io.IOException;
import java.io.OutputStreamWriter;
import javax.microedition.io.Connector;

/**
 *
 * @author tom@team254.com (Tom Bottiglieri)
 */
public class Logger {
  String filename;
  OutputStreamWriter w;
  FileConnection c;

  public Logger(String filename, boolean open) {
    this.filename = filename;
    if (open)
      open();
  }

  public Logger(String filename) {
    this(filename, false);
  }

  public void open() {
    String loc = "file:///" + filename;
    try {
      c = (FileConnection) Connector.open(loc);
      w = new OutputStreamWriter(c.openOutputStream());
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public void log(String s) {
    if (w == null) {
      return;
    }
    try {
      w.write(s);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public void close() {
    try {
      c.close();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}
