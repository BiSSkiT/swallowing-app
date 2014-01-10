package de.vogella.android.todos;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import de.vogella.android.todos.contentprovider.MyTodoContentProvider;
import de.vogella.android.todos.database.TodoTable;

/*
 * TodoDetailActivity allows to enter a new todo item 
 * or to change an existing
 */
public class TodoDetailActivity extends Activity {
	private Spinner mCategory;
	private EditText mTitleText;
	private EditText mBodyText;
	private CheckBox mCheckBox1, mCheckBox2, mCheckBox3, mCheckBox4;
	
	private Uri todoUri;
	
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.todo_edit);
		
		mCategory = (Spinner) findViewById(R.id.category);
		mTitleText = (EditText) findViewById(R.id.todo_edit_summary);
		mBodyText = (EditText) findViewById(R.id.todo_edit_description);
		mCheckBox1 = (CheckBox) findViewById(R.id.checkBox1);
		mCheckBox2 = (CheckBox) findViewById(R.id.checkBox2);
		mCheckBox3 = (CheckBox) findViewById(R.id.checkBox3);
		mCheckBox4 = (CheckBox) findViewById(R.id.checkBox4);
		Button confirmButton = (Button) findViewById(R.id.todo_edit_button);
		
		Bundle extras = getIntent().getExtras();
		
		// check from the saved Instance
		todoUri	 = (bundle == null) ? null : (Uri) bundle
    		.getParcelable(MyTodoContentProvider.CONTENT_ITEM_TYPE);
    
		// Or passed from the other activity
    	if (extras != null) {
    		todoUri = extras
    				.getParcelable(MyTodoContentProvider.CONTENT_ITEM_TYPE);

    		fillData(todoUri);
    	}

    	confirmButton.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View view) {
    			if (TextUtils.isEmpty(mTitleText.getText().toString())) {
    				makeToast();
    			} else {
    				setResult(RESULT_OK);
    				finish();
    			}
    		}

    	});
	}

	private void fillData(Uri uri) {
		String[] projection = { 
				TodoTable.COLUMN_SUMMARY,
				TodoTable.COLUMN_DESCRIPTION, 
				TodoTable.COLUMN_CATEGORY, 
				TodoTable.COLUMN_CB1, 
				TodoTable.COLUMN_CB2, 
				TodoTable.COLUMN_CB3,
				TodoTable.COLUMN_CB4 };
		
		// Get a cursor to access the table
		Cursor cursor = getContentResolver().query(uri, projection, null, null,
				null);
		// if the entry exists in the database
		if (cursor != null) {
			cursor.moveToFirst();
			String category = cursor.getString(cursor
					.getColumnIndexOrThrow(TodoTable.COLUMN_CATEGORY));
			
			for (int i = 0; i < mCategory.getCount(); i++) {
				// iterates through the options in the spinner and sets the one 
				// that matches the string from the database query "category"
				String s = (String) mCategory.getItemAtPosition(i);
				if (s.equalsIgnoreCase(category)) {
					mCategory.setSelection(i);
				}
			}

			mTitleText.setText(cursor.getString(cursor
					.getColumnIndexOrThrow(TodoTable.COLUMN_SUMMARY)));
			mBodyText.setText(cursor.getString(cursor
					.getColumnIndexOrThrow(TodoTable.COLUMN_DESCRIPTION)));
			
			// if the returned values from the cursor is 0 then 
			// setChecked(false) or else setChecked(true)
			int i = cursor.getInt(cursor.getColumnIndexOrThrow(TodoTable.COLUMN_CB1));
			mCheckBox1.setChecked(i == 0 ? false : true);
			i = cursor.getInt(cursor.getColumnIndexOrThrow(TodoTable.COLUMN_CB2));
			mCheckBox2.setChecked(i == 0 ? false : true);
			i = cursor.getInt(cursor.getColumnIndexOrThrow(TodoTable.COLUMN_CB3));
			mCheckBox3.setChecked(i == 0 ? false : true);
			i = cursor.getInt(cursor.getColumnIndexOrThrow(TodoTable.COLUMN_CB4));
			mCheckBox4.setChecked(i == 0 ? false : true);

			// always close the cursor
			cursor.close();
		}
	}

	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		saveState();
		outState.putParcelable(MyTodoContentProvider.CONTENT_ITEM_TYPE, todoUri);
	}

	@Override
	protected void onPause() {
		super.onPause();
		saveState();
	}

	private void saveState() {
		String category = (String) mCategory.getSelectedItem();
		String summary = mTitleText.getText().toString();
		String description = mBodyText.getText().toString();
		boolean checkbox1 = mCheckBox1.isChecked();
		boolean checkbox2 = mCheckBox2.isChecked();
		boolean checkbox3 = mCheckBox3.isChecked();
		boolean checkbox4 = mCheckBox4.isChecked();

		// only save if either summary or description
		// is available

		if (description.length() == 0 && summary.length() == 0) {
			return;
		}

		ContentValues values = new ContentValues();
		values.put(TodoTable.COLUMN_CATEGORY, category);
		values.put(TodoTable.COLUMN_SUMMARY, summary);
		values.put(TodoTable.COLUMN_DESCRIPTION, description);
		values.put(TodoTable.COLUMN_CB1, (checkbox1 ? true : false) );
		values.put(TodoTable.COLUMN_CB2, (checkbox2 ? true : false) );
		values.put(TodoTable.COLUMN_CB3, (checkbox3 ? true : false) );
		values.put(TodoTable.COLUMN_CB4, (checkbox4 ? true : false) );
		
		if (todoUri == null) {
			// 	New todo
			todoUri = getContentResolver().insert(MyTodoContentProvider.CONTENT_URI, values);
		} else {
			// 	Update todo
			getContentResolver().update(todoUri, values, null, null);
		}
	}
	
	private void makeToast() {
		Toast.makeText(TodoDetailActivity.this, "Please maintain a summary",
				Toast.LENGTH_LONG).show();
	}
} 
