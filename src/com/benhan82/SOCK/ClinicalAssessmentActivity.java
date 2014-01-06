package com.benhan82.SOCK;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.benhan82.SOCK.fragments.ClinLangmoreFragment;
import com.benhan82.SOCK.fragments.ClinObservationsFragment;
import com.benhan82.SOCK.fragments.ClinOralTrialsFragment;
import com.benhan82.SOCK.fragments.ClinTomassFragment;
import com.benhan82.SOCK.fragments.ClinWaterSwallowFragment;
import com.benhan82.SOCK.fragments.Cn05bFragment;
import com.benhan82.SOCK.fragments.Cn05cFragment;
import com.benhan82.SOCK.fragments.Cn07aFragment;
import com.benhan82.SOCK.fragments.CnParentFragment;

public class ClinicalAssessmentActivity extends FragmentActivity implements
		ActionBar.TabListener {

	private SectionsPagerAdapter mSectionsPagerAdapter;
	private ViewPager mViewPager;		//subclass of ViewGroup, holds fragments
	private CnParentFragment cnParentFragment;
	
    // Tab titles
    private String[] tabs = { "Observations", "Langmore", "CN exam", "Water Swallow", "TOMASS", "Oral Trials" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_clinical_assessment);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		// Show the Up button in the action bar.
		actionBar.setDisplayHomeAsUpEnabled(true);

		// Create the adapter that will return a fragment for each section of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
        for (String tab_name : tabs) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
            actionBar.addTab(actionBar.newTab()
            		.setText(tab_name)
                    .setTabListener(this));
        }
        
        actionBar.setTitle("Clinical Assessment");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.clinical_assessment, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	@Override
	public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
		
	}
	
	
	
	
	
	/***********************************************
	 * onClick event handler methods
	 ***********************************************/
	public void cn05WNL(View v) {
		// Skip to slide 11 (CN7)
		FragmentTransaction ft = cnParentFragment.getChildFragmentManager().beginTransaction();
		Fragment f = new Cn07aFragment();
		ft.replace(R.id.cnFragmentContainer, f);
		ft.addToBackStack(null);
		ft.commit();
	}

	public void cn05Impaired(View v) {
		// Go to slide "Impaired CN V"
		FragmentTransaction ft = cnParentFragment.getChildFragmentManager().beginTransaction();
		Fragment f = new Cn05bFragment();
		ft.replace(R.id.cnFragmentContainer, f);
		ft.addToBackStack(null);
		ft.commit();
	}
	
	public void nextPage(View v) {
		FragmentTransaction ft = cnParentFragment.getChildFragmentManager().beginTransaction();
		Fragment f = new Cn05cFragment();
		ft.replace(R.id.cnFragmentContainer, f);
		ft.addToBackStack(null);
		ft.commit();
	}
	
	
	
	
	
	

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public android.support.v4.app.Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
	        switch (position) {
		        case 0:
		            return new ClinObservationsFragment();
		        case 1:
		            return new ClinLangmoreFragment();
		        case 2:
		        	cnParentFragment = new CnParentFragment();
		        	return cnParentFragment;
		        case 3:
		        	return new ClinWaterSwallowFragment();
		        case 4:
		        	return new ClinTomassFragment();
		        case 5:
		        	return new ClinOralTrialsFragment();
	        }
	 
	        return null;
		}

		@Override
		public int getCount() {
			// return total number of pages.
			return 6;
		}
	}
	
}
