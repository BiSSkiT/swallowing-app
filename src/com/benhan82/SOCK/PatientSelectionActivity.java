package com.benhan82.SOCK;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class PatientSelectionActivity extends Activity {

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

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	public void onClickNew(View view) {
		// Create a new patient entry and open Clinical Assessment Activity
		// TODO === call method to create new patient entry
		Intent intent = new Intent(this, ClinicalAssessmentActivity.class);
		startActivity(intent);
	}
	
	public void onClickOpen(View view) {
		// Open an existing patient entry and open Clinical Assessment Activity
		
		// TODO == call method to retrieve patient data from the database table
		startActivity(new Intent(this, ClinicalAssessmentActivity.class));
	}
	
	public void onClickBrowse(View view) {
		// Open the patient list activity, browse entries in the database
		startActivity(new Intent(this, PatientListActivity.class));
	}

}
