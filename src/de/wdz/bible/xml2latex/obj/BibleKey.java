package de.wdz.bible.xml2latex.obj;

public class BibleKey {

	private int bookNumber;
	private int chapterNumber;
	private int versNumber;
	private String language;

	public int getBookNumber() {
		return bookNumber;
	}

	public void setBookNumber(int bookNumber) {
		this.bookNumber = bookNumber;
	}

	public int getChapterNumber() {
		return chapterNumber;
	}

	public void setChapterNumber(int chapterNumber) {
		this.chapterNumber = chapterNumber;
	}

	public int getVersNumber() {
		return versNumber;
	}

	public void setVersNumber(int versNumber) {
		this.versNumber = versNumber;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
}