package com.benhan82.SOCK;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.benhan82.SOCK.database.Patient;
import com.benhan82.SOCK.database.PatientDatabaseHelper;

public class NewPatientActivity extends Activity {
	
	EditText firstNameEdit;
	EditText lastNameEdit;
	private PatientDatabaseHelper db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_patient);
		// Show the Up button in the action bar.
		setupActionBar();
		db = MyApp.getDb();
		firstNameEdit = (EditText) findViewById(R.id.newPatientFirstNameEditText);
		lastNameEdit = (EditText) findViewById(R.id.newPatientLastNameEditText);
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_patient, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void onClickCreate(View view) {
		// Create new patient object
		String firstName = firstNameEdit.getText().toString();
		String lastName = lastNameEdit.getText().toString();
		
		Patient patient = new Patient(firstName, lastName);
		int id = db.addPatient(patient);
		MyApp.setPatient(db.getPatient(id));
		
		if (id == -1) {
			makeToast("There was an error creating the Patient file.");
		} else {
			makeToast("New patient \"" +  firstName + " " + lastName + "\" was successfully " +
					"added to the database. New patient ID: " + id);
		}
		
		Intent intent = new Intent(this, ClinicalActivity.class);
		startActivity(intent);
		finish();
	}
	
	/**
	 * Display error message provided
	 * @param string
	 */
	private void makeToast(String string) {
		Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
	}
}
