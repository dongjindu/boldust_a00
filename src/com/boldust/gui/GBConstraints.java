/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boldust.gui;

import java.awt.*;

/**
 *
 * @author oefish
 */
public class GBConstraints {
    private GridBagConstraints gbc = new GridBagConstraints();
    private Insets insets0 = new Insets(5, 50, 5, 5);
    private Insets insets1 = new Insets(5, 5, 5, 45);
    private int lastLineHeight = 1;
    private int count = 0;
    public GridBagConstraints getGridBagConstraints() {
        return gbc;
    }
   public GBConstraints(int x, int y) {
      gbc.weightx = 0.1;
      gbc.weighty = 0.1;
//      gbc.fill = GridBagConstraints.HORIZONTAL;
    }
    public GBConstraints() {
      gbc.weightx = 0.1;
      gbc.weighty = 0.1;
//      gbc.fill = GridBagConstraints.HORIZONTAL;
    }
    public void next(int height) {
//        gbc.gridheight = height;
        if (height < 1) {
            System.err.println("Each Java Component height must be greater than 0.");
            System.exit(-1);
        }
        if ( count == 0) { 
          gbc.gridx = 0;
          gbc.gridy = 0; 
          gbc.insets = insets0;
          gbc.fill = GridBagConstraints.HORIZONTAL;
          lastLineHeight = height;
        } else if (count > 0 ) {
          if ( gbc.gridx == 0 ) {
               gbc.gridx = 1;
               gbc.insets = insets1;
               lastLineHeight = height;
          } else if ( gbc.gridx == 1 ) {
               gbc.gridx = 0;
               gbc.insets = insets0;
               gbc.gridy = gbc.gridy + this.lastLineHeight;
               gbc.fill = GridBagConstraints.HORIZONTAL;
               lastLineHeight = Math.max(lastLineHeight, height);
          }  
        }
        if (height != 1) {
          gbc.ipady = height * 10;
        } else {
            gbc.ipady = 0;
        }
        System.out.print(count);
        System.out.print(":" + Integer.valueOf(gbc.gridx).toString() + "\t" + Integer.valueOf(gbc.gridy).toString() + "\n");
        count++;
    }
    public int getCount() {
        return count;
    }
}
