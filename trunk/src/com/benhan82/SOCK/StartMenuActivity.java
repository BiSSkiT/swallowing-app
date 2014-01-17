package com.benhan82.SOCK;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;

import com.benhan82.SOCK.database.Patient;
import com.benhan82.SOCK.database.PatientDatabaseHelper;
import com.benhan82.SOCK.tests.TestDatabase;

public class StartMenuActivity extends Activity {
	
	private PatientDatabaseHelper db;
		
	@Override
	protected void onCreate(Bundle savedInstanceState1) {
		super.onCreate(savedInstanceState1);
		//Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_top_menu);
		this.db = MyApp.getDb();
		
		testDatabase();	// testing method for database functions
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu1, menu);
		return true;
	}
	
	public void openClinical (View v) {
		// method to open the clinical mode menu
		Intent intent = new Intent(this, PatientSelectionActivity.class);
		startActivity(intent);
	}
	
	public void openLearning (View v) {
		// method to open the clinical mode menu
		Intent intent = new Intent(this, LearningActivity.class);
		startActivity(intent);
	}

	private void testDatabase() {
		// Test method for SQLiteDatabase and related classes
		TestDatabase.testPatient();
		
		
		
		/**
		
		// Make some Patient objects
		Patient patients[] = new Patient[5];
		patients[0] = new Patient("1. Jessie", "Blake");
		patients[1] = new Patient("2. Edward", "Charmers");
		patients[2] = new Patient("3. Neil", "Diamond");
		patients[3] = new Patient("4. Natalie", "Harbott");
		patients[4] = new Patient("5. Ella", "Keena");
				
		// Clear out any old entries in the database table
		db.clearTable();
		
		// Add our example patients
		for (int i = 0; i < patients.length; i++)
			db.addPatient(patients[i]);
		
		// Verify that the database has all the patients loaded
		List<Patient> list = db.getAllPatients();
		
		// Update some entries
		Patient pTemp = list.get(0);
		MyApp.setPatient(pTemp);
		pTemp.setFirstName("Bob");
		pTemp.setLastName("Parker");
		pTemp.setNotes("Cheers to the old mayor");
		db.updatePatient(pTemp);
		db.getPatient(pTemp.getId());
		Patient pzb = MyApp.getPatient();
		Log.d("patient", pzb.toString());
		db.deletePatient(pTemp.getId());
		
		**/
	}
	
}
