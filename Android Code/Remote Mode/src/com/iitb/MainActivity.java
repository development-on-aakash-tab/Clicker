/* 
 This is the main activity of the project which starts, when Clicker Icon is clicked.
 It opens Registration page when the user login for the first time.
 When user click on Clicker Icon  for the second time then it opens Connect Screen.
  */


package com.iitb; // package name of the project.

//Importing android packages.
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

//Main Activity starts here.
public class MainActivity extends Activity 
{
	int resultcode,flag; 
    String macaddress,response,data,server_ip,centre ,enrollment_id,ipaddress;
    private ProgressDialog mProgressDialog;
    public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
    HttpConnection con;
    WifiManager wifiMan;
	WifiInfo wifiInf;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)  
	{
		
		
		super.onCreate(savedInstanceState);// Always call the superclass method first.
	
		ConnectivityManager connMgr = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE); // Class that answers queries about the state of network connectivity. 
        
     
        State wifi = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();//Reports the current coarse-grained state of the network i.e. WI-FI.

        if (!(wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING)) // Check for WI-FI connection
        {
        	// Show alert dialog for Wi-Fi connectivity.
        	
        	AlertDialog.Builder builder = new AlertDialog.Builder(this);
        	builder.setIcon(R.drawable.alert)
        	.setTitle("Wi-Fi Disabled !!!!")
        	.setMessage("Please Check Wi-Fi Settings"+"\n"+"Settings->Wireless & networks->Wi-Fi")
        	
        	       .setCancelable(false)
        	       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
        	           public void onClick(DialogInterface dialog, int id) {
        	        	
        	        	   MainActivity.this.finish();
        	        	
        	           }
        	       });
        	AlertDialog alert = builder.create();   
        	alert.show();
        	
        }
		
        else
        {
         wifiMan = (WifiManager) this.getSystemService(Context.WIFI_SERVICE);// class to manage the WI-FI connection.
   	     wifiInf = wifiMan.getConnectionInfo();//class to handle the connection information of WI-FI.
   	     ipaddress=getIPAddress();//get IPaddress of the android tablet.
        // Read macaddress from shared preferences .
		macaddress=SharedPreferenceConnector.readString(this,SharedPreferenceConnector.MACADDRESS, null);
	
		if(macaddress==null) // check for macaddress
		{
			
		
			
			Intent i = new Intent(MainActivity.this,RegistrationActivtiy.class); // Initialize intent for Registration Activity.
	
			startActivityForResult(i, resultcode);// start Registration Activity and getting result  from activity
			
			Intent in = new Intent(); 
			setResult(1, in);
		}
		else
		{
			
			server_ip=SharedPreferenceConnector.readString(this,SharedPreferenceConnector.SERVER_IP, null);// Read Server IP address from shared preferences.
			enrollment_id=SharedPreferenceConnector.readString(this,SharedPreferenceConnector.ENROLLMENT_ID, null);// Read Enrollment ID from shared prefernces.
			centre=SharedPreferenceConnector.readString(this,SharedPreferenceConnector.CENTRE, "centre");// Read Centre name from shared prefernces. 
			new DownloadFileAsync().execute(); // call Asynchronous task to execute in back ground.
		
		}
		
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
	
	
	
	
	// class defined to execute Asynchronous task in back ground.
	 class DownloadFileAsync extends AsyncTask<String, String, String> {
		   
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
			    
				 con = new HttpConnection(); // defined object for HttpConnection class.
				
				response = con.getConnection(server_ip, enrollment_id, macaddress,ipaddress, flag,centre); // call the getConnection() method of HttpConnection class and response is collected.
		
				
				if(response.equals("HTTP/1.1 200 OK"))// check for the response if it is  OK.
				{
					
					data=con.getData();// call the getData() method of HttpConnection class and data is collected.
					
					if(data!=null) // check for data
					{
						
					callActivity(data);	// call callActivity(data) method
					
					}
					
				}
				
				if(response.equals("HTTP/1.1 404 Not Found"))// check for the response if it is Not Found.
				{
					String msg="Please Set Correct Server IP Address";
					publishProgress(msg);// calling onProgressUpdate() method. 
				
				}
				
		      
				return null;// return the null value.

			}
			// onProgressUpdate method,invoked on the UI thread after a call to publishProgress(Progress...).
			protected void onProgressUpdate(String... progress)
			{
			
				
				Toast.makeText(MainActivity.this, progress[0],Toast.LENGTH_SHORT).show();// shows message 
             Intent i = new Intent(MainActivity.this,ConnectActivity.class);// Initialize intent  for ConnectActivity
				
				startActivityForResult(i, resultcode);// start Connect Activity and getting result from activity.
				
				Intent in = new Intent();
				setResult(1, in);
			}

			
			
			   //override method of AsyncTask,invoked on the UI thread after the background computation finishes.
			@Override
			protected void onPostExecute(String unused)
			{
				dismissDialog(DIALOG_DOWNLOAD_PROGRESS);// dismiss dialog.
			}
			
		}
	
	
	 //Method to handle result collected from Intent.
	 
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == 1)// check for result code.
		{
			finish();// finish/exit the Main Activity.
		}
	}
	
	
	//This method check for the data collected from getData() method of HttpConnection class.
	public void callActivity(String data)
	{

		
		if (data.equals("Connect")) // check for data if it is Connect
		{
			
			
			Intent i = new Intent(MainActivity.this,ConnectActivity.class);// Initialize the intent for ConnectActivity.
	
			startActivityForResult(i, resultcode);// start ConnectActivity and getting result.
			
			Intent in = new Intent();
			setResult(1, in);
   	
		}

		if (data.equals("Registration")) // check for data if it is Registration
		{
			   Intent i = new Intent(MainActivity.this,RegistrationActivtiy.class);// Initialize intent for Registration Activity.
			
				startActivityForResult(i, resultcode);// start Registration Activity and getting result.
				
				Intent in = new Intent();
				setResult(1, in);
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
			
			
			if (ipaddress.equals("0.0.0.0")) // check for IP address
			{
				ipaddress = "No IP ADDRESS";
			}

			return ipaddress;// return IP address.
		}


}
//Main Activity ends.