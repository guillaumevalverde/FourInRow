package com.gve;

import org.jivesoftware.smack.packet.Message;

import com.gvestyle.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AcceptActivity extends Activity implements OnClickListener{

	
	private static final String TAG = "AcceptActivity";
	private Button mButtonAccept;
	private Button mButtonDecline;
	private String currentName;
	private int jaune =1;
	private int rouge= -1;
	//c
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acceptactivity);
		
		  Intent intent = this.getIntent();
  		
	        currentName = intent.getStringExtra("currentName");
	       
		mButtonAccept = (Button) findViewById(R.id.buttonAccept);
		mButtonDecline = (Button) findViewById(R.id.buttonDecline);
		mButtonAccept.setOnClickListener(this);
		mButtonDecline.setOnClickListener(this);
		
	}
	
	
	@Override
	public void onClick(View v) {
		if (v== mButtonAccept){
			String text = "<puissance4> ok";
			Message msg = new Message(currentName, Message.Type.chat);
			msg.setBody(text);
			FourInRowActivity.m_connection.sendPacket(msg);
			Intent intent = new Intent(this,PuissanceInternetActivity.class);
			intent.putExtra("color", rouge);
			intent.putExtra("yourTurn", false);
			intent.putExtra("adversaire", currentName);
			Log.v(TAG,"le joueur a accepte l invitation" + currentName + "false rouge");
			startActivity(intent);
			Log.v(TAG,"on ccepte l invitation");
			
		}
		else if(v==mButtonDecline){
			String text = "<puissance4> Non";
			Message msg = new Message(currentName, Message.Type.chat);
			msg.setBody(text);
			FourInRowActivity.m_connection.sendPacket(msg);
			//Intent intent = new Intent(this,FourInRowActivity.class);
			//startActivity(intent);
			finish();
		}
		
		
	}
	
	

}
