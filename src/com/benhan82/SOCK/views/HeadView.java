package com.benhan82.SOCK.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import com.benhan82.SOCK.R;

public class HeadView extends View {

	Bitmap head, scaledHead;
	int bitX, bitY;
	int canX, canY;
	
	public HeadView(Context context) {
		super(context);
		// Create bitmap object from image resource
		head = BitmapFactory.decodeResource(getResources(), R.drawable.learning_head);
		scaleHead(context);
		
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(Color.GREEN);
		canvas.drawBitmap(scaledHead, 0, 0, null);
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

}
