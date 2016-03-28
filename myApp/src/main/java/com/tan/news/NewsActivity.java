package com.tan.news;

import com.tan.base.BaseActivity;
import com.tan.myapp.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsActivity extends BaseActivity {
	
	private TextView title;
	private TextView content;
	
	
	
	public static void actionStart(Context context, String t, String c){
		Intent intent = new Intent(context, NewsActivity.class);
		intent.putExtra("title", t);
		intent.putExtra("content",c);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.content);
		
	
		
		title = (TextView)findViewById(R.id.titleName);
		content = (TextView)findViewById(R.id.content);
		
		Intent intent = getIntent();
		title.setText(intent.getStringExtra("title"));
		content.setText(intent.getStringExtra("content"));
		
	}
	
	

}
