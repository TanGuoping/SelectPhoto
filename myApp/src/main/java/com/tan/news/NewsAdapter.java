package com.tan.news;

import java.util.List;

import com.tan.myapp.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsAdapter extends ArrayAdapter<News> {
	
	private int resourceId;

	public NewsAdapter(Context context, int resource, List<News> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		resourceId = resource;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		News news = getItem(position);
		
		View view;
		
		if(convertView == null){
			view = LayoutInflater.from(getContext()).inflate(resourceId, null);
		}
		
		else{
			view = convertView;
		}
		
		ImageView label = (ImageView)view.findViewById(R.id.label);
		label.setImageResource(news.getLabel());
		
		TextView content = (TextView)view.findViewById(R.id.content);
		content.setText(news.getTitle());
		
		return view;
	}
	
	

}
