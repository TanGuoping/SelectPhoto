package com.tan.myapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.tan.base.BaseActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends BaseActivity implements OnClickListener {
	
	
	private EditText user;
	private EditText pwd;
	private Button login;
	
	private SharedPreferences sp;
	private SharedPreferences.Editor editor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.setContentView(R.layout.login);
		
		
		user = (EditText) findViewById(R.id.userId);
		pwd = (EditText) findViewById(R.id.pwdId);
		login = (Button) findViewById(R.id.btnId);
		
		login.setOnClickListener(this);
		sp = this.getSharedPreferences("account",MODE_PRIVATE);
		editor = sp.edit();
		
		
		String username = sp.getString("username", "");
		String password = sp.getString("password", "");
		
		if(username != null && password != null){
			user.setText(username);
			pwd.setText(password);
		}
	
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		String username = user.getText().toString();
		String password = pwd.getText().toString();
		System.out.println(username.equals("admin"));
		System.out.println(password == "1234");
		
		if(username.equals("admin")  && password.equals("1234")){
			
			
			editor.putString("username", username);
			editor.putString("password", password);
			
			editor.commit();
			
			
			Intent intent = new Intent(this,MainActivity.class);
			Log.d("pass","pass");
			startActivity(intent);
			finish();
		}
		
		else{
			
			editor.clear().commit();
			Toast.makeText(this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
		}
		
	}
	
	
	public void save(String content) throws FileNotFoundException{
		
		FileOutputStream out = openFileOutput("name", MODE_PRIVATE);
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
		
		try {
			
			writer.write(content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  finally{
			if(writer != null){
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
	}
	
	
	
	public String load() throws FileNotFoundException{
		FileInputStream input = this.openFileInput("name");
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		StringBuffer buffer = new StringBuffer();
		String line = null;
		try {
			while((line = reader.readLine()) != null){
				buffer.append(line+"\n");
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  finally {
			if(reader != null){
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return buffer.toString();
		
	}

}
