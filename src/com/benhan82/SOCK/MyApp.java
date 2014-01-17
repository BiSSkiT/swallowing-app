package com.benhan82.SOCK;

import android.app.Application;
import android.util.Log;

import com.benhan82.SOCK.database.Patient;
import com.benhan82.SOCK.database.PatientDatabaseHelper;

public class MyApp extends Application {
	
	private static PatientDatabaseHelper db;
	private static Patient patientPrivate = null;

	public MyApp() {}

	@Override
	public void onCreate() {
		super.onCreate();
		db = new PatientDatabaseHelper(this);
		
		Log.d("debug", "just finished making the database, boo yah!");
	}

	
	// Getters and setters

	public static Patient getPatient() {
		return patientPrivate;
	}

	public static void setPatient(Patient patient) {
		patientPrivate = patient;
	}

	public static PatientDatabaseHelper getDb() {
		return db;
	}

	public static void setDb(PatientDatabaseHelper db) {
		MyApp.db = db;
	}

}
