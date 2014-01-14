package com.benhan82.SOCK;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.benhan82.SOCK.database.PatientContentProvider;

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
	
	public void onClickNew(View view) {
		// Open Clinical Assessment Activity
		Intent intent = new Intent(this, ClinicalAssessmentActivity.class);
		startActivity(intent);
	}
	
	public void onClickOpen(View view) {
		// Open an existing patient entry and open Patient Summary Activity
		
		// TODO == call method to retrieve patient data from the database table
		startActivity(new Intent(this, PatientSummaryActivity.class));
	}
	
	public void onClickBrowse(View view) {
		// Open the patient list activity, to browse entries in the database
		startActivity(new Intent(this, PatientListActivity.class));
	}

}
