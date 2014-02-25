package com.benhan82.SOCK.tests;

import java.util.List;

import android.content.Context;
import android.util.Log;

import com.benhan82.SOCK.MyApp;
import com.benhan82.SOCK.database.Patient;
import com.benhan82.SOCK.database.PatientDatabaseHelper;

/**
 * @author Ben14
 * Class to test the various pieces of the application.
 *
 */
public class TestDatabase {
	
	
	public TestDatabase() {}
	
	/**
	 * class testPatient
	 * test the Patient class methods
	 */
	public void testPatient() {
		// contructor
		Patient pat = new Patient("firstNAME","lastNAME");
		
		// toString
		String s = pat.toString();
		Log.d("test", s);
		
		// getters and setters
		// id
		for (int i = 0; i < 10000000; i+=1000) {
			pat.setId(i);
			s = "Testing setId() and getId()\n" +
					"Set to:" + i + ". Retrived value:" + 
					Integer.toString(pat.getId()) + "\n";
			Log.d("test", s);
		}
		
		// notes
		String note = "This is a test note";
		pat.setNotes(note);
		s = "Testing setNotes() and getNotes()\n" +
				"Set to:" + note + ". Retrived value:" + 
				pat.getNotes() + "\n";
		Log.d("test", s);
		
		// firstName
		String name1 = "Hillary";
		pat.setFirstName(name1);
		s = "Testing setFirstName() and getFirstName()\n" +
				"Set to:" + name1 + ". Retrived value:" + 
				pat.getFirstName() + "\n";
		Log.d("test", s);
		
		// lastName
		String name2 = "Clinton";
		pat.setFirstName(name2);
		s = "Testing setLastName() and getLastName()\n" +
				"Set to:" + name2 + ". Retrived value:" + 
				pat.getLastName() + "\n";
		Log.d("test", s);
		
		// checkboxes
		boolean bArray1[] = { true, false, true, false, true, true, false, false, true, false };
		pat.setCheckBoxes(bArray1);
		boolean bArray2[] = pat.getCheckBoxes();
		
		String s1, s2;
		s1 = s2 = "";
		for (int i = 0; i < bArray1.length; i++)
			s1 += (bArray1[i] + ", ");		
		for (int j = 0; j < bArray2.length; j++)
			s2 += (bArray2[j] + ", ");
			
		s = "Testing setCheckBoxes() and getCheckBoxes()\n" +
				"Set to:" + s1 + ". Retrived value:" + 
				s2 + "\n";
		Log.d("test", s);
		
	}

	/**
	 * class testPatientDatabaseHelper
	 * test all methods in class
	 */
	public void testPatientDatabaseHelper(Context context) {
		PatientDatabaseHelper db = new PatientDatabaseHelper(context);

		// Make some Patient objects
		Patient patients[] = new Patient[5];
		patients[0] = new Patient("1. Jessie", "Blake");
		patients[1] = new Patient("2. Edward", "Charmers");
		patients[2] = new Patient("3. Neil", "Diamond");
		patients[3] = new Patient("4. Natalie", "Harbott");
		patients[4] = new Patient("5. Ella", "Keena");
//				
//		// Clear out any old entries in the database table
//		db.clearTable();
//		
//		// Add our example patients
//		for (int i = 0; i < patients.length; i++)
//			db.addPatient(patients[i]);
//		
//		// Get individual patients. Note: Indexing starts at 1 for database
//		for (int i = 1; i <= patients.length; i++)
//			db.getPatient(i);
//		
//		// Get all patients
//		List<Patient> list = db.getAllPatients();
//		
//		// Update some entries
//		Patient p1 = list.get(0);
//		Patient p2 = db.getPatient(p1.getId());
//		p2.setFirstName("Bob");
//		p2.setLastName("Parker");
//		p2.setNotes("Cheers to the old mayor");
//		db.updatePatient(p2);
//		p2 = db.getPatient(p1.getId());
//		
//		// set the current patient reference to point to the new pTemp (1st row)
//		MyApp.setPatient(p1);
//		Patient p3 = MyApp.getPatient();
//		
//		Log.d("patient","p1: " + p1.toString() + "\np2: " + p2.toString() + "\np3: " + p3.toString());
//		
//		db.deletePatient(p1.getId());
//		
//		// check the patient has been deleted
//		db.getAllPatients();
		
	}
	

}
