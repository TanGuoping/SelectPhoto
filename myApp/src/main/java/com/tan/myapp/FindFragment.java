package com.tan.myapp;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

public class FindFragment extends Fragment {
	private ImageView back;
	private TextView title;
	private WebView webview;
    @Override  
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  
            Bundle savedInstanceState)  
    {  
        View view=inflater.inflate(R.layout.fragment2, container, false);  
        
        back = (ImageView)view.findViewById(R.id.imageViewId);
        back.setVisibility(View.GONE);
        
        title = (TextView)view.findViewById(R.id.titleName);
        title.setText("·¢ÏÖ");
        title.setTextSize(20);
        
        webview=(WebView)view.findViewById(R.id.webView);
        webview.setWebViewClient(new WebViewClient());
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        webview.loadUrl("http://www.chexiang.com/cxb/infos/saycars/index.shtml");
        
        
        
        
        
        return view;
    }
    

}
