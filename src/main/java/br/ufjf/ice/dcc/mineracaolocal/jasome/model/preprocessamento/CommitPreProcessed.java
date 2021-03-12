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
public class CommitPreProcessed {

    private static final String MAIOR = "MAIOR";
    private static final String MENOR = "MENOR";
    private static final String IGUAL = "IGUAL";

    private String commiter;
    private String sha;
    private String packages;
    private String classes;
    private String methods;
    private String tloc;

    public static CommitPreProcessed preProcessing(CommitDataMetric anterior, CommitDataMetric atual) {
        CommitPreProcessed resultado = new CommitPreProcessed();

        resultado.setCommiter(atual.getCommiter());
        resultado.setSha(atual.getSha());
        resultado.setPackages(comparador(anterior.getPackages(), atual.getPackages()));
        resultado.setClasses(comparador(anterior.getClasses(), atual.getClasses()));
        resultado.setMethods(comparador(anterior.getMethods(), atual.getMethods()));
        resultado.setTloc(comparador(anterior.getTloc(), atual.getTloc()));
        
        return resultado;
    }

    private static String comparador(int anterior, int atual) {
        if (anterior == atual) {
            return IGUAL;
        } else if (anterior > atual) {
            return MENOR;
        } else {
            return MAIOR;
        }
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

    public String getPackages() {
        return packages;
    }

    public void setPackages(String packages) {
        this.packages = packages;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getMethods() {
        return methods;
    }

    public void setMethods(String methods) {
        this.methods = methods;
    }

    public String getTloc() {
        return tloc;
    }

    public void setTloc(String tloc) {
        this.tloc = tloc;
    }

    @Override
    public String toString() {
        return "\'"+commiter + "\', " + sha + ", " + packages + ", " + classes + ", " + methods + ", " + tloc;
    }
    
    

}
