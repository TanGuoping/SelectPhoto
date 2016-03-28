package com.tan.setting;

import com.tan.base.BaseActivity;
import com.tan.myapp.R;
import com.tan.myapp.R.id;
import com.tan.myapp.R.layout;

import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class Test extends BaseActivity {
	
	private TextView titleName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.test);
		
		titleName = (TextView)this.findViewById(R.id.titleName);
		titleName.setText("ÎÒµÄ¶©µ¥");
	}

}
