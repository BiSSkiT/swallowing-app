package com.benhan82.SOCK;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.benhan82.SOCK.database.PatientDatabaseHelper;
import com.benhan82.SOCK.tests.TestUI;

public class PatientSelectionActivity extends Activity {
	
	EditText et;
	PatientDatabaseHelper db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient_selection);
		et = (EditText) findViewById(R.id.edit_patientID);
		db = MyApp.getDb();
		MyApp.setPSA(this);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.patient_selection, menu);
		return true;
	}
	
	public void onClickNew(View view) {
		// Open New Patient screen
		Intent intent = new Intent(this, NewPatientActivity.class);
		startActivity(intent);
	}
	
	public void onClickOpen(View view) {
		// Open an existing patient entry and open Patient Summary Activity, only
		// if the user has entered an ID		
		String s = et.getText().toString().trim();
		boolean success = false;
		
		if ( et.getText() == null || s.equals("") ) {
			makeToast("Please enter a patient ID");
		} else {
			// get ID entered
			int id = 0;
			try {
			    id = Integer.parseInt(et.getText().toString());
			} catch(NumberFormatException nfe) {
				Log.d("exception", "Number format exception in onClickOpen method of PatientSelectionActivity.");
				makeToast("The ID you entered is not in the correct format. Please enter a number, e.g. 25");
			} 
			
			// check that the ID exists in the database
			// if it does then set that to the current patient, else show error
			try {
				if (db.getPatient(id) != null) {
					success = true;
					MyApp.setPatient(db.getPatient(id));
				} else {
					success = false;
					String em = "Error retrieving patient file. Patient ID:" + id + " does not exist.";
					makeToast(em);	
				}
			} catch(Exception e) {
				Log.d("exception", e.getMessage());
			}
			
			if (success) {
				Intent intent = new Intent(this, PatientSummaryActivity.class);
				startActivity(intent);
			}
		}
		
	}

	public void onClickBrowse(View view) {
		// Open the patient list activity, to browse entries in the database
		startActivity(new Intent(this, PatientListActivity.class));
	}
	
	/**
	 * Display error message provided
	 * @param string
	 */
	private void makeToast(String string) {
		Toast.makeText(this, string, Toast.LENGTH_LONG).show();
	}

}
