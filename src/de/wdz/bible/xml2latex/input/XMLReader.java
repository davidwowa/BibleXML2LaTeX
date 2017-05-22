package de.wdz.bible.xml2latex.input;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.log4j.Logger;

public class XMLReader {

	private Logger logger = Logger.getLogger(XMLReader.class);

	private String bibleText;

	public XMLReader(String pathToFile) {
		logger.info("read file " + pathToFile);
		bibleText = readFile(pathToFile);
	}

	public String getBibleTextAsString() {
		return bibleText;
	}

	private String readFile(String pathToFile) {
		try {
			byte[] encoded = Files.readAllBytes(Paths.get(pathToFile));
			return new String(encoded, "utf-8");
		} catch (Throwable throwable) {
			logger.error("error on read file ", throwable);
		}
		return null;
	}
}