package edu.westga.simplexmlparser.controller;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import edu.westga.simplexmlparser.model.SimpleXmlDocumentParser;
import edu.westga.simplexmlparser.view.XmlDocumentFactory;

/**
 * This application demonstrates the techniques, classes, and methods
 * used to parse an XML file and retrieve its contents.
 * 
 * @author CS 3211, Spring 2009
 *
 */
public class SimpleXMLParserApp {

	/**
	 * 
	 * @param args	not used
	 */
	public static void main(String[] args) {
		
		try {
			Document theDocument = XmlDocumentFactory.createDocument(new File("people.xml"));
			SimpleXmlDocumentParser theParser = new SimpleXmlDocumentParser(theDocument);
			System.out.println(theParser.toString().length());
			System.out.println(theParser.toString());
		}
		catch (IOException ioe) {
			System.out.println("** IO error: could not read the XML file");
		}
		catch (ParserConfigurationException pce) {
			System.out.println("** Parsing config error" + pce.getMessage());
		}
		catch (SAXParseException err) {
			System.out.println("** Parsing error" + ", line "
					+ err.getLineNumber() + ", uri " + err.getSystemId());
			System.out.println(" " + err.getMessage());

		} 
		catch (SAXException e) {
			System.out.println("** SAX error: could not process XML");
			Exception x = e.getException();
			((x == null) ? e : x).printStackTrace();

		}
		
		System.exit (0);

	}

}
