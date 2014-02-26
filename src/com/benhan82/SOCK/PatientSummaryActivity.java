package com.benhan82.SOCK;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.benhan82.SOCK.database.PatientContentProvider;
import com.benhan82.SOCK.database.PatientDatabaseHelper;
import com.benhan82.SOCK.database.PatientTable;

/**
 * @author Ben Han
 * class PatientSummaryActivity
 * This activity displays the notes of the patient data stored in the database.
 *
 */
public class PatientSummaryActivity extends Activity {
	
	private EditText mFirstName;
	private EditText mLastName;
	private TextView mIdText;
	private EditText mNotesText;
	private CheckBox mCheckBox1, mCheckBox2, mCheckBox3, mCheckBox4;
	private PatientDatabaseHelper db = MyApp.getDb();
	
	private Uri patientUri;
	
	@Override
	protected void onCreate(Bundle bundle) {		
		super.onCreate(bundle);
		setContentView(R.layout.activity_patient_summary);
		// Show the Up button in the action bar.
//		setupActionBar();
		
		mFirstName = (EditText) findViewById(R.id.firstName);
		mLastName = (EditText) findViewById(R.id.lastName);
		mIdText = (TextView) findViewById(R.id.patientIdText);
		mNotesText = (EditText) findViewById(R.id.patientNotesText);
		mCheckBox1 = (CheckBox) findViewById(R.id.clin_asse_1a_cb04);
		mCheckBox2 = (CheckBox) findViewById(R.id.clin_asse_1a_cb05);
		mCheckBox3 = (CheckBox) findViewById(R.id.clin_asse_1a_cb07);
		Button doneButton = (Button) findViewById(R.id.doneButton);

	    Bundle extras = getIntent().getExtras();

	    // check from the saved Instance
	    patientUri = (bundle == null) ? null : (Uri) bundle
	    		.getParcelable(PatientContentProvider.CONTENT_ITEM_TYPE);

	    // Or passed from the other activity
	    if (extras != null) {
	    	patientUri = extras
	    			.getParcelable(PatientContentProvider.CONTENT_ITEM_TYPE);

	    	fillData(patientUri);
	    }
		
    	doneButton.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View view) {
				setResult(RESULT_OK);
				finish();
    		}
    	});
	}
//
//	/**
//	 * Set up the {@link android.app.ActionBar}.
//	 */
//	private void setupActionBar() {
//
//		getActionBar().setDisplayHomeAsUpEnabled(true);
//
//	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.patient_detail, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		switch (item.getItemId()) {
//		case android.R.id.home:
//			NavUtils.navigateUpFromSameTask(this);
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}

	/**
	 * method fillData()
	 * Opposite of saveState, this method loads data from the database helper
	 * into the view fields.
	 */
	private void fillData(Uri uri) {
		String[] projection = PatientTable.COLUMNS;
		Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
			
			// fill in the name
			String firstName = cursor.getString(cursor
					.getColumnIndexOrThrow(PatientTable.COLUMN_FIRSTNAME));
			String lastName = cursor.getString(cursor
					.getColumnIndexOrThrow(PatientTable.COLUMN_LASTNAME));
			
			mFirstName.setText(firstName);
			mLastName.setText(lastName);
			
			// set ID text and notes
			mIdText.setText(cursor.getString(cursor
					.getColumnIndexOrThrow(PatientTable.COLUMN_ID)));
	      	mNotesText.setText(cursor.getString(cursor
	      			.getColumnIndexOrThrow(PatientTable.COLUMN_NOTES)));
	      	
	      	// need to set checkboxes
	      	
	      	// always close the cursor
	      	cursor.close();
		}
//
//		Patient pat = MyApp.getPatient();
//		mIdText.setText( Integer.toString(pat.getId()) );
//		mNotesText.setText( pat.getNotes() );
//		
//		// if the returned values from the cursor is not 0 then 
//		// setChecked(true) or else setChecked(false)
//		
//		try {
//			boolean[] checkBoxes = pat.getCheckBoxes();
//			for (int i = 0; i < checkBoxes.length; i++)
//				mCheckBox1.setChecked(checkBoxes[i]);
//		} catch (Exception e) {
//			Log.d("exception", e.getMessage());
//			e.printStackTrace();
//		}
//		
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		saveState();
	}

	@Override
	protected void onPause() {
		super.onPause();
		saveState();
	}
	
	/**
	 * method saveState()
	 * As the name implies this method is called to save the view fields to the
	 * database using the helper
	 */
	private void saveState() {
		// 	Update patient
//		db.updatePatient(MyApp.getPatient());
		
		String name = (String) mFirstName.getText().toString();
		String notes = (String) mNotesText.getText().toString();

	    ContentValues values = new ContentValues();
	    values.put(PatientTable.COLUMN_FIRSTNAME, name);
	    values.put(PatientTable.COLUMN_NOTES, notes);

	    if (patientUri == null) {
	    	// New todo
	    	patientUri = getContentResolver().insert(PatientContentProvider.CONTENT_URI, values);
	    } else {
	    	// Update todo
	    	getContentResolver().update(patientUri, values, null, null);
	    }
	}

}
