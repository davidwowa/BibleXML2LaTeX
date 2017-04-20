package de.wdz.xml2latex.input;

import java.nio.file.Files;
import java.nio.file.Paths;

public class XMLReader {

	private String bibleText;

	public XMLReader(String pathToFile) {
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
			System.err.println("error on read file " + throwable.getMessage());
			throwable.printStackTrace();
		}
		return null;
	}
}