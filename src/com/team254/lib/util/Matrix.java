package com.team254.lib.util;

/**
 * Represents a matrix with standard operations.
 *
 * @author stephen@team254.com (Stephen Pinkerton)
 * @author jonathanc@team254.com (Jonathan Chang)
 * @author art.kalb96@gmail.com (Arthur Kalb)
 * @author liam.hardiman13@bcp.org (Liam Hardiman)
 */
public class Matrix {
  private int width;
  private int height;
  private double data[][];

  /**
   * Creates an empty Matrix of size (rows) by (cols)
   * @param rows: number of rows
   * @param cols: number of columns
   */
  public Matrix(int rows, int cols) {
    this.width = cols;
    this.height = rows;
    this.data = new double[height][width];
  }

  /**
   * Creates a Matrix of size (rows) by (cols) populated by (vals)
   * @param rows: number of rows
   * @param cols: number of columns
   * @param vals: values to insert into the matrix
   */
  public Matrix(int rows, int cols, double vals[][]) {
    this(rows, cols);
    if (vals.length == height && vals[0].length == width) {
      this.data = vals;
    } else {
      throw new IndexOutOfBoundsException();
    }
  }
  
  /**
   * Check if this matrix is of the same dimensions as the other matrix
   * @param other: the matrix to check against
   * @return: true if dimensions match
   */
  public boolean checkDimensions(Matrix other) {
    return (other.getHeight() == height && other.getWidth() == width);
  }

  /**
   * Check if this matrix can be multiplied by the other matrix
   * @param other: the matrix to check against
   * @return: true if the matrices can me multiplied
   */
  public boolean canMultiply(Matrix other) {
    return (other.getWidth() == height);
  }

  /**
   * Add this matrix and the other matrix. Result is stored in this matrix.
   * @param other: the matrix to add
   * @return: true if the matrices were successfully added
   */
  public boolean addMatrix(Matrix other) {
    if (checkDimensions(other)) {
      for (int i = 0; i < other.height; i++) {
        for (int j = 0; j < other.width; j++) {
          setValue(i, j, getValue(i, j) + other.getValue(i, j));
        }
      }
      return true;
    } else {
      return false;
    }
  }

  /**
   * Subtract this matrix and the other matrix. Result is stored in this matrix.
   * @param other: the matrix to subtract
   * @return: true if the matrices were successfully subtracted
   */
  public boolean subtractMatrix(Matrix other) {
    if (checkDimensions(other)) {
      for (int i = 0; i < other.height; i++) {
        for (int j = 0; j < other.width; j++) {
          setValue(i, j, getValue(i, j) - other.getValue(i, j));
        }
      }
      return true;
    } else {
      return false;
    }
  }

  /**
   * Multiplies this matrix by the other matrix. Result is stored in this matrix.
   * @param other: the matrix to multiply by
   * @return: true if the matrices were successfully subtracted
   */
  public boolean multiplyMatrix(Matrix other) {
    if (canMultiply(other)) {
      double product[][] = new double[height][other.width];
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < other.width; j++) {
          for (int k = 0; k < width; k++) {
            product[i][j] += data[i][k] * other.data[k][j];
          }
        }
      }
      data = product;
      height = product.length;
      width = product[0].length;
      return true;
    } else {
      return false;
    }
  }

  /**
   * Multiply this matrix by a scalar
   * @param scale: the scalar to multiply by
   */
  public void multiplyByScalar(double scale) {
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          setValue(i, j, getValue(i, j) * scale);
        }
      }
  }

  /**
   * Fill this matrix with an array of given values
   * @param values: the values with which to flash the matrix
   */
  public void flashMatrix(double[] values) {
    int k = 0;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++,k++) {
        if(k >= values.length) {
           return;
        } else {
          data[i][j] = values[k];
        }
      }
    }
  }

  /**
   * Creates an identity matrix of size (length) by (length)
   * @param length: the length of the identity matrix
   * @return: an identity matrix with the specified dimensions
   */
  public static Matrix makeIdentityMatrix(int length) {
    Matrix identityMatrix = new Matrix(length, length);
    for (int i = 0; i < length; i++) {
      identityMatrix.setValue(i, i, 1.0);
    }
    return identityMatrix;
  }

  /**
   * Prints a formatted representation of this matrix
   * @return: a formatted string containing values from the matrix.
   */
  public String toString() {
    String theMatrix = ""; // you're living in a dreamworld, Neo
    for (int i = 0; i < height; i++) {
      theMatrix += "| ";
      for (int j = 0; j < width; j++) {
        theMatrix += Double.toString(data[i][j]);
        if (j == width-1) {
          theMatrix += " |";
        } else {
          theMatrix += ", ";
        }
      }
      theMatrix += "\n";
    }
    return theMatrix; // to blockbuster
  }

  /**
   * Puts value in the matrix at coordinates y and x
   * @param y: y-coordinate
   * @param x: x-coordinate
   * @param value: the value to store
   */
  public void setValue(int y, int x, double value) {
    if (y > height || x > width) {
      throw new IndexOutOfBoundsException();
    } else {
      data[y][x] = value;
    }
  }

  /**
   * Gets the value of the matrix at coordinates (x,y)
   * @param y: y-coordinate
   * @param x: x-coordinate
   * @return: the value at (x,y)
   */
  public double getValue(int y, int x) {
    return data[y][x];
  }

  /**
   * How tall is this matrix?
   * @return: Row count of this matrix
   */
  public int getHeight() {
    return height;
  }

  /**
   * How wide is this matrix?
   * @return: Column count of this matrix
   */
  public int getWidth() {
    return width;
  }
  /**
   * Creates a carbon copy of the Matrix
   * @return a new Matrix with the same dimensions and respective elements
   */
  public Matrix clone(){
      Matrix m = new Matrix(this.height,this.width);
      for(int i = 0; i < data.length; i++){
          m.flashMatrix(data[i]);
      }
      return m;
  }
}
