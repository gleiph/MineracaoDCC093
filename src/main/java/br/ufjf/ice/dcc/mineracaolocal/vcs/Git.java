/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.ice.dcc.mineracaolocal.vcs;

import br.ufjf.ice.dcc.mineracaolocal.Mineracao;
import br.ufjf.ice.dcc.mineracaolocal.cli.CLIExecute;
import br.ufjf.ice.dcc.mineracaolocal.cli.CLIExecution;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gleiph
 */
public class Git {

    public static List<String> listVersionsSHA(String repositoryPath) {

        try {
            CLIExecution execution = CLIExecute.execute("git log --all --pretty=%H", repositoryPath);

            if (execution.getError() != null && !execution.getError().isEmpty()) {
                return null;
            } else {
                Collections.reverse(execution.getOutput());
                return execution.getOutput();
            }

        } catch (IOException ex) {
            Logger.getLogger(Mineracao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    public static List<CommitData> listVersionsCommitData(String repositoryPath) {

        try {
            CLIExecution execution = CLIExecute.execute("git log --all --pretty=\"%H-%cn\"", repositoryPath);

            if (execution.getError() != null && !execution.getError().isEmpty()) {
                return null;
            } else {
                Collections.reverse(execution.getOutput());
                List<CommitData> result = new ArrayList<>();
                
                for (String line : execution.getOutput()) {
                    String[] split = line.split("-");
                    
                    result.add(new CommitData(split[0].trim().replaceFirst("\"", ""), split[1].trim().replace("\"", "")));
                }
                return result;
            }

        } catch (IOException ex) {
            Logger.getLogger(Mineracao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static List<String> listDevelopers(String repositoryPath) {
        List<String> developers = new ArrayList<>();

        for (String commiter : listCommiters(repositoryPath)) {
            if (!developers.contains(commiter)) {
                developers.add(commiter);
            }

        }

        for (String author : listAuthors(repositoryPath)) {
            if (!developers.contains(author)) {
                developers.add(author);
            }
        }

        return developers;
    }

    public static List<String> listCommiters(String repositoryPath) {

        List<String> commiters = null;
        try {
            CLIExecution execution = CLIExecute.execute("git log --pretty=%cn", repositoryPath);

            if (execution.getError() != null && !execution.getError().isEmpty()) {
                return null;
            } else {
                commiters = new ArrayList<>();
                for (String commiter : execution.getOutput()) {
                    if (!commiters.contains(commiter)) {
                        commiters.add(commiter);
                    }
                }
                return commiters;
            }

        } catch (IOException ex) {
            Logger.getLogger(Mineracao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static List<String> listAuthors(String repositoryPath) {

        List<String> authors = null;
        try {
            CLIExecution execution = CLIExecute.execute("git log --pretty=%an", repositoryPath);

            if (execution.getError() != null && !execution.getError().isEmpty()) {
                return null;
            } else {
                authors = new ArrayList<>();
                for (String commiter : execution.getOutput()) {
                    if (!authors.contains(commiter)) {
                        authors.add(commiter);
                    }
                }
                return authors;
            }

        } catch (IOException ex) {
            Logger.getLogger(Mineracao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static List<String> checkout(String repositoryPath, String sha) {

        try {
            CLIExecution execution = CLIExecute.execute("git checkout " + sha, repositoryPath);

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

    public static List<String> reset(String repositoryPath) {

        try {
            CLIExecution execution = CLIExecute.execute("git reset --hard", repositoryPath);

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

    public static List<String> clean(String repositoryPath) {

        try {
            CLIExecution execution = CLIExecute.execute("git clean -df", repositoryPath);

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

    public static List<String> diff(String repositoryPath, String sha1, String sha2) {

        try {
            CLIExecution execution = CLIExecute.execute("git diff " + sha1 + " " + sha2, repositoryPath);

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


}
