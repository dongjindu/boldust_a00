/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boldust.trisets;

import java.util.ArrayList;

/**
 *
 * @author oefish
 */
public class FieldValues {
    private RowValues rvs = null;
    private Fields flds = null;
    
    private ArrayList<Object> fvs = new ArrayList<>();
    public void setFieldValues(ArrayList<Object> al) {
        this.fvs = al;
    }
    public ArrayList<Object> getFieldValues() {
        return this.fvs;
    }
    public void setRowValues(RowValues rvs) {
        this.rvs = rvs;
    }
    public RowValues getRowValues() {
        return this.rvs;
    }
}
