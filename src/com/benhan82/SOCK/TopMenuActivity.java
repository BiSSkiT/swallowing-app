package com.benhan82.SOCK;

import com.benhan82.SOCK.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;

public class TopMenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState1) {
		super.onCreate(savedInstanceState1);
		//Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_top_menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu1, menu);
		return true;
	}
	
	public void openClinical (View v) {
		// method to open the clinical mode menu
		Intent intent = new Intent(this, PatientSelectionActivity.class);
		startActivity(intent);
	}
	
	public void openLearning (View v) {
		// method to open the clinical mode menu
		Intent intent = new Intent(this, LearningActivity.class);
		startActivity(intent);
	}
}
