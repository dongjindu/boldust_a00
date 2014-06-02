/*
 * To change this license header, choose License Headers in Project BoldustProperties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boldust.general;

import java.io.Serializable;
import java.lang.String;
import java.util.concurrent.Callable;
/**
 *
 * @author oefish
 */
public class BoldustProperties implements Prop {

    private static Prop p = null;

    /*
    * if no parameter, default name "PropertiesDB" will be used.
    */
    public BoldustProperties(String PropertyClassName) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class clazz = Class.forName(PropertyClassName);
        Prop prop = (Prop) clazz.newInstance();
        BoldustProperties.p = prop;
    };
    public BoldustProperties() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class clazz = Class.forName("PropertiesDB");
        Prop prop = (Prop) clazz.newInstance();
        BoldustProperties.p = prop;
    };
    /**
    * Get Property calling interface method which in turn will goto the instance method.
    * By default it is PropertiesDB.
    */
    @Override
    public Object getProp(String s) {
        return p.getProp(s);
    }

    @Override
    public String setProp(String name, Object value, String type) {
        return p.setProp(name, value, type);
    }
    
}
