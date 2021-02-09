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
public class Clazz {
    
    private String name;
    private int numberAttributes;
    private int numberMethods;
    private int tloc;
    
    List<Method> methods;

    public Clazz() {
        this.name = null;
        this.numberAttributes = 0;
        this.numberMethods = 0;
        this.tloc = 0;
        this.methods = new ArrayList<>();
    }

    public int getNumberAttributes() {
        return numberAttributes;
    }

    public void setNumberAttributes(int numberAttributes) {
        this.numberAttributes = numberAttributes;
    }

    public int getNumberMethods() {
        return numberMethods;
    }

    public void setNumberMethods(int numberMethods) {
        this.numberMethods = numberMethods;
    }

    public int getTloc() {
        return tloc;
    }

    public void setTloc(int tloc) {
        this.tloc = tloc;
    }

    public List<Method> getMethods() {
        return methods;
    }

    public void setMethods(List<Method> methods) {
        this.methods = methods;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Clazz{" + "name=" + name + ", numberAttributes=" + numberAttributes + ", numberMethods=" + numberMethods + ", tloc=" + tloc + '}';
    }
    
    
}
