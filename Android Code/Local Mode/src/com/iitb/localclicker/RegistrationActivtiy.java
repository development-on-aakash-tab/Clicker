
// This activity opens the Registration page for the user.Then user enters the Enrollment No. and Server IP address.
 


package com.iitb.localclicker;// Package name of the project.

//List of all necessary android classes.
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.http.NameValuePair;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//Registration Activity begins
public class RegistrationActivtiy extends Activity implements OnClickListener 
{


	int resultcode;
	
	EditText EnrollmentID, ServerIP;
	String macaddress, server_ip,response,data,ipaddress;
  String enrollment_id;
	Button Connect, Reset;

	private ProgressDialog progress_dialog;
	public static final int DIALOG_LOADING_PROGRESS = 0;
	List<NameValuePair> nameValuePairs;
	int flag,id;
	HttpConnection con;
	WifiManager wifiMan;
	WifiInfo wifiInf;
	

	Bundle b = new Bundle();
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);// Always call the superclass method first.
		setContentView(R.layout.registration); // refer to registration.xml.
		
		EnrollmentID = (EditText) findViewById(R.id.enrollment_id); // refer to edittextbox having enrollment_id in registration.xml .
		ServerIP = (EditText) findViewById(R.id.server_ip);// refer to edittextbox having server_ip in registration.xml .

		Intent in = new Intent();//initialize intent.
		setResult(1, in);//set result code for MainActivity.

		Connect = (Button) findViewById(R.id.Connect1);// refer to Connect button id in registration.xml .
		Reset = (Button) findViewById(R.id.Reset);// refer to Reset button id in registration.xml .
		Reset.setOnClickListener(this);// add listener to Reset button.
		Connect.setOnClickListener(this);// add listener to Connect button.
   
		 wifiMan = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);// class to manage the WI-FI connection.
	     wifiInf = wifiMan.getConnectionInfo();//class to handle the connection information of WI-FI.
		
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
		
	
	
	
	
//Method to handle the button click event.
	@Override
	public void onClick(View v) {
	
		
		if (v.getId() == R.id.Connect1) // check for the Connect button click event
		{

			ipaddress=getIPAddress();
	        try {
	        	MyEncryption crypto = new MyEncryption();
				macaddress = crypto.encrypt(getMac());	//get wi-fi macaddress of the android tablet.
				//ipaddress=AESCrypto.encrypt(getIPAddress());
	        } catch (Exception e) {
				e.printStackTrace();
			}
			 Log.d("MACADDRESS", macaddress);
	        Log.d("IPADDRESS", ipaddress);
			if(!EnrollmentID.getText().toString().equals("") && !ServerIP.getText().toString().equals("")) //check EnrollmentID and ServerIP for not null string 
			{
				try {
					MyEncryption crypto = new MyEncryption();
					enrollment_id = crypto.encrypt(EnrollmentID.getText().toString());// get the String value of EnrollmentID string entered by user.
				} catch (Exception e) {					
					e.printStackTrace();
				}
				server_ip = ServerIP.getText().toString();//get the string value of ServerIP entered by user.
			   
				//Regular expression for checking validity of IP address.
				final String IPV4_REGEX = "(([0-1]?[0-9]{1,2}\\.)|(2[0-4][0-9]\\.)|(25[0-5]\\.)){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))";
				   Pattern IPV4_PATTERN = Pattern.compile(IPV4_REGEX);

				  boolean check_validity = IPV4_PATTERN.matcher(ServerIP.getText().toString()).matches();
				  
				  if(check_validity)// check for validity of IP address
				  {
					
					  new Network_OperationAsync().execute();// if valid IP Address then execute Asynchronous task in background.
				  }
				  
				  else
				  {
                    //show the message if invalid IP address.
					  Toast.makeText(getApplicationContext(),"INVALID IP ADDRESS,Please check Help menu",Toast.LENGTH_SHORT).show();
					  
				  }
			
			}
			
			else
			{
				if(ServerIP.getText().toString().equals("")&& EnrollmentID.getText().toString().equals(""))//check EnrollmentID and ServerIP for  null string 
				{
					 //show the message if null string.
					Toast.makeText(RegistrationActivtiy.this, "Please Enter Enrollment ID and  Server IP Address",Toast.LENGTH_SHORT).show();
					
				}
				else
				{
					if(ServerIP.getText().toString().equals(""))//check if only ServerIP address is null string
					{
						//show message to enter the ServerIP Address if it is null string.
						Toast.makeText(RegistrationActivtiy.this, "Please Enter Server IP Address",Toast.LENGTH_SHORT).show();
					}
					else
					{
						
						if(EnrollmentID.getText().toString().equals(""))//check if only EnrollmentID is null string.
						{
							//show message to enter EnrollmentID if it is null string.
							Toast.makeText(RegistrationActivtiy.this, "Please Enter Enrollment ID",Toast.LENGTH_SHORT).show();
						}
						
					}
					
				}
				
			}
		
			
			

	
		}

		if (v.getId() == R.id.Reset) // check for Reset button click event.
		{
			EnrollmentID.setText("");// Reset EnrollmentID.
			ServerIP.setText("");// Reset ServerIP.

		}

	}

	
	 @Override
	    protected Dialog onCreateDialog(int id)
	 {
	        switch (id) {
			case DIALOG_LOADING_PROGRESS:
				progress_dialog = new ProgressDialog(this);// initailize the object of ProgressDialog(context);
				progress_dialog.setMessage("Loading.......");//set the message while showing dialog.
				progress_dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);//Creates a ProgressDialog with a circular, spinning progress bar.
				progress_dialog.setIndeterminate(true);// to show indeterminate line as progress bar.
				progress_dialog.setCancelable(false);//Sets whether this dialog is cancelable with the BACK key.
				progress_dialog.show();//show the dialog.
				return progress_dialog;//return ProgressDialog.
			default:
				return null;
	        }
	    }
	
	
	
	// class defined to execute Asynchronous task in background.
	
	class Network_OperationAsync extends AsyncTask<String, String, String> {
		
		 //override method of AsyncTask,invoked on the UI thread immediately after the task is executed. 
		@Override
		protected void onPreExecute() 
		{
			super.onPreExecute();
			showDialog(DIALOG_LOADING_PROGRESS);//call onCreateDialog(int) method to open ProgressDialog.

		}
		
		//override method of AsyncTask, invoked on the background thread immediately after onPreExecute() finishes executing.

		@Override
		protected String doInBackground(String... aurl)
		{
			
		//	publishProgress(ipaddress);// call to onProgressUpdate() method.
			flag=1;
		    
			 con = new HttpConnection();// initialize HttpConnection class object.
			
			response = con.getConnection(server_ip, enrollment_id, macaddress,ipaddress, flag);// call the getConnection() method of HttpConnection class and get response.
		//	response = con.getConnection(server_ip, enrollment_id, macaddress, flag);// call the getConnection() method of HttpConnection class and get response.
			publishProgress(response);//call onProgressUpdate() method.
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
			dismissDialog(DIALOG_LOADING_PROGRESS);// dismiss dialog.
		}
	}
	
	
	

	
//This method check for the data collected from getData() method of HttpConnection class.
	public void callActivity(String data)
	{
	
		
		if (data.equals("QuizPage")) //check for the data if it is QuizPage.
		{
			//storing macaddress,server_ip,enrollment_id in shared preferences.
			SharedPreferenceConnector.writeString(RegistrationActivtiy.this, SharedPreferenceConnector.MACADDRESS,macaddress);
			SharedPreferenceConnector.writeString(RegistrationActivtiy.this, SharedPreferenceConnector.SERVER_IP,server_ip);
			SharedPreferenceConnector.writeString(RegistrationActivtiy.this, SharedPreferenceConnector.ENROLLMENT_ID,enrollment_id);	
			
			
			Intent i = new Intent();
			
			i.setClass(getApplicationContext(),	QuizPageActivity.class);// Initialize intent for QuizPageActivity.
		
			b.putString("MacAddress", macaddress);//storing macaddress in bundle.
			b.putString("ServerIP", server_ip);//storing server_ip in bundle.
		
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
		
		String macadd = wifiInf.getMacAddress();// get the wi-fi macaddress of the android tablet. 
		
		if (macadd == null) // check for macaddress
		{
			//macadd = "No MACADDRESS";
			macadd="00:92:c3:d7:4d:3e";
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
