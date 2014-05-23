/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boldust.db;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oefish
 */
public class Field {
    public static final int TYPE_INT = 1;
    public static final int TYPE_BOOL = 2;
    public static final int TYPE_STRING = 3;
    public static final int TYPE_BIGINT = 4;
    public static final int TYPE_FLOAT = 5;
    public static final int TYPE_DOUBLE = 6;
    public static final int TYPE_BIGDECIMAL = 7;
    public static final int TYPE_BLOB = 8;
    public static final int TYPE_URL = 9;
    public static final int TYPE_FILENAME = 10;
    public static final int TYPE_HOSTNAME = 11;
    public static final int TYPE_IPV4 = 12;
    public static final int TYPE_IPV6 = 13;
    public static final int TYPE_PACK = 14;
                    
    public String name = "";
    public int ftype = TYPE_INT;
    public int length = 0;
    public int decimal = 0;
    public short displaylength = 0;
    public short displaydecimal = 0;
    public Field(int fieldtype, int length, int decimal) { //Shall be pack
        if (fieldtype == TYPE_PACK ) {
            ftype = fieldtype;
        } else{
            try {
                throw new Exception("Only pack");
            } catch (Exception ex) {
                Logger.getLogger(Field.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public Field(int fieldtype) { //Shall be int, bigint etc.
        
    }
    public Field(int fieldtype, int length) { //Shall be String
        
    }
}
