package com.ece4600.mainapp;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Pedometer extends Activity{
	
	private TextView currentX, currentY, currentZ, maxX, maxY, maxZ, step,speed;
	Button reset, returnbutton, start, stop;
	private int stepnum = 0;
	private float speednum = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pedometer);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		initializeViews();
		reset = (Button) findViewById(R.id.pedo_reset);
		returnbutton = (Button) findViewById(R.id.returnpedo);
		start = (Button) findViewById(R.id.pedo_start);
		stop = (Button) findViewById(R.id.pedo_stop);
		
		IntentFilter intentFilter = new IntentFilter("PEDOMETER_EVENT");
        registerReceiver(broadcastRx, intentFilter);

	}

	public void initializeViews() {
		currentX = (TextView) findViewById(R.id.pedo_xaxisdata);
		currentY = (TextView) findViewById(R.id.pedo_yaxisdata);
		currentZ = (TextView) findViewById(R.id.pedo_zaxisdata);
		maxX = (TextView) findViewById(R.id.pedo_accxmax);
		maxY = (TextView) findViewById(R.id.pedo_accymax);
		maxZ = (TextView) findViewById(R.id.pedo_acczmax);
		step = (TextView) findViewById(R.id.pedo_stepnum);
		speed = (TextView) findViewById(R.id.pedo_speednum);
	}

	 @Override
	 protected void onDestroy() {
	  super.onDestroy();
	  //un-register BroadcastReceiver
	  unregisterReceiver(broadcastRx);
	 }

	

	@Override
	
	protected void onResume() {
	super.onResume();
	
    IntentFilter intentFilter = new IntentFilter();
    intentFilter.addAction("PEDOMETER_ACTION");
    registerReceiver(broadcastRx, intentFilter);
	}
	
	@Override
	protected void onPause() {
	super.onPause();

    LocalBroadcastManager bManager = LocalBroadcastManager.getInstance(this);   
    bManager.unregisterReceiver(broadcastRx);
	}

	

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.pedo_start:
			onResume();
			//countdowndisplay();
			break;
		case R.id.pedo_stop:
			onPause();
			break;
		case R.id.returnpedo:
			startActivity(new Intent(Pedometer.this, MainActivity.class));
			finish();
			break;
		case R.id.pedo_reset:
			currentX.setText("0.0");
			currentY.setText("0.0");
			currentZ.setText("0.0");
			maxX.setText("0.0");
			maxY.setText("0.0");
			maxZ.setText("0.0");
			step.setText("0.0");
			speed.setText("0.0");
			break;
		default:
			break;
		}
	}

//	private void countdowndisplay() {
//		final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
//		alertDialog.setMessage("00:05");
//		alertDialog.show();
//
//		new CountDownTimer(5000, 1000) {
//			public void onTick(long millisUntilFinished) {
//				alertDialog.setMessage("00:" + (millisUntilFinished / 1000));
//			}
//
//			@Override
//			public void onFinish() {
//				alertDialog.setMessage("Completed");
//				alertDialog.dismiss();
//			}
//		}.start();
//	}

	public void onBackPressed() {
		// do something on back.return;
		startActivity(new Intent(Pedometer.this, MainActivity.class));
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pedometer, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case R.id.pedomenu_heart:
			startActivity(new Intent(this, Heartrate.class));
			finish();
			break;
		case R.id.pedomenu_loca:
			startActivity(new Intent(this, Location.class));
			finish();
			break;
		case R.id.pedomenu_post:
			startActivity(new Intent(this, Posture.class));
			finish();
			break;
		}
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return true;
	}

	// Broadcast receiver
	// Receives updates from postureService
		

		
		private BroadcastReceiver broadcastRx = new BroadcastReceiver() {
		    @Override
		    public void onReceive(Context context, Intent intent) {
		        
		        	stepnum = intent.getIntExtra("STEP", stepnum);
		        	speednum = intent.getFloatExtra("SPEED",speednum);
		        	float MaxX  = intent.getFloatExtra("MaxX", 0.0f);
		        	float MaxY  = intent.getFloatExtra("MaxY", 0.0f);
		        	float MaxZ  = intent.getFloatExtra("MaxZ", 0.0f);
		        	
		        	float CurrentX  = intent.getFloatExtra("CurrentX", 0.0f);
		        	float CurrentY  = intent.getFloatExtra("CurrentY", 0.0f);
		        	float CurrentZ  = intent.getFloatExtra("CurrentZ", 0.0f);
		        	
		        	maxX.setText(Float.toString(MaxX)); // This is different from posture. Perhaps you dont have to convert your float value
		        	maxY.setText(Float.toString(MaxY));
		        	maxZ.setText(Float.toString(MaxZ));
		        	currentX.setText(Float.toString(CurrentX));
		        	currentY.setText(Float.toString(CurrentY));
		        	currentZ.setText(Float.toString(CurrentZ));
		        	step.setText(Integer.toString(stepnum));
		        	speed.setText(Float.toString(speednum));
		        	
		        	
		       }
		        
		};

}
