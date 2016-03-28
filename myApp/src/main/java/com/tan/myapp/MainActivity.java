package com.tan.myapp;

import java.util.ArrayList;
import java.util.List;

import com.tan.base.FragmentForActivity;
import com.tan.setting.SettingFragment;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction ;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends FragmentForActivity implements OnPageChangeListener,OnClickListener{
	
	private LinearLayout home;
	private LinearLayout find;
	private LinearLayout setting;
	
	private ImageView homeImage;
	private ImageView findImage;
	private ImageView settingImage;
	
	private TextView homeName;
	private TextView findName;
	private TextView settingName;
	
	private MainFragment homeF;
	private FindFragment findF;
	private SettingFragment settingF;
	
	private ViewPager viewPager;
	
	
	private int currentIndex;
	
	private List<Fragment> list = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
       
        setContentView(R.layout.activity_main);
        
        initViewPager();
        
        
        
        home = (LinearLayout)findViewById(R.id.homeId);
        find = (LinearLayout)findViewById(R.id.findId);
        setting = (LinearLayout)findViewById(R.id.settingId);
        
        homeImage = (ImageView)findViewById(R.id.homeImageId);
        findImage = (ImageView)findViewById(R.id.findImageId);
        settingImage = (ImageView)findViewById(R.id.settingImageId);
        
        homeName = (TextView)findViewById(R.id.homeNameId);
        findName = (TextView)findViewById(R.id.findNameId);
        settingName = (TextView)findViewById(R.id.settingNameId);
        
        homeImage.setImageResource(R.drawable.tab_home_press);
        homeName.setTextColor(Color.parseColor("#00BFFF"));
        
        
        currentIndex = 0;
        home.setOnClickListener(this);
        find.setOnClickListener(this);
        setting.setOnClickListener(this);
        
        viewPager.setAdapter(new MainAdapter(this.getSupportFragmentManager(),list));
        viewPager.setCurrentItem(currentIndex);
        viewPager.addOnPageChangeListener(this);
        
        
        
        
        
    }
    
    
    public void initViewPager(){
    	
    	viewPager = (ViewPager)findViewById(R.id.viewPager);
    	
    	homeF = new MainFragment();
    	findF = new FindFragment();
    	settingF = new SettingFragment();
    	
    	list.add(homeF);
    	list.add(findF);
    	list.add(settingF);
    	
    	
    	
    }
    
    
 
    
    
    public void selectTab(int index){
    	
    	if(currentIndex == index){
    		return;
    	}
    	
    	else{
    		switch(index){
    		
    		case 0:
    			findName.setTextColor(Color.parseColor("#000000"));
	        	findImage.setImageResource(R.drawable.tab_find_icon);
	        	
	        	settingName.setTextColor(Color.parseColor("#000000"));
	        	settingImage.setImageResource(R.drawable.tab_my_center_icon);
	        	
	        	homeImage.setImageResource(R.drawable.tab_home_press);
	            homeName.setTextColor(Color.parseColor("#00BFFF"));
	            
	            break;
	            
    		case 1:
    			findName.setTextColor(Color.parseColor("#00BFFF"));
	        	findImage.setImageResource(R.drawable.tab_find_press);
	        	
	        	settingName.setTextColor(Color.parseColor("#000000"));
	        	settingImage.setImageResource(R.drawable.tab_my_center_icon);
	        	
	        	homeImage.setImageResource(R.drawable.tab_home_icon);
	            homeName.setTextColor(Color.parseColor("#000000"));
	            
	            
	            break;
	            
	         
    		case 2:
    			
    			findName.setTextColor(Color.parseColor("#000000"));
	        	findImage.setImageResource(R.drawable.tab_find_icon);
	        	
	        	settingName.setTextColor(Color.parseColor("#00BFFF"));
	        	settingImage.setImageResource(R.drawable.tab_my_center_press);
	        	
	        	homeImage.setImageResource(R.drawable.tab_home_icon);
	            homeName.setTextColor(Color.parseColor("#000000"));
	            
	            break;
    			
    			
    		
    		
    		}
    	}
    	
    	currentIndex = index;
    	
    }
    
    
    
    /*
    
    
    public void switchContent(Fragment to){
    	if(fragment == to){
    		return;
    	}
    	
    	else{
    		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    		if(!to.isAdded()){
    			transaction.hide(fragment).add(R.id.viewPager, to).commit(); 
    		}
    		
    		else{
    			transaction.hide(fragment).show(to).commit();
    		}
    		
    	}
    	
    	fragment = to;
    }

    
    
    /*
     * 
     * 
     * 
     * 
     * 
    public void switchContent(Fragment to) {
                if (mContent != to) {
                        FragmentTransaction transaction = getSupportFragmentManager()
                                        .beginTransaction();
                        if (!to.isAdded()) { // 先判断是否被add过
                                transaction.hide(mContent).add(R.id.content, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
                        } else {
                                transaction.hide(mContent).show(to).commit(); // 隐藏当前的fragment，显示下一个
                        }
                        mContent = to;
                }
                showContent();
        }
    
    class TabListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
	        
	        switch (v.getId())  
	        {  
	        case R.id.homeId: 
	        	
	        	findName.setTextColor(Color.parseColor("#000000"));
	        	findImage.setImageResource(R.drawable.tab_find_icon);
	        	
	        	settingName.setTextColor(Color.parseColor("#000000"));
	        	settingImage.setImageResource(R.drawable.tab_my_center_icon);
	        	
	        	homeImage.setImageResource(R.drawable.tab_home_press);
	            homeName.setTextColor(Color.parseColor("#00BFFF"));
	        	
	        	
	            if (homeF == null)  
	            {  
	            	homeF = new MainFragment();  
	            	Log.d("fragment", "homeF");
	            }  
	            
	           // transaction.replace(R.id.fragmentId, homeF);  
	            switchContent(homeF);
	            break; 
	            
	        case R.id.findId:  
	        	
	        	
	        	findName.setTextColor(Color.parseColor("#00BFFF"));
	        	findImage.setImageResource(R.drawable.tab_find_press);
	        	
	        	settingName.setTextColor(Color.parseColor("#000000"));
	        	settingImage.setImageResource(R.drawable.tab_my_center_icon);
	        	
	        	homeImage.setImageResource(R.drawable.tab_home_icon);
	            homeName.setTextColor(Color.parseColor("#000000"));
	        	
	        	
	            if (findF == null)  
	            {  
	                findF = new FindFragment();  
	            }  
	            
	            //transaction.replace(R.id.fragmentId, findF);  
	            switchContent(findF);
	            break; 
	             
	        case R.id.settingId:  
	        	
	        	findName.setTextColor(Color.parseColor("#000000"));
	        	findImage.setImageResource(R.drawable.tab_find_icon);
	        	
	        	settingName.setTextColor(Color.parseColor("#00BFFF"));
	        	settingImage.setImageResource(R.drawable.tab_my_center_press);
	        	
	        	homeImage.setImageResource(R.drawable.tab_home_icon);
	            homeName.setTextColor(Color.parseColor("#000000"));
	        	
	        	
	        	
	        	
	            if (settingF == null)  
	            {  
	                settingF = new SettingFragment();  
	                
	            }  
	            
	            //transaction.replace(R.id.fragmentId, settingF);  
	            switchContent(settingF);
	            break; 
	            
	            
	        }
			
		}
    	
    }


	*/


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
		
		selectTab(arg0);
		
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch(v.getId()){
		
		case R.id.homeId:
			viewPager.setCurrentItem(0);
			selectTab(0);
			break;
			
		case R.id.findId:
			viewPager.setCurrentItem(1);
			selectTab(1);
			break;
			
		case R.id.settingId:
			viewPager.setCurrentItem(2);
			selectTab(2);
			break;
			
		
		
		
		
		
		
		}
		
	}


}
