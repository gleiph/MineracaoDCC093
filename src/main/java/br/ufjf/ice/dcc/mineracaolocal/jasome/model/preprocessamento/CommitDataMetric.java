/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.ice.dcc.mineracaolocal.jasome.model.preprocessamento;

/**
 *
 * @author gleiph
 */
public class CommitDataMetric {
    
    private String commiter;
    private String sha;
    private int packages;
    private int classes;
    private int methods;
    private int tloc;

    public CommitDataMetric(String commiter, String sha, int packages, int classes, int methods, int tloc) {
        this.commiter = commiter;
        this.sha = sha;
        this.packages = packages;
        this.classes = classes;
        this.methods = methods;
        this.tloc = tloc;
    }

    public CommitDataMetric() {
    }

    public String getCommiter() {
        return commiter;
    }

    public void setCommiter(String commiter) {
        this.commiter = commiter;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public int getPackages() {
        return packages;
    }

    public void setPackages(int packages) {
        this.packages = packages;
    }

    public int getClasses() {
        return classes;
    }

    public void setClasses(int classes) {
        this.classes = classes;
    }

    public int getMethods() {
        return methods;
    }

    public void setMethods(int methods) {
        this.methods = methods;
    }

    public int getTloc() {
        return tloc;
    }

    public void setTloc(int tloc) {
        this.tloc = tloc;
    }
    
    public static CommitDataMetric toCommitData(String linha){
        String[] split = linha.split(",");
        
        if(split.length != 6)
            return null;
        
        CommitDataMetric result = new CommitDataMetric();
        
        result.setCommiter(split[0].trim());
        result.setSha(split[1].trim());
        result.setPackages(Integer.parseInt(split[2]));
        result.setClasses(Integer.parseInt(split[3]));
        result.setMethods(Integer.parseInt(split[4]));
        result.setTloc(Integer.parseInt(split[5]));
        
        return result;
    }
     
    
}
