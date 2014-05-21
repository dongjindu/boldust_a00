package com.boldust.gui;

import javax.swing.SwingUtilities;

public class AppClient {
  private Transaction transaction;
  private Session session;
  public static void main() {
    SwingUtilities.invokeLater( new Runnable() {
         public void run() {
            createAndShowGUI();
         }
      }
    );
  }
  private static void createAndShowGUI() {
  }
}
