package com.tan.setting;

import com.tan.myapp.R;

public class SettingItem {
	private int imageId;
	private String name;
	private static int go = R.drawable.more;
	
	
	public SettingItem(){}
	
	public SettingItem(String name,int imageId){
		this.name=name;
		this.imageId=imageId;
	}
	
	public String getName(){
		return name;
	}
	


	public int getImageId() {
		// TODO Auto-generated method stub
		return imageId;
	}
	
	public static int getGo(){
		return go;
	}

}
