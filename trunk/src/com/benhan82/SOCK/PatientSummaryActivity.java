package com.benhan82.SOCK;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.benhan82.SOCK.database.PatientDatabaseHelper;

/**
 * @author Ben Han
 * class PatientSummaryActivity
 * This activity displays a summary of the patient data stored in the database.
 *
 */
public class PatientSummaryActivity extends Activity {
	private EditText mIdText;
	private EditText mSummaryText;
	private CheckBox mCheckBox1, mCheckBox2, mCheckBox3, mCheckBox4;
	private PatientDatabaseHelper db = MyApp.db;

	// todoUri is a handle for the PatientContentProvider from the saved instance
	private Uri todoUri;
	
	@Override
	protected void onCreate(Bundle bundle) {		
		super.onCreate(bundle);
		// Show the Up button in the action bar.
		setupActionBar();
		
		mIdText = (EditText) findViewById(R.id.patientIdText);
		mSummaryText = (EditText) findViewById(R.id.patientSummaryText);
		mCheckBox1 = (CheckBox) findViewById(R.id.clin_asse_1a_cb04);
		mCheckBox2 = (CheckBox) findViewById(R.id.clin_asse_1a_cb05);
		mCheckBox3 = (CheckBox) findViewById(R.id.clin_asse_1a_cb07);
		Button doneButton = (Button) findViewById(R.id.doneButton);
		
		Bundle extras = getIntent().getExtras();
		
//		// check from the saved Instance
//		todoUri	 = (bundle == null) ? null : 
//			(Uri) bundle.getParcelable(PatientContentProvider.CONTENT_ITEM_TYPE);
//    
//		// Or passed from the other activity
//    	if (extras != null) {
//    		todoUri = extras.getParcelable(PatientContentProvider.CONTENT_ITEM_TYPE);
//
//    		fillData(todoUri);
//    	}

    	doneButton.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View view) {
				setResult(RESULT_OK);
				finish();
    		}
    	});
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

	@Override
	protected void onPause() {
		super.onPause();
		saveState();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		saveState();
//		outState.putParcelable(PatientContentProvider.CONTENT_ITEM_TYPE, todoUri);		
	}
	

	/**
	 * method fillData(Uri uri)
	 * @param uri
	 * Opposite of saveState, this method loads data from the database to
	 * the view fields.
	 */
	private void fillData(Uri uri) {
		String[] projection = { 
				PatientDatabaseHelper.COLUMN_ID, 
				PatientDatabaseHelper.COLUMN_SUMMARY,
				PatientDatabaseHelper.COLUMN_CB1, 
				PatientDatabaseHelper.COLUMN_CB2, 
				PatientDatabaseHelper.COLUMN_CB3 };
		
		// Get a cursor to access the table
		Cursor cursor = getContentResolver().query(uri, projection, null, null,
				null);
		// if the entry exists in the database
		if (cursor != null) {
			cursor.moveToFirst();
			
			mIdText.setText(cursor.getString(cursor
					.getColumnIndexOrThrow(db.COLUMN_ID)));
			mSummaryText.setText(cursor.getString(cursor
					.getColumnIndexOrThrow(db.COLUMN_SUMMARY)));
			
			// if the returned values from the cursor is not 0 then 
			// setChecked(true) or else setChecked(false)
			int i = cursor.getInt(cursor.getColumnIndexOrThrow(db.COLUMN_CB1));
			mCheckBox1.setChecked(i != 0 ? true : false);
			i = cursor.getInt(cursor.getColumnIndexOrThrow(db.COLUMN_CB2));
			mCheckBox2.setChecked(i != 0 ? true : false);
			i = cursor.getInt(cursor.getColumnIndexOrThrow(db.COLUMN_CB3));
			mCheckBox3.setChecked(i != 0 ? true : false);

			// always close the cursor
			cursor.close();
		}
	}

	/**
	 * method saveState()
	 * As the name implies this method is called to save the view fields to the
	 * database by way of calls to the getContentResolver() method.
	 */
	private void saveState() {
		String id = mIdText.getText().toString();
		String summary = mSummaryText.getText().toString();
		boolean checkbox1 = mCheckBox1.isChecked();
		boolean checkbox2 = mCheckBox2.isChecked();
		boolean checkbox3 = mCheckBox3.isChecked();
		
		// only save if either summary or description
		// is available
		if (id.length() == 0 && id.length() == 0) {
			return;
		}

		ContentValues values = new ContentValues();
		values.put(db.COLUMN_ID, id);
		values.put(db.COLUMN_SUMMARY, summary);
		values.put(db.COLUMN_CB1, (checkbox1 ? true : false) );
		values.put(db.COLUMN_CB2, (checkbox2 ? true : false) );
		values.put(db.COLUMN_CB3, (checkbox3 ? true : false) );
		
		if (todoUri == null) {
			// 	New patient
//			todoUri = getContentResolver().insert(PatientContentProvider.CONTENT_URI, values);
		} else {
			// 	Update patient
			getContentResolver().update(todoUri, values, null, null);
		}
	}
	
}
