/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.ice.dcc.mineracaolocal.exceptions;

/**
 *
 * @author gleiph
 */
public class NoJavaFilesJasome extends Exception{

    public NoJavaFilesJasome() {
        super("There is no Java files in the current version");
    }
    
}
