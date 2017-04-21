package de.wdz.bible.xml2latex;

import java.util.Map;

import org.w3c.dom.NodeList;

import de.wdz.bible.xml2latex.input.XMLReader;
import de.wdz.bible.xml2latex.obj.BibleKey;
import de.wdz.bible.xml2latex.obj.BibleVers;
import de.wdz.bible.xml2latex.output.LaTeXParser;
import de.wdz.bible.xml2latex.xml.XMLHandler;

public class Main {

	// TODO find another version, without booknumbers
	private static String ru = "resources/SF_2009-01-20_RUS_RUSSUB_(RUSSIAN VERSION).xml";
	private static String ua = "resources/SF_2009-01-23_RUS_UKRUB_(UKRAINIAN VERSION).xml";
	// TODO find bible without notes on every chapter
	private static String de = "resources/SF_2014-09-30_GER_NEö_(NEUE EVANGELISTISCHE öBERSETZUNG (NEö)).xml";
	// TODO find another version, without booknumbers
	private static String en = "resources/SF_2015-08-14_ENG_BWE96_(BIBLE IN WORLDWIDE ENGLISH).xml";

	// private static String enK = "resources/SF_2009-01-20_ENG_KJV_(KJV+).xml";

	public static void main(String[] args) {

		LaTeXParser laTeXParser = new LaTeXParser();

		XMLReader uaLangXMLReader = new XMLReader(ua);
		String uaBibleAsXML = uaLangXMLReader.getBibleTextAsString();
		XMLHandler uaBibleXMLHandler = new XMLHandler(uaBibleAsXML);

		NodeList uaNodeList = uaBibleXMLHandler.getRoot().getElementsByTagName("BIBLEBOOK");

		XMLReader ruLangXMLReader = new XMLReader(ru);
		String ruBibleAsXML = ruLangXMLReader.getBibleTextAsString();
		XMLHandler ruBibleXMLHandler = new XMLHandler(ruBibleAsXML);

		NodeList ruNodeList = ruBibleXMLHandler.getRoot().getElementsByTagName("BIBLEBOOK");

		laTeXParser.parse(uaNodeList, "ua");
		laTeXParser.parse(ruNodeList, "ru");

		Map<BibleKey, BibleVers> bibleMap = laTeXParser.getBibleTextMap();
		System.out.println("X");
	}
}