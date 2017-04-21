package de.wdz.bible.xml2latex.output;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import de.wdz.bible.xml2latex.obj.BibleKey;
import de.wdz.bible.xml2latex.obj.BibleVers;

public class LaTeXParser {

	private StringBuilder currentStringBuilder;

	private Map<BibleKey, BibleVers> bibleTextMap;

	private int bookCounter = 1;
	private int chapterCounter = 1;
	private String language;

	public LaTeXParser() {
		currentStringBuilder = new StringBuilder();
		bibleTextMap = new HashMap<BibleKey, BibleVers>();
	}

	public void parse(NodeList nodeList, String language) {
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
							currentStringBuilder.append("\\begin{tcolorbox}\n");
							currentStringBuilder.append("\\textsubscript{");
							currentStringBuilder.append(node.getNodeValue());
							currentStringBuilder.append("}");
							currentStringBuilder.append(" ");
							currentStringBuilder.append(tempNode.getTextContent());
							currentStringBuilder.append("\n");
							currentStringBuilder.append("\\end{tcolorbox}\n");

							// add to map
							BibleKey bibleKey = new BibleKey();
							bibleKey.setLanguage(this.language);
							bibleKey.setBookNumber(this.bookCounter);
							bibleKey.setChapterNumber(this.chapterCounter);
							int versNumber = new Integer(node.getNodeValue());
							bibleKey.setVersNumber(versNumber);

							BibleVers bibleVers = new BibleVers();
							bibleVers.setLanguage(this.language);
							bibleVers.setVers(tempNode.getTextContent());

							bibleTextMap.put(bibleKey, bibleVers);
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

	public Map<BibleKey, BibleVers> getBibleTextMap() {
		return bibleTextMap;
	}

	public void setBibleTextMap(Map<BibleKey, BibleVers> bibleTextMap) {
		this.bibleTextMap = bibleTextMap;
	}
}