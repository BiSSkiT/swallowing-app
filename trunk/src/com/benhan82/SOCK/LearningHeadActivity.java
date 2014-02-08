package com.benhan82.SOCK;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.LinearLayout;

import com.benhan82.SOCK.views.HeadView;

public class LearningHeadActivity extends Activity {
	
	LinearLayout container;
	HeadView myView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_learning_head);
		
		myView = new HeadView(this);
        container = (LinearLayout) findViewById(R.id.container);
        container.addView(myView);
	}

}
