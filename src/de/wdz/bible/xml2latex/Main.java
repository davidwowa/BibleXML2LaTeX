package de.wdz.bible.xml2latex;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.w3c.dom.NodeList;

import de.wdz.bible.xml2latex.input.XMLReader;
import de.wdz.bible.xml2latex.obj.BibleVers;
import de.wdz.bible.xml2latex.output.LaTeXParser;
import de.wdz.bible.xml2latex.xml.XMLHandler;

public class Main {

	static Logger logger = Logger.getLogger(Main.class);

	// TODO find another version, without booknumbers
	private static String ru = "resources/SF_2009-01-20_RUS_RUSSUB_(RUSSIAN VERSION).xml";
	private static String ua = "resources/SF_2009-01-23_RUS_UKRUB_(UKRAINIAN VERSION).xml";
	// TODO find bible without notes on every chapter
	private static String de = "resources/SF_2009-01-20_GER_SCH1951_(SCHLACHTER 1951).xml";
	// TODO find another version, without booknumbers
	private static String en = "resources/SF_2009-01-20_ENG_WEB_(WORLD ENGLISH BIBLE).xml";

	// private static String enK = "resources/SF_2009-01-20_ENG_KJV_(KJV+).xml";

	public static void main(String[] args) {

		logger.info("start with parsing");
		LaTeXParser laTeXParser = new LaTeXParser();

		XMLReader uaLangXMLReader = new XMLReader(ua);
		String uaBibleAsXML = uaLangXMLReader.getBibleTextAsString();
		XMLHandler uaBibleXMLHandler = new XMLHandler(uaBibleAsXML);

		NodeList uaNodeList = uaBibleXMLHandler.getRoot().getElementsByTagName("BIBLEBOOK");

		XMLReader ruLangXMLReader = new XMLReader(ru);
		String ruBibleAsXML = ruLangXMLReader.getBibleTextAsString();
		XMLHandler ruBibleXMLHandler = new XMLHandler(ruBibleAsXML);

		NodeList ruNodeList = ruBibleXMLHandler.getRoot().getElementsByTagName("BIBLEBOOK");

		XMLReader deLangXMLReader = new XMLReader(de);
		String deBibleAsXML = deLangXMLReader.getBibleTextAsString();
		XMLHandler deBibleXMLHandler = new XMLHandler(deBibleAsXML);

		NodeList deNodeList = deBibleXMLHandler.getRoot().getElementsByTagName("BIBLEBOOK");

		XMLReader enLangXMLReader = new XMLReader(en);
		String enBibleAsXML = enLangXMLReader.getBibleTextAsString();
		XMLHandler enBibleXMLHandler = new XMLHandler(enBibleAsXML);

		NodeList enNodeList = enBibleXMLHandler.getRoot().getElementsByTagName("BIBLEBOOK");

		laTeXParser.parse(uaNodeList, "ua");
		laTeXParser.parse(ruNodeList, "ru");
		laTeXParser.parse(deNodeList, "de");
		laTeXParser.parse(enNodeList, "en");

		// contains all data
		Map<Integer, List<BibleVers>> bibleMap = laTeXParser.getBibleTextMap();

		logger.info("end with job");
	}
}