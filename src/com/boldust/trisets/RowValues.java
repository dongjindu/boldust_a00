/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boldust.trisets;

import com.boldust.general.Prop;
import java.util.ArrayList;

/**
 *
 * @author oefish
 */
public class RowValues {
    public ArrayList<FieldValues> rvs = new ArrayList<>();
    private Fields flds = null;
    public Prop buffersize = null;
    
    public void load(String selectstring) {
        rvs.get(0).setRowValues(this);
    }
}
