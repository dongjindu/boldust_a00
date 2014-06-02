/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boldust.general;

public class LocalDAOException extends Exception {
  public LocalDAOException() {
  }
 
  public LocalDAOException(String msg) {
    super(msg);
  }
  public LocalDAOException(String msg, Exception e) {
      super(msg, e);
  }
}