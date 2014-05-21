/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boldust.math001;

import java.math.BigInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author oefish
 */
public class Math001Test {
    
    public Math001Test() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of pow001 method, of class Math001.
     */
    @Test
    public void testPow001() {
        System.out.println("pow001");
        Logger l = Logger.getLogger("powtest001");
//        l.addHandler(new java.util.logging.ConsoleHandler());
        l.log(Level.SEVERE, "Powtest001");
        int base = 20;
        int exp = 5;
        BigInteger expResult = new BigInteger(Integer.valueOf(3200000).toString());
        BigInteger result1 = new BigInteger(Integer.valueOf(3200000).toString());
        BigInteger result = Math001.pow001(base, exp);
        System.out.println(expResult.equals(result));
        assertEquals(expResult, result);
        assertEquals(result1, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
}
