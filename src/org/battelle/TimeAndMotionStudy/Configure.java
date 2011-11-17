package org.battelle.TimeAndMotionStudy;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class Configure extends Activity {
	ListView lstTests;
	Button btnAddTest;
	Button btnDeleteTest;
	EditText editTask1;
	EditText editTask2;
	EditText editTask3;
	EditText editTask4;
	EditText editTask5;
	EditText editTask6;
	EditText editTask7;
	EditText editTask8;
	EditText editTask9;
	EditText editTask10;
	
	public static final String prefs_name = "MyPrefsFile";
	public String LastTest;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Restore preferences
		SharedPreferences settings = getPreferences(0);
		LastTest = settings.getString("LastTest","false");
		setContentView(R.layout.configure);
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		//We need an editor object to make preference changes
		//All objects are from android.context.Context
		SharedPreferences settings = getPreferences(0);
		SharedPreferences.Editor editor = settings.edit();
		
		//Need to set LastTest = current test name
		
		editor.putString("LastTest", LastTest);
		//Commit the changes!
		editor.commit();
	}
	
}
