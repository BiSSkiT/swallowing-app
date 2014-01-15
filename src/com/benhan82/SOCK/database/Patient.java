package com.benhan82.SOCK.database;

public class Patient {
	
	private int id;
	private String firstName;
	private String lastName;
	private boolean checkBoxes[] = new boolean[10];
	
	
	
	public Patient() {}	
	
	public Patient(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		
	}
	
	public String toString() {
		return "Patient [id=" + id + ", first name=" + firstName + ", lastName" + "]";
	}
	
	// Getters and setters
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	
	
}
