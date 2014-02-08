package com.benhan82.SOCK;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.benhan82.SOCK.views.HeadView;

public class LearningHeadActivity extends Activity {
	
	LinearLayout container;
	HeadView myView;
    ObjectAnimator translateAnimation;
    ObjectAnimator scaleAnimation;
//    private GestureDetectorCompat mDetector; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_learning_head);

//        mDetector = new GestureDetectorCompat(this, new MyGestureListener());
        myView = new HeadView(this);
        container = (LinearLayout) findViewById(R.id.container);
        container.addView(myView);

        // Move the button over to the right and then back
        translateAnimation = ObjectAnimator.ofFloat(myView, View.TRANSLATION_X, 800);
        translateAnimation.setRepeatCount(1);
        translateAnimation.setRepeatMode(ValueAnimator.REVERSE);
        
        // Scale the button in X and Y. Note the use of PropertyValuesHolder to animate
        // multiple properties on the same object in parallel.
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat(View.SCALE_X, 2);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 2);
        scaleAnimation = ObjectAnimator.ofPropertyValuesHolder(myView, pvhX, pvhY);
        scaleAnimation.setRepeatCount(1);
        scaleAnimation.setRepeatMode(ValueAnimator.REVERSE);
        
//        myView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//            	float x = event.getX();
//            	float y = event.getY();
//                if ( (x > 100) && (x < 400) && (y > 100) && (y < 400) ) {
//                	translateAnimation.start();
//                	return true;
//                }
//                else {
//                	scaleAnimation.start();
//                	return false;
//                }
//            }
//        });

	}

    @Override 
    public boolean onTouchEvent(MotionEvent event){ 
    	float x = event.getX();
    	float y = event.getY();
        if ( (x > 100) && (x < 400) && (y > 100) && (y < 400) ) {
        	Log.d("touch", "translateAnimation");
        	translateAnimation.start();
        }
        else {
        	Log.d("touch", "scaleAnimation");
        	scaleAnimation.start();
        }
        return true;
    }

	// Subclass of SimpleOnGestureListener to respond only to single tap events
//    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
//
//        @Override
//        public boolean onDown(MotionEvent event) { 
//            return true;
//        }
//        
//        @Override
//        public boolean onSingleTapUp(MotionEvent event) { 
//
//        	float x = event.getX();
//        	float y = event.getY();
//            if ( (x > 100) && (x < 400) && (y > 100) && (y < 400) ) {
//            	Log.d("touch", "translateAnimation");
//            	translateAnimation.start();
//            }
//            else {
//            	Log.d("touch", "scaleAnimation");
//            	scaleAnimation.start();
//            }
//            return true;
//        }
//    }
	

}
