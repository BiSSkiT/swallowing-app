package com.benhan82.SOCK;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
		this.db = MyApp.db;
		
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
		
		Patient myPatient = new Patient("Neil", "Diamond");
		MyApp app = (MyApp) this.getApplication();
		app.setPatient(myPatient);
		
		db.addPatient(myPatient);
		int id = myPatient.getId();
		db.deletePatient(id);
		
	}
	
}
