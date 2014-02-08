package com.benhan82.SOCK.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

import com.benhan82.SOCK.R;

public class HeadView extends ImageView{

	Bitmap head, scaledHead;
	int bitX, bitY;

	public HeadView(Context context) {
		super(context);
		head = BitmapFactory.decodeResource(getResources(), R.drawable.learning_head);
		scaleHead(context);
		this.setImageBitmap(scaledHead);
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
