/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boldust.trisets;

import com.boldust.general.BoldustProperties;
import com.boldust.general.Res;
import static com.boldust.general.Res.*;
import java.util.logging.Level;

/**
 *
 * @author oefish
 */
public class IniHelper {

    public static void iniMetaDB() {
        try {
            TriSet triset = new TriSet();
            triset.cpdsmeta.getConnection().prepareStatement("create table if exists rawtypes(driver char(30)," + 
                    " version char(10), rawtype char(10), javatype char(10), javavertion"
                    + "length boolean, precision boolean, scale boolean, "
                    + "length0 int, precision0 int, scale 0, "
                    + "primary key(driver, version, rawtype, javatype, javaversion"
                    + "))").execute();
            triset.cpdsmeta.getConnection().prepareStatement("create table if exists fields(field char(30)," + 
                    " type char(10), length int, precision int, scale int, primary key(field))").execute();
        } catch (Exception e) {
            logger.log(Level.SEVERE, Res.class.getName() + "Cannot initial Meta DB");
        }
    }

}
