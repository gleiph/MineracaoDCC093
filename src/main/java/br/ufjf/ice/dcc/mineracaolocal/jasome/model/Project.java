/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.ice.dcc.mineracaolocal.jasome.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gleiph
 */
public class Project {

    private String sourceDir;
    private int tloc;
    private List<Packagee> packages;

    public Project() {
        this.sourceDir = null;
        this.tloc = 0;
        this.packages = new ArrayList<>();
    }

    public int getTloc() {
        return tloc;
    }

    public void setTloc(int tloc) {
        this.tloc = tloc;
    }

    public List<Packagee> getPackages() {
        return packages;
    }

    public void setPackages(List<Packagee> packages) {
        this.packages = packages;
    }

    public String getSourceDir() {
        return sourceDir;
    }

    public void setSourceDir(String sourceDir) {
        this.sourceDir = sourceDir;
    }

    @Override
    public String toString() {
        return "Project{" + "sourceDir=" + sourceDir + ", tloc=" + tloc + '}';
    }

    public void print() {
        System.out.println(this);

        for (Packagee aPackage : this.getPackages()) {
            System.out.println("\t" + aPackage);

            for (Clazz clazz : aPackage.getClasses()) {
                System.out.println("\t\t" + clazz);

                for (Method method : clazz.getMethods()) {
                    System.out.println("\t\t\t" + method);
                }
            }
        }
    }

    public int getNumberPackages(){
        return this.packages.size();
    }
    
    public int getNumberClasses(){
        int classes = 0;
        for (Packagee packgee : packages) {
            classes += packgee.getNumberClasses();
        }
        
        return classes;
    }
    
    public int getNumberMethods(){
        
        int methods = 0;
        for (Packagee packgee : packages) {
            for (Clazz clazz : packgee.getClasses()) {
                methods += clazz.getNumberMethods();
            }
        }
        
        return methods;
    }
    
    
    
}
