package com.harman11;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


public class HarmanService extends Service{

	private final IRemoteService.Stub mBinder = new IRemoteService.Stub() {
	    public int add(int a,int b){
	    //Added to test git
	        return a+b;
	    }
	   
	};
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return mBinder;
	}
	
	
	
	
	
	
}