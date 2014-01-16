package com.benhan82.SOCK;

import android.app.Application;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.benhan82.SOCK.database.Patient;
import com.benhan82.SOCK.database.PatientDatabaseHelper;

/**
 * class PatientListActivity
 * This class lists the available patients in the database. The activity is started
 * by clicking browse in the PatientSelectionActivity.
 * @author Ben Han
 *
 */
public class PatientListActivity extends ListActivity  {
	
	private static final int DELETE_ID = Menu.FIRST + 1;
	private PatientDatabaseHelper db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient_list);
		this.getListView().setDividerHeight(2);
		fillData();
		registerForContextMenu(getListView());
		this.db = MyApp.db;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.patient_list, menu);
		return true;
	}

	// Reaction to the menu selection
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// handler method called when the user clicks insert button on the menu
		case R.id.insert:
			createPatient();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}	

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case DELETE_ID:
//			Patient patient = 
//			db.deletePatient(patient);
			fillData();
			return true;
		}
		return super.onContextItemSelected(item);
	}

	// handler method called when the user clicks insert button on the menu, or the
	// New button or Open button with a valid patient id
	private Patient createPatient() {
		Patient patient = new Patient("Bob", "Jones");		
		db.addPatient(patient);
		return patient;
	}
	
	// Opens the second activity if an entry is clicked   TODO!!!!!
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Intent i = new Intent(this, PatientSummaryActivity.class);		
		startActivity(i);
	}
	

	private void fillData() {
		
	// 	Fields from the database (projection)
	//	To fill the list of available patient entries.
    // 	Must include the _id column for the adapter to work
		String[] from = new String[] { PatientDatabaseHelper.COLUMN_SUMMARY };
    // 	Fields on the UI to which we map
		int[] to = new int[] { R.id.label };
		
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.patient_list_row, null, from,
				to, 0);
		
		setListAdapter(adapter);
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, DELETE_ID, 0, R.string.menu_delete);
	}

}
