/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufjf.ice.dcc.mineracaolocal.sax;

import br.ufjf.ice.dcc.mineracaolocal.jasome.model.Clazz;
import br.ufjf.ice.dcc.mineracaolocal.jasome.model.Method;
import br.ufjf.ice.dcc.mineracaolocal.jasome.model.Packagee;
import br.ufjf.ice.dcc.mineracaolocal.jasome.model.Project;
import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author gleiph
 */
public class ParserJasome extends DefaultHandler {

    private Project project;
    private Packagee packagee;
    private Clazz clazz;
    private Method method;

    private boolean inProject;
    private boolean inPackagee;
    private boolean inClazz;
    private boolean inMethod;

    private final String PROJECT = "Project";
    private final String PACKAGE = "Package";
    private final String CLASS = "Class";
    private final String METHOD = "Method";
    private final String METRIC = "Metric";

    public ParserJasome() {

        super();
        this.project = new Project();
        this.inProject = false;
        this.inPackagee = false;
        this.inClazz = false;
        this.inMethod = false;

    }
    
    
    /**
     * 
     * @param xml - content of a XML file
     */
    public void contentParsing(String xml) {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser saxParser;
        
        try {
            saxParser = factory.newSAXParser();
            saxParser.parse(new InputSource(new StringReader(xml)), this);

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(ParserSAX.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    /**
     * 
     * @param xmlPath - path to a XML file
     */
    public void pathParsing(String xmlPath) {

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
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equals(PROJECT)) {
            this.inProject = true;
            this.project.setSourceDir(attributes.getValue("sourceDir"));
        } else if (qName.equals(PACKAGE)) {
            this.inPackagee = true;
            this.packagee = new Packagee();
            this.packagee.setName(attributes.getValue("name"));
        } else if (qName.equals(CLASS)) {
            this.inClazz = true;
            this.clazz = new Clazz();
            this.clazz.setName(attributes.getValue("name"));
        } else if (qName.equals(METHOD)) {
            this.inMethod = true;
            this.method = new Method();
            this.method.setName(attributes.getValue("name"));
        } else if (qName.equals(METRIC)) {

            String name = attributes.getValue("name");
            String value = attributes.getValue("value");

            if (inMethod) {

                if (name.equals("TLOC")) {
                    this.method.setTloc(Integer.parseInt(value));
                } else if (name.equals("NOP")) {
                    this.method.setNumberParameters(Integer.parseInt(value));
                }

            } else if (inClazz) {

                if (name.equals("TLOC")) {
                    this.clazz.setTloc(Integer.parseInt(value));
                } else if (name.equals("Aa")) {
                    this.clazz.setNumberAttributes(Integer.parseInt(value));
                } else if (name.equals("Ma")) {
                    this.clazz.setNumberMethods(Integer.parseInt(value));
                }

            } else if (inPackagee) {

                if (name.equals("TLOC")) {
                    this.packagee.setTloc(Integer.parseInt(value));
                } else if (name.equals("NOC")) {
                    this.packagee.setNumberClasses(Integer.parseInt(value));
                }

            } else if (inProject) {

                if (name.equals("TLOC")) {
                    this.project.setTloc(Integer.parseInt(value));
                }

            }
        }

        super.startElement(uri, localName, qName, attributes); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals(PROJECT)) {
            this.inProject = false;
        } else if (qName.equals(PACKAGE)) {
            this.inPackagee = false;
            this.project.getPackages().add(this.packagee);
        } else if (qName.equals(CLASS)) {
            this.inClazz = false;
            this.packagee.getClasses().add(this.clazz);
        } else if (qName.equals(METHOD)) {
            this.inMethod = false;
            this.clazz.getMethods().add(this.method);
        }

        super.endElement(uri, localName, qName); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

//        String text = new String(ch, start, length);
        super.characters(ch, start, length); //To change body of generated methods, choose Tools | Templates.
    }

    public Project getProject() {
        return project;
    }

}
