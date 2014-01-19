package com.benhan82.SOCK;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PatientSelectionActivity extends Activity {
	
	EditText et = (EditText) findViewById(R.id.edit_patientID);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient_selection);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.patient_selection, menu);
		return true;
	}
	
	public void onClickNew(View view) {
		// Open Clinical Assessment Activity
		Intent intent = new Intent(this, ClinicalActivity.class);
		startActivity(intent);
	}
	
	public void onClickOpen(View view) {
		// Open an existing patient entry and open Patient Summary Activity, only
		// if the user has entered an ID
		EditText et = (EditText) findViewById(R.id.edit_patientID);
		
		String s = et.getText().toString().trim();
		if ( et.getText() == null || s.equals("") ) {
			makeToast();
		} else {
//			// check that the ID exists in the database
//			int i = this.et.get
//			if (condition) {
				Intent intent = new Intent(this, PatientSummaryActivity.class);
				startActivity(intent);
//			}
		}
		
	}
	
	public void onClickBrowse(View view) {
		// Open the patient list activity, to browse entries in the database
		startActivity(new Intent(this, PatientListActivity.class));
	}

	
	private void makeToast() {
		Toast.makeText(PatientSelectionActivity.this, "Please enter a patient ID",
				Toast.LENGTH_LONG).show();
	}

}
