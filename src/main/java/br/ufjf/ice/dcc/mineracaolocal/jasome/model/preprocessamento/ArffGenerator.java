/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.ice.dcc.mineracaolocal.jasome.model.preprocessamento;

import java.util.List;

/**
 *
 * @author gleiph
 */
public class ArffGenerator {

    public static String generate(List<CommitPreProcessed> commits) {
        StringBuilder output = new StringBuilder();
        output.append("@relation commits\n");
        output.append("\n");
        output.append("@attribute commiter string\n");
        output.append("@attribute sha string\n");
        output.append("@attribute packages string\n");
        output.append("@attribute classes string\n");
        output.append("@attribute methods string\n");
        output.append("@attribute tloc string\n");
        output.append("\n");
        output.append("@data\n");
        
        for (CommitPreProcessed commit : commits) {
            output.append(commit.toString()).append("\n");
        }
        
        
        return output.toString();
    }

}
