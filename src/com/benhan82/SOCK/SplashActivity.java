package com.benhan82.SOCK;

import com.benhan82.SOCK.R;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.Window;

public class SplashActivity extends Activity {

	MediaPlayer ourSong;
	
	@Override
	protected void onCreate(Bundle savedInstanceState1) {
		super.onCreate(savedInstanceState1);
		//Remove title bar
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);
		
		//Play sound clip
		ourSong = MediaPlayer.create(SplashActivity.this, R.raw.swallow);
		ourSong.start();

		//Open the StartMenuActivity in a new thread
		Thread timer = new Thread() {
			public void run() {
				try {
					sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					Intent openMainActivity = new Intent("android.intent.action.MENU1");
					startActivity(openMainActivity);
				}
			}
		};
		timer.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}

	@Override
	protected void onPause() {
		// when the activity goes to the background then end
		super.onPause();
		finish();
	}
}