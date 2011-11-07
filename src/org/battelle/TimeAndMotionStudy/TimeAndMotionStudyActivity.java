package org.battelle.TimeAndMotionStudy;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;
import android.widget.Toast;

public class TimeAndMotionStudyActivity extends Activity {
	Chronometer mChronometer;
	Button btnStart;
	Button btnStop;
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
	Button btnPicture;
	Button btnNote;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        //calls the parent's onCreate
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        

        
        mChronometer = (Chronometer) findViewById(R.id.chronometer1);
        //mChronometer.setFormat("HH:MM:SS.SS");
        
        //(Button) is a cast. findViewById returns a view object, the (Button) CASTS it as a button object
    	btnStart = (Button) findViewById(R.id.btnStart);
    	btnStop  = (Button) findViewById(R.id.btnStop);
    	
    	btnStart.setOnClickListener(btnStartOnClickListener());
        btnStop.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		String elapsedTimeStr;
        		long elapsedTime = 0;
        		long minutes = ((elapsedTime)/1000)/60;
			    long seconds = ((elapsedTime)/1000)%60;
			    elapsedTimeStr = minutes+":0"+seconds;
        		Toast.makeText(getApplicationContext(), elapsedTimeStr, Toast.LENGTH_SHORT).show();
        		mChronometer.stop();
        		btnStart.setText("Start");
        	}
        });
        btnTask1 = (Button) findViewById(R.id.btnTask1);
        btnTask1.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		Toast.makeText(getApplicationContext(), "Task 1", Toast.LENGTH_SHORT).show();
        	}
        });
        btnTask2 = (Button) findViewById(R.id.btnTask2);
        btnTask2.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		Toast.makeText(getApplicationContext(), "Task 2", Toast.LENGTH_SHORT).show();
        	}
        });
        btnTask3 = (Button) findViewById(R.id.btnTask3);
        btnTask3.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		Toast.makeText(getApplicationContext(), "Task 3", Toast.LENGTH_SHORT).show();
        	}
        });
        btnTask4 = (Button) findViewById(R.id.btnTask4);
        btnTask4.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		Toast.makeText(getApplicationContext(), "Task 4", Toast.LENGTH_SHORT).show();
        	}
        });
        btnTask5 = (Button) findViewById(R.id.btnTask5);
        btnTask5.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		Toast.makeText(getApplicationContext(), "Task 5", Toast.LENGTH_SHORT).show();
        	}
        });
        btnTask6 = (Button) findViewById(R.id.btnTask6);
        btnTask6.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		Toast.makeText(getApplicationContext(), "Task 6", Toast.LENGTH_SHORT).show();
        	}
        });
        btnTask7 = (Button) findViewById(R.id.btnTask7);
        btnTask7.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		Toast.makeText(getApplicationContext(), "Task 7", Toast.LENGTH_SHORT).show();
        	}
        });
        btnTask8 = (Button) findViewById(R.id.btnTask8);
        btnTask8.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		Toast.makeText(getApplicationContext(), "Task 8", Toast.LENGTH_SHORT).show();
        	}
        });
        btnTask9 = (Button) findViewById(R.id.btnTask9);
        btnTask9.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		Toast.makeText(getApplicationContext(), "Task 9", Toast.LENGTH_SHORT).show();
        	}
        });
        btnTask10 = (Button) findViewById(R.id.btnTask10);
        btnTask10.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		Toast.makeText(getApplicationContext(), "Task 10", Toast.LENGTH_SHORT).show();
        	}
        });
        btnPicture = (Button) findViewById(R.id.btnPicture);
        btnPicture.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		Toast.makeText(getApplicationContext(), "Take a picture", Toast.LENGTH_SHORT).show();
        	}
        });
        btnNote = (Button) findViewById(R.id.btnNote);
        btnNote.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View v) {
        		Toast.makeText(getApplicationContext(), "record a voice note", Toast.LENGTH_SHORT).show();
        	}
        });

        mChronometer.setOnChronometerTickListener(new OnChronometerTickListener()
        {
			String currentTime = "";
	        long elapsedTime=0;
			public void onChronometerTick(Chronometer arg0) {
				// TODO Auto-generated method stub

				
				if(btnStart.getText().equals("Start"))
			    {
			     
			     long minutes = ((SystemClock.elapsedRealtime()-mChronometer.getBase())/1000)/60;
			     long seconds = ((SystemClock.elapsedRealtime()-mChronometer.getBase())/1000)%60;
			     if (seconds<10){
			    	 currentTime = minutes+":0"+seconds;
			     }			     
			     else{
			    	 currentTime = minutes+":"+seconds;	
			     }
			     arg0.setText(currentTime);
			     elapsedTime = SystemClock.elapsedRealtime();
			    }
			    else
			    {
			     
			     long minutes = ((elapsedTime-mChronometer.getBase())/1000)/60;
			     long seconds = ((elapsedTime-mChronometer.getBase())/1000)%60;
			     if (seconds<10){
			    	 currentTime = minutes+":0"+seconds;
			     }			     
			     else{
			    	 currentTime = minutes+":"+seconds;	
			     }
			     arg0.setText(currentTime);
			     elapsedTime = elapsedTime+1000;
			    }
			}

         
        }
        );

    }
    
        
    private View.OnClickListener btnStartOnClickListener() {
    	return new View.OnClickListener() {
        	public void onClick(View v) {
        	    	
        		Toast.makeText(getApplicationContext(), btnStart.getText(), Toast.LENGTH_SHORT).show();
        		if (btnStart.getText().equals("Start"))
        			//Jess said to make this one line for best practices. Got rid of the brackets {}. makes it easier to read
        			startChronometer();
        		else if  (btnStart.getText().equals("Pause")) {
        			btnStart.setText("Restart");
        			mChronometer.stop();
        		}
        		else if (btnStart.getText().equals("Restart")) {
        			mChronometer.start();
        			btnStart.setText("Pause");
        		}
        	}
        };
    }
    
    //from above
    private void startChronometer(){
    	String currentTime = "";
		Toast.makeText(getApplicationContext(), "Made it into Start", Toast.LENGTH_SHORT).show();
		mChronometer.setBase(SystemClock.elapsedRealtime());
		mChronometer.start();
		btnStart.setText("Pause");
		mChronometer.setText(currentTime);
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




}