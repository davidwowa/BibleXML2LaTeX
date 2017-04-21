package de.wdz.xml2latex.output;

import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class LaTeXParser {

	private StringBuilder currentStringBuilder;

	private int bookCounter = 1;
	private int chapterCounter = 1;

	public void parse(NodeList nodeList) {
		currentStringBuilder = new StringBuilder();
		printNote(nodeList);
		// System.out.println(currentStringBuilder.toString());
	}

	// found on
	// https://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
	private void printNote(NodeList nodeList) {
		for (int count = 0; count < nodeList.getLength(); count++) {
			Node tempNode = nodeList.item(count);
			// make sure it's element node.
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
				// get node name and value
				// System.out.println("\nNode Name =" + tempNode.getNodeName() +
				// " [OPEN]");
				// System.out.println("Node Value =" +
				// tempNode.getTextContent());
				//

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

								String pathFileName = "resources/LaTeX/BIBLEBOOK_" + bookCounter + ".tex";
								LaTeXWriter.writeToFile(pathFileName, currentStringBuilder.toString());
								currentStringBuilder = null;
								currentStringBuilder = new StringBuilder();

								currentStringBuilder.append("Book " + bookCounter + "\n");
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
						}
					}
				}
				if (tempNode.hasChildNodes()) {
					// loop again if has child nodes
					printNote(tempNode.getChildNodes());
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
}