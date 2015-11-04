package com.example.digitaslbi;

import android.graphics.Bitmap;

public class Animal_Feature_class {

	public String Species, family, IUCN, year, notes, typeofAnimal;

	public Bitmap picture;

	public Bitmap getPicture() {
		return picture;
	}

	public void setPicture(Bitmap picture) {
		this.picture = picture;
	}

	public Animal_Feature_class() {
	}

	public Animal_Feature_class(String species, String family, String ICUN,
			String year, String notes, String typeofAnimal, Bitmap picture,
			byte[] photo) {
		this.Species = species;
		this.family = family;
		this.IUCN = ICUN;
		this.year = year;
		this.notes = notes;
		this.typeofAnimal = typeofAnimal;
		this.picture = picture;
		this.Photo = photo;

	}

	public String getTypeofAnimal() {
		return typeofAnimal;
	}

	public void setTypeofAnimal(String typeofAnimal) {
		this.typeofAnimal = typeofAnimal;
	}

	byte[] Photo;

	public String getSpecies() {
		return Species;
	}

	public void setSpecies(String species) {
		Species = species;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getIUCN() {
		return IUCN;
	}

	public void setIUCN(String iUCN) {
		IUCN = iUCN;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public byte[] getPhoto() {
		return Photo;
	}

	public void setPhoto(byte[] photo) {
		Photo = photo;
	}

}
