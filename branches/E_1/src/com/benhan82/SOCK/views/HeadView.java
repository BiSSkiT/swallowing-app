package com.benhan82.SOCK.views;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.benhan82.SOCK.LearningHeadActivity;
import com.benhan82.SOCK.R;

/**
 * @author Ben14
 *
 */
/**
 * @author Ben14
 *
 */
public class HeadView extends ImageView{

	Bitmap head, scaledHead;
	int bitX, bitY;
    AnimatorSet setEye;
    AnimatorSet mCurrentAnimator;
    boolean zoomed = false;
    int viewNo = 0;
    int currY = 0;
    TextView currInfo;
    RelativeLayout relativeLayout;
    LearningHeadActivity parentActivity;
   
    public static final int zoomEyes = 1;
    public static final int zoomNose = 2;
    public static final int zoomBrain = 3;
    public static final int zoomLips = 4;
    public static final int zoomJaw = 5;
    public static final int zoomTongue = 6;
    public static final int zoomVFs = 7;
    public static final int zoomPalate = 8;
    public static final int zoomHyoid = 9;
    public static final int zoomEpig = 10;
    public static final int zoomUES = 11;
    public static final float scaleFactor = 3;
    public final int duration = 500; 
    
    // bounding boxes for touch areas, some overlap, in which case the box that is checked 
    // first will handle the tap. Currently only an issue with tongue and jaw areas.
    // The tongue is being checked first to leave an L-shaped area for the jaws to catch taps.
    public static final int[] eye = {120,230,250,350,500,500};
    public static final int[] nose = {30,140,340,440,800,230};
    public static final int[] brain = {215,635,110,360,-70,250};
    public static final int[] lips = {30,140,440,560,700,-130};
    public static final int[] jaws = {30,210,560,680,700,-400};
    public static final int[] tongue = {150,305,490,590,400,-200};
    public static final int[] vfs = {280,400,690,810,100,-860};
    public static final int[] palate = {300,400,390,570,150,-100};
    public static final int[] hyoid = {210,280,600,700,500,-600};
    public static final int[] epig = {280,370,580,670,120,-550};
    public static final int[] ues = {400,500,690,810,-100,-860};
    
    // Standard constructor
	public HeadView(Context context) {
		super(context);
		
		// Set image for zooming
		head = BitmapFactory.decodeResource(getResources(), R.drawable.head_sagital_800_wide);
		scaleHead(context);
		this.setImageBitmap(scaledHead);
		parentActivity = (LearningHeadActivity) context;
	}

	// Change the size of the bitmap to match the width
	// Currently set only for landscape.
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
		float height = sf * (float) bitY;
		scaledHead = Bitmap.createScaledBitmap(head, width, (int) height, false);
	}

	// Handle all single taps
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
	    switch (event.getAction()) {
	        case MotionEvent.ACTION_UP: {
                //click event has occurred
	        	// get the screen coordinates of the view
	        	int[] loc = {0, 0};
	        	this.getLocationOnScreen(loc);
            	
            	// get tap coordinates
	        	float x = event.getX();
	        	float y = event.getY();
		        
	        	// if not already zoomed in, look to see if the tap was inside any bounding box
		        if (!zoomed) {
		        	viewNo = 0;
		        	if ( (x > eye[0]) && (x < eye[1]) && (y > eye[2]) && (y < eye[3]) ) {
			        	// ----------------------> EYE <-------------------------------------------
		            	Log.d("touch", "eye");
		            	
		            	// Save state info
		            	zoomed = true;
		            	viewNo = zoomEyes;
		            	currY = loc[1];
		            	
		                // Zoom in on 1. Eye
		                AnimatorSet set = new AnimatorSet();
		                set.play(ObjectAnimator.ofFloat(this, View.TRANSLATION_X, eye[4]))
		                            .with(ObjectAnimator.ofFloat(this, 
		                                            View.TRANSLATION_Y, eye[5] - currY))
		                            .with(ObjectAnimator.ofFloat(this, 
		                                            View.SCALE_X, scaleFactor))
		                            .with(ObjectAnimator.ofFloat(this, 
		                                            View.SCALE_Y, scaleFactor));
		                set.setDuration(duration);
		                set.start();
		                mCurrentAnimator = set;
		                
		                // display information
		                addText(viewNo);
		                
		            } else if((x > nose[0]) && (x < nose[1]) && (y > nose[2]) && (y < nose[3]) ) {
			        	// ----------------------> NOSE <-------------------------------------------
		            	Log.d("touch", "nose");

		            	// Save state info
		            	zoomed = true;
		            	viewNo = zoomNose;
		            	currY = loc[1];
		            	
		                // Zoom in on 2. Nose
		                AnimatorSet set = new AnimatorSet();
		                set.play(ObjectAnimator.ofFloat(this, View.TRANSLATION_X, nose[4]))
		                            .with(ObjectAnimator.ofFloat(this, 
		                                            View.TRANSLATION_Y, nose[5] - currY))
		                            .with(ObjectAnimator.ofFloat(this, 
		                                            View.SCALE_X, scaleFactor))
		                            .with(ObjectAnimator.ofFloat(this, 
		                                            View.SCALE_Y, scaleFactor));
		                set.setDuration(duration);
		                set.start();
		                mCurrentAnimator = set;
		            	
		            } else if((x > brain[0]) && (x < brain[1]) && (y > brain[2]) && (y < brain[3]) ) {
			        	// ----------------------> BRAIN <-------------------------------------------
		            	Log.d("touch", "brain");

		            	// Save state info
		            	zoomed = true;
		            	viewNo = zoomBrain;
		            	currY = loc[1];
		            	
		                // Zoom in on 3. Brain
		                AnimatorSet set = new AnimatorSet();
		                set.play(ObjectAnimator.ofFloat(this, View.TRANSLATION_X, brain[4]))
		                            .with(ObjectAnimator.ofFloat(this, 
		                                            View.TRANSLATION_Y, brain[5] - currY))
		                            .with(ObjectAnimator.ofFloat(this, 
		                                            View.SCALE_X, scaleFactor/2))
		                            .with(ObjectAnimator.ofFloat(this, 
		                                            View.SCALE_Y, scaleFactor/2));
		                set.setDuration(duration);
		                set.start();
		                mCurrentAnimator = set;
		            	
		            } else if((x > lips[0]) && (x < lips[1]) && (y > lips[2]) && (y < lips[3]) ) {
			        	// ----------------------> LIPS <-------------------------------------------
		            	Log.d("touch", "lips");

		            	// Save state info
		            	zoomed = true;
		            	viewNo = zoomLips;
		            	currY = loc[1];
		            	
		                // Zoom in on 4. Lips
		                AnimatorSet set = new AnimatorSet();
		                set.play(ObjectAnimator.ofFloat(this, View.TRANSLATION_X, lips[4]))
		                            .with(ObjectAnimator.ofFloat(this, 
		                                            View.TRANSLATION_Y, lips[5] - currY))
		                            .with(ObjectAnimator.ofFloat(this, 
		                                            View.SCALE_X, scaleFactor))
		                            .with(ObjectAnimator.ofFloat(this, 
		                                            View.SCALE_Y, scaleFactor));
		                set.setDuration(duration);
		                set.start();
		                mCurrentAnimator = set;
		            	
		            } else if((x > tongue[0]) && (x < tongue[1]) && (y > tongue[2]) && (y < tongue[3]) ) {
			        	// ----------------------> TONGUE <-------------------------------------------
		            	Log.d("touch", "tongue");

		            	// Save state info
		            	zoomed = true;
		            	viewNo = zoomTongue;
		            	currY = loc[1];
		            	
		                // Zoom in on 6. Tongue
		                AnimatorSet set = new AnimatorSet();
		                set.play(ObjectAnimator.ofFloat(this, View.TRANSLATION_X, tongue[4]))
		                            .with(ObjectAnimator.ofFloat(this, 
		                                            View.TRANSLATION_Y, tongue[5] - currY))
		                            .with(ObjectAnimator.ofFloat(this, 
		                                            View.SCALE_X, scaleFactor))
		                            .with(ObjectAnimator.ofFloat(this, 
		                                            View.SCALE_Y, scaleFactor));
		                set.setDuration(duration);
		                set.start();
		                mCurrentAnimator = set;
		            	
		            } else if((x > jaws[0]) && (x < jaws[1]) && (y > jaws[2]) && (y < jaws[3]) ) {
			        	// ----------------------> JAWS <-------------------------------------------
		            	Log.d("touch", "jaws");

		            	// Save state info
		            	zoomed = true;
		            	viewNo = zoomJaw;
		            	currY = loc[1];
		            	
		                // Zoom in on 5. Jaws
		                AnimatorSet set = new AnimatorSet();
		                set.play(ObjectAnimator.ofFloat(this, View.TRANSLATION_X, jaws[4]))
		                            .with(ObjectAnimator.ofFloat(this, 
		                                            View.TRANSLATION_Y, jaws[5] - currY))
		                            .with(ObjectAnimator.ofFloat(this, 
		                                            View.SCALE_X, scaleFactor))
		                            .with(ObjectAnimator.ofFloat(this, 
		                                            View.SCALE_Y, scaleFactor));
		                set.setDuration(duration);
		                set.start();
		                mCurrentAnimator = set;
		            	
		            } else if((x > vfs[0]) && (x < vfs[1]) && (y > vfs[2]) && (y < vfs[3]) ) {
			        	// ----------------------> VFS <-------------------------------------------
		            	Log.d("touch", "vfs");

		            	// Save state info
		            	zoomed = true;
		            	viewNo = zoomVFs;
		            	currY = loc[1];
		            	
		                // Zoom in on 7. VFs
		                AnimatorSet set = new AnimatorSet();
		                set.play(ObjectAnimator.ofFloat(this, View.TRANSLATION_X, vfs[4]))
		                            .with(ObjectAnimator.ofFloat(this, 
		                                            View.TRANSLATION_Y, vfs[5] - currY))
		                            .with(ObjectAnimator.ofFloat(this, 
		                                            View.SCALE_X, scaleFactor))
		                            .with(ObjectAnimator.ofFloat(this, 
		                                            View.SCALE_Y, scaleFactor));
		                set.setDuration(duration);
		                set.start();
		                mCurrentAnimator = set;
		            	
		            } else if((x > palate[0]) && (x < palate[1]) && (y > palate[2]) && (y < palate[3]) ) {
			        	// ----------------------> PALATE <-------------------------------------------
		            	Log.d("touch", "palate");

		            	// Save state info
		            	zoomed = true;
		            	viewNo = zoomPalate;
		            	currY = loc[1];
		            	
		                // Zoom in on 8. Palate
		                AnimatorSet set = new AnimatorSet();
		                set.play(ObjectAnimator.ofFloat(this, View.TRANSLATION_X, palate[4]))
		                            .with(ObjectAnimator.ofFloat(this, 
		                                            View.TRANSLATION_Y, palate[5] - currY))
		                            .with(ObjectAnimator.ofFloat(this, 
		                                            View.SCALE_X, scaleFactor))
		                            .with(ObjectAnimator.ofFloat(this, 
		                                            View.SCALE_Y, scaleFactor));
		                set.setDuration(duration);
		                set.start();
		                mCurrentAnimator = set;
		            	
		            } else if((x > hyoid[0]) && (x < hyoid[1]) && (y > hyoid[2]) && (y < hyoid[3]) ) {
			        	// ----------------------> HYOID <-------------------------------------------
		            	Log.d("touch", "hyoid");

		            	// Save state info
		            	zoomed = true;
		            	viewNo = zoomHyoid;
		            	currY = loc[1];
		            	
		                // Zoom in on 9. Hyoid
		                AnimatorSet set = new AnimatorSet();
		                set.play(ObjectAnimator.ofFloat(this, View.TRANSLATION_X, hyoid[4]))
		                            .with(ObjectAnimator.ofFloat(this, 
		                                            View.TRANSLATION_Y, hyoid[5] - currY))
		                            .with(ObjectAnimator.ofFloat(this, 
		                                            View.SCALE_X, scaleFactor))
		                            .with(ObjectAnimator.ofFloat(this, 
		                                            View.SCALE_Y, scaleFactor));
		                set.setDuration(duration);
		                set.start();
		                mCurrentAnimator = set;
		            	
		            } else if((x > epig[0]) && (x < epig[1]) && (y > epig[2]) && (y < epig[3]) ) {
			        	// ----------------------> EPIGLOTTIS <-------------------------------------------
		            	Log.d("touch", "epig");

		            	// Save state info
		            	zoomed = true;
		            	viewNo = zoomEpig;
		            	currY = loc[1];
		            	
		                // Zoom in on 10. Epiglottis
		                AnimatorSet set = new AnimatorSet();
		                set.play(ObjectAnimator.ofFloat(this, View.TRANSLATION_X, epig[4]))
		                            .with(ObjectAnimator.ofFloat(this, 
		                                            View.TRANSLATION_Y, epig[5] - currY))
		                            .with(ObjectAnimator.ofFloat(this, 
		                                            View.SCALE_X, scaleFactor))
		                            .with(ObjectAnimator.ofFloat(this, 
		                                            View.SCALE_Y, scaleFactor));
		                set.setDuration(duration);
		                set.start();
		                mCurrentAnimator = set;
		            	
		            } else if((x > ues[0]) && (x < ues[1]) && (y > ues[2]) && (y < ues[3]) ) {
			        	// ----------------------> UES <-------------------------------------------
		            	Log.d("touch", "ues");

		            	// Save state info
		            	zoomed = true;
		            	viewNo = zoomUES;
		            	currY = loc[1];
		            	
		                // Zoom in on 11. UES		            	
		                AnimatorSet set = new AnimatorSet();
		                set.play(ObjectAnimator.ofFloat(this, View.TRANSLATION_X, ues[4]))
		                            .with(ObjectAnimator.ofFloat(this, 
		                                            View.TRANSLATION_Y, ues[5] - currY))
		                            .with(ObjectAnimator.ofFloat(this, 
		                                            View.SCALE_X, scaleFactor))
		                            .with(ObjectAnimator.ofFloat(this, 
		                                            View.SCALE_Y, scaleFactor));
		                set.setDuration(duration);
		                set.start();
		                mCurrentAnimator = set;
		            	
		            }
		        	
		        	// If the tap opened up a zoomed view then add the corresponding text
		        	if (viewNo != 0)
		        		addText(viewNo);
		        	
	        	} else {
					// reverse zoom back out to previous position
	                AnimatorSet set = new AnimatorSet();
	                set.play(ObjectAnimator.ofFloat(this, View.X, 0))
	                            .with(ObjectAnimator.ofFloat(this, 
	                                            View.Y, 0))
	                            .with(ObjectAnimator.ofFloat(this, 
	                                            View.SCALE_X, 1))
	                            .with(ObjectAnimator.ofFloat(this, 
	                                            View.SCALE_Y, 1));
	                set.setDuration(duration);
	                set.start();
	                mCurrentAnimator = set;
	        		zoomed = false;
	        		if (currInfo != null) {
	        			currInfo.setVisibility(View.INVISIBLE);
	        		}
	        	}
			}
	    }

		return true;
	}

	private void addText(int viewNo) {
		// add the informational text to the zoomed in view
    	if (parentActivity != null) {
    		try {
				currInfo = parentActivity.tvEye;
				switch (viewNo) {
				case zoomEyes:
					currInfo.setText("EYES: see the bolus. \n" +
							"<Cranial Nerve> 2");
					currInfo.setVisibility(View.VISIBLE);
					break;
				case zoomNose:
					currInfo.setText("NOSE: smell the bolus. \n" +
							"<Cranial Nerve> 1");
					currInfo.setVisibility(View.VISIBLE);
					break;
				case zoomBrain:
					currInfo.setText("BRAIN: (association cortex, insular cortex, limbic system, hippocampus, frontal lobe)\n" +
							"BRAINSTEM: (Nucleus tractus solitaries and nucleus ambiguous)");
					currInfo.setVisibility(View.VISIBLE);
					break;
				case zoomLips:
					currInfo.setText("LIPS: Open for bolus entry and close to seal it in.\n" +
							"<Muscle> Orbicularis oris\n" +
							"<Cranial Nerve> 7");
					currInfo.setVisibility(View.VISIBLE);
					break;
				case zoomJaw:
					currInfo.setText("JAW: Opens for bolus entry and ‘chews’ it. \n" +
							"<Muscle> \n" +
							"Primary masticatory closers: masseter, temporalis and lateral pterygoid." +
							"Jaw openers: external pterygoid, anterior belly of digastric, geniohyoid and mylohyoid.\n" +
							"<Cranial Nerve> 5 and ansa cervicalis.");
					currInfo.setVisibility(View.VISIBLE);
					break;
				case zoomTongue:
					currInfo.setText("TONGUE: Accepts, contains, moves and transfers the bolus.\n" +
							"<Muscle> Verticalis, transversius, hyoglossus and genioglossus.\n" +
							"<Cranial Nerve> 12");
					currInfo.setVisibility(View.VISIBLE);
					break;
				case zoomVFs:
					currInfo.setText("VOCAL FOLDS: Adduction to protect the airway.\n" +
							"<Muscle> Interarytenoids and lateral cricoarytenoid.\n" +
							"<Cranial Nerve> 10 and the recurrent laryngeal nerve.");
					currInfo.setVisibility(View.VISIBLE);
					break;
				case zoomPalate:
					currInfo.setText("SOFT PALATE: Velopharyngeal closure to block off the nasal cavity.\n" +
							"<Muscle> Levator veli palatini.	\n" +
							"<Cranial Nerve> Pharyngeal plexus (CNs 9 and 10).");
					currInfo.setVisibility(View.VISIBLE);
					break;
				case zoomHyoid:
					currInfo.setText("HYOID BONE: Moves up and forward.\n" +
							"<Muscle>\n" +
							"Up: stylohyoid and posterior belly of the digastric.\n" +
							"Forward: geniohyoid, mylohyoid and the anterior belly of the digastric.\n" +
							"<Cranial Nerve>\n" +
							"Up: CN7.   Forward: CN5 (and ansa cervicalis).");
					currInfo.setVisibility(View.VISIBLE);
					break;
				case zoomEpig:
					currInfo.setText("EPIGLOTTIS: Deflects (folds) to protect the airway. \n" +
							"This is due to: " +
							" - Hyolaryngeal excursion " +
							" - neuromuscular closure" +
							" - the architecture of the epiglottis " +
							" - the superior pressure of the base of tongue.");
					currInfo.setVisibility(View.VISIBLE);
					break;
				case zoomUES:
					currInfo.setText("UPPER ESOPHAGEAL SPHINCTER: \n" +
							"This relaxes and opens to allow the bolus to pass to the stomach.\n" +
							"<Muscle> Cricopharyngeus\n" +
							"<Cranial Nerve> 10");
					currInfo.setVisibility(View.VISIBLE);
					break;
				default:
					currInfo.setVisibility(View.INVISIBLE);
					break;
				}
			} catch (Exception e) {
				Log.d("touch", e.toString());
				e.printStackTrace();
			}
			
		}
	}
}