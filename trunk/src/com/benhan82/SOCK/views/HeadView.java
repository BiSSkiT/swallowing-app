package com.benhan82.SOCK.views;

import java.util.Calendar;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.benhan82.SOCK.R;

public class HeadView extends ImageView{

	Bitmap head, scaledHead;
	int bitX, bitY;
    ObjectAnimator translateAnimation;
    ObjectAnimator scaleAnimation;
//    private GestureDetectorCompat mDetector; 
    
    // Variables for onTouchEvent()
    private float mDownX;
    private float mDownY;
    private final float SCROLL_THRESHOLD = 10;
    private boolean isOnClick;

	public HeadView(Context context) {
		super(context);
		head = BitmapFactory.decodeResource(getResources(), R.drawable.learning_head);
		scaleHead(context);
		this.setImageBitmap(scaledHead);

        // Move the button over to the right and then back
        translateAnimation = ObjectAnimator.ofFloat(this, View.TRANSLATION_X, 800);
        translateAnimation.setRepeatCount(1);
        translateAnimation.setRepeatMode(ValueAnimator.REVERSE);
        
        // Scale the button in X and Y. Note the use of PropertyValuesHolder to animate
        // multiple properties on the same object in parallel.
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat(View.SCALE_X, 2);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 2);
        scaleAnimation = ObjectAnimator.ofPropertyValuesHolder(this, pvhX, pvhY);
        scaleAnimation.setRepeatCount(1);
        scaleAnimation.setRepeatMode(ValueAnimator.REVERSE);
        
//        mDetector = new GestureDetectorCompat(context, new MyGestureListener());
	}

	public void scaleHead(Context context) {
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		// method to scale the bitmap to the same width as the screen size
		bitX = head.getWidth();
		bitY = head.getHeight();
		float sf = (float) width / (float) bitX;
		scaledHead = Bitmap.createScaledBitmap(head, width, (int) sf*bitY, false);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
	    switch (event.getAction()) {
	        case MotionEvent.ACTION_UP: {
                //click event has occurred
            	Log.d("touch", "onClick()");
            	
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
			}
	        
	    }

    	Log.d("touch", "action: " + event.getAction());
		return true;
	}

	// Subclass of SimpleOnGestureListener to respond only to single tap events
//    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
//
//        @Override
//        public boolean onDown(MotionEvent event) { 
//        	Log.d("touch", "onDown()");
//            return true;
//        }
//        
//        @Override
//		public void onLongPress(MotionEvent e) {
//			// TODO Auto-generated method stub
//        	Log.d("touch", "onLongPress()");
//
//        	float x = e.getX();
//        	float y = e.getY();
//            if ( (x > 100) && (x < 400) && (y > 100) && (y < 400) ) {
//            	Log.d("touch", "translateAnimation");
//            	translateAnimation.start();
//            }
//            else {
//            	Log.d("touch", "scaleAnimation");
//            	scaleAnimation.start();
//            }
//            
//			super.onLongPress(e);
//		}


//		@Override
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
//	 }    
	
}

