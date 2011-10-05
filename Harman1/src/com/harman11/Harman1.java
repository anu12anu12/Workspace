package com.harman11;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Harman1 extends Activity implements OnClickListener {
    /** Called sample when the activity is first created. */
	IRemoteService mService;
	private static final String DATABASE_NAME = "harman.db";
	
	 private static final int DATABASE_VERSION = 1;
	 private static final String HARMAN_TABLE_NAME = "marathalli";
	 public static final String AUTHORITY = "com.harmanhero.HarmanProvider";
	 public static final Uri CONTENT_URI = Uri.parse("content://"
			                 + AUTHORITY + "/" + DATABASE_NAME);

     EditText edit1;
     EditText edit2;
	
	IRemoteService mIRemoteService;
	private ServiceConnection mConnection = new ServiceConnection() {
	    // Called when the connection with the service is established
	    public void onServiceConnected(ComponentName className, IBinder service) {
	        // Following the example above for an AIDL interface,
	        // this gets an instance of the IRemoteInterface, which we can use to call on the service
	        mIRemoteService = IRemoteService.Stub.asInterface(service);
	        Log.d("Dikshant", "mIRemoteService = " + mIRemoteService);
	    }

	    // Called when the connection with the service disconnects unexpectedly
	    public void onServiceDisconnected(ComponentName className) {
	       
	        mIRemoteService = null;
	    }
	};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        bindService(new Intent("android.intent.action.harmanservice"),
                mConnection, Context.BIND_AUTO_CREATE);
        
        
    edit1 = (EditText) findViewById(R.id.edit1);
        
      edit2 = (EditText) findViewById(R.id.edit2);
        
         Button  button1= (Button) findViewById(R.id.button1);
        
         Button  button2= (Button) findViewById(R.id.button2);
         
         button1.setOnClickListener(this);
         button2.setOnClickListener(this);
        
    }

	@Override
	public void onClick(View aView) {
		switch (aView.getId())
		{
			case R.id.button1:
			{
				int addition=0;	
				int num1=0;
				int num2=0;
				try{
					num1=Integer.parseInt(edit1.getText().toString());
					num2=Integer.parseInt(edit2.getText().toString());
				}catch(NumberFormatException e){
					Toast.makeText(Harman1.this,"enter some no",1).show();
				}
				
			try {
				
			 addition=	mIRemoteService.add(num1, num2);
				
			} 
			catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//	Toast.makeText(Harman1.this, "addition = " + addition , Toast.LENGTH_SHORT).show();
			
			ContentValues values=new ContentValues();
			values.put("noteValue", addition);
			getContentResolver().insert(CONTENT_URI, values);
			
				break;
			}
			case R.id.button2:
			{
				Context lContext = null;
//				Cursor cur=getContentResolver().query(CONTENT_URI,null,null,null,null);
//				try {
//					lContext = createPackageContext("com.harman2",  CONTEXT_IGNORE_SECURITY);
//				} catch (NameNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				Intent lIntent = new Intent( "android.intent.action.HARMAN2");
				this.startActivity(lIntent);
				break;
			}
			default:
				break;

		}
		
		
	}
}
