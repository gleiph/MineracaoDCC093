/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.ice.dcc.mineracaolocal.jasome.cli;

import br.ufjf.ice.dcc.mineracaolocal.cli.CLIExecute;
import br.ufjf.ice.dcc.mineracaolocal.cli.CLIExecution;
import br.ufjf.ice.dcc.mineracaolocal.exceptions.NoJavaFilesJasome;
import java.io.IOException;

/**
 *
 * @author gleiph
 */
public class JasomeCLI {

    public static String execute(String jasomePath, String projectPath) throws IOException, NoJavaFilesJasome {
        boolean error = false;
        StringBuilder result = new StringBuilder();
        String command = jasomePath + " " + projectPath;

        CLIExecution execution = CLIExecute.executeJasome(command, ".");

        if (execution.getError() != null && execution.getError().size() > 0) {
            for (String line : execution.getError()) {

                if (!line.startsWith("[main] WARN") && !line.startsWith("Problem stacktrace :")
                        && !line.startsWith("  ") && line.length() > 0) {

                    if (line.startsWith("[main] ERROR") && line.contains("- No .java files found")) {
                        throw new NoJavaFilesJasome();
                    }

                    error = true;
                }
            }
            for (String line : execution.getError()) {
                System.out.println(line);
            }

        }

        if (error) {
            return null;
        } else {
            for (String line : execution.getOutput()) {
                result.append(line).append("\n");
            }
            return result.toString();
        }

    }

}
