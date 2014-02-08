package com.benhan82.SOCK.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.benhan82.SOCK.R;

public class HeadView2 extends View{
	

	  private final Drawable logo;

	  public HeadView2(Context context) {
	    super(context);
	    logo = context.getResources().getDrawable(R.drawable.learning_head);
	    setBackgroundDrawable(logo);
	  }

	  public HeadView2(Context context, AttributeSet attrs) {
	    super(context, attrs);
	    logo = context.getResources().getDrawable(R.drawable.learning_head);
	    setBackgroundDrawable(logo);
	  }

	  public HeadView2(Context context, AttributeSet attrs, int defStyle) {
	    super(context, attrs, defStyle);
	    logo = context.getResources().getDrawable(R.drawable.learning_head);
	    setBackgroundDrawable(logo);
	  }

	  @Override protected void onMeasure(int widthMeasureSpec,
	      int heightMeasureSpec) {
	    int width = MeasureSpec.getSize(widthMeasureSpec);
	    int height = width * logo.getIntrinsicHeight() / logo.getIntrinsicWidth();
	    setMeasuredDimension(width, height);
	  }
	

}
