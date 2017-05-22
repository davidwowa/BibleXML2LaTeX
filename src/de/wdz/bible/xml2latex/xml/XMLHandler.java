package de.wdz.bible.xml2latex.xml;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

public class XMLHandler {

	private Logger logger = Logger.getLogger(XMLHandler.class);

	private Document bibleAsXMLDoc;

	public XMLHandler(String bibleAsString) {
		bibleAsXMLDoc = getDocFromXMLString(bibleAsString);
	}

	private Document getDocFromXMLString(String bibleAsXMLString) {
		logger.info("get XML document");
		// example found on
		// http://stackoverflow.com/questions/562160/in-java-how-do-i-parse-xml-as-a-string-instead-of-a-file
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(bibleAsXMLString));

			Document documentToReturn = builder.parse(is);

			return documentToReturn;
		} catch (Throwable throwable) {
			logger.error("error on xml parsing ", throwable);
		}
		return null;
	}

	public Element getRoot() {
		return bibleAsXMLDoc.getDocumentElement();
	}
}