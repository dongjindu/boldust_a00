/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boldust.trisets;

import com.boldust.general.BoldustProperties;
import com.boldust.general.Res;
import static com.boldust.general.Res.logger;
import com.boldust.general.Tree;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.util.Properties;
import java.util.logging.Level;

/**
 *
 * @author oefish
 */
public class TriSet {
    public Fields fs;
    public RowValues rvs;
    public static ComboPooledDataSource cpds = new ComboPooledDataSource();
    public static ComboPooledDataSource cpdsmeta = new ComboPooledDataSource();
    private static Boolean ifDataSourceSet = false;
    private static String DSname = null;

    static {
        setDataSource(Res.DATASOURCENAME);
    }
    
    public TriSet() throws Exception {
    }

    /**
     * If DataSource set successfully or set already, return true. Otherwise
     * return false.
     *
     * @param name
     * @return
     */
    public static boolean setDataSource(String name) {
        if (!ifDataSourceSet) {
            try {
                TriSet.DSname = name.toLowerCase();
                BoldustProperties p = new BoldustProperties();
                String sdc = "cpds." + name + ".driverclass" ;
                String sju = "cpds." + name + ".url";
                String suser = "cpds." + name + ".user";
                String spass = "cpds." + name + ".password";
                String sminps = "cpds." + name + ".minpoolsize";
                String smaxps = "cpds." + name + ".maxpoosize";
                cpds.setDriverClass((String) p.getProp(sdc));
                cpds.setJdbcUrl((String)p.getProp(sju)); //Can set to "localhost jarpath"
                cpds.setUser((String) p.getProp(suser));
                cpds.setPassword((String) p.getProp(spass));
                cpds.setMinPoolSize((Integer) p.getProp(sminps));
                cpds.setMaxPoolSize((Integer) p.getProp(smaxps));
                sdc = "cpds." + name + "meta.driverclass" ;
                sju = "cpds." + name + "meta.url";
                suser = "cpds." + name + "meta.user";
                spass = "cpds." + name + "meta.password";
                sminps = "cpds." + name + "meta.minpoolsize";
                smaxps = "cpds." + name + "meta.maxpoosize";
                cpdsmeta.setDriverClass((String) p.getProp(sdc));
                cpdsmeta.setJdbcUrl((String)p.getProp(sju)); //Can set to "localhost jarpath"
                cpdsmeta.setUser((String) p.getProp(suser));
                cpdsmeta.setPassword((String) p.getProp(spass));
                cpdsmeta.setMinPoolSize((Integer) p.getProp(sminps));
                cpdsmeta.setMaxPoolSize((Integer) p.getProp(smaxps));
                ifDataSourceSet = true;
            } catch (Exception e) {
                Res.logger.log(Level.SEVERE, TriSet.class.getName());
                return false;
            }
        }
        return ifDataSourceSet;
    }
    public static Boolean ifDataSourceSet() {
        return ifDataSourceSet;
    }
    
    public void rvsAppend(FieldValues fvs) {
        
    }
    public void rvsAppend(RowValues rvs0) {
        
    }
    public void rvsSQLAppend(String s) {
        
    }
    public void rvsRefresh() {
        rvs = null;
    }
}
