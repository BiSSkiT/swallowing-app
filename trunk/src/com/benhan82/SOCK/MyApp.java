package com.benhan82.SOCK;

import android.app.Application;
import android.util.Log;

import com.benhan82.SOCK.database.Patient;
import com.benhan82.SOCK.database.PatientDatabaseHelper;

public class MyApp extends Application {
	
	public static PatientDatabaseHelper db;
	public Patient patient = null;

	public MyApp() {}

	@Override
	public void onCreate() {
		super.onCreate();
		db = new PatientDatabaseHelper(this);
		
		Log.d("debug", "just finished making the database, boo yah!");
	}

	
	// Getters and setters

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

}
