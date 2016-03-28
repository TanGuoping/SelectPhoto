package com.tan.myapp;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tan.base.BaseActivity;

public class Splash extends BaseActivity implements OnPageChangeListener,OnClickListener {
	
	
	private Button start;
	private ImageView[] dots;
	private ViewPager viewPager;
	
	private int currentIndex;
	
	
	private List<View> list = new ArrayList<View>();
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		this.setContentView(R.layout.splash);
		
		viewPager = (ViewPager)findViewById(R.id.splashPager);
		start = (Button)findViewById(R.id.start);
		start.setVisibility(View.GONE);
		start.setOnClickListener(this);
		
		initList();
		initDots();
		
		
		
		
		PagerAdapter pagerAdapter = new PagerAdapter(){

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return list.size();
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				return arg0 == arg1;
			}

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				// TODO Auto-generated method stub
				container.removeView(list.get(position));
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				// TODO Auto-generated method stub
				container.addView(list.get(position));
				
				
				return list.get(position);
			}
			
			
			
			
		};
		
		viewPager.setAdapter(pagerAdapter);
		viewPager.addOnPageChangeListener(this);
		
		
		
		
		
		
	}
	
	private void initList(){
		View pager1 = LayoutInflater.from(this).inflate(R.layout.pic, null);
		View pager2 = LayoutInflater.from(this).inflate(R.layout.pic, null);
		View pager3 = LayoutInflater.from(this).inflate(R.layout.pic, null);
		View pager4 = LayoutInflater.from(this).inflate(R.layout.pic, null);
		View pager5 = LayoutInflater.from(this).inflate(R.layout.pic, null);
		
		pager1.setBackground(getBitmapDrawale(R.drawable.splash_bg_01));
		list.add(pager1);
		
		pager2.setBackground(getBitmapDrawale(R.drawable.splash_bg_02));
		list.add(pager2);
		
		pager3.setBackground(getBitmapDrawale(R.drawable.splash_bg_03));
		list.add(pager3);
		
		pager4.setBackground(getBitmapDrawale(R.drawable.splash_bg_04));
		list.add(pager4);
		
		pager5.setBackground(getBitmapDrawale(R.drawable.splash_bg_05));
		list.add(pager5);
	
		
	}
	
	
	public BitmapDrawable getBitmapDrawale(int pic){
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		InputStream is = getResources().openRawResource(pic);
		
		Bitmap bm = BitmapFactory.decodeStream(is, null, opt);
		BitmapDrawable bd = new BitmapDrawable(getResources(), bm);
		
		return bd;
	}
	
	private void initDots(){
		
		LinearLayout ll = (LinearLayout)findViewById(R.id.liearLayout);
		dots = new ImageView[list.size()];
		
		
		for(int i =0; i<list.size();i++){
			
			dots[i] = (ImageView)ll.getChildAt(i);
			
			dots[i].setEnabled(false);
		}
		
		currentIndex = 0;
		dots[currentIndex].setEnabled(true);
		
		
	}
	
	private void setCurrentDot(int index){
		
		
		
		dots[index].setEnabled(true);
		
		dots[currentIndex].setEnabled(false);
		
		currentIndex = index;
		
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int arg0) {
		// TODO Auto-generated method stub
		setCurrentDot(arg0);
		
		if(arg0 == 4){
			start.setVisibility(View.VISIBLE);
		}
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this,Login.class);
		startActivity(intent);
		finish();
		
	}
	
	
	
	

}
