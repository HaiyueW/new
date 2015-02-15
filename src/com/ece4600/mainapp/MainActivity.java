package com.ece4600.mainapp;

import org.achartengine.GraphicalView;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
 

public class MainActivity extends Activity {
//	public static final String RECEIVE_JSON = "com.your.ece4600.RECEIVE_JSON";
	private PosturePie pieChart = new PosturePie();
	private static Context context;
	
	public SharedPreferences settings;
	public SharedPreferences.Editor editor;
	
	TextView  dob, weight,name,sex;
	
	//public static final String RECEIVE_JSON = "com.your.ece4600.RECEIVE_JSON";
//	private TextView user_name;
//	private TextView user_gender;
	
	private BroadcastReceiver broadcastRx = new BroadcastReceiver() {
	    @Override
	    public void onReceive(Context context, Intent intent) {
	        //if(intent.getAction().equals(RECEIVE_JSON)) {
	    	int datapassed = intent.getIntExtra("DATAPASSED", 0);
	    	  
	    	  Toast.makeText(MainActivity.this,
	    	    "Triggered by Service!\n"
	    	    + "Data passed: " + String.valueOf(datapassed),
	    	    Toast.LENGTH_LONG).show();
				//Log.i("MainActivity", msg);
	        //}
	    	  
	    
	    }
	};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); 
        
        
        //database related  starts here
//        user_name = (TextView)findViewById(R.id.name);
//        user_gender = (TextView)findViewById(R.id.gender);
//        
//
//        
//        Bundle extras = getIntent().getExtras();
//        if (extras != null) {		    
//        	
//        	String username = extras.getString("database_user");
//		    String address = extras.getString("database_address");
//
//		    user_name.setText("Name: " + username);
//		    user_gender.setText("Address: " +address);
//		}
//        
        
        //database related ends here
//        
    	context = getBaseContext();
		pieChart.initialize();
		pieChart.updateData();
		paintGraph();

        setupMessageButton1();
        setupMessageButton2();
        setupMessageButton3();
        setupMessageButton4();
        
        //
        
        
        
        
        //Intent Login_window = new Intent(this, Login.class); // adds the log in window here
        //startActivity(Login_window);
        
        //LocalBroadcastManager bManager = LocalBroadcastManager.getInstance(this);
        //IntentFilter intentFilter = new IntentFilter();
        //intentFilter.addAction(bleService.MY_ACTION);
        //registerReceiver(broadcastRx, intentFilter);
        
        //Start our own service
       // Intent intent = new Intent(MainActivity.this, bleService.class);
        //startService(intent);
        
        name = (TextView)findViewById(R.id.name);
        sex = (TextView)findViewById(R.id.gender);
        dob = (TextView)findViewById(R.id.DOB);
        weight = (TextView)findViewById(R.id.weightMain);
        
  	  	setUpPreferences();
  	  	restorePreferences();
        
    }

    private void setupMessageButton1(){
    	Button messageButton = (Button)findViewById(R.id.heart);
    	messageButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Toast.makeText(MainActivity.this, "Heart rate", Toast.LENGTH_LONG).show();
				startActivity(new Intent(MainActivity.this, Heartrate.class));
				finish();
			}
		});	
    }
    
    private void setupMessageButton2(){
    	Button messageButton = (Button)findViewById(R.id.pedo);
    	messageButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Toast.makeText(MainActivity.this, "Pedometer", Toast.LENGTH_LONG).show();
				startActivity(new Intent(MainActivity.this, Pedometer.class));
				finish();
			}
		});	
    }
    
    private void setupMessageButton3(){
    	Button messageButton = (Button)findViewById(R.id.loca);
    	messageButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Toast.makeText(MainActivity.this, "Location", Toast.LENGTH_LONG).show();
				startActivity(new Intent(MainActivity.this, Location.class));
				finish();
			}
		});	
    }
    
    private void setupMessageButton4(){
    	Button messageButton = (Button)findViewById(R.id.post);
    	messageButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Toast.makeText(MainActivity.this, "Posture", Toast.LENGTH_LONG).show();
				startActivity(new Intent(MainActivity.this, Posture.class));
				finish();
			}
		});	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
   
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
    	super.onOptionsItemSelected(item);
    	switch(item.getItemId()){
  
    	case R.id.mainmenu_logout:
    		PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().clear().commit();
    		startActivity(new Intent(this, Login.class));
    		finish();
    		break;
    	case R.id.editProfile:
    		PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().clear().commit();
    		startActivity(new Intent(this, EditUserSettings.class));
    		finish();
    		break;
    		
    	}
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return true; 
    }
    
    public void paintGraph(){
		//Get Graph information:
		GraphicalView lineView = pieChart.getView(context);
		//Get reference to layout:
		LinearLayout layout =(LinearLayout)findViewById(R.id.posturePie);
		//clear the previous layout:
		//layout.removeAllViews();
		//add new graph:
		if (layout != null)
				layout.addView(lineView);
	}
//    
    
	public void setUpPreferences(){
    	settings = getSharedPreferences("userPrefs", MODE_PRIVATE);
    	editor = settings.edit();
    }
	
	public void restorePreferences(){
		name.setText("NAME: " + settings.getString("name", "Mike"));
		dob.setText("D.O.B.: " + settings.getString("DOB", "MM/DD/YYYY"));
		weight.setText("WEIGHT: " + settings.getString("weight", "xxx"));
		sex.setText("GENDER: " + settings.getString("sex", "Male"));
		
	}
}
