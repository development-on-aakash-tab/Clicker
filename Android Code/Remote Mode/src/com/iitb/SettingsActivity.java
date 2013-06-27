/*
 This activity opens the Settings preferences screen for changing Server IP address.User can change the ServerIP address.
 */

//package name of the project.
package com.iitb;

//Importing android packages.
import java.util.regex.Pattern;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.widget.Toast;

//SettingsActivity class begins.
public class SettingsActivity extends PreferenceActivity implements OnSharedPreferenceChangeListener 
		{
	String serverip_old,response,macaddress,centre;
	// Called when the activity is first created. 
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);// Always call the superclass method first.
	
		addPreferencesFromResource(R.xml.settings);// Refer to settings.xml file.
		EditTextPreference server_ip = (EditTextPreference)findPreference("Server IP");//Refer to EditTextPreference having key Server IP.
     // ListPreference centre = (ListPreference)findPreference("Centre");//Refer to EditTextPreference having key Server IP.
		 server_ip.setSummary(SharedPreferenceConnector.readString(this,SharedPreferenceConnector.SERVER_IP, null));//Read string Server_IP from shared preferences and set summary to preferences screen.
		 server_ip.setText(SharedPreferenceConnector.readString(this,SharedPreferenceConnector.SERVER_IP, null));//Read string Server_IP from shared preferences and set text to preferences screen.
			
		 //centre.setSummary(SharedPreferenceConnector.readString(this,SharedPreferenceConnector.CENTRE, null));//Read string CENTRE from shared preferences and set summary to preferences screen.
		
				
	}

	   //override method of Activity
	//when user returns from paused state , it calls onResume() method.
	@Override
	protected void onResume() 
	{
		super.onResume();// Always call the superclass method first.
		
		getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this); // Set up a listener whenever a key changes.
	}

	 //override method of Activity
	//when activity goes to paused state, it calls onPause() method.
	@Override
	protected void onPause() 
	{
		super.onPause();// Always call the superclass method first.
		
		getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);// Unregister the listener whenever a key changes.
	}

	
	//Called when a shared preference is changed, added, or removed.
	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,String key)
	{
		//Regular expression for checking validity of IP address.
		final String IPV4_REGEX = "(([0-1]?[0-9]{1,2}\\.)|(2[0-4][0-9]\\.)|(25[0-5]\\.)){3}(([0-1]?[0-9]{1,2})|(2[0-4][0-9])|(25[0-5]))";
		   Pattern IPV4_PATTERN = Pattern.compile(IPV4_REGEX);
		  
		   if(key.equals("Server IP"))// check for key having value Server IP.
		   {
		  boolean check_validity = IPV4_PATTERN.matcher(sharedPreferences.getString("Server IP", null)).matches();
		  
		  if(check_validity)
		  {
			  initSummary(findPreference(key));//call initSummary method.
			
		  }
		  
		  else
		  {
              // Show message if invalid IP address.
			  Toast.makeText(getApplicationContext(),"INVALID IP ADDRESS,Please check Help menu",Toast.LENGTH_SHORT).show();
			  
		  }
		  
	}
		   /*
		   if(key.equals("Centre"))// check for key having value Centre.
		   {
			   ListPreference centre=(ListPreference)findPreference(key); 
			   findPreference(key).setSummary(centre.getValue());
		   }
		   */
		   
	}

	//Method to set current status of Preference in preference summary.
	private void initSummary(Preference p)
	{
		EditTextPreference editTextPref = (EditTextPreference) p;//type cast preference to EditTextPreference.
		   		
			p.setSummary(editTextPref.getText());// set current status of Preference in preference summary.

	}

}
//SettingsActivity class ends.







