package de.wdz.bible.xml2latex.obj;

public class BibleVers {

	private String language;
	private String vers;

	private String latexString;
	private String xmlString;
	private String jsonString;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getVers() {
		return vers;
	}

	public void setVers(String vers) {
		this.vers = vers;
	}

	public String getLatexString() {
		return latexString;
	}

	public void setLatexString(String latexString) {
		this.latexString = latexString;
	}

	public String getXmlString() {
		return xmlString;
	}

	public void setXmlString(String xmlString) {
		this.xmlString = xmlString;
	}

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}
}