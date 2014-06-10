/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boldust.trisets;

import com.boldust.general.BoldustProperties;
import com.boldust.general.Prop;
import com.boldust.general.PropertiesDB;
import com.boldust.general.Res;
import static com.boldust.general.Res.*;
import java.sql.PreparedStatement;
import java.util.logging.Level;

/**
 *
 * @author oefish
 */
public class IniHelper {

    public static void iniMetaDB() {
        try {
            Prop p = new BoldustProperties(Res.PROPERTYCLASS);
            p.setProp("triset.ddver", "1.0", "string");
            TriSet triset = new TriSet();
            triset.cpdsmeta.getConnection().prepareStatement("create table if exists ddtypes(ddtype char(30), ddstatus char(1), "
                    + "ddver char(20), javaclass char(20), javaver char(20), "
                    + "length int, precision int, scale int, outputlength int, "
                    + "elength small, eprecision small, escale small, eoutputlength small, " //0: N/A, 1, to be specified 
                    + "primary key(ddtype, ddstatus, ddver)").execute();
            triset.cpdsmeta.getConnection().prepareStatement("create table if exists dbtypes(ddtype char(30), ddver char(20)," //Shall be 2 unique index
                    + "driver char(30), dbtype char(20), dbver char(20), primary key(ddtype, ddver), unique index (driver, dbtype, dber)) ").execute();
            triset.cpdsmeta.getConnection().prepareStatement("create table if exists ddeles(ddele char(30), ddtype char(30),"
                    + "length int, precision int, scale int, outputlength int,"
                    + "ddver char(20), stext char(15), ltext char(15), "
                    + "javaclass char(20), javaver char(20),"
                    + "primary key (ddele))").execute();
            triset.cpdsmeta.getConnection().prepareStatement("create table if exists ddsqls(sqlname char(20)"
                    + "sqlstring char(500), primary key (sqlname))").execute();
            triset.cpdsmeta.getConnection().prepareStatement("create table if exists ddsqlfields(sqlname char(20),"
                    + "field char(30), ddele char(30), reffeild varchar(30), primary key (sqlname, field))").execute();
            PreparedStatement ps = triset.cpdsmeta.getConnection().prepareStatement("insert into ddtypes"
                    + "(ddtype, ddstatus, ddver, javaclass, javaver"
                    + "length, precision, scale, outputlength,"
                    + "elength, eprecision, escale, eoutputlength ) values "
                    + "(?, ?, ?, ?, ?,     ?, ?, ?, ?,   ?, ?, ?, ?)");
            setddtype(ps);
            triset.cpdsmeta.getConnection().prepareStatement("");
            triset.cpdsmeta.getConnection().prepareStatement("");
        } catch (Exception e) {
            logger.log(Level.SEVERE, Res.class.getName() + "Exception: Cannot initial Meta DB");
        }
    }
    private static void setddtype(PreparedStatement ps) {
        try {
            ps.setString(1, "String"); //Will use varchar as storage UTF-8
            ps.setString(2, "X"); //ddstatus, X means active
            ps.setString(3, "1.0"); //ddver
            ps.setString(4, "String");//javaclass
            ps.setString(5, ""); //java version
            ps.setInt(6, 1); //Length
            ps.setInt(7, 0); //Precision is the signifcant numbers. Either precision or Scale can be set but not both.
            ps.setInt(8, 0); //Scale is the number of digits to the right of decimal point
            ps.setInt(9, 20);//Output length, Default on ddtype level
            ps.setInt(10, Res.BYTETRUE);
            ps.setInt(11, Res.BYTEFALSE);
            ps.setInt(12, Res.BYTEFALSE);
            ps.setInt(13, Res.BYTETRUE);
                            //could be variable for this one. Meaning output customized implementation could set it extra
            ps.addBatch();
            ps.setString(1, "Integer"); //Will use varchar as storage UTF-8
            ps.setString(2, "X"); //ddstatus, X means active
            ps.setString(3, "1.0"); //ddver
            ps.setString(4, "Long");//javaclass
            ps.setString(5, ""); //java version
            ps.setInt(6, 0); //Length
            ps.setInt(7, 0); //Precision is the signifcant numbers. Either precision or Scale can be set but not both.
            ps.setInt(8, 0); //Scale is the number of digits to the right of decimal point
            ps.setInt(9, 12);//Output length, Default on ddtype level
            ps.setInt(10, Res.BYTEFALSE);
            ps.setInt(11, Res.BYTEFALSE);
            ps.setInt(12, Res.BYTEFALSE);
            ps.setInt(13, Res.BYTETRUE);
                            //could be variable for this one. Meaning output customized implementation could set it extra
            ps.addBatch();
            ps.setString(1, "BigDecimal"); //Will use varchar as storage UTF-8
            ps.setString(2, "X"); //ddstatus, X means active
            ps.setString(3, "1.0"); //ddver
            ps.setString(4, "BigDecimal");//javaclass
            ps.setString(5, ""); //java version
            ps.setInt(6, 0); //Length
            ps.setInt(7, 0); //Precision is the signifcant numbers. Either precision or Scale can be set but not both.
            ps.setInt(8, 0); //Scale is the number of digits to the right of decimal point
            ps.setInt(9, 20);//Output length, Default on ddtype level
            ps.setInt(10, Res.BYTEFALSE);
            ps.setInt(11, Res.BYTEEITHER2);
            ps.setInt(12, Res.BYTEEITHER2);
            ps.setInt(13, Res.BYTETRUE);
                            //could be variable for this one. Meaning output customized implementation could set it extra
            ps.addBatch();

            ps.setString(1, "Double"); //Will use varchar as storage UTF-8
            ps.setString(2, "X"); //ddstatus, X means active
            ps.setString(3, "1.0"); //ddver
            ps.setString(4, "Double");//javaclass
            ps.setString(5, ""); //java version
            ps.setInt(6, 0); //Length
            ps.setInt(7, 0); //Precision is the signifcant numbers. Either precision or Scale can be set but not both.
            ps.setInt(8, 0); //Scale is the number of digits to the right of decimal point
            ps.setInt(9, 16);//Output length, Default on ddtype level
            ps.setInt(10, Res.BYTEFALSE);
            ps.setInt(11, Res.BYTEFALSE);
            ps.setInt(12, Res.BYTEFALSE);
            ps.setInt(13, Res.BYTETRUE);
                            //could be variable for this one. Meaning output customized implementation could set it extra
            ps.addBatch();
            
            ps.setString(1, "Float"); //Will use varchar as storage UTF-8
            ps.setString(2, "X"); //ddstatus, X means active
            ps.setString(3, "1.0"); //ddver
            ps.setString(4, "Float");//javaclass
            ps.setString(5, ""); //java version
            ps.setInt(6, 0); //Length
            ps.setInt(7, 0); //Precision is the signifcant numbers. Either precision or Scale can be set but not both.
            ps.setInt(8, 0); //Scale is the number of digits to the right of decimal point
            ps.setInt(9, 15);//Output length, Default on ddtype level
            ps.setInt(10, Res.BYTEFALSE);
            ps.setInt(11, Res.BYTEFALSE);
            ps.setInt(12, Res.BYTEFALSE);
            ps.setInt(13, Res.BYTETRUE);
                            //could be variable for this one. Meaning output customized implementation could set it extra
            ps.addBatch();
            ps.setString(1, "QuanBigDecimal"); //Will use varchar as storage UTF-8
            ps.setString(2, "X"); //ddstatus, X means active
            ps.setString(3, "1.0"); //ddver
            ps.setString(4, "BigDecimal");//javaclass
            ps.setString(5, ""); //java version
            ps.setInt(6, 0); //Length
            ps.setInt(7, 0); //Precision is the signifcant numbers. Either precision or Scale can be set but not both.
            ps.setInt(8, 0); //Scale is the number of digits to the right of decimal point
            ps.setInt(9, 20);//Output length, Default on ddtype level
            ps.setInt(10, Res.BYTEFALSE);
            ps.setInt(11, Res.BYTEEITHER2);
            ps.setInt(12, Res.BYTEEITHER2);
            ps.setInt(13, Res.BYTETRUE);
                            //could be variable for this one. Meaning output customized implementation could set it extra
            ps.addBatch();
            ps.setString(1, "AmtBigDecimal"); //Will use varchar as storage UTF-8
            ps.setString(2, "X"); //ddstatus, X means active
            ps.setString(3, "1.0"); //ddver
            ps.setString(4, "BigDecimal");//javaclass
            ps.setString(5, ""); //java version
            ps.setInt(6, 0); //Length
            ps.setInt(7, 0); //Precision is the signifcant numbers. Either precision or Scale can be set but not both.
            ps.setInt(8, 0); //Scale is the number of digits to the right of decimal point
            ps.setInt(9, 20);//Output length, Default on ddtype level
            ps.setInt(10, Res.BYTEFALSE);
            ps.setInt(11, Res.BYTEEITHER2);
            ps.setInt(12, Res.BYTEEITHER2);
            ps.setInt(13, Res.BYTETRUE);
                            //could be variable for this one. Meaning output customized implementation could set it extra
            ps.addBatch();
            ps.setString(1, "UnitKey"); //Will use varchar as storage UTF-8
            ps.setString(2, "X"); //ddstatus, X means active
            ps.setString(3, "1.0"); //ddver
            ps.setString(4, "String");//javaclass
            ps.setString(5, ""); //java version
            ps.setInt(6, 0); //Length
            ps.setInt(7, 0); //Precision is the signifcant numbers. Either precision or Scale can be set but not both.
            ps.setInt(8, 0); //Scale is the number of digits to the right of decimal point
            ps.setInt(9, 5);//Output length, Default on ddtype level
            ps.setInt(10, Res.BYTEFALSE);
            ps.setInt(11, Res.BYTEFALSE);
            ps.setInt(12, Res.BYTEFALSE);
            ps.setInt(13, Res.BYTEFALSE);
                            //could be variable for this one. Meaning output customized implementation could set it extra
            ps.addBatch();
            ps.setString(1, "CurrKey"); //Will use varchar as storage UTF-8
            ps.setString(2, "X"); //ddstatus, X means active
            ps.setString(3, "1.0"); //ddver
            ps.setString(4, "String");//javaclass
            ps.setString(5, ""); //java version
            ps.setInt(6, 0); //Length
            ps.setInt(7, 0); //Precision is the signifcant numbers. Either precision or Scale can be set but not both.
            ps.setInt(8, 0); //Scale is the number of digits to the right of decimal point
            ps.setInt(9, 5);//Output length, Default on ddtype level
            ps.setInt(10, Res.BYTEFALSE);
            ps.setInt(11, Res.BYTEFALSE);
            ps.setInt(12, Res.BYTEFALSE);
            ps.setInt(13, Res.BYTEFALSE);
                            //could be variable for this one. Meaning output customized implementation could set it extra
            ps.addBatch();
            ps.setString(1, "TimeStamp"); //Will use varchar as storage UTF-8
            ps.setString(2, "X"); //ddstatus, X means active
            ps.setString(3, "1.0"); //ddver
            ps.setString(4, "String");//javaclass
            ps.setString(5, ""); //java version
            ps.setInt(6, 16); //Length
            ps.setInt(7, 0); //Precision is the signifcant numbers. Either precision or Scale can be set but not both.
            ps.setInt(8, 0); //Scale is the number of digits to the right of decimal point
            ps.setInt(9, 22);//Output length, Default on ddtype level
            ps.setInt(10, Res.BYTEFALSE);
            ps.setInt(11, Res.BYTEFALSE);
            ps.setInt(12, Res.BYTEFALSE);
            ps.setInt(13, Res.BYTEFALSE);
                            //could be variable for this one. Meaning output customized implementation could set it extra
            ps.addBatch();
            ps.setString(1, "Date"); //Will use varchar as storage UTF-8
            ps.setString(2, "X"); //ddstatus, X means active
            ps.setString(3, "1.0"); //ddver
            ps.setString(4, "String");//javaclass
            ps.setString(5, ""); //java version
            ps.setInt(6, 8); //Length
            ps.setInt(7, 0); //Precision is the signifcant numbers. Either precision or Scale can be set but not both.
            ps.setInt(8, 0); //Scale is the number of digits to the right of decimal point
            ps.setInt(9, 10);//Output length, Default on ddtype level
            ps.setInt(10, Res.BYTEFALSE);
            ps.setInt(11, Res.BYTEFALSE);
            ps.setInt(12, Res.BYTEFALSE);
            ps.setInt(13, Res.BYTEFALSE);
                            //could be variable for this one. Meaning output customized implementation could set it extra
            ps.addBatch();
            ps.setString(1, "Time"); //Will use varchar as storage UTF-8
            ps.setString(2, "X"); //ddstatus, X means active
            ps.setString(3, "1.0"); //ddver
            ps.setString(4, "String");//javaclass
            ps.setString(5, ""); //java version
            ps.setInt(6, 8); //Length
            ps.setInt(7, 0); //Precision is the signifcant numbers. Either precision or Scale can be set but not both.
            ps.setInt(8, 0); //Scale is the number of digits to the right of decimal point
            ps.setInt(9, 11);//Output length, Default on ddtype level
            ps.setInt(10, Res.BYTEFALSE);
            ps.setInt(11, Res.BYTEFALSE);
            ps.setInt(12, Res.BYTEFALSE);
            ps.setInt(13, Res.BYTEFALSE);
            ps.addBatch();
            ps.setString(1, "UTCTime"); //Will use varchar as storage UTF-8
            ps.setString(2, "X"); //ddstatus, X means active
            ps.setString(3, "1.0"); //ddver
            ps.setString(4, "Long");//javaclass
            ps.setString(5, ""); //java version
            ps.setInt(6, 0); //Length
            ps.setInt(7, 0); //Precision is the signifcant numbers. Either precision or Scale can be set but not both.
            ps.setInt(8, 0); //Scale is the number of digits to the right of decimal point
            ps.setInt(9, 15);//Output length, Default on ddtype level
            ps.setInt(10, Res.BYTEFALSE);
            ps.setInt(11, Res.BYTEFALSE);
            ps.setInt(12, Res.BYTEFALSE);
            ps.setInt(13, Res.BYTEFALSE);
            ps.addBatch();
            ps.setString(1, "RAW"); //Will use varchar as storage UTF-8
            ps.setString(2, "X"); //ddstatus, X means active
            ps.setString(3, "1.0"); //ddver
            ps.setString(4, "Byte[]");//javaclass
            ps.setString(5, ""); //java version
            ps.setInt(6, 0); //Length
            ps.setInt(7, 0); //Precision is the signifcant numbers. Either precision or Scale can be set but not both.
            ps.setInt(8, 0); //Scale is the number of digits to the right of decimal point
            ps.setInt(9, 20);//Output length, Default on ddtype level
            ps.setInt(10, Res.BYTEFALSE);
            ps.setInt(11, Res.BYTEFALSE);
            ps.setInt(12, Res.BYTEFALSE);
            ps.setInt(13, Res.BYTEFALSE);
            ps.addBatch();
            ps.setString(1, "Object"); //Will use varchar as storage UTF-8
            ps.setString(2, "X"); //ddstatus, X means active
            ps.setString(3, "1.0"); //ddver
            ps.setString(4, "Object");//javaclass
            ps.setString(5, ""); //java version
            ps.setInt(6, 0); //Length
            ps.setInt(7, 0); //Precision is the signifcant numbers. Either precision or Scale can be set but not both.
            ps.setInt(8, 0); //Scale is the number of digits to the right of decimal point
            ps.setInt(9, 20);//Output length, Default on ddtype level
            ps.setInt(10, Res.BYTEFALSE);
            ps.setInt(11, Res.BYTEFALSE);
            ps.setInt(12, Res.BYTEFALSE);
            ps.setInt(13, Res.BYTEFALSE);
            ps.addBatch();
            ps.execute();

        } catch (Exception e) {
            logger.log(Level.SEVERE, Res.class.getName() + "Exception: Cannot set data diction data type ");
        }
    }
}
/*
String: 
Long: Integer
BigDecimal: Precision and Decimal points
Double: double
Float: float
QuanBigDecimal
AmtBigDecimal
Unitkey: Unit key
Currkey: Currency key
Timestamp: YYYYMMDDHHMMSSmm
Date: YYYYMMDD
Time: HHMMSSmm
UTCtime: big integer
Raw: Raw feild (blob)
Object: Serialization of serializable java object
*/

/* ABAP dictionary data types shall be mapped to javatype
 X ACCP	Posting period YYYYMM
 CHAR	Character String
 CLNT	Client
 X CUKY	Currency key, referenced by CURR fields
 X CURR	Currency field, stored as DEC
 D16D	Decimal Floating Point, 16 Digits, DEC on Database
 D16R	Decimal Floating Point, 16 Digits,  RAW on Database
 D16S	Decimal Floating Point. 16 Digits, with Scale Field
 D34D	Decimal Floating Point, 34 Digits, DEC on Database
 D34R	Decimal Floating Point, 34 Digits, RAW on Database
 D34S	Decimal Floating Point, 34 Digits, with Scale Field
 X DATS	Date field (YYYYMMDD) stored as char(8)
 DEC	Counter or amount field with comma and sign
 FLTP	Floating point number, accurate to 8 bytes
 INT1	1-byte integer, integer number <= 255
 INT2	2-byte integer, only for length field before LCHR or LRAW
 INT4	4-byte integer, integer number with sign
 LANG	Language key
 LCHR	Long character string, requires preceding INT2 field
 LRAW	Long byte string, requires preceding INT2 field
 X NUMC	Character string with only digits
 PREC	Obsolete data type, do not use
 X QUAN	Quantity field, points to a unit field with format UNIT
 X RAW	Uninterpreted sequence of bytes
 RSTR	Byte String of Variable Length
 SSTR	Short Character String of Variable Length
 X STRG	Character String of Variable Length
 X TIMS	Time field (hhmmss), stored as char(6)
 X UNIT	Unit key for QUAN fields
 VARC	Long character string, no longer supported from Rel. 3.0
 */
