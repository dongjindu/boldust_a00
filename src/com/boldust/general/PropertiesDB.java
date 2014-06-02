/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boldust.general;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oefish Real implementation of Prop which read properties from local
 * hsqldb LocalDAO
 */
public class PropertiesDB implements Prop {

    @Override
    public Object getProp(String s) {
        String type = null;
        String value = null;
        String name = null;
        try {
            int i0 = Res.localdao.query("select name, value, type from properties where name = ?");
            Res.localdao.setString(1, s, i0);
            ResultSet rs = Res.localdao.executeQuery(i0);
            type = rs.getString("3");
            value = rs.getString("2");
            name = rs.getString("1");
            
            if (type.equalsIgnoreCase("integer") || type.equalsIgnoreCase("int")) {
                return Integer.valueOf(value);
            };
            if (type.equalsIgnoreCase("string")) {
                if (  name.substring(0, 9).equals("cpds.hsql") && 
                        value.contains("localhost") && value.contains("jarpath")) {
                    return Res.DATABASE_ADDRESS + Res.getJarPath() + "\\DB\\" + name.split(".")[1];
                }
                return value;
            }
        } catch (SQLException x) {
            Res.logger.log(Level.SEVERE, null, x);
        } catch (LocalDAOException ex) {
            Res.logger.log(Level.SEVERE, null, ex);
        }
        try {
            ObjectInputStream oi = new ObjectInputStream(new ByteArrayInputStream(Base64.decode(value)));
            return Class.forName(type).cast(oi.readObject());
        } catch (Exception e) {
            Res.logger.log(Level.SEVERE, this.getClass().getName());
        }
        return null;
    }

    @Override
    public String setProp(String name, Object value, String type) {
        try {
            String strvalue = null;
            if (type.equalsIgnoreCase("string")) {
                strvalue = (String) value;
            } else if (type.equalsIgnoreCase("integer") || type.equalsIgnoreCase("int")) {
                strvalue = ((Integer) value).toString();
            } else {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(value);
                oos.flush();
                strvalue = Base64.encode(baos.toByteArray());
            }
            int i0 = Res.localdao.query("select name, value , type from properties where name = ?");
            Res.localdao.setString(1, name, i0);
            ResultSet rs = Res.localdao.executeQuery(i0);
            if (rs.first()) {
                int i1 = Res.localdao.update("update properties set name = ?, value = ?, type = ?");
                Res.localdao.setString(1, name, i1);
                Res.localdao.setString(2, strvalue, i1);
                Res.localdao.setString(3, type, i1);
                Res.localdao.executeUpdate(i1);
                return "Update";
            } else {
                int i1 = Res.localdao.update("insert properties values(?, ?, ?)");
                Res.localdao.setString(1, name, i1);
                Res.localdao.setString(2, strvalue, i1);
                Res.localdao.setString(3, type, i1);
                Res.localdao.executeUpdate(i1);
                return "Create";
            }
        } catch (Exception e) {
            Res.logger.log(Level.SEVERE, this.getClass().getName());
            return "Exception";
        }
    }
}
