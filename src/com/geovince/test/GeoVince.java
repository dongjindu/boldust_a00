/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geovince.test;

/**
 *
 * @author oefish
 */
import com.boldust.math001.Math001;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
//import java.awt.EventQueue;

public class GeoVince {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        try {
            SwingUtilities.invokeLater(new Runnable() {
//            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    createAndShowGUI();
                }
               });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void createAndShowGUI() {
      int i1 = 2;
      Object o1 = (Integer) i1;
      int i2 = 1;
      Object o2 = (Integer) i2;
      Object o3;
      o3 = (Integer) o1 + (Integer) o2;
      System.out.println("I " + "Printed here:" + o3.toString());
      System.out.println(Math001.pow001(-2, 20).toString());
      System.exit(0);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MWindow m = new MWindow();
        frame.setTitle(m.getAppName());
//        frame.setBounds(10, 10, 630, 470); //Generally used for JComponents position and size when no layout is used.we can set layout manager to null.
        Container cp = frame.getContentPane();
        m.makeUI(cp);
        frame.setSize(640, 620);
        //frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        //frame.pack();
        frame.setVisible(true);
    }
}


