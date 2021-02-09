/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.ice.dcc.mineracaolocal.jasome.model;

/**
 *
 * @author gleiph
 */
public class Method {

    private String name;
    private int tloc;
    private int numberParameters;

    public Method() {
        this.name = null;
        this.tloc = 0;
        this.numberParameters = 0;
    }

    public int getTloc() {
        return tloc;
    }

    public void setTloc(int tloc) {
        this.tloc = tloc;
    }

    public int getNumberParameters() {
        return numberParameters;
    }

    public void setNumberParameters(int numberParameters) {
        this.numberParameters = numberParameters;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Method{" + "name=" + name + ", tloc=" + tloc + ", numberParameters=" + numberParameters + '}';
    }

}
