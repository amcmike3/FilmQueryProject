package com.skilldistillery.filmquery.entities;

import java.util.List;
import java.util.Objects;

public class Film {
	private int filmId;
	private String title;
	private String desc;
	private short releaseYear;
	private int langId;
	private String language;
	private int rentDur;
	private double rate;
	private int length;
	private double repCost;
	private String rating;
	private String features;
	private List<Actor> actors;
	private String genre;
	private Object copiesInInventory;

	public Film() {

	};


	public Film(int filmId, String title, String desc, short releaseYear, int langId, int rentDur, double rate,
			int length, double repCost, String rating, String features, List<Actor> actors, String genre, int copies) {
		super();
		this.filmId = filmId;
		this.title = title;
		this.desc = desc;
		this.releaseYear = releaseYear;
		this.langId = langId;
		this.rentDur = rentDur;
		this.rate = rate;
		this.length = length;
		this.repCost = repCost;
		this.rating = rating;
		this.features = features;
		this.actors = actors;
		this.genre = genre;
		this.copiesInInventory = copies;
	}

	public int getFilmId() {
		return filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public short getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(short releaseYear) {
		this.releaseYear = releaseYear;
	}

	public int getLangId() {
		return langId;
	}

	public void setLangId(int langId) {
		this.langId = langId;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public int getRentDur() {
		return rentDur;
	}

	public void setRentDur(int rentDur) {
		this.rentDur = rentDur;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public double getRepCost() {
		return repCost;
	}

	public void setRepCost(double repCost) {
		this.repCost = repCost;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	@Override
	public int hashCode() {
		return Objects.hash(desc, features, filmId, langId, length, rate, rating, releaseYear, rentDur, repCost, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return Objects.equals(desc, other.desc) && Objects.equals(features, other.features) && filmId == other.filmId
				&& langId == other.langId && length == other.length
				&& Double.doubleToLongBits(rate) == Double.doubleToLongBits(other.rate)
				&& Objects.equals(rating, other.rating) && releaseYear == other.releaseYear && rentDur == other.rentDur
				&& Double.doubleToLongBits(repCost) == Double.doubleToLongBits(other.repCost)
				&& Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Film [filmId=");
		builder.append(filmId);
		builder.append(", title=");
		builder.append(title);
		builder.append(", desc=");
		builder.append(desc);
		builder.append(", releaseYear=");
		builder.append(releaseYear);
		builder.append(", langId=");
		builder.append(langId);
		builder.append(", language=");
		builder.append(language);
		builder.append(", rentDur=");
		builder.append(rentDur);
		builder.append(", rate=");
		builder.append(rate);
		builder.append(", length=");
		builder.append(length);
		builder.append(", repCost=");
		builder.append(repCost);
		builder.append(", rating=");
		builder.append(rating);
		builder.append(", features=");
		builder.append(features);
		builder.append(", genre=");
		builder.append(genre);
		builder.append(", copiesInInventory=");
		builder.append(copiesInInventory);
		builder.append(", actors=");
		builder.append(actors);
		builder.append("]");
		return builder.toString();
	}

}
