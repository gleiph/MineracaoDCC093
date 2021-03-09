/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.ice.dcc.mineracaolocal;

import br.ufjf.ice.dcc.mineracaolocal.exceptions.NoJavaFilesJasome;
import br.ufjf.ice.dcc.mineracaolocal.jasome.cli.JasomeCLI;
import br.ufjf.ice.dcc.mineracaolocal.jasome.model.Project;
import br.ufjf.ice.dcc.mineracaolocal.sax.ParserJasome;
import br.ufjf.ice.dcc.mineracaolocal.vcs.CommitData;
import br.ufjf.ice.dcc.mineracaolocal.vcs.Git;
import br.ufjf.ice.dcc.mineracaolocal.vcs.utils.HideCommiterName;
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
        String projectPath = "/Users/gleiph/repositories/cobaia";

        try {
            List<CommitData> listVersionsCommitData = Git.listVersionsCommitData(projectPath);
            HideCommiterName hideCommiterName = new HideCommiterName();
            System.out.println("Commiter, SHA, Packages, Classes, Methods");
            for (CommitData commitData : listVersionsCommitData) {

                Git.reset(projectPath);
                Git.clean(projectPath);
                Git.checkout(projectPath, commitData.getSha());

                try {

                    String xmlContent = JasomeCLI.execute(jasomePath, projectPath);

                    ParserJasome parserJasome = new ParserJasome();
                    parserJasome.contentParsing(xmlContent);
                    Project project = parserJasome.getProject();

                    System.out.println(hideCommiterName.hideCommiterName(commitData.getCommiter())
                            + "," + commitData.getSha() + "," + project.getNumberPackages()
                            + "," + project.getNumberClasses() + "," + project.getNumberMethods()
                            + "," + project.getTloc());
                } catch (NoJavaFilesJasome ex) {
                    System.out.println(hideCommiterName.hideCommiterName(commitData.getCommiter())
                            + "," + commitData.getSha() + "," + 0
                            + "," + 0 + "," + 0 + "," + 0);

                }
            }

        } catch (IOException ex) {
            Logger.getLogger(Mineracao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
