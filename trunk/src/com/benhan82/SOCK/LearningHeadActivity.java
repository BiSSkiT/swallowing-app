package com.benhan82.SOCK;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.benhan82.SOCK.views.HeadView;

public class LearningHeadActivity extends Activity {
	
	LinearLayout container;
	HeadView myView;
	public TextView tvEye;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_learning_head);
		
		myView = new HeadView(this);
        container = (LinearLayout) findViewById(R.id.container);
        container.addView(myView);
        
        // this is the TextView for the info in the zoomed view
        // the look and feel is set here.
        tvEye = (TextView) findViewById(R.id.tvEye);
        tvEye.setVisibility(View.INVISIBLE);
        
        String fontPath = "fonts/a song for jennifer bold.ttf";
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
        tvEye.setTypeface(tf);
        tvEye.setBackgroundColor(getResources().getColor(R.color.transparent_white));
        tvEye.setTextColor(getResources().getColor(R.color.text_colour));
	}

}
