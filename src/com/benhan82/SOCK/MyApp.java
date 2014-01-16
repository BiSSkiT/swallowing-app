package com.benhan82.SOCK;

import android.app.Application;

import com.benhan82.SOCK.database.PatientDatabaseHelper;

public class MyApp extends Application {
	
	public static PatientDatabaseHelper db;

	public MyApp() {}

	@Override
	public void onCreate() {
		super.onCreate();
		db = new PatientDatabaseHelper(this);
	}

}
