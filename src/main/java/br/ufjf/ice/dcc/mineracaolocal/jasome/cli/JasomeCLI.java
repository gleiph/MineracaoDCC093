/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.ice.dcc.mineracaolocal.jasome.cli;

import br.ufjf.ice.dcc.mineracaolocal.cli.CLIExecute;
import br.ufjf.ice.dcc.mineracaolocal.cli.CLIExecution;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gleiph
 */
public class JasomeCLI {

    public static String execute(String jasomePath, String projectPath) throws IOException {

        StringBuffer result = new StringBuffer();
        String command = jasomePath + " " + projectPath;

        CLIExecution execution = CLIExecute.execute(command, ".");

        if(execution.getError() != null && execution.getError().size() > 0)
            return null;
        
        for (String line : execution.getOutput()) {
            result.append(line).append("\n");
        }

        return result.toString();

    }

    public static void main(String[] args) {
        String jasomePath = "/Users/gleiph/repositories/jasome/build/distributions/jasome/bin/jasome";
        String projectPath = "/Users/gleiph/NetBeansProjects/MineracaoLocal";

        try {
            String execute = JasomeCLI.execute(jasomePath, projectPath);
            System.out.println(execute);
        } catch (IOException ex) {
            Logger.getLogger(JasomeCLI.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

}
