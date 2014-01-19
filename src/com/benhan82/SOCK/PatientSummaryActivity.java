package com.benhan82.SOCK;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.benhan82.SOCK.database.Patient;
import com.benhan82.SOCK.database.PatientDatabaseHelper;

/**
 * @author Ben Han
 * class PatientSummaryActivity
 * This activity displays the notes of the patient data stored in the database.
 *
 */
public class PatientSummaryActivity extends Activity {
	
	private EditText mIdText;
	private EditText mNotesText;
	private CheckBox mCheckBox1, mCheckBox2, mCheckBox3, mCheckBox4;
	private PatientDatabaseHelper db = MyApp.getDb();
	
	@Override
	protected void onCreate(Bundle bundle) {		
		super.onCreate(bundle);
		setContentView(R.layout.activity_patient_summary);
		// Show the Up button in the action bar.
		setupActionBar();
		
		mIdText = (EditText) findViewById(R.id.patientIdText);
		mNotesText = (EditText) findViewById(R.id.patientSummaryText);
		mCheckBox1 = (CheckBox) findViewById(R.id.clin_asse_1a_cb04);
		mCheckBox2 = (CheckBox) findViewById(R.id.clin_asse_1a_cb05);
		mCheckBox3 = (CheckBox) findViewById(R.id.clin_asse_1a_cb07);
		Button doneButton = (Button) findViewById(R.id.doneButton);
		
    	doneButton.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View view) {
				setResult(RESULT_OK);
				finish();
    		}
    	});
    	
    	fillData();
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
		getMenuInflater().inflate(R.menu.patient_detail, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPause() {
		super.onPause();
		saveState();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		saveState();
	}
	

	/**
	 * method fillData()
	 * Opposite of saveState, this method loads data from the database helper
	 * into the view fields.
	 */
	private void fillData() {

		Patient pat = MyApp.getPatient();
		mIdText.setText( Integer.toString(pat.getId()) );
		mNotesText.setText( pat.getNotes() );
		
		// if the returned values from the cursor is not 0 then 
		// setChecked(true) or else setChecked(false)
		
		try {
			boolean[] checkBoxes = pat.getCheckBoxes();
			for (int i = 0; i < checkBoxes.length; i++)
				mCheckBox1.setChecked(checkBoxes[i]);
		} catch (Exception e) {
			Log.d("exception", e.getMessage());
			e.printStackTrace();
		}
		
	}

	/**
	 * method saveState()
	 * As the name implies this method is called to save the view fields to the
	 * database using the helper
	 */
	private void saveState() {
		// 	Update patient
		db.updatePatient(MyApp.getPatient());
	}
	
}
