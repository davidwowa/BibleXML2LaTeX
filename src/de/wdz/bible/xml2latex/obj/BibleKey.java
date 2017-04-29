package de.wdz.bible.xml2latex.obj;

public class BibleKey {

	private int bookNumber;
	private int chapterNumber;
	private int versNumber;

	private int acrossSum;

	private String readableKey;

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

	public int getAcrossSum() {
		acrossSum = this.bookNumber + this.chapterNumber + this.versNumber;
		return acrossSum;
	}

	public void setAcrossSum(int acrossSum) {
		this.acrossSum = acrossSum;
	}

	public String getReadableKey() {
		readableKey = new String(this.bookNumber + "_" + this.chapterNumber + "_" + this.versNumber);
		return readableKey;
	}

	public void setReadableKey(String readableKey) {
		this.readableKey = readableKey;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bookNumber;
		result = prime * result + chapterNumber;
		result = prime * result + versNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BibleKey other = (BibleKey) obj;
		if (bookNumber != other.bookNumber)
			return false;
		if (chapterNumber != other.chapterNumber)
			return false;
		if (versNumber != other.versNumber)
			return false;
		return true;
	}
}