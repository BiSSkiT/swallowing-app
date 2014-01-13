package com.benhan82.SOCK.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * @author Ben
 * 
 * PatientTable.java 
 * This class describes the main patient table that stores the data in the database.
 * This includes column declarations and the create table command for initialising 
 * the table.
 *
 */
public class PatientTable {

	// Database table
	public static final String TABLE_PATIENTS = "patients";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_SUMMARY = "SUMMARY";
	public static final String COLUMN_CB1 = "CB1";
	public static final String COLUMN_CB2 = "CB2";
	public static final String COLUMN_CB3 = "CB3";
	
	// Database creation SQL statement
	private static final String DATABASE_CREATE = "create table " 
			+ TABLE_PATIENTS
			+ "(" 
			+ COLUMN_ID + " integer primary key autoincrement," 
			+ COLUMN_SUMMARY + " text not null,"
			+ COLUMN_CB1 + " integer not null," 
			+ COLUMN_CB2 + " integer not null,"
			+ COLUMN_CB3 + " integer not null,"
			+ ");";
	
	public static void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
	}
	
	public static void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		Log.w(PatientTable.class.getName(), "Upgrading database from version "
				+ oldVersion + " to " + newVersion
				+ ", which will destroy all old data");
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENTS);
		onCreate(database);
	}
}
