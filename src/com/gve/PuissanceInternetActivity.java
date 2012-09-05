
package com.gve;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.util.StringUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PuissanceInternetActivity extends Activity implements OnClickListener{
	


	//private final static String SERVER_HOST = "talk.google.com";
	//private final static int SERVER_PORT = 5222;
	//private final static String SERVICE_NAME = "gmail.com";	
	//private final static String LOGIN = "guillaume.valverde@gmail.com";
	//private final static String PASSWORD = 
	private static final String TAG = "ConnectGmailActivity";
	private List<String> m_discussionThread;
	private ArrayAdapter<String> m_discussionThreadAdapter;
	//private XMPPConnection m_connection;
	private Handler m_handler;
	
	
	private int joueur;
	private matricePuissance4 puissance4;
	private int scoreJaune =0;
	private int scoreRouge = 0;
	int width =7;
	int height = 6;
	private int jaune  = 1;
	private int rouge = -1;
	private int jouable = 0;
	private int nonjouable = -9;
	private int tour = 1 ;// les jaunes commencent 
	private List<Button> listButton = new ArrayList<Button>();
	private boolean yourTurn;

	private TextView mTextView;
	private TextView mTextTurn;
	private TextView mTextScoreJaune;
	private TextView mTextScoreRouge;
	private Button mButtonReplay;
	private String adversaire=null;
	private int color;
	private ListView list;
	private boolean booleanOpConnected = false;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puissanceinternet);
       Intent intent = this.getIntent();
        		
        adversaire = intent.getStringExtra("adversaire");
        color = intent.getIntExtra("color",0);
        
        yourTurn = intent.getBooleanExtra("yourTurn", true);
        
        m_handler = new Handler();
		try {
			initConnection();
		} catch (XMPPException e) {
			e.printStackTrace();
		}
		
		mTextView = (TextView) findViewById(R.id.text);
		mTextTurn = (TextView) findViewById(R.id.textOponent);
		final EditText message = (EditText) this.findViewById(R.id.message);
		list = (ListView) this.findViewById(R.id.thread);
		
		m_discussionThread = new ArrayList<String>();
		m_discussionThreadAdapter = new ArrayAdapter<String>(this,
				R.layout.multi_line_list_item, m_discussionThread);
		list.setAdapter(m_discussionThreadAdapter);
		
		Button send = (Button) this.findViewById(R.id.send);
		send.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				String to = adversaire;//"delarue.ariane@gmail.com"; //recipient.getText().toString();
				String text = message.getText().toString();
				message.setText(null);
				Message msg = new Message(to, Message.Type.chat);
				msg.setBody(text);
				FourInRowActivity.m_connection.sendPacket(msg);
				m_discussionThread.add("moi :");
				m_discussionThread.add(text);
				m_discussionThreadAdapter.notifyDataSetChanged();
			}
		});
        
        if(color==jaune)
        {
        	booleanOpConnected = true;

        	mTextTurn.setText("Your turn");
        	
        	mTextView.setText("You are the Yellow, playing against " + adversaire);
        	
        	//String to = adversaire;//"delarue.ariane@gmail.com"; //recipient.getText().toString();
    		//String text = "<puissance4 oponent connected>";//.getText().toString();
    		//Message msg = new Message(to, Message.Type.chat);
    		//msg.setBody(text);
    		//FourInRowActivity.m_connection.sendPacket(msg);
        }
        else
        {
        	booleanOpConnected = true;
        	mTextTurn.setText(adversaire + "playing");
        	mTextView.setText("You are the Red, playing against " + adversaire);
        }
        
        
        mTextScoreJaune = (TextView) findViewById(R.id.scoreJaune);
        mTextScoreRouge = (TextView) findViewById(R.id.scoreRouge);
        mButtonReplay = (Button) findViewById(R.id.buttonReplay);
        mButtonReplay.setOnClickListener(this);
        
        puissance4 = new matricePuissance4();
        listButton.add((Button) findViewById(R.id.button11));
        listButton.add((Button) findViewById(R.id.button12));
        listButton.add((Button) findViewById(R.id.button13));
        listButton.add((Button) findViewById(R.id.button14));
        listButton.add((Button) findViewById(R.id.button15));
        listButton.add((Button) findViewById(R.id.button16));
        listButton.add((Button) findViewById(R.id.button17));
        
        listButton.add((Button) findViewById(R.id.button21));
        listButton.add((Button) findViewById(R.id.button22));
        listButton.add((Button) findViewById(R.id.button23));
        listButton.add((Button) findViewById(R.id.button24));
        listButton.add((Button) findViewById(R.id.button25));
        listButton.add((Button) findViewById(R.id.button26));
        listButton.add((Button) findViewById(R.id.button27));
        
        listButton.add((Button) findViewById(R.id.button31));
        listButton.add((Button) findViewById(R.id.button32));
        listButton.add((Button) findViewById(R.id.button33));
        listButton.add((Button) findViewById(R.id.button34));
        listButton.add((Button) findViewById(R.id.button35));
        listButton.add((Button) findViewById(R.id.button36));
        listButton.add((Button) findViewById(R.id.button37));
        
        listButton.add((Button) findViewById(R.id.button41));
        listButton.add((Button) findViewById(R.id.button42));
        listButton.add((Button) findViewById(R.id.button43));
        listButton.add((Button) findViewById(R.id.button44));
        listButton.add((Button) findViewById(R.id.button45));
        listButton.add((Button) findViewById(R.id.button46));
        listButton.add((Button) findViewById(R.id.button47));
        
        listButton.add((Button) findViewById(R.id.button51));
        listButton.add((Button) findViewById(R.id.button52));
        listButton.add((Button) findViewById(R.id.button53));
        listButton.add((Button) findViewById(R.id.button54));
        listButton.add((Button) findViewById(R.id.button55));
        listButton.add((Button) findViewById(R.id.button56));
        listButton.add((Button) findViewById(R.id.button57));
        
        listButton.add((Button) findViewById(R.id.button61));
        listButton.add((Button) findViewById(R.id.button62));
        listButton.add((Button) findViewById(R.id.button63));
        listButton.add((Button) findViewById(R.id.button64));
        listButton.add((Button) findViewById(R.id.button65));
        listButton.add((Button) findViewById(R.id.button66));
        listButton.add((Button) findViewById(R.id.button67));      
        
  
        for(int k =0; k<42;++k){
        	//listButton.add((Button) findViewById(R.id.button11+k));
        	listButton.get(k).setOnClickListener(this);
        }
        

               
    }
    
	@Override
	protected void onDestroy() {
		
		String to = adversaire;//"delarue.ariane@gmail.com"; //recipient.getText().toString();
		String text = "<puissance4 stop>";//.getText().toString();
		Message msg = new Message(to, Message.Type.chat);
		msg.setBody(text);
		FourInRowActivity.m_connection.sendPacket(msg);
		// TODO Auto-generated method stub
		super.onDestroy();
	}
    
    
    void setColor(int i, int j ,int color){
    	if (puissance4.get(i, j) == jouable){
			if (color == jaune)
			{
				int statut = puissance4.setColor(i, j, jaune);
				if (j>0)
					listButton.get((j-1)*width+i).setBackgroundResource(R.drawable.etoile);
				
				if (statut == 0)
						{
					listButton.get(j*width+i).setBackgroundResource(R.drawable.jaune);
						}
				else
				{
					int[] indiceX = puissance4.getIndiceX();
					int[] indiceY = puissance4.getIndiceY();
					
					for(int p =0; p<indiceX.length;p++)
					{
						if(statut == 1 )
							listButton.get(indiceY[p]*width+indiceX[p]).setBackgroundResource(R.drawable.jaunehori);
						if(statut == 2 )
							listButton.get(indiceY[p]*width+indiceX[p]).setBackgroundResource(R.drawable.jauneverti);
						if(statut == 3 )
							listButton.get(indiceY[p]*width+indiceX[p]).setBackgroundResource(R.drawable.jaunediag1);
						if(statut == 4 )
							listButton.get(indiceY[p]*width+indiceX[p]).setBackgroundResource(R.drawable.jaunediag2);
					}
					puissance4.Agagner(); // met les boutons en mode non ouables
					Agagner(jaune);
				}
			}
			else if (color == rouge)
			{
				int statut = puissance4.setColor(i, j, rouge);
				if (j>0)
					listButton.get((j-1)*width+i).setBackgroundResource(R.drawable.etoile);
				
				if (statut == 0)
						{
					listButton.get(j*width+i).setBackgroundResource(R.drawable.rouge);
						}
				else{
					int[] indiceX = puissance4.getIndiceX();
					int[] indiceY = puissance4.getIndiceY();
					for(int p =0; p<indiceX.length;p++)
					{
						if(statut == 1 )
							listButton.get(indiceY[p]*width+indiceX[p]).setBackgroundResource(R.drawable.rbarho);
						if(statut == 2 )
							listButton.get(indiceY[p]*width+indiceX[p]).setBackgroundResource(R.drawable.rvert);
						if(statut == 3 )
							listButton.get(indiceY[p]*width+indiceX[p]).setBackgroundResource(R.drawable.rougediag1);
						if(statut == 4 )
							listButton.get(indiceY[p]*width+indiceX[p]).setBackgroundResource(R.drawable.rougediag2);
					}
					puissance4.Agagner(); // met les boutons en mode non ouables
					Agagner(rouge);
			
				}	
			}
    	}
    	
    	
    }
   
	public void onClick(View v) {
		int statut;
		int i=-9;
		int j = -9;
		// 1 ligne
		if (mButtonReplay == v && booleanOpConnected)
		{
			innialiserPlateau();
			String to = adversaire;
			String text = "<puissance4 replay>";//.getText().toString();
			Message msg = new Message(to, Message.Type.chat);
			msg.setBody(text);
			FourInRowActivity.m_connection.sendPacket(msg);
			
		}
		else
		{
			if(yourTurn && booleanOpConnected)
			{
				for(int k =0;k<42;++k){
					if (listButton.get(k)==v && yourTurn)
					{
						j = k/width;
						i = k - (k/width * width);
						
						setColor(i,j,color);
						
						mTextTurn.setText( adversaire + " turn");
						
						String to = adversaire;//"delarue.ariane@gmail.com"; //recipient.getText().toString();
						String text = "<puissance4 coordonnees>"+i+j;//.getText().toString();
						Message msg = new Message(to, Message.Type.chat);
						msg.setBody(text);
						FourInRowActivity.m_connection.sendPacket(msg);
						
						yourTurn =false;
						
					k=43;
					}
				}
			}
		}
	}

	private void Agagner(int color) {
		if (color==jaune)
		{
			++scoreJaune;
			mTextView.setText("Yellow won");
			mTextScoreJaune.setText("Yellow score : "+scoreJaune);
			
		}
		if (color==rouge)
		{
			++scoreRouge;
			mTextView.setText("Red won");
			mTextScoreRouge.setText("Red score : "+scoreRouge);
			
		}
		// faire apparaitre
		// TODO Auto-generated method stub
		
	}
	
	private void innialiserPlateau()
	{
		if (color==jaune)
			mTextView.setText("Replay : You are the Yellow, playing against " + adversaire);
		else
			mTextView.setText("Replay : You are the Red, playing against " + adversaire);
		for(int i = 0;i<(width*(height-1));++i)
			listButton.get(i).setBackgroundResource(R.drawable.blanc);
		
		for(int i = (width*(height-1));i<(width*height);++i)
			listButton.get(i).setBackgroundResource(R.drawable.etoile);
		puissance4.innitialiserPlateau();
	}
	
	private void initConnection() throws XMPPException {
		//Initialisation de la connexion
      //  ConnectionConfiguration config =
        //        new ConnectionConfiguration(SERVER_HOST, SERVER_PORT, SERVICE_NAME);
        //m_connection = new XMPPConnection(config);
        //m_connection.connect();
        //m_connection.login(LOGIN, PASSWORD);
        //Presence presence = new Presence(Presence.Type.available);
        //m_connection.sendPacket(presence);
       

        //enregistrement de l'écouteur de messages
		PacketFilter filter = new MessageTypeFilter(Message.Type.chat);
		FourInRowActivity.m_connection.addPacketListener(new PacketListener() {
				public void processPacket(Packet packet) {
					Message message = (Message) packet;
					if (message.getBody() != null) {
						String fromName = StringUtils.parseBareAddress(message
								.getFrom());
						
						if (fromName.contentEquals(adversaire))
						{
							if (message.getBody().contains("<puissance4 coordonnees>"))
							{
								String itemp = ""+message.getBody().charAt(24);
								final int i =Integer.parseInt(itemp);
								
								itemp = ""+message.getBody().charAt(25);
								final int j =Integer.parseInt(itemp);
								
								Log.v(TAG,"indice i : "+ i  +" et j : "+j);
								
								m_handler.post(new Runnable() {
									public void run() {
										if(color==jaune)
											setColor(i,j,rouge);
										if(color==rouge) // ma couleur, puis celle de l adversaire
											setColor(i,j,jaune);
										yourTurn = true;
										mTextTurn.setText("Your turn");
										//m_discussionThreadAdapter.notifyDataSetChanged();
									}
								});
							}
							else if((color==jaune) && message.getBody().contains("<puissance4 oponent connected>")){
								 m_handler.post(new Runnable() {
		        						public void run() {
		        							booleanOpConnected=true;
		        							//mTextTurn.setText(adversaire +"is connected, wait for him to play");
		        						}
		        					});
								
							} 
							else if (message.getBody().contains("<puissance4 replay>")){
								 m_handler.post(new Runnable() {
		        						public void run() {
		        							innialiserPlateau();
		        						}
		        					});
								
							}
							else if (message.getBody().contains("<puissance4 stop>")){
								
								 
								 finish();
							}
							else{
								m_discussionThread.add(fromName + ":");
								m_discussionThread.add(message.getBody());
								m_handler.post(new Runnable() {
									public void run() {
										m_discussionThreadAdapter.notifyDataSetChanged();
										list.setSelectionFromTop(m_discussionThread.size(), 2);
										
									}
								});
								
							}
						}
					}
				}
			}, filter);
	}


}

