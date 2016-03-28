package com.tan.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class FragmentForActivity extends FragmentActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ActivityCollector.addActivity(this);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		ActivityCollector.removeActivity(this);
	}
	

}
