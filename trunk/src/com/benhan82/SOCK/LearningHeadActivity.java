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
import android.widget.ImageView;

public class LearningHeadActivity extends Activity {
	
	ImageView imageView;
    private GestureDetectorCompat mDetector; 
    ObjectAnimator translateAnimation;
    ObjectAnimator scaleAnimation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_learning_head);

		imageView = (ImageView) findViewById(R.id.imageHead);
        mDetector = new GestureDetectorCompat(this, new MyGestureListener());

        // Move the button over to the right and then back
        translateAnimation = ObjectAnimator.ofFloat(imageView, View.TRANSLATION_X, 800);
        translateAnimation.setRepeatCount(1);
        translateAnimation.setRepeatMode(ValueAnimator.REVERSE);
        
        // Scale the button in X and Y. Note the use of PropertyValuesHolder to animate
        // multiple properties on the same object in parallel.
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat(View.SCALE_X, 2);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 2);
        scaleAnimation = ObjectAnimator.ofPropertyValuesHolder(imageView, pvhX, pvhY);
        scaleAnimation.setRepeatCount(1);
        scaleAnimation.setRepeatMode(ValueAnimator.REVERSE);
        
//        translateAnimation.start();

//        imageView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//            		
//            	
//            	float x = event.getX();
//            	float y = event.getY();
//                if ( (x > 50) && (x < 250) && (y > 100) && (y < 500) ) {
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
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
	
	// Subclass of SimpleOnGestureListener to respond only to single tap events
    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        
        @Override
        public boolean onSingleTapUp(MotionEvent event) { 

        	float x = event.getX();
        	float y = event.getY();
            if ( (x > 50) && (x < 250) && (y > 100) && (y < 500) ) {
            	translateAnimation.start();
            }
            else {
            	scaleAnimation.start();
            }
            return true;
        }
    }

}
