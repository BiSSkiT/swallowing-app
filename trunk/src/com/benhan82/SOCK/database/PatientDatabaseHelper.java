package com.benhan82.SOCK.database;

import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

	
public class PatientDatabaseHelper extends SQLiteOpenHelper {

	public static PatientDatabaseHelper db;
	private static final String DATABASE_NAME = "patients.db";
	private static final int DATABASE_VERSION = 1;
	
	// Database table
	public static final String TABLE_PATIENTS = "patients";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_SUMMARY = "SUMMARY";
	public static final String COLUMN_FIRSTNAME = "FIRSTNAME";
	public static final String COLUMN_LASTNAME = "LASTNAME";
	public static final String COLUMN_CB1 = "CB1";
	public static final String COLUMN_CB2 = "CB2";
	public static final String COLUMN_CB3 = "CB3";
	public static final String COLUMN_CB4 = "CB4";
	public static final String COLUMN_CB5 = "CB5";
	public static final String COLUMN_CB6 = "CB6";
	public static final String COLUMN_CB7 = "CB7";
	public static final String COLUMN_CB8 = "CB8";
	public static final String COLUMN_CB9 = "CB9";
	public static final String COLUMN_CB10 = "CB10";
	
	private static final String[] COLUMNS = {COLUMN_CB1,COLUMN_CB2,COLUMN_CB3,
		COLUMN_CB4,COLUMN_CB5,COLUMN_CB6,COLUMN_CB7,COLUMN_CB8,COLUMN_CB9,COLUMN_CB1};
	
	private static final int SIZE_OF_CB_ARRAY = 10;
	
	// Database creation SQL statement
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_PATIENTS
			+ "("
			+ COLUMN_ID + " integer primary key autoincrement,"
			+ COLUMN_SUMMARY + " text,"
			+ COLUMN_FIRSTNAME + " text,"
			+ COLUMN_LASTNAME + " text,"
			+ COLUMN_CB1 + " integer,"
			+ COLUMN_CB2 + " integer,"
			+ COLUMN_CB3 + " integer,"			
			+ COLUMN_CB4 + " integer,"
			+ COLUMN_CB5 + " integer,"
			+ COLUMN_CB6 + " integer,"
			+ COLUMN_CB7 + " integer,"
			+ COLUMN_CB8 + " integer,"
			+ COLUMN_CB9 + " integer,"
			+ COLUMN_CB10 + " integer"
			+ ");";
	
	public PatientDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	public void onCreate(SQLiteDatabase database) {
		// Create database when this class is instantiated in the MyApp class
		database.execSQL(DATABASE_CREATE);
	}
	
	public void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		Log.w("SQL", "Upgrading database from version "
				+ oldVersion + " to " + newVersion
				+ ", which will destroy all old data");
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENTS);
		onCreate(database);
	}
	
	
	// CRUD operations
	
	
	public void addPatient(Patient patient) {

		// log transaction
		Log.d("addPatient", patient.toString());
		
		// get reference to database
    	SQLiteDatabase db = getWritableDatabase();
		
    	// create ContentValues to add key "column"/value
    	ContentValues values = new ContentValues();
    	values.put(COLUMN_SUMMARY, patient.getSummary());
    	values.put(COLUMN_FIRSTNAME, patient.getFirstName());
    	values.put(COLUMN_LASTNAME, patient.getLastName());
    	// get a reference to the boolean array
    	boolean checkBoxes[] = patient.getCheckBoxes();
    	// if the checkBoxes[i] is true, then save 1 to database, or 0 for false
    	values.put(COLUMN_CB1, checkBoxes[0] ? 1 : 0);
    	values.put(COLUMN_CB2, checkBoxes[1] ? 1 : 0);
    	values.put(COLUMN_CB3, checkBoxes[2] ? 1 : 0);
    	values.put(COLUMN_CB4, checkBoxes[3] ? 1 : 0);
    	values.put(COLUMN_CB5, checkBoxes[4] ? 1 : 0);
    	values.put(COLUMN_CB6, checkBoxes[5] ? 1 : 0);
    	values.put(COLUMN_CB7, checkBoxes[6] ? 1 : 0);
    	values.put(COLUMN_CB8, checkBoxes[7] ? 1 : 0);
    	values.put(COLUMN_CB9, checkBoxes[8] ? 1 : 0);
    	values.put(COLUMN_CB10, checkBoxes[9] ? 1 : 0);
    	
    	// insert contentValues into the database
    	db.insert(TABLE_PATIENTS, null, values);
    	
    	// close database
    	db.close(); 
	}
	
	
	public Patient getPatient(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        // build query
        Cursor cursor = 
                db.query(TABLE_PATIENTS, // a. table
                COLUMNS, // b. column names
                " id = ?", // c. selections 
                new String[] { String.valueOf(id) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
     
        // if we got results get the first one
        if (cursor != null)
            cursor.moveToFirst();
        
        // build a new patient object
        Patient p = new Patient();
        p.setId(cursor.getInt(0));
        p.setSummary(cursor.getString(1));
        p.setFirstName(cursor.getString(2));
        p.setLastName(cursor.getString(3));
        // recreate the check box array by iterating through the columns
        // if cursor.getInt(i) returns non-zero, then set the corresponding array 
        // element to true, otherwise false.
        boolean checkBoxes[] = new boolean[SIZE_OF_CB_ARRAY];
        for (int i=0; i<SIZE_OF_CB_ARRAY; i++) {
        	checkBoxes[i] = cursor.getInt(i)!=0 ? true : false;
        }
        p.setCheckBoxes(checkBoxes);
        
        //log 
        Log.d("getPatient("+id+")", p.toString());
        
        return p;
	}
	
	public List<Patient> getAllPatients(Patient patient) {
		List<Patient> patients = new LinkedList<Patient>();
		  
        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_PATIENTS;
  
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
  
        // 3. go over each row, build patient and add it to list
        Patient p = null;
        if (cursor.moveToFirst()) {
            do {
            	p = new Patient();
                p.setId(cursor.getInt(0));
                p.setSummary(cursor.getString(1));
                p.setFirstName(cursor.getString(2));
                p.setLastName(cursor.getString(3));
                // recreate the check box array by iterating through the columns
                // if cursor.getInt(i) returns non-zero, then set the corresponding array 
                // element to true, otherwise false.
                boolean checkBoxes[] = new boolean[SIZE_OF_CB_ARRAY];
                for (int i=0; i<SIZE_OF_CB_ARRAY; i++) {
                	checkBoxes[i] = cursor.getInt(i)!=0 ? true : false;
                }
                p.setCheckBoxes(checkBoxes);
  
                // Add p to patients
                patients.add(p);
            } while (cursor.moveToNext());
        }

        Log.d("getAllPatients", patients.toString());
  
        return patients;
	}
	
	public int updatePatient(Patient patient) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
     
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
    	values.put(COLUMN_SUMMARY, patient.getSummary());
    	values.put(COLUMN_FIRSTNAME, patient.getFirstName());
    	values.put(COLUMN_LASTNAME, patient.getLastName());
    	// create boolean array
    	boolean checkBoxes[] = patient.getCheckBoxes();
    	// if the checkBoxes[i] is true, then save 1 to database, or 0 for false
    	values.put(COLUMN_CB1, checkBoxes[0] ? 1 : 0);
    	values.put(COLUMN_CB2, checkBoxes[1] ? 1 : 0);
    	values.put(COLUMN_CB3, checkBoxes[2] ? 1 : 0);
    	values.put(COLUMN_CB4, checkBoxes[3] ? 1 : 0);
    	values.put(COLUMN_CB5, checkBoxes[4] ? 1 : 0);
    	values.put(COLUMN_CB6, checkBoxes[5] ? 1 : 0);
    	values.put(COLUMN_CB7, checkBoxes[6] ? 1 : 0);
    	values.put(COLUMN_CB8, checkBoxes[7] ? 1 : 0);
    	values.put(COLUMN_CB9, checkBoxes[8] ? 1 : 0);
    	values.put(COLUMN_CB10, checkBoxes[9] ? 1 : 0);
     
        // 3. updating row
        int i = db.update(TABLE_PATIENTS, //table
                values, // column/value
                COLUMN_ID + " = ?", // selections
                new String[] { String.valueOf(patient.getId()) }); //selection args
     
        // 4. close
        db.close();
        
        Log.d("updatePatient", patient.toString());
        
        return i;
	}
	
	public void deletePatient(Patient patient) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
 
        // 2. delete
        db.delete(TABLE_PATIENTS, //table name
        		COLUMN_ID + " = ?",  // selections
                new String[] { String.valueOf(patient.getId()) }); //selections args
 
        // 3. close
        db.close();
 
        //log
        Log.d("deletePatient", patient.toString());
		
	}
	
	
}
