package com.tan.myapp;

import java.util.ArrayList;
import java.util.List;


import com.tan.news.NewsActivity;
import com.tan.news.NewsAdapter;

import com.tan.news.News;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainFragment extends Fragment implements OnItemClickListener {
	
	private TextView title;
	private ListView list;
	private ImageView back;

	private List<News> l=new ArrayList<News>();
	
	
	
	
	
	
    @Override
	public void onAttach(Context context) {
		// TODO Auto-generated method stub
		super.onAttach(context);
		initNews();
	}


	@Override  
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState)  
    
    {  
        View view = inflater.inflate(R.layout.fragment1, container, false);  
        
        
        title=(TextView)view.findViewById(R.id.titleName);
        title.setText("Ð¡Å£²¥±¨");
        
        
        back = (ImageView)view.findViewById(R.id.imageViewId);
		back.setVisibility(View.GONE);
        
        
        
        list=(ListView)view.findViewById(R.id.list);
        list.setAdapter(new NewsAdapter(this.getActivity(),R.layout.news,l));
        list.setOnItemClickListener(this);
      
        
        
        
        
        return view;
    }
    
    
    public void initNews(){
    	News news1 = new News();
    	news1.setTitle(getString(R.string.news1_title));
    	news1.setContent(getString(R.string.news1_content));
    	l.add(news1);
    	
    	News news2 = new News();
    	news2.setTitle(getString(R.string.news2_title));
    	news2.setContent(getString(R.string.news2_content));
    	l.add(news2);
    	
    	News news3 = new News();
    	news3.setTitle(getString(R.string.news3_title));
    	news3.setContent(getString(R.string.news3_content));
    	l.add(news3);
    	
    	
    	News news4 = new News();
    	news4.setTitle(getString(R.string.news4_title));
    	news4.setContent(getString(R.string.news4_content));
    	l.add(news4);
    	
    	
    	News news5 = new News();
    	news5.setTitle(getString(R.string.news5_title));
    	news5.setContent(getString(R.string.news5_content));
    	l.add(news5);
    	
    	News news6 = new News();
    	news6.setTitle(getString(R.string.news6_title));
    	news6.setContent(getString(R.string.news6_content));
    	l.add(news6);
    	
    	
    	News news7 = new News();
    	news7.setTitle(getString(R.string.news7_title));
    	news7.setContent(getString(R.string.news7_content));
    	l.add(news7);
    	
    	News news8 = new News();
    	news8.setTitle(getString(R.string.news8_title));
    	news8.setContent(getString(R.string.news8_content));
    	l.add(news8);
    	
    	News news9 = new News();
    	news9.setTitle(getString(R.string.news9_title));
    	news9.setContent(getString(R.string.news9_content));
    	l.add(news9);
    	
    	News news10 = new News();
    	news10.setTitle(getString(R.string.news10_title));
    	news10.setContent(getString(R.string.news10_content));
    	l.add(news10);
    	
    	News news11 = new News();
    	news11.setTitle(getString(R.string.news11_title));
    	news11.setContent(getString(R.string.news11_content));
    	l.add(news11);
    	
    	News news12 = new News();
    	news12.setTitle(getString(R.string.news12_title));
    	news12.setContent(getString(R.string.news12_content));
    	l.add(news12);
    	
    	News news13 = new News();
    	news13.setTitle(getString(R.string.news13_title));
    	news13.setContent(getString(R.string.news13_content));
    	l.add(news13);
    	
    	
    	News news14 = new News();
    	news14.setTitle(getString(R.string.news14_title));
    	news14.setContent(getString(R.string.news14_content));
    	l.add(news14);
    }

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		
		NewsActivity.actionStart(getActivity(), l.get(position).getTitle(), l.get(position).getContent());
		
	} 

}
