package com.benhan82.SOCK.database;

import com.benhan82.SOCK.MyApp;

import android.content.ContentValues;

/**
 * @author Adapted by Ben Han. Original code from 
 * 
 * http://www.vogella.com/tutorials/AndroidSQLite/article.html
 * Android SQLite database and content provider - Tutorial - 
 * by Lars Vogel
 *
 */
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
		
		// Set the global reference to the current patient to point at this
		MyApp.setPatient(this);
	}
	
	public String toString() {
		String s = "Patient [id=" + id + ", Full name = " + firstName + " " + lastName + "]\n";
		
		if (this.notes != null)
			s += (" Notes: " + notes);
		
		return s;
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
	
}
