package com.tan.news;

import com.tan.myapp.R;



public class News {
	
	private String title;
	private String content;
	
	private static int label = R.drawable.label;
	
	
	public void setTitle(String title){
		this.title =title;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	
	
	public String getTitle(){
		return this.title;
		
	}
	
	public String getContent(){
		return this.content;
	}
	
	public int getLabel(){
		return label;
	}
	

}
