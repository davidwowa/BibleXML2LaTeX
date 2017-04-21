package de.wdz.xml2latex.output;

import java.io.PrintWriter;

public class LaTeXWriter {
	public static void writeToFile(String path, String text) {
		try {
			PrintWriter writer = new PrintWriter(path, "UTF-8");
			writer.print(text);
			writer.close();
		} catch (Throwable throwable) {
			System.err.println("error on write file " + throwable.getMessage());
			throwable.printStackTrace();
		}
	}
}