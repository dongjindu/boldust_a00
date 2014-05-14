package com.boldust.gui;

public class BDApp {
  private transaction tran;
  private session sess;
  public static void main() {
    SwingUtilities.InvokeLater( new Runnable() {
         public void run() {
            createAndShowGUI();
         }
      }
    );
  }
  private createAndShowGUI() {
      
  }
}
