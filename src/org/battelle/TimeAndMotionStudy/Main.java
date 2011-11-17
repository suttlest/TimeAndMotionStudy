package org.battelle.TimeAndMotionStudy;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.ImageButton;
import android.widget.Chronometer;
import android.widget.Toast;

public class Main extends Activity {
	Chronometer mChronometer;
	Button btnStartStop;
	Button btnReset;
	Button btnTask1;
	Button btnTask2;
	Button btnTask3;
	Button btnTask4;
	Button btnTask5;
	Button btnTask6;
	Button btnTask7;
	Button btnTask8;
	Button btnTask9;
	Button btnTask10;
	ImageButton btnPicture;
	ImageButton btnNote;

	String lastTest;
	boolean chronoRunFlag = false;
	long elapsedTime = 0;
	long lastStart;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		//"super.onCreate" calls the parent's onCreate
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		//Get preferences
		SharedPreferences settings = getPreferences(0);
		lastTest = settings.getString("LastTest","false");
		
		mChronometer = (Chronometer) findViewById(R.id.chronometer1);
		//mChronometer.setFormat("HH:MM:SS");

		//(Button) is a cast. findViewById returns a view object, the (Button) CASTS it as a button object
		btnStartStop = (Button) findViewById(R.id.btnStartStop);
		btnReset  = (Button) findViewById(R.id.btnReset);
		btnTask1 = (Button) findViewById(R.id.btnTask1);
		btnTask2 = (Button) findViewById(R.id.btnTask2);
		btnTask3 = (Button) findViewById(R.id.btnTask3);
		btnTask4 = (Button) findViewById(R.id.btnTask4);
		btnTask5 = (Button) findViewById(R.id.btnTask5);
		btnTask6 = (Button) findViewById(R.id.btnTask6);
		btnTask7 = (Button) findViewById(R.id.btnTask7);
		btnTask8 = (Button) findViewById(R.id.btnTask8);
		btnTask9 = (Button) findViewById(R.id.btnTask9);
		btnTask10 = (Button) findViewById(R.id.btnTask10);
		btnPicture = (ImageButton) findViewById(R.id.btnPicture);
		btnNote = (ImageButton) findViewById(R.id.btnNote);

		//moved the function lower in the file
		btnStartStop.setOnClickListener(btnStartStopOnClickListener());  	
		btnReset.setOnClickListener(btnResetOnClickListener());
		
		btnTask1.setOnClickListener(btnTaskOnClickListener());
		btnTask2.setOnClickListener(btnTaskOnClickListener());
		btnTask3.setOnClickListener(btnTaskOnClickListener());
		btnTask4.setOnClickListener(btnTaskOnClickListener());
		btnTask5.setOnClickListener(btnTaskOnClickListener());
		btnTask6.setOnClickListener(btnTaskOnClickListener());
		btnTask7.setOnClickListener(btnTaskOnClickListener());
		btnTask8.setOnClickListener(btnTaskOnClickListener());
		btnTask9.setOnClickListener(btnTaskOnClickListener());
		btnTask10.setOnClickListener(btnTaskOnClickListener());

		btnPicture.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				startActivity(cameraIntent);
			}
		});

		btnNote.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), getString(R.string.VoiceRecordInst), Toast.LENGTH_LONG).show();
				Intent VoiceRecordIntent = new Intent(Intent.ACTION_MAIN);
				PackageManager manager = getPackageManager();
				VoiceRecordIntent = manager.getLaunchIntentForPackage("com.tokasiki.android.voicerecorder");
				VoiceRecordIntent.addCategory(Intent.CATEGORY_LAUNCHER);
				startActivity(VoiceRecordIntent);
			}
		});
	}


	private View.OnClickListener btnStartStopOnClickListener() {
		return new View.OnClickListener() {
			public void onClick(View v) {
				if (btnStartStop.getText().equals("Start"))
					//Jess said to make this one line for best practices. Got rid of the brackets {}. makes it easier to read
					startChronometer();
					//btnTaskOnClickListener();} Need to record event
				else if  (btnStartStop.getText().equals("Stop"))
					stopChronometer();
					//btnTaskOnClickListener();} Need to record event
			}
		};
	}

	private View.OnClickListener btnResetOnClickListener() {
		return new View.OnClickListener() {
			public void onClick(View v) {
				resetChronometer();
			}
		};
	}

	//onClickListener for all task buttons, needs to write the text of the button and time stamp to file
	private View.OnClickListener btnTaskOnClickListener() {
		return new View.OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), ((Button) v).getText(), Toast.LENGTH_SHORT).show();
			}
		};
	}
	
	//from View.OnClickListener btnStartStopOnClickListener()
	private void startChronometer(){
		
		//If this is the first time starting
		if (!(chronoRunFlag)) {
			mChronometer.setBase(SystemClock.elapsedRealtime());
			chronoRunFlag = true;
		}
		//The stop watch was just paused
		else{
			mChronometer.setBase(SystemClock.elapsedRealtime()-elapsedTime);
		}
		mChronometer.start();
		btnStartStop.setText("Stop");
		lastStart = mChronometer.getBase();
		
	}

	//from View.OnClickListener btnStartStopOnClickListener()
	private void stopChronometer(){
		mChronometer.stop();
		btnStartStop.setText("Start");
		elapsedTime = elapsedTime+SystemClock.elapsedRealtime()-lastStart;
	}

	//from View.OnClickListener btnResetOnClickListener()
	private void resetChronometer(){
		btnStartStop.setText("Start");
		mChronometer.stop();
		mChronometer.setBase(SystemClock.elapsedRealtime());
		chronoRunFlag = false;
		elapsedTime = 0;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		CreateMenu(menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return MenuChoice(item);
	}

	private void CreateMenu(Menu menu) {
		MenuItem mnu1 = menu.add(0, 0, 0, "Edit Task Names");
		{
			mnu1.setAlphabeticShortcut('a');
			mnu1.setIcon(R.drawable.icon);
		}
		MenuItem mnu2 = menu.add(0, 1, 1, "View Data Log");
		{
			mnu2.setAlphabeticShortcut('b');
			mnu2.setIcon(R.drawable.icon);
		}
		MenuItem mnu3 = menu.add(0, 2, 2, "View Data Summary Graphs");
		{
			mnu3.setAlphabeticShortcut('c');
			mnu3.setIcon(R.drawable.icon);
		}

	}

	private boolean MenuChoice(MenuItem item) {
		switch (item.getItemId()) {
		case 0:
			Toast.makeText(this, "This should take you to another ?Activity? to edit the names",
					Toast.LENGTH_LONG).show();
			return true;
		case 1:
			Toast.makeText(this, "This should take you to another ?Activity? to view data logs",
					Toast.LENGTH_LONG).show();
			return true;
		case 2:
			Toast.makeText(this, "This should take you to another ?Activity? to view data summary graphs",
					Toast.LENGTH_LONG).show();
			return true;
		}
		return false;

	}

	@Override
	protected void onStop() {
		super.onStop();
		//We need an editor object to make preference changes
		//All objects are from android.context.Context
		SharedPreferences settings = getPreferences(0);
		SharedPreferences.Editor editor = settings.edit();
		
		//Need to set LastTest = current test name
		
		editor.putString("LastTest", lastTest);
		//Commit the changes!
		editor.commit();
	}


}