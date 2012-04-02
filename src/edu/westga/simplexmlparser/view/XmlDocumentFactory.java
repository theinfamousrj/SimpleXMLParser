/**
 * 
 */
package edu.westga.simplexmlparser.view;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * XmlDocumentFactory contains a static factory method that
 * creates and returns an org.w3c.dom.Document object.
 * 
 * @author CS 3211, Spring 2009
 *
 */
public class XmlDocumentFactory {
	
	/**
	 * Factory method that creates a Document object from
	 * the given file.
	 * <p>
	 * Requires: anXmlFile != null
	 * 
	 * @param anXmlFile the input file to be parsed into a Document
	 * @return the Document creating from the input file
	 * 
	 * @throws IOException	if the file cannot be read
	 * @throws ParserConfigurationException	if there is aserious config error 
	 * @throws SAXParseException	if the file contains invalid XML
	 * @throws SAXException		if there is some other parsing error
	 */
	public static Document createDocument(File anXmlFile) 
							throws IOException,
								   ParserConfigurationException,
								   SAXParseException,
								   SAXException {
		// Throws an IllegalArgumentException if the anXmlFile variable is empty.
		if (anXmlFile == null) {
			throw new IllegalArgumentException("*** XML file parameter null ***");
		}
		
		// Creates a new empty Document object named theDocument.
		Document theDocument = null;
		
		// Creates a new DocumentBuilderFactory object named theFactory and obtains
		// a new instance of the DocumentBuilderFactory via the newInstance() method.
		DocumentBuilderFactory theFactory = DocumentBuilderFactory.newInstance();
		
		// Creates a new DocumentBuilder object named theBuilder, which allows
		// parsing of XML documents for a variety of input sources.
		DocumentBuilder theBuilder = theFactory.newDocumentBuilder();
		
		// Parses the content of the anXmlFile and returns a Document object.
		theDocument = theBuilder.parse(anXmlFile);
				
		return theDocument;
	}

}
