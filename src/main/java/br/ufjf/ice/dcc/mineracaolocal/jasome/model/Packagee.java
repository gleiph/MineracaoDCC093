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
public class Packagee {
    
    private String name;
    private int tloc;
    private int numberClasses;
    private List<Clazz> classes;

    public Packagee() {
        this.name = null;
        this.tloc = 0;
        this.numberClasses = 0;
        this.classes = new ArrayList<>();
    }

    public int getTloc() {
        return tloc;
    }

    public void setTloc(int tloc) {
        this.tloc = tloc;
    }

    public int getNumberClasses() {
        return numberClasses;
    }

    public void setNumberClasses(int numberClasses) {
        this.numberClasses = numberClasses;
    }

    public List<Clazz> getClasses() {
        return classes;
    }

    public void setClasses(List<Clazz> classes) {
        this.classes = classes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Packagee{" + "name=" + name + ", tloc=" + tloc + ", numberClasses=" + numberClasses + '}';
    }

    
}
