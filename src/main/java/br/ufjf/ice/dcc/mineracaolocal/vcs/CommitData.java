/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.ice.dcc.mineracaolocal.vcs;

/**
 *
 * @author gleiph
 */
public class CommitData {
    
    private String sha;
    private String commiter;

    public CommitData(String sha, String commiter) {
        this.sha = sha;
        this.commiter = commiter;
    }
    
    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getCommiter() {
        return commiter;
    }

    public void setCommiter(String commiter) {
        this.commiter = commiter;
    }

}
