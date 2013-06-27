/*
 This activity opens connect screen when user clicks on CLICKER Icon for the second time.
 */

//package name of the project.
package com.iitb;

//Importing android packages.
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

//ConnectActivity class begins.
public class ConnectActivity extends Activity implements android.view.View.OnClickListener 
{
	public static final int OPEN_SETTINGS_REQUEST = 1;
	private SharedPreferences sharedpreferences;
	
	int resultcode,id;
	String serverip_new,macaddress,serverip_old,response,centre,centre_new,data,ipaddress,enrollment_id;
	int flag;
	Button Connect,Settings;
	private ProgressDialog mProgressDialog;
	public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
	Bundle b = new Bundle();// Initialize bundle.
	WifiManager wifiMan;
	WifiInfo wifiInf;
	
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);// Always call the superclass method first.
		setContentView(R.layout.connect_server);//Refer to connect_server.xml
		PreferenceManager.setDefaultValues(this, R.xml.settings, false);//Load the default values of settings preferences screen.
	
		sharedpreferences = PreferenceManager.getDefaultSharedPreferences(this);//get the default values from PreferenceManager.
	
		macaddress=SharedPreferenceConnector.readString(this,SharedPreferenceConnector.MACADDRESS, null);//read the  macaddress from shared preferences.
        
		centre=SharedPreferenceConnector.readString(this,SharedPreferenceConnector.CENTRE, "centre");//read the centre from shared preferences.
		
		
		Connect=(Button)findViewById(R.id.Connect2);//Refer to Connect button having Connect2 id.
	
		 Connect.setOnClickListener(this);//add listener to Connect button.
	 
		 if(centre.equals("Local"))// check the state of radio button.
		 {
			 ((RadioButton) findViewById(R.id.connect_local)).setChecked(true);// set the state of radio button.
			 
			 ((RadioButton) findViewById(R.id.connect_local)).setBackgroundColor(Color.GREEN);// set the background color.
			 ((RadioButton) findViewById(R.id.connect_remote)).setBackgroundColor(Color.GRAY);// set the background color.
			 ((RadioButton) findViewById(R.id.connect_local)).setTextColor(Color.BLACK);// store the text color.
			 
		 }
		 if(centre.equals("Remote"))// check the state of radio button.
		 {
			 ((RadioButton) findViewById(R.id.connect_remote)).setChecked(true);// set the state of radio button.
			 ((RadioButton) findViewById(R.id.connect_local)).setBackgroundColor(Color.GRAY);// set the background color.
			 ((RadioButton) findViewById(R.id.connect_remote)).setBackgroundColor(Color.GREEN);// set the background color.
			 ((RadioButton) findViewById(R.id.connect_remote)).setTextColor(Color.BLACK);// store the text color.
		 }
		 
		 
		
		 
		 
		 Intent in = new Intent();//initialize intent
			setResult(1, in);//set result code for MainActivity.

	}
	
	//create Menu
	 public boolean onCreateOptionsMenu(Menu menu)
	 {
	    	super.onCreateOptionsMenu(menu);// Always call the superclass method first.
	    	menu.add(0, 0,0,"Help");//add Help Menu Item to Menu. 
	    	menu.add(0, 1,0,"Settings");//add Settings Menu Item to Menu.
	    	
	    	return true;//return true value.
	    	
	    	}
	    //callback method to handle Menu Item action event
	    public boolean onOptionsItemSelected(MenuItem item)
	    {
	    
	    id=item.getItemId();//get the selected Menu Item id.
	  
	    switch (item.getItemId()) // check for case.
	    {
	    case 0:   
	    	       showHelp();// call showHelp() method.
	               return true;//return true value.
	               
	    case 1:   
 	       showSettings();// call showSettings() method.
            return true;// return true value.
	    
	   
	    	      
	    }
	    return true;// return true value.
	    }
	
	
	    //Method to call Settings_helpActivity.
	    public void showHelp()
		{
			Intent intent = new Intent(ConnectActivity.this,Settings_helpActivtiy.class);//initialize intent for Settings_helpActivity.
			
			  startActivity(intent);// start Settings_helpActivity.
		}
		
	
	    //Method to call SettingsActivity.
	    public void showSettings()
	    {
	    	
			serverip_old=SharedPreferenceConnector.readString(this,SharedPreferenceConnector.SERVER_IP, null);//read SERVER_IP from shared preferences.
		
			Intent i = new Intent(ConnectActivity.this, SettingsActivity.class);//initialize intent for SettingsActivity.
			startActivityForResult(i, OPEN_SETTINGS_REQUEST);//start SettingsActivity and get result code.
	    }
	
	   
	  //Method to handle radio button click event. 
	    public void checkLocalRadioButton(View v)
	    {
	    	
	    	if(((RadioButton) findViewById(R.id.connect_local)).isChecked())// check the state of radio button.
			{
			SharedPreferenceConnector.writeString(this,SharedPreferenceConnector.CENTRE,"Local");// store the Centre in shared preferences.
			((RadioButton) findViewById(R.id.connect_local)).setBackgroundColor(Color.GREEN);// set the background color.
			 ((RadioButton) findViewById(R.id.connect_remote)).setBackgroundColor(Color.GRAY);// set the background color.
			 ((RadioButton) findViewById(R.id.connect_local)).setTextColor(Color.BLACK);// store the text color.
			}
	    }
	    
	  //Method to handle radio button click event.
	    public void checkRemoteRadioButton(View v)
	    {
	    	if(((RadioButton) findViewById(R.id.connect_remote)).isChecked())// check the state of radio button.
			{
			SharedPreferenceConnector.writeString(this,SharedPreferenceConnector.CENTRE,"Remote");// store the Centre in shared preferences.
			((RadioButton) findViewById(R.id.connect_local)).setBackgroundColor(Color.GRAY);// set the background color.
			 ((RadioButton) findViewById(R.id.connect_remote)).setBackgroundColor(Color.GREEN);// set the background color.
			 ((RadioButton) findViewById(R.id.connect_remote)).setTextColor(Color.BLACK);// store the text color.
			}
	    	
	    }
	    
	    
    //Method to handle button click events.
	@Override
	public void onClick(View v) 
	
	{
		
		if(v.getId()==R.id.Connect2)// check for Connect button click.
		{
			
			
			
			ipaddress=getIPAddress();//get IPaddress of the android tablet.
			enrollment_id=SharedPreferenceConnector.readString(ConnectActivity.this, SharedPreferenceConnector.ENROLLMENT_ID,null);	//read Enrollment_ID from shared preferences.
			serverip_old=SharedPreferenceConnector.readString(this,SharedPreferenceConnector.SERVER_IP, null);//read SERVER_IP from shared preferences.
			centre=SharedPreferenceConnector.readString(this,SharedPreferenceConnector.CENTRE, "centre");//read the centre from shared preferences.
			new DownloadFileAsync().execute();// execute Asynchronous task in background process.
		
		
		
			
		}
		

	}
	
	
	 @Override
	    protected Dialog onCreateDialog(int id)
	 {
	        switch (id) {
			case DIALOG_DOWNLOAD_PROGRESS:
				mProgressDialog = new ProgressDialog(this);// initailize the object of ProgressDialog(context);
				mProgressDialog.setMessage("Loading.......");//set the message while showing dialog.
				mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);//Creates a ProgressDialog with a circular, spinning progress bar.
				mProgressDialog.setIndeterminate(true);// to show indeterminate line as progress bar.
				mProgressDialog.setCancelable(false);//Sets whether this dialog is cancelable with the BACK key.
				mProgressDialog.show();//show the dialog.
				return mProgressDialog;//return ProgressDialog.
			default:
				return null;
	        }
	    }
	
	
	// This class performs asynchronous operation for network connection.
	
	 class DownloadFileAsync extends AsyncTask<String,String, String>
	 {
		
		  //override method of AsyncTask,invoked on the UI thread immediately after the task is executed. 
			@Override
			protected void onPreExecute() 
			{
				super.onPreExecute();// Always call the superclass method first.
				showDialog(DIALOG_DOWNLOAD_PROGRESS);//call onCreateDialog(int) method to open ProgressDialog.

			}
			 //override method of AsyncTask, invoked on the background thread immediately after onPreExecute() finishes executing.
			@Override
			protected String doInBackground(String... aurl)
			{
				flag=3;
				
				
				HttpConnection con = new HttpConnection();// initialize HttpConnection object.
				
				response = con.getConnection(serverip_old,enrollment_id, macaddress,ipaddress,flag,centre);// call getConnection() method of HttpConnection class and getting response.
				
				if(response.equals("HTTP/1.1 200 OK"))// check for response if is OK.
				{
					
					 data=con.getData();// call the getData() method of HttpConnection class and data is collected.
						
						if(data!=null) // check for data
						{
							
						callActivity(data);	// call callActivity(data) method
						
						}
				
				}
				
				if(response.equals("HTTP/1.1 404 Not Found"))// check for response if it is Not Found.
				{
					
					String msg="Please Set Correct Server IP Address";
					publishProgress(msg);//call onProgressUpdate() method.
					
					
				}
				
				return null; //return null string.

			}
			// onProgressUpdate method,invoked on the UI thread after a call to publishProgress(Progress...).
			protected void onProgressUpdate(String... progress)
			{
				 
				//show message which is set by publishProgress().
				Toast.makeText(ConnectActivity.this, progress[0],Toast.LENGTH_SHORT).show();
			}
			 //override method of AsyncTask,invoked on the UI thread after the background computation finishes.
			@Override
			protected void onPostExecute(String unused)
			{
				dismissDialog(DIALOG_DOWNLOAD_PROGRESS);// dismiss dialog.
				
			}
		}
		
		//This method check for the data collected from getData() method of HttpConnection class.
	 
	 public void callActivity(String data)
		{
	
			
			if (data.equals("Connect")) // check for data if it is Connect.
			{
				
				
				Intent i = new Intent();//initialize intent.
				i.setClass(getApplicationContext(), QuizPageActivity.class);//set QuizPageActivity to call from intent.
				b.putString("MacAddress", macaddress);//put macaddress value in  bundle.
				b.putString("ServerIP",serverip_old);//put serverip_old value in bundle.
				b.putString("Centre", centre);//put centre value in bundle..
				i.putExtras(b);//put bundle to intent.
				
				startActivityForResult(i, resultcode);//start QuizPageActivity and getting result code from it.
				
				Intent in = new Intent();//initialize intent.
				setResult(1, in);//set result code for MainActivity.
	   	
			}

			if (data.equals("Registration")) // check for data if it is Registration.
			{
				     Intent i = new Intent(ConnectActivity.this,RegistrationActivtiy.class);// initialize intent for  RegistrationActivity.
				
					startActivityForResult(i, resultcode);//start RegistrationActivity and getting result code from it.
					
					Intent in = new Intent();//initialize intent.
					setResult(1, in);//set result code for MainActivity.
			}
			
			
		}
	 

	
	 //Method to handle result collected from Intent.
	 
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == 1) // check for result code.
		{
			finish();// finish/exit the ConnectActivity.
		}

		
		// check for the result code obtained by accessing SettingsPreferences screen.
		if (requestCode == OPEN_SETTINGS_REQUEST)
		{
			if (resultCode == RESULT_OK)
			{
				
			
			} else if (resultCode == RESULT_CANCELED)
			{
				
				serverip_new = sharedpreferences.getString("Server IP",serverip_old);// read the Server IP from settings preferences 
				SharedPreferenceConnector.writeString(this,SharedPreferenceConnector.SERVER_IP, serverip_new);// store the Server IP in shared preferences.
			//	centre_new =sharedpreferences.getString("Centre",centre_old);// read the centre from settings preferences 
			//	SharedPreferenceConnector.writeString(this,SharedPreferenceConnector.CENTRE, centre_new);// store the Centre in shared preferences.
			//	((TextView) findViewById(R.id.centermode)).setText("Your center mode is : "+centre_new);
			
		}
		}
	
		
		
	}
	
	
	//Method perform the task to collect the IP Address from android tablet.
	private String getIPAddress() {
		 wifiMan = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);// class to manage the WI-FI connection.
		 wifiInf = wifiMan.getConnectionInfo();//class to handle the connection information of WI-FI.
		int ipAddress = wifiInf.getIpAddress();// get the IP address of the android tablet. 
		
		ipaddress=String.format("%d.%d.%d.%d", 
				(ipAddress & 0xff), 
				(ipAddress >> 8 & 0xff), 
				(ipAddress >> 16 & 0xff),
				(ipAddress >> 24 & 0xff));
		
		
		if (ipaddress.equals("0.0.0.0")) // check for IP address
		{
			ipaddress = "No IP ADDRESS";
		}

		return ipaddress;// return IP address.
	}

	
	
}
//ConnectActivity class ends.
