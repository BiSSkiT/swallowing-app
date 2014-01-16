package com.benhan82.SOCK.fragments;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.benhan82.SOCK.ClinicalAssessmentActivity;
import com.benhan82.SOCK.R;

public class ClinAsse01ObseFragment extends Fragment {
	
	public ClinAsse01ObseFragment() {
		
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_clinical01_observations, container, false);
		return rootView;
	}

	@Override
	public void onPause() {
		super.onPause();
//		saveState();
	}
	
//	private void saveState() {
//		boolean checked[] = new boolean[11];
//		
//        for (int i = 0; i < numCheckBoxes; i++) {
//        	checked[i] = mCheckBoxes[i].isChecked();
//        }
//
//		ContentValues values = new ContentValues();
//		values.put(PatientTable.COLUMN_CB1, checked[0]);
//		values.put(PatientTable.COLUMN_CB2, checked[1]);
//		values.put(PatientTable.COLUMN_CB3, checked[2]);
//		
//		ClinicalAssessmentActivity parent =  (ClinicalAssessmentActivity) getActivity(); 
//		Uri patientUri = parent.getPatientUri();
//		
//		
//		if (patientUri == null) {
//			// A new entry is made to the patient
//			patientUri = getContentResolver().insert(PatientContentProvider.CONTENT_URI, values);
//		} else {
//			// 	Update todo
//			getContentResolver().update(todoUri, values, null, null);
//		}
//	}
}
