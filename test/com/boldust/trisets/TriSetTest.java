/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boldust.trisets;

import com.boldust.general.*;
import java.util.logging.Level;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author oefish
 */
public class TriSetTest {

    private TriSet triset = null;

    public TriSetTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        Res.setLogLevel(); //Will create properties table as well.
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        try {
            
            triset = new TriSet();
            BoldustProperties p = new BoldustProperties(Res.PROPERTYCLASS);
//            p.setProp("cpds." + Res.DATASOURCENAME + ".name", Res.DATASOURCENAME, "String");
            p.setProp("cpds." + Res.DATASOURCENAME + ".driverclass", "org.hsqldb.jdbcDriver", "String");
            p.setProp("cpds." + Res.DATASOURCENAME + ".url", "jdbc:hsqldb:file:c:\\temp\\trisets\\test", "String");
            p.setProp("cpds." + Res.DATASOURCENAME + ".user", Res.DATASOURCENAME, "String");
            p.setProp("cpds." + Res.DATASOURCENAME + ".password", Res.DATASOURCENAME, "String");
            p.setProp("cpds." + Res.DATASOURCENAME + ".minpoolsize", 1, "int");
            p.setProp("cpds." + Res.DATASOURCENAME + ".maxpoolsize", 1, "int");
            p.setProp("cpds." + Res.DATASOURCENAME + ".dbver", "1.0", "string");
            p.setProp("cpds." + Res.DATASOURCENAME + "meta.driverclass", "org.hsqldb.jdbcDriver", "String");
            p.setProp("cpds." + Res.DATASOURCENAME + "meta.url", "jdbc:hsqldb:file:c:\\temp\\trisets\\testmeta", "String");
            p.setProp("cpds." + Res.DATASOURCENAME + "meta.user", Res.DATASOURCENAME, "String");
            p.setProp("cpds." + Res.DATASOURCENAME + "meta.password", Res.DATASOURCENAME, "String");
            p.setProp("cpds." + Res.DATASOURCENAME + "meta.minpoolsize", 1, "int");
            p.setProp("cpds." + Res.DATASOURCENAME + "meta.maxpoolsize", 1, "int");
            p.setProp("cpds." + Res.DATASOURCENAME + ".dbver", "1.0", "string");

            triset.cpds.setDriverClass((String) p.getProp("cpds." + Res.DATASOURCENAME + ".driverclass"));
            triset.cpds.setJdbcUrl((String) p.getProp("cpds." + Res.DATASOURCENAME + ".url")); //Can set to "localhost jarpath"
            triset.cpds.setUser((String) p.getProp("cpds." + Res.DATASOURCENAME + ".user"));
            triset.cpds.setPassword((String) p.getProp("cpds." + Res.DATASOURCENAME + ".password"));
            triset.cpds.setMinPoolSize((Integer) p.getProp("cpds." + Res.DATASOURCENAME + ".minpoolsize"));
            triset.cpds.setMaxPoolSize((Integer) p.getProp("cpds." + Res.DATASOURCENAME + "maxpoolsize"));
            
            triset.cpdsmeta.setDriverClass((String) p.getProp("cpds.hsqldb001meta.DriverClass"));
            triset.cpdsmeta.setJdbcUrl((String) p.getProp("cpds.hsqldb001meta.url")); //Can set to "localhost jarpath"
            triset.cpdsmeta.setUser((String) p.getProp("cpds.hsqldb001meta.user"));
            triset.cpdsmeta.setPassword((String) p.getProp("cpds.hsqldb001meta.password"));
            triset.cpdsmeta.setMinPoolSize((Integer) p.getProp("cpds.hsqldb001.minpoolsize"));
            triset.cpdsmeta.setMaxPoolSize((Integer) p.getProp("cpds.hsqldb001.maxpoolsize"));
//            assert p.getClass().getName().equals("abc");
        } catch (Exception e) {
            Res.logger.log(Level.SEVERE, this.getClass().getName());
        }

    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSomeMethod() {
        // TODO review the generated test code and remove the default call to fail.
        
        fail("The test case is a prototype.");
    }
}
