package com.tan.myapp;


import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.ViewGroup;


public class MainAdapter extends FragmentPagerAdapter {

	
	private List<Fragment> list;
	


	public MainAdapter(FragmentManager fm, List<Fragment> list){
		super(fm);
		this.list = list;
		

	
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		
		Log.d("tan","getItem"+arg0);
		
		
		return list.get(arg0);
	}
	


	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}
	
	

}
