package com.tan.setting;

import java.util.List;

import com.tan.myapp.R;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SettingItemAdapter extends ArrayAdapter<SettingItem> {



	int resourceId;
	

	public SettingItemAdapter(Context context, int resource,
			List<SettingItem> objects) {
		super(context, resource, objects);
		// TODO Auto-generated constructor stub
		this.resourceId = resource;
		
	}
	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		SettingItem item = getItem(position);
		
		View view;
		
		if(convertView == null){
			view = LayoutInflater.from(getContext()).inflate(resourceId, null);
		}
		
		else{
			view = convertView;
		}
	
		ImageView image = (ImageView)view.findViewById(R.id.imageId);
		TextView name = (TextView)view.findViewById(R.id.nameId);
		ImageView go = (ImageView)view.findViewById(R.id.goId);
		image.setImageResource(item.getImageId());
		name.setText(item.getName());
		go.setImageResource(SettingItem.getGo());
		
		return view;
	}


}
