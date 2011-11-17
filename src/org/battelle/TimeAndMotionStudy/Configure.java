package org.battelle.TimeAndMotionStudy;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Configure extends Activity {
	Spinner spTests;
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
		setContentView(R.layout.configure);
		
		spTests = (Spinner) findViewById(R.id.spTests);
		btnAddTest = (Button) findViewById(R.id.btnAddTest);
		btnDeleteTest = (Button)  findViewById(R.id.btnDeleteTest);
		editTask1 = (EditText)  findViewById(R.id.editTask1);
		editTask2 = (EditText)  findViewById(R.id.editTask2);
		editTask3 = (EditText)  findViewById(R.id.editTask3);
		editTask4 = (EditText)  findViewById(R.id.editTask4);
		editTask5 = (EditText)  findViewById(R.id.editTask5);
		editTask6 = (EditText)  findViewById(R.id.editTask6);
		editTask7 = (EditText)  findViewById(R.id.editTask7);
		editTask8 = (EditText)  findViewById(R.id.editTask8);
		editTask9 = (EditText)  findViewById(R.id.editTask9);
		editTask10 = (EditText)  findViewById(R.id.editTask10);
		
		//Restore preferences
		SharedPreferences settings = getPreferences(0);
		LastTest = settings.getString("LastTest","false");
		setContentView(R.layout.configure);


		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.planets_array,android.R.layout.simple_spinner_item);
		
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		spTests.setAdapter(adapter);

		spTests.setOnItemSelectedListener(new MyOnItemSelectedListener());
	}
	
	public class MyOnItemSelectedListener implements OnItemSelectedListener {
	    public void onItemSelected(AdapterView<?> parent,View view, int pos, long id) {
	      Toast.makeText(parent.getContext(), "The planet is " + parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
	    }

	    public void onNothingSelected(AdapterView parent) {
	      // Do nothing.
	    }
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
