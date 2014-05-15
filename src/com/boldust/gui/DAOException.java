/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boldust.gui;

public class DAOException extends Exception {
  public DAOException() {
  }
 
  public DAOException(String msg) {
    super(msg);
  }
  public DAOException(String msg, Exception e) {
      super(msg, e);
  }
}