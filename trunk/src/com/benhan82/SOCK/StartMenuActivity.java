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
		
		Patient patient1 = new Patient("1. Jessie", "Blake");
		Patient patient2 = new Patient("2. Edward", "Charmers");
		Patient patient3 = new Patient("3. Neil", "Diamond");
		Patient patient4 = new Patient("4. Natalie", "Harbott");
		Patient patient5 = new Patient("5. Ella", "Keena");
		
		Log.d("patient", "Just added the following patients to the database:");
		Log.d("patient", patient1.toString());
		Log.d("patient", patient2.toString());
		Log.d("patient", patient3.toString());		
		Log.d("patient", patient4.toString());
		Log.d("patient", patient5.toString());
		Log.d("patient", "/n");
		
		// Clear out any old entries in the database table
		db.clearTable();
		
		// Add our example patients
		db.addPatient(patient1);
		db.addPatient(patient2);
		db.addPatient(patient3);
		db.addPatient(patient4);
		db.addPatient(patient5);
		
		// Verify that the database has all the patients loaded
		List<Patient> list = db.getAllPatients();
		
		// BUG HERE
		Patient p = db.getPatient(0);
		p = db.getPatient(1);
		p = db.getPatient(2);
		p = db.getPatient(3);
		p = db.getPatient(4);
		
		
		try {
			// this should swap patients 2 and 5, and 3 and 4
//			Patient temp = patient4;
//			patient4 = list.get(2);
//			patient3 = temp;
//			temp = patient2;
//			patient2 = list.get(list.size()-1);
//			patient5 = temp;
		} catch (Exception e) {
			Log.d("exception", e.getMessage());
			e.printStackTrace();
		}

		int id = patient1.getId();
		patient1.setFirstName("bob");
		Log.d("patient", patient1.toString());
		Log.d("patient", db.getPatient(id).toString() );
		Log.d("patient", "the second one is from the database");		
		db.deletePatient(id);
		
	}
	
}
