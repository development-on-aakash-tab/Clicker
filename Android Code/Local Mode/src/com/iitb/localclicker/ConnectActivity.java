
// This activity opens connect screen when user clicks on CLICKER Icon for the second time.

package com.iitb.localclicker;//package name of the project.

//List of necessary android classes.



import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//ConnectActivity class begins.
public class ConnectActivity extends Activity implements android.view.View.OnClickListener 
{
	public static final int OPEN_SETTINGS_REQUEST = 1;
	private SharedPreferences sharedpreferences;
	int resultcode,id;
	String serverip_new,macaddress,serverip_old,response,data,ipaddress,enrollment_id;
	int flag;
	Button Connect,Settings;
	private ProgressDialog progress_dialog;
	public static final int DIALOG_LOADING_PROGRESS = 0;
	WifiManager wifiMan;
	WifiInfo wifiInf;
	Bundle b = new Bundle();// Initialize bundle.
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);// Always call the superclass method first.
		setContentView(R.layout.connect_server);//Refer to connect_server.xml
		
		PreferenceManager.setDefaultValues(this, R.layout.settings, false);//Load the default values of settings preferences screen.
	
		sharedpreferences = PreferenceManager.getDefaultSharedPreferences(this);//get the default values from PreferenceManager.
	
		macaddress=SharedPreferenceConnector.readString(this,SharedPreferenceConnector.MACADDRESS, null);//read the  macaddress from shared preferences.
   
		Connect=(Button)findViewById(R.id.Connect2);//Refer to Connect button having Connect2 id in connect_server.xml .
	
		 Connect.setOnClickListener(this);//add listener to Connect button.
		 
		 wifiMan = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);// class to manage the WI-FI connection.
	     wifiInf = wifiMan.getConnectionInfo();//class to handle the connection information of WI-FI.
	 
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
	
     
	    
	    //Method to handle button click events.
	@Override
	public void onClick(View v) 
	
	{
		
		if(v.getId()==R.id.Connect2)// check for Connect button click.
		{
			
			enrollment_id=SharedPreferenceConnector.readString(ConnectActivity.this, SharedPreferenceConnector.ENROLLMENT_ID,null);	//read Enrollment_ID from shared preferences.
			serverip_old=SharedPreferenceConnector.readString(this,SharedPreferenceConnector.SERVER_IP, null);//read SERVER_IP from shared preferences.
			new Network_OperationAsync().execute();// execute Asynchronous task in background process.
			
		
			
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
	
	
	// This class performs asynchronous operation for network connection.
	
	 class Network_OperationAsync extends AsyncTask<String,String, String>
	 {
		
		  //override method of AsyncTask,invoked on the UI thread immediately after the task is executed. 
			@Override
			protected void onPreExecute() 
			{
				super.onPreExecute();// Always call the superclass method first.
				showDialog(DIALOG_LOADING_PROGRESS);//call onCreateDialog(int) method to open ProgressDialog.

			}
			 //override method of AsyncTask, invoked on the background thread immediately after onPreExecute() finishes executing.
			@Override
			protected String doInBackground(String... aurl)
			{
				flag=3;
				HttpConnection con = new HttpConnection();// initialize HttpConnection object.
				response = con.getConnection(serverip_old,enrollment_id, macaddress,ipaddress, flag);// call getConnection() method of HttpConnection class and getting response.
				//response = con.getConnection(serverip_old,0, macaddress, flag);// call getConnection() method of HttpConnection class and getting response.
				publishProgress(response);//call onProgressUpdate() method.
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
				dismissDialog(DIALOG_LOADING_PROGRESS);// dismiss dialog.
				
			}
		}
		
		//This method check for the data collected from getData() method of HttpConnection class.
	 
	 public void callActivity(String data)
		{
	
			Log.d("data", data);
			if (data.equals("Connect")) // check for data if it is Connect.
			{
				
				
				Intent i = new Intent();//initialize intent.
				i.setClass(getApplicationContext(), QuizPageActivity.class);//set QuizPageActivity to call from intent.
				b.putString("MacAddress", macaddress);//put macaddress value in mapping of this bundle.
				b.putString("ServerIP",serverip_old);//put serverip_old value in mapping of this bundle.
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
				
			
			
		}
		}
	
	

		
		
	}
	
	
	
	//Method perform the task to collect the IP Address from android tablet.
			private String getIPAddress() {
				
				int ipAddress = wifiInf.getIpAddress();// get the IP address of the android tablet. 
				
				ipaddress=String.format("%d.%d.%d.%d", 
						(ipAddress & 0xff), 
						(ipAddress >> 8 & 0xff), 
						(ipAddress >> 16 & 0xff),
						(ipAddress >> 24 & 0xff));
				
				
				if (ipaddress == null) // check for IP address
				{
					ipaddress = "No IP ADDRESS";
				}

				return ipaddress;// return IP address.
			}
	
	
}
//ConnectActivity class ends.
