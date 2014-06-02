/*
 * @Author: yetaai
 * yetaai@gmail.com
 * This software piece is apache license. But the contents generated is governed by wiktionary.org policy.
 */

package com.boldust.general;

import java.io.FileInputStream;
import java.io.IOException;
import java.rmi.server.UID;
import java.sql.*;
import org.apache.commons.configuration.PropertiesConfiguration;
import java.util.logging.Logger;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;

public class LocalDAO {
//    private static LocalDAO instance; //not necessary design for single user environment
    private static Connection conn;
    private static ConcurrentHashMap<Integer, PreparedStatement> chmps = new ConcurrentHashMap();
    private static final AtomicInteger cter = new AtomicInteger();
            
    static {
        cter.set(0);
        createConnection();
    }
    
/*    public static void deleteDAO() {
        closeConnection(); //This will release all resources
    }
*/    
    //set parameter methods
    public synchronized void setObject(int parameterIndex, Object x, int psIndex) {
        try {
            getPstmt(psIndex).setObject(parameterIndex, x);
        }  catch (SQLException sqle) {
            System.err.println(" pstmt.setObject(parameterIndex, x) Fail! Error = " + sqle.toString());
        }
        
    }
    
    public synchronized void setBoolean(int parameterIndex, boolean flag, int psIndex) {
       try {
            getPstmt(psIndex).setBoolean(parameterIndex, flag);
        } catch (SQLException sqle) {
            System.err.println(" pstmt.setBoolean(parameterIndex, flag) Fail! Error = " + sqle.toString());
        }
        
    }
    
    public synchronized void setDouble(int parameterIndex, double x, int psIndex) {
        try {
            getPstmt(psIndex).setDouble(parameterIndex, x);
        }
        catch (SQLException sqle) {
            System.err.println(" pstmt.setDouble(parameterIndex, x) Fail! Error = " + sqle.toString());
        }
        
    }
    
    public synchronized void setInt(int parameterIndex, int x, int psIndex) {
        try {
            getPstmt(psIndex).setInt(parameterIndex, x);
        } catch (SQLException sqle) {
            System.err.println(" pstmt.setInt(parameterIndex, x) Fail! Error = " + sqle.toString());
        } catch (Exception e) {
            System.err.println("Possibly null pointer in setInt");
        }
    }
    
    public synchronized void setString(int parameterIndex, String s, int psIndex) {
        
        try {
            getPstmt(psIndex).setString(parameterIndex, s);
        } catch (SQLException sqle) {
            System.err.println(" pstmt.setString(parameterIndex, s) Fail! Error = " + sqle.toString() + " SQL state?" + getPstmt(psIndex).toString());
        } catch (Exception e) {
            System.err.println("Possibly null pointer in setInt");
        }
    }
    
    public synchronized boolean executeUpdate(int psIndex) throws LocalDAOException {
        boolean flag = true;
        try {
                getPstmt(psIndex).executeUpdate();
                chmps.remove(psIndex);
        }
        catch (SQLException sqle) {
            flag = false;
            System.out.println("executeUpdate() Error!" + sqle.toString() + getPstmt(psIndex).toString());
            throw new LocalDAOException("SQL exception in dao.executeUpdate:", sqle);
        }
        return flag;
        
    }
    
    public synchronized int update(String expression) throws LocalDAOException{
//        pstmt = null;
        int k = cter.getAndIncrement();
        try {
            chmps.put(k, conn.prepareStatement(expression));
        } catch (SQLException sqle) {
            System.err.print("\nUpdate DataBase Preparation Fail! Error = " + sqle.toString());
            throw new LocalDAOException("SQL statement preparation in dao.Update:", sqle);
        }
        return k;
        
    }
    
    public synchronized ResultSet executeQuery(int psIndex) {
        ResultSet rs;
        try {
                rs = getPstmt(psIndex).executeQuery();
                chmps.remove(psIndex);
                return rs;
        }
        catch (SQLException sqle) {
            System.err.print("\nQuery DataBase Fail! Returning null resultset!! Error: = " + sqle.toString());
            //sqle.printStackTrace();
            return null;
            //throw new LocalDAOException("SQL exception in localdao.executeQuery:", sqle);
        }
    }
        
    public synchronized int query(String expression) throws LocalDAOException {
        //pstmt = null;
        int k = cter.getAndIncrement();
            try {
                chmps.put(k, conn.prepareStatement(expression));
            } catch (SQLException sqle) {
                System.err.print("\nQuery DataBase Preparation Fail! Error = " + sqle.toString());
                throw new LocalDAOException("SQL statement preparation in dao.Query:", sqle);
            }
            return k;
    }
    
    public synchronized static void closeConnection() {
        try {
            conn.close();
            conn = null;
            System.out.println("Close Connect DataBase Success!");
        }
        catch (SQLException e) {
            System.err.println("Close Connect DataBase Fail! Error = " + e.toString());
        }
    }
    
    public synchronized void commit() throws LocalDAOException {
        
         try {
            conn.commit();
        }
        catch (SQLException sqle) {
            System.err.println(sqle.toString());
            throw new LocalDAOException("DAOException commit:", sqle);
        }
        
    }
    
    public synchronized void rollback() {
        
        try {
            conn.rollback();
        }
        catch (SQLException sqle) {
            System.err.println(sqle.toString());
        }
    }
    public synchronized void executeQueryAndClose(int psIndex) {
        this.executeQuery(psIndex);
        closeConnection();
    } 
    public synchronized void executeUpdateAndClose(int psIndex) {
        this.executeQuery(psIndex);
        closeConnection();
    } 
    
    public void setAutoCommit(boolean flag) {
        try {
            conn.setAutoCommit(flag);
        }
        catch (SQLException sqle) {
            System.err.println(sqle.toString());
        }
    }
    
    public static void createConnection(){
         try {
            if (conn == null) {
                Class.forName(Res.DRIVER_NAME);
//                conn = DriverManager.getConnection(Res.DATABASE_ADDRESS + ";shutdown=true", Res.getProp().getString("dbusername"), Res.getProp().getString("dbpassword"));
                conn = DriverManager.getConnection(Res.DATABASE_ADDRESS + ";shutdown=true", Res.PROPDBUSER, Res.PROPDBPASS);  
            }
            Res.logger.log(Level.INFO, LocalDAO.class.getName() + Res.DATABASE_ADDRESS + ". Account: " +
                   Res.PROPDBUSER + ", password: " + Res.PROPDBPASS);
        } catch (Exception e) {
            Res.logger.log(Level.SEVERE, LocalDAO.class.getName() + e.getMessage());
        } 
    }
    
/*    public static LocalDAO getInstance(){

        
        if (instance != null) {
            return instance;
        } else {
            instance = new LocalDAO();
            return instance;
        }
        
    }
*/
    private PreparedStatement getPstmt(int psIndex) {
        try {
            if (chmps.get(psIndex) != null) {
                //
            } else {
                Res.logger.log(Level.SEVERE, this.getClass().getName() + "\n nullified preparedstatement? psIndex is");
                Res.logger.log(Level.SEVERE, this.getClass().getName() + Integer.valueOf(psIndex).toString() + ". In chmps, try to print it to check: " + chmps.get(psIndex).toString());
                throw new LocalDAOException("nullified preparedstatement");
            }
        } catch (Exception e) {
            Res.logger.log(Level.SEVERE, "Exception" + this.getClass().getName());
            closeConnection();
            System.exit(-1);
        }
        return chmps.get(psIndex);
    }
}

