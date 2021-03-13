/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.ice.dcc.mineracaolocal.sax;

import br.ufjf.ice.dcc.mineracaolocal.model.Repositorio;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author gleiph
 */
public class ParserSAX extends DefaultHandler {

    private List<Repositorio> repositorios;
    private String tagAtual;
    private Repositorio repositorio;

    public ParserSAX() {

        super();
        this.repositorios = new ArrayList<>();
    }

    public void fazerParsing(String xmlPath) {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser;

        try {
            parser = factory.newSAXParser();

            parser.parse(xmlPath, this);

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(ParserSAX.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Início do processamento...");
        super.startDocument(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("Final do processamento...");
        super.endDocument(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        this.tagAtual = qName;
        if (this.tagAtual.equals("repositorio")) {
            this.repositorio = new Repositorio();
        }

//        System.out.println("Início do elemento: " + qName);
        super.startElement(uri, localName, qName, attributes); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        
//        System.out.println("Final do elemento: " + qName);
        if (qName.equals("repositorio")) {
            this.repositorios.add(this.repositorio);
        }

        this.tagAtual = "";
        super.endElement(uri, localName, qName); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        String texto = new String(ch, start, length);
//        System.out.println(texto);

        if (this.tagAtual.equals("nome")) {
            this.repositorio.setNome(texto);
        } else if (this.tagAtual.equals("organizacao")) {
            this.repositorio.setOrganizacao(texto);
        } else if (this.tagAtual.endsWith("url")) {
            this.repositorio.setUrl(texto);
        }

        super.characters(ch, start, length); //To change body of generated methods, choose Tools | Templates.
    }

    public void imprimeRepositorios(){
        for (Repositorio rep : this.repositorios) {
            System.out.println(rep);
        }
    }
    
}
