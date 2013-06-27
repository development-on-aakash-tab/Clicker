
 // This activity provides the storage for the Enrollment ID,SERVER_IP,MACADDRESS using shared preferences.
 


package com.iitb.localclicker;//package name of the project.

//List of necessary android classes.

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

//SharedPreferenceConnector begins.

public class SharedPreferenceConnector
{
	public static final String PREF_NAME = "CLICKER_PREFERENCES";//Preferences file name.
	public static final int MODE = Context.MODE_PRIVATE;// mode in which preferences should operate.
	
	public static final String ENROLLMENT_ID = "ENROLLMENT_ID";//Preferences name for ENROLLMENT_ID
	public static final String SERVER_IP = "SERVER_IP";//Preferences name for SERVER_IP
	public static final String MACADDRESS = "MACADDRESS";//Preferences name for MACADDRESS.
	
	
	

	public static void writeInteger(Context context, String key, int value) 
	{
		getEditor(context).putInt(key, value).commit();
	//	store an integer value in the preferences.

	}

	public static int readInteger(Context context, String key, int defValue)
	{
		return getPreferences(context).getInt(key, defValue);
   //	Retrieve an integer value from the preferences.

	}
	
	

	public static void writeString(Context context, String key, String value) 
	{
		getEditor(context).putString(key, value).commit();
   //	store an integer value in the preferences.
	}
	
	public static String readString(Context context, String key, String defValue)
	{
		return getPreferences(context).getString(key, defValue);
		   //	Retrieve an string value from the preferences.
	}
	
	
     
	public static SharedPreferences getPreferences(Context context) {
		return context.getSharedPreferences(PREF_NAME, MODE);
	//	Retrieve and hold the contents of the preferences file 'PREF_NAME', returning a SharedPreferences through which you can retrieve and modify its values.
		//MODE defines operation mode.
	}

	public static Editor getEditor(Context context) {
		return getPreferences(context).edit();
		//Create a new Editor for these preferences, through which you can make modifications to the data in the preferences. 

	}

}
