/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geovince.test;

import com.boldust.gui.Res;
import com.boldust.gui.DAO;
import com.boldust.gui.GBConstraints;
import java.awt.*;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import javax.swing.*;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.HashMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;   
import javax.swing.table.DefaultTableModel;
import javax.swing.JSplitPane;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.ScrollPaneLayout;
import javax.swing.ScrollPaneConstants;

public class MWindow {
    private DAO dao = new DAO();
    private ResultSet rs;
    private HashMap<String, Object> hm = new HashMap();
    
    private String[] columnNames = {"File", "Left Length", "Left Width", 
        "Right Length", "Right Width", "Order", "Date", "Order price", "Delivery", "Messages"};
    private Object[][] data = {{"","", "", "", "","", "", "", ""}}; //ArrayasList({{"Overall progress", 0}};
    private static final Integer pslock = 1;
    
    private DefaultTableModel model = new DefaultTableModel(data, columnNames) {
        private static final long serialVersionUID = 1L;
        @Override
        public Class<?> getColumnClass(int column) {
            return getValueAt(0, column).getClass();
        }

        @Override
        public boolean isCellEditable(int row, int col) {
            return false;
        }
    };
    private JTable table = new JTable(model);
    private     ResultSet[] mrs = new ResultSet[5]; 
    private     Integer m0[] = new Integer[5];
    
    private void addComponents() {
        final JPanel p = new JPanel();
//        Container p = f.getContentPane();
        JScrollPane sp = new JScrollPane(p);
        //f.getContentPane().add(BorderLayout.CENTER, sp);
//        ((Container) hm.get("cp")).add(BorderLayout.CENTER, sp);
        hm.put("p", p); //better to be done after object construction
        hm.put("sp", sp);//cp (ContentPane of frame is already in hm)
        //hm.put("frame", (JFrame) p.getRootPane().getParent()); //Not sure could get JFrame's rootpane. Or just call a loop to get the JFrame?
        p.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        p.setLayout(new GridBagLayout());

/*
  JLabel lminx = new JLabel("Mininum width step");
  JLabel lminy = new JLabel("Mininum thickness step");
  JLabel lminz = new JLabel("Minimum height step");
*/
        ArrayList<JComponent> jcompal = new ArrayList<JComponent>();
        JLabel ldir = new JLabel("Data file directory");
        JLabel lfile = new JLabel("New data files(eg 1.x3d;2.x3d)"); //Separated by ";"
        JLabel ldbdir = new JLabel("Database directory name");
        Collections.addAll(jcompal, ldir);
        Collections.addAll(jcompal, lfile);
        Collections.addAll(jcompal, ldbdir);
        
        
//        JPanel p = new JPanel(new GridBagLayout());
        JLabel lmaxlength = new JLabel("Max length each word");
 //           lmaxlength.setBounds(5, 5, 200, 100);
        JLabel lmaxitem50 = new JLabel("Max items for top 50 words");
        JLabel lmaxitem500 = new JLabel("Max items for top 500 words");
        JLabel lmaxitem = new JLabel("Max items for other words");
        JLabel lranklevel = new JLabel("Rank Level");
        JLabel lwordlist = new JLabel("Word list input file");
        lwordlist.setForeground(Color.blue);
        JLabel lhtmldir = new JLabel("HTML directory name");
        JLabel ldictdir = new JLabel("Dictionary directory name");
//        JLabel ldbdir = new JLabel("Database directory name");
            
        JTextField tmaxlength = new JTextField((Res.getProp().getString("maxlength") == null) ? "300" : Res.getProp().getString("maxlength"));
        JTextField tmaxitem50 = new JTextField((Res.getProp().getString("maxitem50") == null) ? "8" : Res.getProp().getString("maxitem50"));
        JTextField tmaxitem500 = new JTextField((Res.getProp().getString("maxitem500") == null) ? "5" : Res.getProp().getString("maxitem500"));
        JTextField tmaxitem = new JTextField((Res.getProp().getString("maxitem") == null) ? "2" : Res.getProp().getString("maxitem"));
        String[] ranklevel = {
            "Rank per 1000",
            "Rank in detail"
        };
        JComboBox tranklevel = new JComboBox(ranklevel);
        if (Res.getProp().getString("ranklevel") == null) {
            tranklevel.setSelectedItem(0);
        } else if (Res.getProp().getString("ranklevel").equals(ranklevel[0])) {
            tranklevel.setSelectedIndex(0);
        } else if (Res.getProp().getString("ranklevel").equals(ranklevel[1])) {
            tranklevel.setSelectedIndex(1);
        } else {
            tranklevel.setSelectedItem(0);
        }
        tranklevel.setEditable(false);
        //tranklevel.addActionListener(tranklevel);

        final JTextField twordlist = new JTextField((Res.getProp().getString("wordlist") == null) ? "" : Res.getProp().getString("wordlist"));
        final JTextField thtmldir = new JTextField((Res.getProp().getString("htmldir") == null) ? "html" : Res.getProp().getString("htmldir"));
        final JTextField tdictdir = new JTextField((Res.getProp().getString("dictdir") == null) ? "dict" : Res.getProp().getString("dictdir"));
        final JTextField tdbdir = new JTextField((Res.getProp().getString("dbdir") == null) ? "db" : Res.getProp().getString("dbdir"));
        
        final JButton runbutton = new JButton("Run");
        final JButton savebutton = new JButton("Save");
        final JButton saveexitbutton = new JButton("Save and Exit");
        final JButton exitbutton = new JButton("Exit");

        GBConstraints gbc = new GBConstraints();
        GridBagConstraints c = gbc.getGridBagConstraints();
        gbc.next(1);
        p.add(lmaxlength, c);
        gbc.next(1);
        p.add(tmaxlength, c);
        gbc.next(1);
        p.add(lmaxitem50, c);
        gbc.next(1);
        p.add(tmaxitem50, c);
        gbc.next(1);
        p.add(lmaxitem500, c);
        gbc.next(1);
        p.add(tmaxitem500, c);
        gbc.next(1);
        p.add(lmaxitem, c);
        gbc.next(1);
        p.add(tmaxitem, c);
        gbc.next(1);
        p.add(lranklevel, c);
        gbc.next(1);
        p.add(tranklevel, c);
        gbc.next(1);
        p.add(lwordlist, c);
        gbc.next(1);
        p.add(twordlist, c);
        gbc.next(1);
        p.add(lhtmldir, c);
        gbc.next(1);
        p.add(thtmldir, c);
        gbc.next(1);
        p.add(ldictdir, c);
        gbc.next(1);
        p.add(tdictdir, c);
        gbc.next(1);
        p.add(ldbdir, c);
        gbc.next(1);
        p.add(tdbdir, c);
        gbc.next(1);
        p.add(runbutton, c);
        gbc.next(1);
        p.add(saveexitbutton, c);
        gbc.next(1);
        p.add(savebutton, c);
        gbc.next(1);
        p.add(exitbutton, c);
        hm.put("maxlength", tmaxlength);
        hm.put("maxitem50", tmaxitem50);
        hm.put("maxitem500", tmaxitem500);
        hm.put("maxitem", tmaxitem); //Maxitem per each word type each word
        hm.put("ranklevel", tranklevel);
        hm.put("wordlist", twordlist);
        hm.put("htmldir", thtmldir);
        hm.put("dictdir", tdictdir);
        hm.put("dbdir", tdbdir);
        DicActionListener dal = new DicActionListener(hm);
        savebutton.addActionListener(dal);
        runbutton.addActionListener(dal);
        saveexitbutton.addActionListener(dal);
        exitbutton.addActionListener(dal);
        twordlist.addFocusListener(new FL(hm)); //Focus Listner is not the only way nor best way. but works right now.
    }
    public void makeUI(Container cp) {
        hm.put("cp", cp);
        addComponents();
        addTable();
//        cp.removeAll();
//        addComponents();
//        addTable();
//get topmost JFrame if necessary
 /*       Container parent = cp;
        while (parent.getParent() != null) {
            parent = parent.getParent();
            System.out.println("What is Parent of cp if not an Instance of JFrame?" + parent.toString());
        }
        if (parent instanceof JFrame) {
            System.out.println("Finally this is an Instance of JFrame?");
        };
*/
/*        try {
            dao.query("select word from voctxt limit 10 offset 1 ");
            rs = dao.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1) + " Printing inside makeUI()");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } */
    }
    public String getAppName() {
        String appname = "";
        String oldappname= "";
        int AppnameMin1 = 5 - 2; //If less than this, user will be reminded
        int AppnameMin2 = 5 - 2; //If less than this, it will not be saved. 
        int AppnameMax = 10;
        try {
             oldappname = Res.getProp().getString("appname");
             if ((oldappname == null) || oldappname.length() < AppnameMin1) {
                 while ((appname == null) || appname.length() < AppnameMin2) {
                     appname = JOptionPane.showInputDialog("Give this application a name", oldappname);
                 }
             }
        } catch (Exception e) {
//             e.printStackTrace();
             if (appname.length() < AppnameMin2) {
                 System.err.printf("Application name is too short");
             } else {
                 e.printStackTrace();
             }
        }
        try {
            if (!(oldappname == appname) && appname.length() <= AppnameMax && appname.length() >= AppnameMin2 ) {
                Res.getProp().setProperty("appname", appname);
                Res.getProp().save();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return appname;
    }

    private void addTable() {
        //throw new UnsupportedOperationException("Not yet implemented");
        JScrollPane ppara = (JScrollPane) hm.get("sp"); //Parameter Pane
        JPanel p = new JPanel(new BorderLayout());
        JScrollPane pstatus = new JScrollPane(p); //Status Pane
//        p.add(table.getTableHeader(), BorderLayout.NORTH);
//        p.add(table,BorderLayout.CENTER);
        
        table.setSize(p.getWidth()-10, p.getHeight()-10);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(500);
        
        TableColumn column = table.getColumnModel().getColumn(1);
        column.setCellRenderer(new ProgressRenderer());

        JSplitPane splp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, ppara, pstatus);
        splp.setDividerLocation(360 + splp.getInsets().top);
        ((Container) hm.get("cp")).add(BorderLayout.CENTER, splp);
        
        hm.put("model", model);
        hm.put("table", table);
/*
        JPanel pb2 = new JPanel(new FlowLayout());
        pb2.add( new JButton("status"));
        pb2.add( new JButton("status1"));
        
        JPanel pb3 = new JPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0; gc.gridy = 0; gc.gridheight = 2;
        pb3.add(pb1, gc);
        gc.gridx = 0; gc.gridy = 3; gc.gridheight = 2;
        pb3.add(pb2, gc);
        msp.add(new JScrollPane(pb3), "status");
*/
    }
}

class ProgressRenderer extends DefaultTableCellRenderer {
    private final JProgressBar b = new JProgressBar(0, 100);
    public ProgressRenderer() {
        super();
        setOpaque(true);
        b.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Integer i = (Integer) value;
        String text = "Completed";
        if (i < 0) {
            text = "Error";
        } else if (i < 100) {
            b.setValue(i);
            if (row == 0 ) {
                b.setForeground(Color.green);
            } else if (row%2 == 0) {
                b.setForeground(Color.blue);
            } else if (row%2 == 1) {
                b.setForeground(Color.pink);
            }
            return b;
        }
        super.getTableCellRendererComponent(table, text, isSelected, hasFocus, row, column);
        return this;
    }
}

