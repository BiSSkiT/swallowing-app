package com.benhan82.SOCK;

import com.benhan82.SOCK.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;

public class LearningMenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_learning_menu);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.learning_menu, menu);
		return true;
	}

}
