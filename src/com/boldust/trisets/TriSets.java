/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.boldust.trisets;

import com.boldust.general.Tree;

/**
 *
 * @author oefish
 */
public class TriSets{
    private Tree<Fields> fieldtrees;

    public TriSets() throws Exception {
        this.fieldtrees = new Tree<Fields>(new Fields(""));
    }
}
