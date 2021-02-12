/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.ice.dcc.mineracaolocal;

import br.ufjf.ice.dcc.mineracaolocal.jasome.cli.JasomeCLI;
import br.ufjf.ice.dcc.mineracaolocal.jasome.model.Project;
import br.ufjf.ice.dcc.mineracaolocal.sax.ParserJasome;
import br.ufjf.ice.dcc.mineracaolocal.vcs.Git;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gleiph
 */
public class Mineracao {

    public static void main(String[] args) {

        String jasomePath = "/Users/gleiph/repositories/jasome/build/distributions/jasome/bin/jasome";
        String projectPath = "/Users/gleiph/NetBeansProjects/MineracaoLocalCopy";


        try {
            List<String> listVersionsSHA = Git.listVersionsSHA(projectPath);

            for (String sha : listVersionsSHA) {

                System.out.println("Version: " + sha);
                Git.checkout(projectPath, sha);
                String xmlContent = JasomeCLI.execute(jasomePath, projectPath);
                
                ParserJasome parserJasome = new ParserJasome();
                parserJasome.contentParsing(xmlContent);
                Project project = parserJasome.getProject();
                
                System.out.println("\tPackages: " + project.getNumberPackages());
                System.out.println("\tClasses: " + project.getNumberClasses());
                System.out.println("\tMethods: " + project.getNumberMethods());
                
                
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(Mineracao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
