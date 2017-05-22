package de.wdz.bible.xml2latex.output;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import de.wdz.bible.xml2latex.obj.BibleKey;
import de.wdz.bible.xml2latex.obj.BibleVers;

public class LaTeXParser {

	private Logger logger = Logger.getLogger(LaTeXParser.class);

	private StringBuilder currentStringBuilder;

	private Map<Integer, List<BibleVers>> bibleTextMap;

	private int bookCounter = 1;
	private int chapterCounter = 1;
	private String language;

	public LaTeXParser() {
		logger.info("create instance of " + this.getClass().getName());

		currentStringBuilder = new StringBuilder();
		bibleTextMap = new HashMap<Integer, List<BibleVers>>();
	}

	public void parse(NodeList nodeList, String language) {
		logger.info("parse text for language " + language);

		this.language = language;
		bookCounter = 1;
		chapterCounter = 1;
		walkTree(nodeList);
	}

	// found on
	// https://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
	private void walkTree(NodeList nodeList) {
		for (int count = 0; count < nodeList.getLength(); count++) {
			Node tempNode = nodeList.item(count);
			// make sure it's element node.
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
				// get node name and value
				// System.out.println("\nNode Name =" + tempNode.getNodeName() +
				// " [OPEN]");
				// System.out.println("Node Value =" +
				// tempNode.getTextContent());

				if (tempNode.hasAttributes()) {
					// get attributes names and values
					NamedNodeMap nodeMap = tempNode.getAttributes();

					for (int i = 0; i < nodeMap.getLength(); i++) {
						Node node = nodeMap.item(i);
						// System.out.println("attr name : " +
						// node.getNodeName());
						// System.out.println("attr value : " +
						// node.getNodeValue());

						if (count > 0) {
							if ("BIBLEBOOK".equals(tempNode.getNodeName())) {

								// TODO Error on saving file
								// TODO remove wrong book numbers (russian as
								// example)
								String pathFileName = "resources/LaTeX/" + this.language + "_BIBLEBOOK_" + bookCounter
										+ ".tex";

								// TODO write LaTeX-File not in use yet
								LaTeXWriter.writeToFile(pathFileName, currentStringBuilder.toString());
								currentStringBuilder = null;
								currentStringBuilder = new StringBuilder();

								currentStringBuilder.append("\\section{BOOK " + bookCounter + "}\n");
								bookCounter++;
								chapterCounter = 1;
							}
						} else {
							currentStringBuilder.append("\\section{BOOK " + bookCounter + "}\n");
						}

						if ("CHAPTER".equals(tempNode.getNodeName())) {
							currentStringBuilder.append("\\subsection{CHAPTER " + chapterCounter + "}\n");
							chapterCounter++;
						}

						if ("vnumber".equals(node.getNodeName())) {

							StringBuilder laTeXBuilder = new StringBuilder();

							laTeXBuilder.append("\\begin{tcolorbox}\n");
							laTeXBuilder.append("\\textsubscript{");
							laTeXBuilder.append(node.getNodeValue());
							laTeXBuilder.append("}");
							laTeXBuilder.append(" ");
							laTeXBuilder.append(tempNode.getTextContent());
							laTeXBuilder.append("\n");
							laTeXBuilder.append("\\end{tcolorbox}\n");

							currentStringBuilder.append(laTeXBuilder.toString());

							// TODO xml, json parts

							// add to map
							BibleKey bibleKey = new BibleKey();
							bibleKey.setBookNumber(this.bookCounter);
							bibleKey.setChapterNumber(this.chapterCounter);
							int versNumber = new Integer(node.getNodeValue());
							bibleKey.setVersNumber(versNumber);

							BibleVers bibleVers = new BibleVers();
							bibleVers.setLanguage(this.language);
							bibleVers.setVers(tempNode.getTextContent());

							bibleVers.setLatexString(laTeXBuilder.toString());

							if (!bibleTextMap.containsKey(bibleKey.getAcrossSum())) {
								List<BibleVers> versesList = new ArrayList<BibleVers>();
								versesList.add(bibleVers);
								bibleTextMap.put(bibleKey.getAcrossSum(), versesList);
							} else {
								bibleTextMap.get(bibleKey.getAcrossSum()).add(bibleVers);
							}
						}
					}
				}
				if (tempNode.hasChildNodes()) {
					// loop again if has child nodes
					walkTree(tempNode.getChildNodes());
				}
				// System.out.println("Node Name =" + tempNode.getNodeName() +
				// "[CLOSE]");
			}
		}
	}

	// found on
	// http://stackoverflow.com/questions/4076910/how-to-retrieve-element-value-of-xml-using-java
	@SuppressWarnings("unused")
	@Deprecated
	private String getString(String tagName, Element element) {
		NodeList list = element.getElementsByTagName(tagName);
		if (list != null && list.getLength() > 0) {
			NodeList subList = list.item(0).getChildNodes();

			if (subList != null && subList.getLength() > 0) {
				return subList.item(0).getNodeValue();
			}
		}
		return null;
	}

	public Map<Integer, List<BibleVers>> getBibleTextMap() {
		return bibleTextMap;
	}

	public void setBibleTextMap(Map<Integer, List<BibleVers>> bibleTextMap) {
		this.bibleTextMap = bibleTextMap;
	}
}