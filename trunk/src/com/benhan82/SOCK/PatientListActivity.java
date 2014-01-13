package com.benhan82.SOCK;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.benhan82.SOCK.database.PatientContentProvider;
import com.benhan82.SOCK.database.PatientTable;

public class PatientListActivity extends ListActivity implements 
	LoaderManager.LoaderCallbacks<Cursor> {
	private static final int DELETE_ID = Menu.FIRST + 1;
	// private Cursor cursor;
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
			AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
			.getMenuInfo();
			Uri uri = Uri.parse(PatientContentProvider.CONTENT_URI + "/"
					+ info.id);
			getContentResolver().delete(uri, null, null);
			fillData();
			return true;
		}
		return super.onContextItemSelected(item);
	}

	// handler method called when the user clicks insert button on the menu, or the
	// New button or Open button with a valid patient id
	private void createPatient() {
		Intent i = new Intent(this, PatientSummaryActivity.class);
		startActivity(i);
	}
	
	// Opens the second activity if an entry is clicked
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
		String[] from = new String[] { PatientTable.COLUMN_SUMMARY };
    // 	Fields on the UI to which we map
		int[] to = new int[] { R.id.label };
		
		getLoaderManager().initLoader(0, null, this);
		adapter = new SimpleCursorAdapter(this, R.layout.patient_list_row, null, from,
				to, 0);
		
		setListAdapter(adapter);
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, DELETE_ID, 0, R.string.menu_delete);
	}

	// creates a new loader after the initLoader () call
	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		String[] projection = { PatientTable.COLUMN_ID, PatientTable.COLUMN_SUMMARY };
		CursorLoader cursorLoader = new CursorLoader(this,
				PatientContentProvider.CONTENT_URI, projection, null, null, null);
		return cursorLoader;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
		adapter.swapCursor(cursor);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
	    // 	data is not available anymore, delete reference
		adapter.swapCursor(null);
	}
}
