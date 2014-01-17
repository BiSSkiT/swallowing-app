package com.benhan82.SOCK.database;

import com.benhan82.SOCK.MyApp;

import android.content.ContentValues;

public class Patient {
	
	private int id;
	private String notes;
	private String firstName;
	private String lastName;
	private boolean checkBoxes[] = new boolean[10];
	private ContentValues cv;
	
	
	

	public Patient() {}	
	
	// Standard constructor, these are the minimum detail for new patients. 
	// Maybe include a Notes section?
	
	public Patient(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		MyApp.setPatient(this);
	}
	
	public String toString() {
		return "Patient [id=" + id + ", first name=" + firstName + 
				", lastName=" + lastName + "]";
	}
	
	// Getters and setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public boolean[] getCheckBoxes() {
		return checkBoxes;
	}

	public void setCheckBoxes(boolean[] checkBoxes) {
		this.checkBoxes = checkBoxes;
	}

	public ContentValues getCv() {
		return cv;
	}

	public void setCv(ContentValues cv) {
		this.cv = cv;
	}
	
}
