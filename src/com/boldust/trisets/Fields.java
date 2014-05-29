/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boldust.trisets;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oefish
 */
public class Fields {
    private boolean initiated = false;
    private ArrayList<Object> fields = new ArrayList<> ();
    public Fields(String[] fieldnames) {
        for (int i = 0; i < fieldnames.length; i++ )  {
            fields.add(new Field(fieldnames[i]));
        }
        this.initiated = true;
    }
    public Fields(String[][] fieldnames) throws Exception {
        if ( fieldnames[0].length > 2 ) {
                Logger logger = Logger.getLogger("Feilds Logger");
                logger.log(Level.SEVERE, "Table name / Field name constructor only takes 2 parameter for one field");
                this.initiated = false;
                throw new Exception("Field ");
        }
        for (int i = 0; i < fieldnames.length; i++ ) {
            if ( fieldnames[i][1].length() < 1 ) {
                if (fieldnames[i][0].length() < 1) continue;
                fields.add(new Field(fieldnames[i][0]));
                continue;
            }
            if ( fieldnames[i][0].length() < 1 ) continue;
            fields.add(new Field(fieldnames[i][0], fieldnames[i][1]));
        }
        if ( fields.size() < 1 ) {
            Logger logger = Logger.getLogger("Feilds Logger");
            logger.log(Level.SEVERE, "No field initiated");
        }
    }
    public Fields(String tablename) throws Exception {
        //Initiate by table name
    }
    
    public void AddChildFields(Fields fs, int relationship) {
        this.fields.add(fs);
        this.fields.add(Integer.valueOf(relationship));
    }
}
