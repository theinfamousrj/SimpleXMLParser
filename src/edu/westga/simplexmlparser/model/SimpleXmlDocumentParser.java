package edu.westga.simplexmlparser.model;

//import java.util.ArrayList;
import org.w3c.dom.*;

/**
 * SimpleXmlFileParser is an immutable class that parses a simple
 * XML file, returning the value of each of its text nodes.
 * 
 * Adapted from code found at 
 * www.developerfusion.com/code/2064/a-simple-way-to-read-an-xml-file-in-java/
 * 
 * @author CS 3211, Spring 2009
 * 
 */
public class SimpleXmlDocumentParser {

	private Document theDocument;
	private Element personElement;
	private StringBuilder documentSummary;

	/**
	 * Creates a new SimpleXmlFileParser that can parse
	 * the given Document object.
	 * <p>
	 * Requires: aDocument != null
	 * <p>
	 * Ensures: getDocumentNode() == aDocument.cloneNode(true)
	 * 
	 * @param aDocument the Document to be parsed
	 */
	public SimpleXmlDocumentParser(Document aDocument) {
		if (aDocument == null) {
			throw new IllegalArgumentException("*** Document parameter null ***");
		}
		
		this.theDocument = aDocument;
	}	
	
	/**
	 * Returns a deep clone of the document node.
	 * <p>
	 * Requires: nothing
	 * 
	 * @return a duplicate of the document's node
	 */
	public Node getDocumentNode() {
		return this.theDocument.cloneNode(true);
	}
	
	/** 
	 * Returns a string representation of the parsed document.
	 * <p>
	 * Requires: nothing
	 * 
	 * @see java.lang.Object#toString()
	 * @return a textual summary of the document
	 */
	@Override
	public String toString() {

		// Stringbuilder object named documentSummary holds the document in string form.
		documentSummary = new StringBuilder();
		
		// Sets theDocumentElement to the child node, which is the document element
		// of theDocument.
		Element theDocumentElement = getNormalizedDocument();
		appendDocumentDataToDocumentSummary(theDocumentElement);
		return this.documentSummary.toString();
	}
	
	/**
	 * Returns a normalized version of the documentElement.
	 * <p>
	 * Requires: nothing
	 * 
	 * @return an element containing the normalized documentElement.
	 */
	private Element getNormalizedDocument() {
		Element documentElement = theDocument.getDocumentElement();
		
		// Normalizes the document by putting all "text" nodes in the full depth
		// of the subtree.
		documentElement.normalize();
		
		return documentElement;
	}
	
	/**
	 * Appends the document data to the documentSummary.
	 * <p>
	 * Requires: theDocumentElement != null
	 * 
	 * @param theDocumentElement
	 */
	private void appendDocumentDataToDocumentSummary(Element theDocumentElement) {
		
		appendRootNameToDocumentSummary(theDocumentElement);

		// Sets personNodes to a NodeList of all the elements contained in <person>
		// tags throughout the document and appends the number of objects in personNodes
		// to the documentSummary.
		NodeList personNodes = theDocument.getElementsByTagName("person");
		
		this.documentSummary.append("Number of people : " + 
							   personNodes.getLength() + "\n");

		// For loop that loops through all of the objects in personNodes.
		for (int i = 0; i < personNodes.getLength(); i++) {

			// Sets nextPersonNode to the current object in the personNodes node list.
			Node nextPersonNode = personNodes.item(i);
			appendPersonDataToDocumentSummary(nextPersonNode);			
		}
	}

	/**
	 * Appends the person data to the documentSummary.
	 * <p>
	 * Requires: nextPersonNode != null
	 * 
	 * @param nextPersonNode
	 */
	private void appendPersonDataToDocumentSummary(Node nextPersonNode) {
		// Checks to see if the nextPersonNode is an element.
		if (nextPersonNode.getNodeType() == Node.ELEMENT_NODE) {

			// An element called personElement stores the object in nextPersonNode
			// and gets the "status" of that person and appends it to the 
			// documentSummary stringbuilder, as long as a status exists.
			personElement = (Element) nextPersonNode;
			appendPersonAttributeToDocumentSummary();

			appendElementDataToDocumentSummary("first", "First Name");
			appendElementDataToDocumentSummary("last", "Last Name");
			appendElementDataToDocumentSummary("age", "Age");
		}
	}

	/**
	 * Appends the root name of the document element to the documentSummary.
	 * <p>
	 * Requires: theDocumentElement != null
	 * 
	 * @param theDocumentElement the document element you wish to use.
	 */
	private void appendRootNameToDocumentSummary(Element theDocumentElement) {
		// Sets rootName to the node name stored in the document element.
		// Appends a string to the documentSummary stringbuilder object.
		String rootName = theDocumentElement.getNodeName(); 
		documentSummary.append("Root element: " + rootName + "\n");
	}

	/**
	 * Appends element data to the document summary by tag name.
	 * <p>
	 * Requires: tag != null && label != null
	 * 
	 * @param tag The XML tag surrounding the data you wish to append.
	 * @param label The label you want to give this tag in the documentSummary.
	 */
	private void appendElementDataToDocumentSummary(String tag, String label) {
		// Sets elementNodes to a NodeList of all the elements contained in <tag>
		// tags in the personElement.
		NodeList elementNodes = 
				this.personElement.getElementsByTagName(tag);

		// Sets theElement to the very first element that appears in the
		// elementNodes node list.
		Element theElement = (Element) elementNodes.item(0);
		
		// Gets the child nodes of the theElement and adds them to the NodeList
		// elementNodes. Then it appends the node value of the first element
		// in the elementChildren node list.
		NodeList elementChildren = theElement.getChildNodes();
		this.documentSummary.append(label + " : "
				+ elementChildren.item(0).getNodeValue() + "\n");
	}

	/**
	 * Appends all personElement elements with a "status" attribute to the documentSummary.
	 * <p>
	 * Requires: nothing
	 */
	private void appendPersonAttributeToDocumentSummary() {
		
		if (personElement.getAttribute("status").length() > 0) { 
			documentSummary.append(
					personElement.getAttributes().item(0).getNodeName() +
					": " +
					personElement.getAttributes().item(0).getNodeValue() + 
					"\n");
		}
	}
	
	/**
	 * 
	 * @param attributeName
	 * @param attributeValue
	 * @return
	 
	private ArrayList<Element> getElementsByAttribute(String attributeName, String attributeValue) {
		ArrayList<Element> allNodeList = new ArrayList<Element>();
		ArrayList<Element> elementList = new ArrayList<Element>();
		NodeList allNodes = theDocument.getElementsByTagName("*");
		
		allNodeList = convertNodeListToArrayList(allNodes);
		
		for(int i = 0; i <= allNodeList.size(); i++) {
			
		}
		
		return elementList;
	}
	*/

	/**
	 * 
	 * @param theNodeList
	 * @return
	 
	private ArrayList<Element> convertNodeListToArrayList(NodeList theNodeList) {
		ArrayList<Element> theArrayList = new ArrayList<Element>();
		
		return theArrayList;
	}
	*/
}