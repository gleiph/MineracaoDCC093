/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.ice.dcc.mineracaolocal;

import br.ufjf.ice.dcc.mineracaolocal.cli.CLIExecute;
import br.ufjf.ice.dcc.mineracaolocal.cli.CLIExecution;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gleiph
 */
public class Mineracao {

    public static void main(String[] args) {

//        List<String> logVersions = logVersion("/Users/gleiph/repositories/voldemort");
        List<String> developers = listDevelopers("/Users/gleiph/repositories/voldemort");
        for (String line : developers) {
            System.out.println(line);
        }

    }

    public static List<String> logVersion(String repositoryPath) {

        try {
            CLIExecution execution = CLIExecute.execute("git log --pretty=%H", repositoryPath);

            if (execution.getError() != null && !execution.getError().isEmpty()) {
                return null;
            } else {
                return execution.getOutput();
            }

        } catch (IOException ex) {
            Logger.getLogger(Mineracao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static  List<String> listDevelopers(String repositoryPath){
        List<String> developers = new ArrayList<>();
        
        for (String commiter : listCommiters(repositoryPath)) {
            if(!developers.contains(commiter))
                developers.add(commiter);
            
        }
        
        for (String author : listAuthors(repositoryPath)) {
            if(!developers.contains(author))
                developers.add(author);
        }
        
        
        return developers;
    }
    
    public static List<String> listCommiters(String repositoryPath){
        
        List<String> commiters = null;
        try {
            CLIExecution execution = CLIExecute.execute("git log --pretty=%cn", repositoryPath);

            if (execution.getError() != null && !execution.getError().isEmpty()) {
                return null;
            } else {
                commiters = new ArrayList<>();
                for (String commiter : execution.getOutput()) {
                    if(!commiters.contains(commiter))
                        commiters.add(commiter);
                }
                return commiters;
            }

        } catch (IOException ex) {
            Logger.getLogger(Mineracao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static List<String> listAuthors(String repositoryPath){
        
        List<String> authors = null;
        try {
            CLIExecution execution = CLIExecute.execute("git log --pretty=%an", repositoryPath);

            if (execution.getError() != null && !execution.getError().isEmpty()) {
                return null;
            } else {
                authors = new ArrayList<>();
                for (String commiter : execution.getOutput()) {
                    if(!authors.contains(commiter))
                        authors.add(commiter);
                }
                return authors;
            }

        } catch (IOException ex) {
            Logger.getLogger(Mineracao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
