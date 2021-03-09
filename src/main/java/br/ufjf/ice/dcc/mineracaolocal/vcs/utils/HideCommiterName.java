/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.ice.dcc.mineracaolocal.vcs.utils;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author gleiph
 */
public class HideCommiterName {
    
    
    private final Map<String, String> commiters;
    private int id;

    public HideCommiterName() {
        this.commiters = new HashMap<>();
        this.id = 1;
    }

    public String hideCommiterName(String name){
        String hideName = commiters.get(name);
        
        if(hideName == null){
            commiters.put(name, "Commiter "+ (this.id++));
        }
        
        return commiters.get(name);
    }
    
    
    
}
