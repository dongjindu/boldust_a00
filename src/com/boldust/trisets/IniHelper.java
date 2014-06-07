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
            triset.cpdsmeta.getConnection().prepareStatement("create table if exists dicttypes(dicttype char(30), dictstatus char(1), "
                    + "dictvers char(20), javaclass char(20), javaver char(20), "
                    + "length int, precision int, scale int, outputlength int, "
                    + "primary key(dicttype, dictstatus, dictvers)").execute();
            triset.cpdsmeta.getConnection().prepareStatement("create table if exists dbtypes(dicttype char(30), dictvers char(20),"
                    + "driver char(30), dbtype char(20), dbversion char(20), primary key(dicttype, dictvers, driver, dbtype, dbversion)) ").execute();
            triset.cpdsmeta.getConnection().prepareStatement("create table if exists diceles(dictele char(30), dicttype char(30),"
                    + "stext char(15), ltext char(15), primary key (dictele))").execute();
            triset.cpdsmeta.getConnection().prepareStatement("create table if exists dictsqls(name char(20)"
                    + "sqlstring varchar(500), comment char(30) ").execute();
            triset.cpdsmeta.getConnection().prepareStatement("create table if exists dicteles("
                    + "name char(20), )").execute();
            triset.cpdsmeta.getConnection().prepareStatement("create table if exists rawtypes(driver char(30),"
                    + " version char(10), rawtype char(10), javatype char(10), javavertion"
                    + "length boolean, precision boolean, scale boolean, "
                    + "length0 int, precision0 int, scale 0, "
                    + "primary key(driver, version, rawtype, javatype, javaversion"
                    + "))").execute();

            triset.cpdsmeta.getConnection().prepareStatement("create table if exists fields(field char(30),"
                    + " type char(10), length int, precision int, scale int, primary key(field))").execute();
        } catch (Exception e) {
            logger.log(Level.SEVERE, Res.class.getName() + "Cannot initial Meta DB");
        }
    }

}
/* ABAP dictionary data types shall be mapped to javatype
 ACCP	Posting period YYYYMM
 CHAR	Character String
 CLNT	Client
 CUKY	Currency key, referenced by CURR fields
 CURR	Currency field, stored as DEC
 D16D	Decimal Floating Point, 16 Digits, DEC on Database
 D16R	Decimal Floating Point, 16 Digits,  RAW on Database
 D16S	Decimal Floating Point. 16 Digits, with Scale Field
 D34D	Decimal Floating Point, 34 Digits, DEC on Database
 D34R	Decimal Floating Point, 34 Digits, RAW on Database
 D34S	Decimal Floating Point, 34 Digits, with Scale Field
 DATS	Date field (YYYYMMDD) stored as char(8)
 DEC	Counter or amount field with comma and sign
 FLTP	Floating point number, accurate to 8 bytes
 INT1	1-byte integer, integer number <= 255
 INT2	2-byte integer, only for length field before LCHR or LRAW
 INT4	4-byte integer, integer number with sign
 LANG	Language key
 LCHR	Long character string, requires preceding INT2 field
 LRAW	Long byte string, requires preceding INT2 field
 NUMC	Character string with only digits
 PREC	Obsolete data type, do not use
 QUAN	Quantity field, points to a unit field with format UNIT
 RAW	Uninterpreted sequence of bytes
 RSTR	Byte String of Variable Length
 SSTR	Short Character String of Variable Length
 STRG	Character String of Variable Length
 TIMS	Time field (hhmmss), stored as char(6)
 UNIT	Unit key for QUAN fields
 VARC	Long character string, no longer supported from Rel. 3.0
 */
