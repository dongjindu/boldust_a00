/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * Copied from NightCracker C version but actually not for BigInteger.
 */
package com.boldust.math001;

import java.math.BigInteger;

/**
 *
 * @author oefish
 */
public class Math001 {

    public static BigInteger pow001(int base, int exp) {
        int bit_set[] = new int[]{
            0, 1, 2, 2, 3, 3, 3, 3,
            4, 4, 4, 4, 4, 4, 4, 4,
            5, 5, 5, 5, 5, 5, 5, 5,
            5, 5, 5, 5, 5, 5, 5, 5,
            6, 6, 6, 6, 6, 6, 6, 6,
            6, 6, 6, 6, 6, 6, 6, 6,
            6, 6, 6, 6, 6, 6, 6, 6,
            6, 6, 6, 6, 6, 6, 6, 255, // anything past 63 is a guaranteed overflow with base > 1
            255, 255, 255, 255, 255, 255, 255, 255,
            255, 255, 255, 255, 255, 255, 255, 255,
            255, 255, 255, 255, 255, 255, 255, 255,
            255, 255, 255, 255, 255, 255, 255, 255,
            255, 255, 255, 255, 255, 255, 255, 255,
            255, 255, 255, 255, 255, 255, 255, 255,
            255, 255, 255, 255, 255, 255, 255, 255,
            255, 255, 255, 255, 255, 255, 255, 255,
            255, 255, 255, 255, 255, 255, 255, 255,
            255, 255, 255, 255, 255, 255, 255, 255,
            255, 255, 255, 255, 255, 255, 255, 255,
            255, 255, 255, 255, 255, 255, 255, 255,
            255, 255, 255, 255, 255, 255, 255, 255,
            255, 255, 255, 255, 255, 255, 255, 255,
            255, 255, 255, 255, 255, 255, 255, 255,
            255, 255, 255, 255, 255, 255, 255, 255,
            255, 255, 255, 255, 255, 255, 255, 255,
            255, 255, 255, 255, 255, 255, 255, 255,
            255, 255, 255, 255, 255, 255, 255, 255,
            255, 255, 255, 255, 255, 255, 255, 255,
            255, 255, 255, 255, 255, 255, 255, 255,
            255, 255, 255, 255, 255, 255, 255, 255,
            255, 255, 255, 255, 255, 255, 255, 255,
            255, 255, 255, 255, 255, 255, 255, 255,};
        BigInteger result = new BigInteger(Integer.valueOf("1").toString());
        BigInteger bigbase = new BigInteger(Integer.valueOf(base).toString());
        switch (bit_set[exp]) {
            case 255: // we use 255 as an overflow marker and return 0 on overflow/underflow
                if (base == 1) {
                    return new java.math.BigInteger("1");
                }
                if (base == -1) {
                    return new java.math.BigInteger(Integer.valueOf(1 - 2 * (exp & 1)).toString());
                }
                return new java.math.BigInteger(Integer.valueOf(0).toString());
            case 6:
                if ((exp & 1) == 1) {
                    result = result.multiply(bigbase);
                }
                exp >>= 1;
                bigbase = bigbase.multiply(bigbase);
            case 5:
                if ((exp & 1) == 1) {
                    result = result.multiply(bigbase);
                }
                exp >>= 1;
                bigbase = bigbase.multiply(bigbase);
            case 4:
                if ((exp & 1) == 1) {
                    result = result.multiply(bigbase);
                }
                exp >>= 1;
                bigbase = bigbase.multiply(bigbase);
            case 3:
                if ((exp & 1) == 1) {
                    result = result.multiply(bigbase);
                }
                exp >>= 1;
                bigbase = bigbase.multiply(bigbase);
            case 2:
                if ((exp & 1) == 1) {
                    result = result.multiply(bigbase);
                }
                exp >>= 1;
                bigbase = bigbase.multiply(bigbase);
            case 1:
                if ((exp & 1) == 1) {
                    result = result.multiply(bigbase);
                }
            default:
                return result;
        }
    }
}
