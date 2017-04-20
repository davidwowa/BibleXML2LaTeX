package de.wdz.xml2latex;

import org.w3c.dom.NodeList;

import de.wdz.xml2latex.input.XMLReader;
import de.wdz.xml2latex.xml.XMLHandler;

public class Main {

	private static String ru = "resources/SF_2009-01-20_RUS_RUSSUB_(RUSSIAN VERSION).xml";
	private static String ua = "resources/SF_2009-01-23_RUS_UKRUB_(UKRAINIAN VERSION).xml";
	private static String de = "resources/SF_2014-09-30_GER_NEö_(NEUE EVANGELISTISCHE öBERSETZUNG (NEö)).xml";
	private static String en = "resources/SF_2015-08-14_ENG_BWE96_(BIBLE IN WORLDWIDE ENGLISH).xml";

	private static String enK = "resources/SF_2009-01-20_ENG_KJV_(KJV+).xml";

	public static void main(String[] args) {
		XMLReader reader = new XMLReader(ua);
		String test = reader.getBibleTextAsString();
		XMLHandler handler = new XMLHandler(test);
		NodeList nodeList = handler.getElementByName("INFORMATION");

		for (int i = 0; i < nodeList.getLength(); i++) {
			System.out.println(nodeList.item(i).getTextContent());
		}
	}
}