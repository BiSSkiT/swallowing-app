package com.benhan82.SOCK;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.benhan82.SOCK.database.PatientContentProvider;
import com.benhan82.SOCK.database.PatientTable;

/**
 * class PatientListActivity
 * This class lists the available patients in the database. The activity is started
 * by clicking browse in the PatientSelectionActivity.
 * @author Adapted by Ben Han. Original code from 
 * 
 * http://www.vogella.com/tutorials/AndroidSQLite/article.html
 * Android SQLite database and content provider - Tutorial - 
 * by Lars Vogel
 *
 */
public class PatientListActivity extends ListActivity implements
	LoaderManager.LoaderCallbacks<Cursor> {
	
	private static final int ACTIVITY_CREATE = 0;
	private static final int ACTIVITY_EDIT = 1;
	private static final int DELETE_ID = Menu.FIRST + 1;
	//private Cursor cursor
	private SimpleCursorAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_patient_list);
		this.getListView().setDividerHeight(2);
		fillData();
		registerForContextMenu(getListView());
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
		case R.id.newPatient:
			createPatient();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}	

	// This is the callback method that is run when the user selects an item from 
	// the context menu
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case DELETE_ID:
			// cast to AdapterContextMenuInfo to allow us to get the position of the item
			AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		    Uri uri = Uri.parse(PatientContentProvider.CONTENT_URI + "/"
		          + info.id);
		    getContentResolver().delete(uri, null, null);
			Log.d("debug", "in delete item");
			fillData();
			return true;
		}
		return super.onContextItemSelected(item);
	}

	// handler method called when the user clicks new button on the menu
	private void createPatient() {
		startActivity(new Intent(this, PatientSummaryActivity.class));
	}
	
	// Opens the second activity if an entry is clicked   TODO!!!!!
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Intent i = new Intent(this, PatientSummaryActivity.class);
		Uri todoUri = Uri.parse(PatientContentProvider.CONTENT_URI + "/" + id);
	    i.putExtra(PatientContentProvider.CONTENT_ITEM_TYPE, todoUri);
	    
		startActivity(i);
	}
	

	private void fillData() {
		
	// 	Fields from the database (projection)
	//	To fill the list of available patient entries.
    // 	Must include the _id column for the adapter to work
		String[] from = new String[] { PatientTable.COLUMN_FIRSTNAME, 
				PatientTable.COLUMN_LASTNAME,
				PatientTable.COLUMN_ID };
    // 	Fields on the UI to which we map
		int[] to = new int[] { R.id.firstName, R.id.lastName, R.id.dbId };
		
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.patient_list_row, null, from,
				to, 0);
		
		setListAdapter(adapter);
	}
	
	// This function is called when the user performs a long click event
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, DELETE_ID, 0, R.string.menu_delete);
	}

	
	/**
	 * The three methods below: onCreateLoader(), onLoadFinished(), and onLoaderReset()
	 * are the implementation for the LoaderManager.LoaderCallbacks interface.
	 */
	
	
	/**
	 * This is the callback method when the Loader is initialised.
	 * The CursorLoader is created here.
	 */
	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
	    String[] projection = PatientTable.COLUMNS;
	    CursorLoader cursorLoader = new CursorLoader(this,
	        PatientContentProvider.CONTENT_URI, projection, null, null, null);
	    return cursorLoader;
	}

	/**
	 * This is the callback method invoked once the Loader has finished loading
	 * the data in the background.
	 */
	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
	    adapter.swapCursor(data);
	}

	/**
	 * This callback method is invoked if the Cursor becomes invalid.
	 */
	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
	    // data is not available anymore, delete reference
	    adapter.swapCursor(null);
	}

}
