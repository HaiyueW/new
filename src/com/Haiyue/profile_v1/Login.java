package com.Haiyue.profile_v1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
//import android.widget.Toast;

public class Login extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		setupMessageButton();
	}

	private void setupMessageButton(){
    	Button messageButton = (Button)findViewById(R.id.login);
    	messageButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Toast.makeText(Posture.this, "Return to profile", Toast.LENGTH_LONG).show();
				//startActivity(new Intent(Heartrate.this, MainActivity.class));
				finish();
			}
		});	
    }
	
	
//    private void setupMessageButton(){
//    	Button messageButton = (Button)findViewById(R.id.heart);
//    	messageButton.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				//Toast.makeText(MainActivity.this, "Heart rate", Toast.LENGTH_LONG).show();
//				startActivity(new Intent(MainActivity.this, Heartrate.class));
//			}
//		});	
//    }
//	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.posture, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}