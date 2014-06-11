/*
 * @Author Yetaai
 * yetaai@gmail.com
 * Local resources
 */

package com.boldust.general;

import com.boldust.general.LocalDAO;
import java.io.*;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.*;
import javax.xml.stream.*;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.commons.configuration.*;
import org.apache.commons.configuration.ConfigurationException;
import org.w3c.dom.*;
import org.xml.sax.*;

public class Res {
 //   private static String PROPERTYFILE="config.properties";
    public final static byte BYTE_FALSE = 0;
    public final static byte BYTE_TRUE = 1; //Field Override dataele
    public final static byte BYTE_EITHER_2 = 12; //Greater than 12 meaing either for each group.
    public final static byte BYTE_EITHER_3 = 13; //Greater than 12 meaing either for each group.
    public final static byte SQLTYPE_TABLE = 1;
    public final static byte SQLTYPE_SQL = 2;
    public final static String PROPERTYCLASS = "PropertiesDB";
    public final static String HSQL_DRIVER_NAME = "org.hsqldb.jdbcDriver";
    public static String IPADDRESS = "localhost";
    public static String PORT = "3306";
    public static String DBNAME = "boldust";
    protected final static String PROPDBUSER = "boldust";
    protected final static String PROPDBPASS = "boldust";
    	
//    public static String DATABASE_ADDRESS = "jdbc:hsqldb:hsql://" + IPADDRESS + ":" + PORT + "/hsql1";    
//    public static String DATABASE_ADDRESS = "jdbc:mysql://localhost:3306/dictionary?useUnicode=yes&characterEncoding=UTF-8";    
//    public static String DATABASE_ADDRESS = "jdbc:hsqldb:file:z:\\wikitionary\\testdb";
    public static String DATABASE_ADDRESS = "jdbc:hsqldb:file:";
    //public final static String DATABASE_ADDRESS = "jdbc:hsqldb:hsql://localhost:9002/hsql1";    
        
    public final static LocalDAO localdao = new LocalDAO();
    public static final Logger logger = Logger.getLogger("com.boldust.general");
    public static String DATASOURCENAME = "hsqldb001";
    static {
/*        try {
            prop = new PropertiesConfiguration(PROPERTYFILE);
        } catch (ConfigurationException ce) {

            ce.printStackTrace();
        }
*/
        try {
/*            if (prop.getString("dbdir") == null ) {
                DATABASE_ADDRESS = DATABASE_ADDRESS + (new File(".").getCanonicalPath()) + "\\db\\db";
            } else {
                DATABASE_ADDRESS = DATABASE_ADDRESS + (new File(".").getCanonicalPath()) + "\\" +
                        prop.getString("dbdir") + "\\db";
            }
*/
            DATABASE_ADDRESS = DATABASE_ADDRESS + Res.getJarPath() + "\\db\\" + Res.DBNAME;
            logger.log(Level.SEVERE, "Properties Database: DB address is got successfully" + DATABASE_ADDRESS);
//        } catch (IOException ioe) {
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Properties Database: Failed to get Database address" + DATABASE_ADDRESS);
            //ioe.printStackTrace();
        }
    }
    /**
     * @throws ConfigurationException
     */
    public Res() {
        try {
            int a = 3;
        } catch(Exception ce) {
            ce.printStackTrace();
        }
    }

    public static Document loadXMLFromString(String xml) throws Exception
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        return builder.parse(is);
    }
    public static Document loadXMLFromFile(File file) throws Exception
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new InputStreamReader(new FileInputStream(file), "UTF-8"));
        is.setEncoding("UTF-8");
        return builder.parse(is);
    }
    public static String getJarPath(){
        Class clazz = new Being().getClass();
        String path = clazz.getName().replace('.', '/');
        path = clazz.getResource("/" + path + ".class").toString();
        path = path.substring(0, path.indexOf(".jar"));
        path = path.substring(path.lastIndexOf(':')-1, path.lastIndexOf('/')+1).replace('%', ' ');
        String s = "";
        for (int k=0; k<path.length(); k++) {
            s += path.charAt(k);
            if (path.charAt(k) == ' ') k += 2;
        }
        return s.replace('/', File.separatorChar);
    }
    public static void setLogLevel() {
        try {
            String s1 = (String) new BoldustProperties().getProp("com.boldust.loglevel");
            if (s1 == null) {
                int i0 = localdao.update("create table if not exists properties(name char(50), value char(30), type char(10), primary key (name))");
                localdao.executeUpdate(i0);
                i0 = localdao.update("insert into properties values (?, ?, ?)");
                localdao.setString(1, "com.boldust.loglevel", i0);
                localdao.setString(2, "Level.ALL", i0);
                localdao.setString(3, "String", i0);
                localdao.executeUpdate(i0);
                localdao.commit();
            }
            logger.setLevel(Level.parse(s1));
        } catch (Exception e) {
            logger.log(Level.SEVERE, Res.class.getName() + e.getMessage());
        }
    }
}