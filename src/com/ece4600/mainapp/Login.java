package com.ece4600.mainapp;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.ece4600.mainapp.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener{
	EditText txtuser;
	EditText txtpass;
	Button login;
	Button cancel;
	
	//database related constants
	
	JSONParser jsonParser = new JSONParser();
	private static final String LOGIN_URL = "http://wbanproject.com/webservice/loginandroid.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    private static final String TAG_USERNAME = "username";
    private static final String TAG_ADDRESS = "address";
    private ProgressDialog pDialog;
    
    // ---
    
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		txtuser = (EditText)findViewById(R.id.txtuser);
		txtpass = (EditText)findViewById(R.id.txtpass);
		login = (Button)findViewById(R.id.login);
		cancel = (Button)findViewById(R.id.cancel);
		login.setOnClickListener(this);
		cancel.setOnClickListener(this);
	}

@Override
public void onClick(View v) {
//	String user = txtuser.getText().toString();
//	String pass = txtpass.getText().toString();
//	
	switch(v.getId()){
	case R.id.login:
		
		new AttemptLogin().execute(); // async task to log in
		break;	
//		if(user.equals("user") && pass.equals("pass")){
//			Intent i = new Intent(Login.this,Bluetooth.class);
//			startActivity(i);
//			Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
//			SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//			SharedPreferences.Editor editor = preferences.edit();
//			editor.putString("user", txtuser.getText().toString());
//			editor.putString("pass", txtpass.getText().toString());
//			editor.commit();
//		}
//		else{
//			Message(v);
//		}
//		break;
	case R.id.cancel:
		txtuser.setText("");
		txtpass.setText("");
		break;
	default:
		break;
	}
	
}

//public void Message(View v){
//	AlertDialog.Builder alertDialogHint = new AlertDialog.Builder(this);
//	alertDialogHint.setMessage("Wrong Username/Password");
//	alertDialogHint.setNeutralButton("OK",
//	new DialogInterface.OnClickListener() {
//		
//		@Override
//		public void onClick(DialogInterface dialog, int which) {
//			Intent i = new Intent(Login.this,Login.class);
//			startActivity(i);
//			
//		}
//	});
//	AlertDialog alertDialog = alertDialogHint.create();
//	alertDialog.show();
//
//}
//	
class AttemptLogin extends AsyncTask<String, String, String> {

	 /**
  * Before starting background thread Show Progress Dialog
  * */
	boolean failure = false;
	
 @Override
 protected void onPreExecute() {
     super.onPreExecute();
     pDialog = new ProgressDialog(Login.this);
     pDialog.setMessage("Attempting login...");
     pDialog.setIndeterminate(false);
     pDialog.setCancelable(true);
     pDialog.show();
 }
	
	@Override
	protected String doInBackground(String... args) {
		// TODO Auto-generated method stub
		 // Check for success tag
     int success;
     String username_result,user_address;
     String username = txtuser.getText().toString();
     String password = txtpass.getText().toString();
     try {
         // Building Parameters
         List<NameValuePair> params = new ArrayList<NameValuePair>();
         params.add(new BasicNameValuePair("username", username));
         params.add(new BasicNameValuePair("password", password));

         Log.d("request!", "starting");
         // getting product details by making HTTP request
         JSONObject json = jsonParser.makeHttpRequest(
                LOGIN_URL, "POST", params);

         // check your log for json response
         Log.d("Login attempt", json.toString());

         // json success tag
         success = json.getInt(TAG_SUCCESS);
         username_result = json.getString(TAG_USERNAME);
         user_address = json.getString(TAG_ADDRESS);
         
         if (success == 1) {
         	Log.d("Login Successful!", json.toString());
         	//Intent i = new Intent(Login.this, ReadComments.class);
         	Intent i = new Intent(getApplicationContext(), MainActivity.class);
         	i.putExtra("database_user",username_result);
         	i.putExtra("database_address",user_address);
         	finish();
				startActivity(i);
         	return "welcome " + json.getString(TAG_USERNAME) + " your address is :" + user_address;
         }else{
         	Log.d("Login Failure!", json.getString(TAG_MESSAGE));
         	return json.getString(TAG_MESSAGE);
         	
         }
     } catch (JSONException e) {
         e.printStackTrace();
     }

     return null;
		
	}
	/**
  * After completing background task Dismiss the progress dialog
  * **/
 protected void onPostExecute(String file_url) {
     // dismiss the dialog once product deleted
     pDialog.dismiss();
     if (file_url != null){
     	Toast.makeText(Login.this, file_url, Toast.LENGTH_LONG).show();
     		}

 		}
	}
}

