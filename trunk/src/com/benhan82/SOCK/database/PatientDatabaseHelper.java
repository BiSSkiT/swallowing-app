package com.benhan82.SOCK.database;

import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

	
public class PatientDatabaseHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "patients.db";
	private static final int DATABASE_VERSION = 1;
	
	// Database table
	public static final String TABLE_PATIENTS = "patients";
	public static final String COLUMN_ID = "ID";				//0
	public static final String COLUMN_NOTES = "NOTES";			//1
	public static final String COLUMN_FIRSTNAME = "FIRSTNAME";	//2
	public static final String COLUMN_LASTNAME = "LASTNAME";	//3
	public static final String COLUMN_CB1 = "CB1";				//4
	public static final String COLUMN_CB2 = "CB2";
	public static final String COLUMN_CB3 = "CB3";
	public static final String COLUMN_CB4 = "CB4";
	public static final String COLUMN_CB5 = "CB5";
	public static final String COLUMN_CB6 = "CB6";
	public static final String COLUMN_CB7 = "CB7";
	public static final String COLUMN_CB8 = "CB8";
	public static final String COLUMN_CB9 = "CB9";
	public static final String COLUMN_CB10 = "CB10";
	
	private static final String[] COLUMNS = {
		COLUMN_ID,			// column index=0
		COLUMN_NOTES,		//1
		COLUMN_FIRSTNAME,	//2
		COLUMN_LASTNAME,	//3
		COLUMN_CB1,			//4
		COLUMN_CB2,			//5
		COLUMN_CB3,			//6
		COLUMN_CB4,			//7
		COLUMN_CB5,			//8
		COLUMN_CB6,			//9
		COLUMN_CB7,			//10
		COLUMN_CB8,			//11
		COLUMN_CB9,			//12
		COLUMN_CB10 };		//13
	
	private static final int SIZE_OF_CB_ARRAY = 10;
	private static final int CB_OFFSET = 4;
	
	// Database creation SQL statement
	private static final String DATABASE_CREATE = "create table "
			+ TABLE_PATIENTS
			+ "("
			+ COLUMN_ID + " integer primary key autoincrement,"
			+ COLUMN_NOTES + " text,"
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
		Log.d("patient", "addPatient," + patient.toString());
		
		// get reference to database
    	SQLiteDatabase db = getWritableDatabase();
		
    	// create ContentValues to add key "column"/value
    	ContentValues values = new ContentValues();
    	values.put(COLUMN_NOTES, patient.getNotes());
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

        // SQLite command string
        String query = "SELECT * FROM "+TABLE_PATIENTS+" WHERE "+COLUMN_ID+" = "+id;
        Log.d("patient", "The query command is: "+query);
        
        // query database
        Cursor cursor = db.rawQuery(query, null);		// THIS QUERY IS RETURNING A ZERO SIZE TABLE
        
        // if we got results get the first one
        if (cursor != null) {
            cursor.moveToFirst();
            int i = cursor.getCount();
            Log.d("patient", Integer.toString(i) );
        }
        
        // build a new patient object
        Patient p = new Patient();
        
        try {	
        	int idIndex, notesIndex, firstIndex, lastIndex;
        	idIndex = notesIndex = firstIndex = lastIndex = 0;
        	String[] data = cursor.getColumnNames();
			
			for (int i=0; i<data.length; i++) {
				Log.d("patient", data[i]);
			}
			
			try {
				idIndex = cursor.getColumnIndexOrThrow(COLUMN_ID);
				notesIndex = cursor.getColumnIndexOrThrow(COLUMN_NOTES);
				firstIndex = cursor.getColumnIndexOrThrow(COLUMN_FIRSTNAME);
				lastIndex = cursor.getColumnIndexOrThrow(COLUMN_LASTNAME);
			} catch (Exception e) {
				Log.d("exception", e.getMessage());
				e.printStackTrace();
			}
        	
			p.setId(cursor.getInt(idIndex));		// BUG HERE, FIX IT!!!!!!!
	        p.setNotes(cursor.getString(notesIndex));
	        p.setFirstName(cursor.getString(firstIndex));
	        p.setLastName(cursor.getString(lastIndex));
		} catch (Exception e) {
			Log.d("exception", e.getMessage());
			e.printStackTrace();
		}
        // recreate the check box array by iterating through the columns
        // if cursor.getInt(i) returns non-zero, then set the corresponding array 
        // element to true, otherwise false.
        boolean checkBoxes[] = new boolean[SIZE_OF_CB_ARRAY];
        for (int i=0; i<SIZE_OF_CB_ARRAY; i++) {
        	
        	try {
				checkBoxes[i] = cursor.getInt(i)!=0 ? true : false;
			} catch (Exception e) {
				Log.d("exception", e.getMessage());
				e.printStackTrace();
			}
        }
        p.setCheckBoxes(checkBoxes);
        
        //log 
        Log.d("patient", "getPatient("+id+")" + p.toString());
        
        return p;
	}
	
	public List<Patient> getAllPatients() {
		List<Patient> patients = new LinkedList<Patient>();
		  
        // 1. build the query
        String query = "SELECT * FROM " + TABLE_PATIENTS;
  
        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
  
        // 3. go over each row, build patient and add it to list
        Patient p = null;
        if (cursor.moveToFirst()) {
            do {
            	p = new Patient();
                p.setId(cursor.getInt(0));
                p.setNotes(cursor.getString(1));
                p.setFirstName(cursor.getString(2));
                p.setLastName(cursor.getString(3));
                // recreate the check box array by iterating through the columns
                // if cursor.getInt(i) returns non-zero, then set the corresponding array 
                // element to true, otherwise false.
                boolean checkBoxes[] = new boolean[SIZE_OF_CB_ARRAY];
                for (int i=0; i<SIZE_OF_CB_ARRAY; i++) {
                	checkBoxes[i] = cursor.getInt(i + CB_OFFSET)!=0 ? true : false;
                }
                p.setCheckBoxes(checkBoxes);
  
                // Add p to patients
                patients.add(p);
            } while (cursor.moveToNext());
        }

        Log.d("patient", "getAllPatients: " + patients.toString());
  
        return patients;
	}
	
	public int updatePatient(Patient patient) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
     
        // 2. create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
    	values.put(COLUMN_NOTES, patient.getNotes());
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
        
        Log.d("updatePatient", "updatePatient" + patient.toString());
        
        return i;
	}
	
	public void deletePatient(int id) {

        // 1. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
 
        // 2. delete
        Log.d("patient", "deletePatient" + getPatient(id).toString());
        
        db.delete(TABLE_PATIENTS, //table name
        		COLUMN_ID + " = ?",  // selections
                new String[] { String.valueOf(id) }); //selections args
 
        // 3. close
        db.close();
 
	}
	
	public void clearTable() {
		try {
			SQLiteDatabase db = this.getWritableDatabase();
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENTS);
			onCreate(db);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Log.d("exception", e.getMessage());
			e.printStackTrace();
		}
	}
		
}
