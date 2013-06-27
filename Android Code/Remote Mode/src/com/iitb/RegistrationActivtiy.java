/*
 This activity opens the Registration page for the user.Then user enters the Enrollment No. and Server IP address.
 */

// Package of the project.
package com.iitb;

//Importing android packages.
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.http.NameValuePair;
import android.os.AsyncTask;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

// Registration Activity begins
public class RegistrationActivtiy extends Activity implements OnClickListener 
{


	int resultcode;
	
	EditText enrollmentID, serverIP;
	String macaddress, server_ip,response,data,centre,ipaddress;
	String enrollment_id;
	Button Connect, Reset;
	RadioButton Local,Remote;
	private ProgressDialog mProgressDialog;
	public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
	List<NameValuePair> nameValuePairs;
	int flag,id;
	HttpConnection con;
	WifiManager wifiMan;
	WifiInfo wifiInf;
	MyEncryption crypto = new MyEncryption();// Initialize object.
	Bundle b = new Bundle();// Initialize object.
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);// Always call the superclass method first.
		setContentView(R.layout.registration); // refer to registration xml.
		
	
		enrollmentID = (EditText) findViewById(R.id.enrollment_id); // refer to edittextbox having enrollment_id in registration xml.
		serverIP = (EditText) findViewById(R.id.serverip);// refer to edittextbox having serverip in registration xml.
 
		Local=(RadioButton)findViewById(R.id.local);//refer to radio button having local id in registration xml.
		Remote=(RadioButton)findViewById(R.id.remote);//refer to radio button having remote id in registration xml.
		
		centre=SharedPreferenceConnector.readString(this,SharedPreferenceConnector.CENTRE,"centre");//read the centre from shared preferences.
		
		 if(centre.equals("Local"))// check the state of radio button.
		 {
			 ((RadioButton) findViewById(R.id.local)).setChecked(true);// set the state of radio button.
			 
			// ((RadioButton) findViewById(R.id.connect_local)).setBackgroundColor(Color.GREEN);// set the background color.
			// ((RadioButton) findViewById(R.id.connect_remote)).setBackgroundColor(Color.GRAY);// set the background color.
			// ((RadioButton) findViewById(R.id.connect_local)).setTextColor(Color.BLACK);// store the text color.
			 
		 }
		 if(centre.equals("Remote"))// check the state of radio button.
		 {
			 ((RadioButton) findViewById(R.id.remote)).setChecked(true);// set the state of radio button
			// ((RadioButton) findViewById(R.id.connect_local)).setBackgroundColor(Color.GRAY);// set the background color.
			// ((RadioButton) findViewById(R.id.connect_remote)).setBackgroundColor(Color.GREEN);// set the background color.
			// ((RadioButton) findViewById(R.id.connect_remote)).setTextColor(Color.BLACK);// store the text color.
		 }
	
		Intent in = new Intent();
		setResult(1, in);

		Connect = (Button) findViewById(R.id.Connect1);// refer to Connect button id in registration xml.
		Reset = (Button) findViewById(R.id.Reset);// refer to Reset button id in registration xml.
		Reset.setOnClickListener(this);// add listener to Reset button.
		Connect.setOnClickListener(this);// add listener to Connect button.
        Local.setOnClickListener(this);// add listener to Reset button.
        Remote.setOnClickListener(this);// add listener to Reset button.
	}

	
	
	
	
	// Menu for Help
	 public boolean onCreateOptionsMenu(Menu menu) 
	 {
	    	super.onCreateOptionsMenu(menu);
	    	menu.add(0, 0,0,"Help");// represents Help as menu item.
	 
	    	return true;
	    	
	    	}
	
	 //Method to handle MenuItem action
	    public boolean onOptionsItemSelected(MenuItem item)
	    {
	    
	    id=item.getItemId();// get the id of Menu Item
	 
	    switch (item.getItemId()) 
	    {
	    case 0:   
	    	       showHelp();// call showHelp() method.
	               return true;// return value
	    
	   
	    	      
	    }
	    return true;
	    }
	
	
	    //Method to start Registration_helpActivity
	    public void showHelp()
		{
			Intent intent = new Intent(RegistrationActivtiy.this,Registration_helpActivtiy.class);// Initialize intent for Registration_helpActivity.
		
			  startActivity(intent);// start Registration_helpActivity.
		}
		
	
	
	
	
// Method to handle the button click event.
	@Override
	public void onClick(View v) {
	
	
		
		if (v.getId() == R.id.Connect1) // check for the Connect button click event
		{

			if(Local.isChecked()||Remote.isChecked())//Checking the state of Radio Button (Local or Remote) if it is checked.
			{
				
		      if(Local.isChecked())centre=Local.getText().toString();//get the text of radio button checked for Local centre.
		      if(Remote.isChecked())centre=Remote.getText().toString();//get the text of radio button checked for Remote centre.
			
			
	        macaddress = getMac();//get wi-fi macaddress of the android tablet.
	        ipaddress=getIPAddress();//get IPaddress of the android tablet.
			if(!enrollmentID.getText().toString().equals("") && !serverIP.getText().toString().equals("")) //check EnrollmentID and Server IP for not null string 
			{
				enrollment_id = enrollmentID.getText().toString();// get the string value of EnrollmentID  entered by user.
				server_ip = serverIP.getText().toString();//get the string value of serverIP entered by user.
			   
				//Regular expression for checking validity of IP address.
				final String IPV4_REGEX = "(([0-1]?[0-9]{1,2}\\.)|(2[0-4][0-9]\\.)|(25[0-5]\\.)){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))";
				   Pattern IPV4_PATTERN = Pattern.compile(IPV4_REGEX);

				  boolean check_validity = IPV4_PATTERN.matcher(serverIP.getText().toString()).matches();
				  
				  if(check_validity)// check for validity of IP address
				  {
					
					  new DownloadFileAsync().execute();// if valid IP Address  then execute Asynchronous task in background.
				  }
				  
				  else
				  {
                      //show the message if invalid IP address.
					  Toast.makeText(getApplicationContext(),"INVALID IP ADDRESS,Please check Help menu",Toast.LENGTH_SHORT).show();
					  
				  }
			
			}
			
			else
			{
				if(serverIP.getText().toString().equals("")&& enrollmentID.getText().toString().equals(""))//check EnrollmentID and Server IP for  null string 
				{
					 //show the message if null string.
					Toast.makeText(RegistrationActivtiy.this, "Please Enter EnrollmentID and Server IP Address",Toast.LENGTH_SHORT).show();
					
				}
				else
				{
					if(serverIP.getText().toString().equals(""))//check if only ServerIP address is null string
					{
						//show message to enter the Server IP Address if ServerIP is null string.
						Toast.makeText(RegistrationActivtiy.this, "Please Enter Server IP Address",Toast.LENGTH_SHORT).show();
					}
					else
					{
						
						if(enrollmentID.getText().toString().equals(""))//check if only EnrollmentID is null string.
						{
							//show message to enter Enrollment ID if EnrollmentID is null string.
							Toast.makeText(RegistrationActivtiy.this, "Please Enter EnrollmentID",Toast.LENGTH_SHORT).show();
						}
						
					}
					
				}
				
			}
		}
			
			
			else
			{
				//show message if any radio button is not checked.
				 Toast.makeText(getApplicationContext(),"Select the Centre",Toast.LENGTH_SHORT).show();
			}
			

	
		}

		if (v.getId() == R.id.Reset) // check for Reset button click event.
		{
			enrollmentID.setText("");// Reset EnrollmentID.
			serverIP.setText("");// Reset ServerIP.

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
	
	
	
	// class defined to execute Asynchronous task in background.
	
	class DownloadFileAsync extends AsyncTask<String, String, String> {
		
		 //override method of AsyncTask,invoked on the UI thread immediately after the task is executed. 
		@Override
		protected void onPreExecute() 
		{
			super.onPreExecute();
			showDialog(DIALOG_DOWNLOAD_PROGRESS);//call onCreateDialog(int) method to open ProgressDialog.

		}
		
		//override method of AsyncTask, invoked on the background thread immediately after onPreExecute() finishes executing.

		@Override
		protected String doInBackground(String... aurl)
		{
			flag=1;
		    
			 con = new HttpConnection();// initialize HttpConnection class object.
			 
			 
				try {
					enrollment_id = crypto.encrypt(enrollment_id);
					macaddress = crypto.encrypt(macaddress);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			response = con.getConnection(server_ip, enrollment_id, macaddress,ipaddress,flag,centre);// call the getConnection() method of HttpConnection class and get response.
		
			if(response.equals("HTTP/1.1 200 OK"))// check response if it is OK.
			{
				
                   data=con.getData();// call getData() method of HttpConnection class and get data.
				
				if(data!=null)// check data for null string.
				{
					
				if(data.equals("No"))//check for the data if it is No.
				{
					
					String msg="Please type correct Enrollment ID";
					publishProgress(msg);// call to onProgressUpdate() method.
				
				}
				else
				{
					
					if(data.equals("Duplicate")) //check for the data if it is Duplicate.
					{
						
						String msg="Enrollment ID Already Exist";
						publishProgress(msg);// call to onProgressUpdate() method.
					}
					else
					{
						
						callActivity(data);// call callActivity(data) method.
					}
			
				}
				}
				
			}
			
			if(response.equals("HTTP/1.1 404 Not Found"))// check for the response if it is Not Found.
			{
				String msg="Network Failure";
				
				publishProgress(msg);// calling onProgressUpdate() method. 
			}
			
			return null;

		}
		
		// onProgressUpdate method,invoked on the UI thread after a call to publishProgress(Progress...).
		protected void onProgressUpdate(String... progress)
		{
			//show the message sent by publishProgress(msg);
			
			Toast.makeText(RegistrationActivtiy.this, progress[0],Toast.LENGTH_SHORT).show();
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
		
		if (data.equals("QuizPage")) //check for the data if it is QuizPage.
		{
			//storing macaddress,server_ip,enrollment_id,centre in shared preferences.
			SharedPreferenceConnector.writeString(RegistrationActivtiy.this, SharedPreferenceConnector.MACADDRESS,macaddress);
			SharedPreferenceConnector.writeString(RegistrationActivtiy.this, SharedPreferenceConnector.SERVER_IP,server_ip);
			SharedPreferenceConnector.writeString(RegistrationActivtiy.this, SharedPreferenceConnector.ENROLLMENT_ID,enrollment_id);	
			SharedPreferenceConnector.writeString(RegistrationActivtiy.this, SharedPreferenceConnector.CENTRE,centre);

			
			Intent i = new Intent();
			
			i.setClass(getApplicationContext(),	QuizPageActivity.class);// Initialize intent for QuizPageActivity.
		
			b.putString("MacAddress", macaddress);//storing macaddress in bundle.
			b.putString("ServerIP", server_ip);//storing server_ip in bundle.
			b.putString("Centre",centre);//storing centre (Local or Remote) in bundle.
			i.putExtras(b);//storing bundle in intent.
			startActivityForResult(i, resultcode);// start QuizPageActivity and getting result.

			Intent in = new Intent();
			setResult(1, in);//set the result value for MainActivity.
   	
		}

	}

	 //Method to handle result collected from Intent.
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == 1) //check for resultCode
		{
			finish();// finish the QuizPageActivity.
		}
	}

	//Method perform the task to collect the macaddress from android tablet.
	private String getMac() {
		 wifiMan = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);// class to manage the WI-FI connection.
		 wifiInf = wifiMan.getConnectionInfo();//class to handle the connection information of WI-FI.
		String macadd = wifiInf.getMacAddress();// get the wi-fi macaddress of the android tablet. 
		
		if (macadd == null) // check for macaddress
		{
			macadd = "No MACADDRESS";
		}

		return macadd;// return macaddress.
	}
	
	
	//Method perform the task to collect the IP Address from android tablet.
			private String getIPAddress() {
				
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
//Registration Activity ends.
