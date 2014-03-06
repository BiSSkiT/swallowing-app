package com.benhan82.SOCK.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * @author Adapted by Ben Han. Original code from 
 * 
 * http://www.vogella.com/tutorials/AndroidSQLite/article.html
 * Android SQLite database and content provider - Tutorial - 
 * by Lars Vogel
 *
 */
public class PatientTable {
	
	// Database table
	public static final String TABLE_PATIENTS = "patients";
	public static final String COLUMN_ID = "_id";				//0
	public static final String COLUMN_NOTES = "notes";			//1
	public static final String COLUMN_FIRSTNAME = "first_name";	//2
	public static final String COLUMN_LASTNAME = "last_name";	//3
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
	
	public static final int ID_INDEX = 0;
	public static final int NOTES_INDEX = 1;
	public static final int FIRST_INDEX = 2;
	public static final int LAST_INDEX = 3;
	
	public static final String[] COLUMNS = {
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
	
	public static void onCreate(SQLiteDatabase database) {
		// Create database when this class is instantiated in the MyApp class
		database.execSQL(DATABASE_CREATE);
	}
	
	public static void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		Log.w("SQL", "Upgrading database from version "
				+ oldVersion + " to " + newVersion
				+ ", which will destroy all old data");
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENTS);
		onCreate(database);
	}
}
