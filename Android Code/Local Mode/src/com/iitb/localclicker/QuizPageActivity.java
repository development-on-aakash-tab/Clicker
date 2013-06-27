
 // This activity opens the browser to show quiz page.
 


package com.iitb.localclicker;//package name of the project.

//List of necessary android classes.
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.KeyEvent;


public class QuizPageActivity extends Activity 
{
   PowerManager powerManager;
   WakeLock wakeLock;
	
	Bundle b;
	String macaddress, server_ip;
	private HttpClient client;
	private HttpPost httppost;
	List<NameValuePair> nameValuePairs;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle instances)
	{
		
		super.onCreate(instances);// Always call the superclass method first.
	

		PowerManager powerManager =	(PowerManager)getSystemService(Context.POWER_SERVICE);// To control power management of device.
		wakeLock = powerManager.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "MyLock");//wake lock ensures that the screen is on but may be dimmed.
	    wakeLock.acquire();//Makes sure the device is on at the level you asked when you created the wake lock.
		
		
		b = this.getIntent().getExtras(); // extract values from bundle sent by intent.

		macaddress = b.getString("MacAddress");//get string value of MacAddress from bundle.
		//macaddress = "SlFISktKTlRKU0xMSFRJfU1NTlROfk1RSVRLTlF+SFRMTVB/UA==";
		server_ip = b.getString("ServerIP");//get string value of ServerIP from bundle.
		
		Uri uri=null;
		client = new DefaultHttpClient();
		uri = Uri.parse("http://" + server_ip + ":8080/AakashClickerV3/DemoStudentLogin?MacAddress=" +  URLEncoder.encode(macaddress));
		//uri = Uri.parse("http://" + server_ip + ":8080/AakashClickerV3/DemoStudentLogin?MacAddress=" + URLEncoder.encode(macaddress,"ISO-8859-1"));
		/*String url = "http://" + server_ip + ":8080/AakashClickerV3/DemoStudentLogin";
		httppost = new HttpPost(url);
		nameValuePairs = new ArrayList<NameValuePair>(1);// initialize object of NameValuePairs.
		nameValuePairs.add(new BasicNameValuePair("MacAddress", macaddress));
		try {
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			client.execute(httppost);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		//System.out.println(uri);
		
		
		
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);//initialize intent object and invoke Activity action to display data to user.
	     
		
		startActivity(intent);//start activity.

		Intent in = new Intent();// Initialize intent.
		setResult(1, in);//set result for Registration activity / Connect Activity.
		finish();// finish/exit the QuizPageActivity.

	}
	
	
	
	 public boolean onKeyDown(int keyCode, KeyEvent event) //Called when a Hardware Key was pressed down.
     {
         if(keyCode == KeyEvent.KEYCODE_BACK)//Check for the Back Button key pressed.
         {
        	 wakeLock.release();//Release your claim to the CPU or screen being on.
        	
             return true;// Return true.
         }
         return false;// Return false.
     }

	

	

}
//QuizPageActivity class ends.