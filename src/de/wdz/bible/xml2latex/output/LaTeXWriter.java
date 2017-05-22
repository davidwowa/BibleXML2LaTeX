package de.wdz.bible.xml2latex.output;

import java.io.PrintWriter;

import org.apache.log4j.Logger;

public class LaTeXWriter {

	private static Logger logger = Logger.getLogger(LaTeXWriter.class);

	public static void writeToFile(String path, String text) {
		try {
			logger.info("start write to file " + path);

			PrintWriter writer = new PrintWriter(path, "UTF-8");
			writer.print(text);
			writer.close();

			logger.info("end write to file " + path);
		} catch (Throwable throwable) {
			logger.error("error on write file ", throwable);
		}
	}
}