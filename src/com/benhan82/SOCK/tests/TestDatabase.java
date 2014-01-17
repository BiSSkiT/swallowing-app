package com.benhan82.SOCK.tests;

import android.util.Log;

import com.benhan82.SOCK.database.Patient;

/**
 * @author Ben14
 * Class to test the various pieces of the application.
 *
 */
public class TestDatabase {
	public TestDatabase() {
		
	}
	
	/**
	 * class testPatient
	 * test the Patient class methods
	 */
	public static void testPatient() {
		Patient pat = new Patient("firstNAME","lastNAME");
		String s = pat.toString();
		Log.d("test", s);
		
		
		for (int i = 0; i < 10000000; i+=1000) {
			pat.setId(i);
			s = "Testing setId() and getId()\n" +
					"Set to:" + i + ". Retrived value:" + 
					Integer.toString(pat.getId()) + "\n";
			Log.d("test", s);
		}
		
		
	}

}
