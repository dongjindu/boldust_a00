/*
 * To change this license header, choose License Headers in Project Prop.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boldust.general;

/**
 *
 * @author oefish
 */
public interface Prop {
    public Object getProp(String s);    
    public String setProp(String name, Object value, String type);
}
