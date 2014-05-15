/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boldust.gui;

import java.awt.Color;
import java.awt.Component;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;


/**
 *
 * @author oefish
 */
public class LocalJDBCTableModel extends AbstractTableModel{ //Actually this is fixed column table model
  private ArrayList datalist = new ArrayList(new ArrayList());
  public int buffer = 50;
  private ResultSet rs = null;
  private ResultSetMetaData rsmd = null;
  private int rowcount;
  private boolean rowcounted = false;
  private boolean loaded = false;
  private DAO dao = new DAO();
  private String sql0;
  private String sql1; //SQL counting
  
  public void setSQL(String sql) {
      String s1[] = sql.split(" ");
      int i1 = 0, i2 = 0;
      while (i1 < s1.length) {
          if ( s1[i1].equalsIgnoreCase("from") ) {
              i2 = i1;
          }
          i1 = i1 + 1;
      }
      if ( s1[0].equals("select") ) {
           
      }
  }
  public void loadData() {
      loadData(false);
  }
  public void loadData(boolean forceload) {
      if ( loaded && !forceload && sql0.length() < 15 ) { //Shortest SQL is 15 chars: select * from a
          return; 
      }
      try {
         int idao = dao.query(sql0);
         rs = dao.executeQuery(idao);
         rsmd = rs.getMetaData();
         loaded = true;
//      } catch (DAOException de) {
      } catch (DAOException ex) {
          ex.printStackTrace();
          System.exit(-1);
      } catch (SQLException se) {
          se.printStackTrace();
          System.exit(-1);
      }
  }
  public int getRowCount() {
      if ( rowcounted ) {
          return rowcount;
      } else {
        try {
            int i = 0;
            rs.first();
            while ( rs.next() ) {
               i = i + 1;
            }
            rowcounted = true;
            return i;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            rowcounted = false;
            return -1;
        }
      }
   }    
  public int getColumnCount() {
   ArrayList al = (ArrayList)datalist.get(1);
   return al.size();
   //return 0;//.size();
  }
  public Object getValueAt(int row, int col) {
   if (this.getRowCount() < row + 1) {
       return null;
   } else if (this.getColumnCount() < col + 1) {
      return null;
   } else {
      ArrayList al = (ArrayList)datalist.get(row + 1);
      return al.get(col + 1); 
   }
 }
}
class OneRow  extends ArrayList {
    
}