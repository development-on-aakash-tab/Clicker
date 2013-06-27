/*
 This activity opens the browser to show quiz page.
 */

//package name of the project.
package com.iitb;

//Importing android packages.


import java.net.URLEncoder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.KeyEvent;



//QuizPageActivity class begins.
public class QuizPageActivity extends Activity 
{
   PowerManager powerManager;
   WakeLock wakeLock;
	
	Bundle b;
	Uri uri;
	String macaddress, server_ip,centre;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle instances)
	{
		
		super.onCreate(instances);// Always call the superclass method first.
		

		PowerManager powerManager =	(PowerManager)getSystemService(Context.POWER_SERVICE);// To control power management of device.
		wakeLock = powerManager.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "MyLock");//wake lock ensures that the screen is on but may be dimmed.
	    wakeLock.acquire();//Makes sure the device is on at the level you asked when you created the wake lock.
		
		
		b = this.getIntent().getExtras(); // extract values from bundle sent by intent.
        centre=b.getString("Centre");//get string value of Centre from bundle.
		macaddress = b.getString("MacAddress");//get string value of MacAddress from bundle.
		server_ip = b.getString("ServerIP");//get string value of ServerIP from bundle.
		
		if(centre.equals("Local"))
		{
			// uri = Uri.parse("http://" + server_ip + ":8080/AakashClickerV3/DemoStudentLogin?MacAddress=" + URLEncoder.encode(macaddress));//URL to access quiz page.
			uri = Uri.parse("http://" + server_ip + ":8080/RemoteAakashClicker/StudentLogin?MacAddress=" + URLEncoder.encode(macaddress));//URL to access quiz page.
		}
		if(centre.equals("Remote"))
		{
               uri = Uri.parse("http://" + server_ip + ":8080/RemoteAakashClicker/AndroidParticipantLogin?MacAddress=" + URLEncoder.encode(macaddress));//URL to access quiz page.
		}
		
		
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