package edu.westga.simplexmlparser.unittests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Document;

import edu.westga.simplexmlparser.model.SimpleXmlDocumentParser;
import edu.westga.simplexmlparser.view.XmlDocumentFactory;

/**
 * Unit tests for class SimpleXmlDocumentParser.
 * @author CS 3211, Spring 2009
 *
 */
public class SimpleXmlDocumentParserTests {
	
	// Expected results for the return value of parser's toString().
	// *** NOTE: Change these if input file people.xml changes! ***
	private static final int PARSED_STRING_LENGTH = 227;
	private static final String PARSED_STRING_PREFIX = "Root element: people";
	private static final String PARSED_STRING_SUFFIX = "Age : 40\n";
	
	private static Document theDocument;
	private SimpleXmlDocumentParser theParser;

	// Runs once when the test framework starts to build the document from the file.
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			theDocument = XmlDocumentFactory.createDocument(new File("people.xml"));
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

	// Runs before each test method to create a parser object.
	@Before
	public void setUp() throws Exception {
		theParser = new SimpleXmlDocumentParser(theDocument);
	}
	
	@Test
	public void shouldHaveEqualDocumentNodeWhenCreated() {
		assertTrue(theDocument.cloneNode(true).isEqualNode(this.theParser.getDocumentNode()));
	}

	@Test
	public void shouldHaveCorrectReturnValueLengthWhenCallToString() {
		assertEquals(PARSED_STRING_LENGTH, this.theParser.toString().length());
	}
	
	@Test
	public void shouldHaveCorrectReturnValuePrefixWhenCallToString() {
		assertTrue(this.theParser.toString().startsWith(PARSED_STRING_PREFIX));
	}
	
	@Test
	public void shouldHaveCorrectReturnValueSuffixWhenCallToString() {
		assertTrue(this.theParser.toString().endsWith(PARSED_STRING_SUFFIX));
	}
	
}
