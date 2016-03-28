package com.tan.base;

import com.tan.myapp.R;
import com.tan.myapp.R.id;
import com.tan.myapp.R.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.app.Activity;

public class BasicTitle extends LinearLayout {

	public BasicTitle(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		LayoutInflater.from(context).inflate(R.layout.title,this);
		
		ImageView back = (ImageView)this.findViewById(R.id.imageViewId);
		back.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
			    ((Activity)getContext()).finish();
				
			}
		});
		
	}

}
