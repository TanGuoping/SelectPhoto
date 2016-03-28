package com.tan.setting;

import java.util.ArrayList;
import java.util.List;

import com.tan.base.ActivityCollector;
import com.tan.myapp.Login;
import com.tan.myapp.R;
import com.tan.myapp.R.drawable;
import com.tan.myapp.R.id;
import com.tan.myapp.R.layout;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SettingFragment extends Fragment {

	private ListView list;
	private AlertDialog dialog;
	
	private List<SettingItem> data = new ArrayList<SettingItem>();
	
	
	
 


	@Override
	public void onAttach(Context context) {
		// TODO Auto-generated method stub
		super.onAttach(context);
		initSetting();
		
		Log.d("SettingFragment","onAttach");
	}


	@Override  
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState)  
    {  

        View view=inflater.inflate(R.layout.fragment3, container, false); 
        
        
        AlertDialog.Builder builder =  new AlertDialog.Builder(getActivity());
       // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
       // builder.setMessage("��ȷ��Ҫ�˳���ǰ�˺���");
        
        /*
        TextView title = new TextView(getActivity());
        title.setText("ȷ���˳�");
        title.setGravity(Gravity.CENTER);
        title.setTextSize(20);
        
        builder.setCustomTitle(title);
        */
        
        //builder.setTitle("ȷ���˳�");
        builder.setView(R.layout.dialog);
        builder.setCancelable(false);
        
        
 
        builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				ActivityCollector.finishAll();
				SettingFragment.this.getActivity().getSharedPreferences("account", 0).edit().clear().commit();
				Intent intent = new Intent(SettingFragment.this.getActivity(),Login.class);
				startActivity(intent);
			}
		});
    
        
        builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});
		
        
        dialog = builder.create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        //String[] data={"��ϵ�ͷ�","�˳���¼","Test Title"};
        
        list = (ListView)view.findViewById(R.id.listViewId);
        
        
        
        list.setAdapter(new SettingItemAdapter(getActivity(),R.layout.item,data));
        
        Log.d("fragment", "settingF");
        
        
        
        //list.setAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, data));
        
        
        
        list.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			
			
				
				
				if(data.get(position).getName().contains("�ͷ�")){
					
					Intent intent = new Intent(Intent.ACTION_DIAL);
					intent.setData(Uri.parse("tel:10086"));
					startActivity(intent);
					
				}
				
				
				if(data.get(position).getName().contains("�˳�")){
					
					dialog.show();
				}
				
				if(data.get(position).getName().contains("����")){
					
					Intent intent = new Intent(getActivity(),Test.class);
					startActivity(intent);
					
				}
				
				
				
		
			}
        	
        });
        
        
        
        
        Log.d("SettingFragment","onCreateView");
        
        return view;
    } 
    
    
    public void initSetting(){
    	SettingItem right = new SettingItem("�ҵ�Ȩ��",R.drawable.my_center_rights);
    	data.add(right);
    	
    	SettingItem cxh = new SettingItem("����㶩��",R.drawable.my_center_order_cxh);
    	data.add(cxh);
    	
    	SettingItem cxj = new SettingItem("����Ҷ���",R.drawable.my_center_order_cxj);
    	data.add(cxj);
    	
    	SettingItem gc = new SettingItem("��������",R.drawable.my_center_order_gc);
    	data.add(gc);
    	
    	SettingItem mc = new SettingItem("�˳���¼",R.drawable.my_center_order_mc);
    	data.add(mc);
    	
    	SettingItem wzdj = new SettingItem("Υ�´��ɶ���",R.drawable.my_center_order_wzdj);
    	data.add(wzdj);
    	
    	SettingItem pay = new SettingItem("����",R.drawable.my_center_pay);
    	data.add(pay);
    	
    	SettingItem money = new SettingItem("�����",R.drawable.my_center_money);
    	data.add(money);
    	
    	SettingItem coupon = new SettingItem("����ȯ",R.drawable.my_center_giccoupon);
    	data.add(coupon);
    	
    	SettingItem obd = new SettingItem("������",R.drawable.my_center_obdbox);
    	data.add(obd);
    	
    	SettingItem kf = new SettingItem("��ϵ�ͷ�",R.drawable.my_center_customeservice);
    	data.add(kf);
    	
    	SettingItem share = new SettingItem("���������",R.drawable.my_center_share);
    	data.add(share);
    	
    	SettingItem setting = new SettingItem("����",R.drawable.my_center_settings);
    	data.add(setting);
    	
    }
    
    
    
    
    
    
}
    
    
