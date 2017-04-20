package de.wdz.xml2latex.xml;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XMLHandler {

	private Document bibleAsXMLDoc;

	public XMLHandler(String bibleAsString) {
		bibleAsXMLDoc = getDocFromXMLString(bibleAsString);
	}

	private Document getDocFromXMLString(String bibleAsXMLString) {
		// example found on
		// http://stackoverflow.com/questions/562160/in-java-how-do-i-parse-xml-as-a-string-instead-of-a-file
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(bibleAsXMLString));
			
			Document documentToReturn = builder.parse(is);
			
			return documentToReturn;
		} catch (Throwable throwable) {
			System.err.println("error on xml parsing " + throwable.getMessage());
			throwable.printStackTrace();
		}
		return null;
	}

	public Element getRoot() {
		return bibleAsXMLDoc.getDocumentElement();
	}

	public NodeList getElementByName(String tagName) {
		return bibleAsXMLDoc.getElementsByTagName(tagName);
	}
}