package com.benhan82.SOCK;

import com.benhan82.SOCK.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;

public class ClinicalMenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_clinical_menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.clinical_menu, menu);
		return true;
	}

	public void openClinAsse(View view) {
		// method to open the clinical assessment activity
		Intent intent = new Intent(this, ClinicalAssessmentActivity.class);
		startActivity(intent);
	}
	
	public void openClinDiag(View view) {
		// method to open the diagnosis activity
		Intent intent = new Intent(this, ClinicalDiagnosisActivity.class);
		startActivity(intent);
	}
	
	public void openClinEduc(View view) {
		// method to open the patient education activity
		Intent intent = new Intent(this, ClinicalPatientEdActivity.class);
		startActivity(intent);
	}
}
