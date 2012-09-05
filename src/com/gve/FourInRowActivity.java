package com.gve;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.RosterListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.util.StringUtils;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class FourInRowActivity extends Activity {


	private static String SERVER_HOST;// = "talk.google.com";
	private static int SERVER_PORT;// = 5222;
	private static String SERVICE_NAME;// = "gmail.com";	
	private static String LOGIN;// 
	private static String PASSWORD;//
	private static final String TAG = "ConnectGmailActivity";
	protected static final int INVITATION = 1;
	protected static final int CONNECTION = 14;
	protected static final int ERRORXMMP = 15;
	protected static final int GMAIL = 16;
	private Context context = this;
	private final int jaune=1;
	private final int rouge=-1;
	private String currentName="";
	AlertDialog alert;
	AlertDialog alert2;
	AlertDialog alertDialogXMMP;
	private EditText EditTextUsername;
	private EditText EditTextPassword;
	private CheckBox CheckBoxRemenber;
	private Boolean Connected = false;
	 EditText ETserverHost ;
     EditText ETserverPort;
     EditText ETserverName;
     
     private NotificationManager notification_manager_;
     /**
 	 * Unique number for notification system in Android
 	 */
 	private static int NOTIFICATION_APPLICATION_ID = 0;
	
	
	 private SharedPreferences settings;
	 public static final String PREFS_NAME = "puissance4";
	
	private  Button connectXMMP;
	
	
    List<String> listEntries;
    List<String> m_ListStatut = new ArrayList<String>();
    
	String mode = null;
	String name =null;
	String status =null;
	
	 ListView list;
	
	public static XMPPConnection m_connection;
	
	private List<String> m_ListAmis;
	private ArrayAdapter<String> m_AdapterListAmis;
	
	private Handler m_handler;

	
	/* Instantiating the Handler associated with the main thread.*/
	private Handler messageHandler = new Handler() {

		  @Override
	      public void handleMessage(android.os.Message msg) {  
	       //  if(msg.what == INVITATION){
	        //	 alert2.show();
	        	// }
	       //  else
	       if(msg.what == CONNECTION){
	        	 alertDialogXMMP.show();
	        	 }
	         else if(msg.what == ERRORXMMP) {
	        	 affiche_error(msg.getData().getString("error"));
	        	 
	        	 
	         }
	         //else if(msg.what == GMAIL){
	        //	 Connected = true;
	       // 	 connectXMMP.setText("Disconnect");
	        	 /* new Thread(new Runnable() {
	        		    public void run() {
	        		    	if (!EditTextPassword.getText().toString().isEmpty()) 
	        	        	{
	        			        	   try {
	        		           			initConnection();
	        		           		} catch (XMPPException e) {
	        		           			e.printStackTrace();
	        		           		}
	        		                   Log.v(TAG,"la liste" + m_ListAmis.toString());
	        	        	} 
	        		    }
	        		  }).start();*/
	         //}
	    }

		
	};
	
	private void affiche_error(String error) {
		Log.v(TAG,"affiche error" + error);
		Toast.makeText(this, error, Toast.LENGTH_LONG).show();

		Toast.makeText(this, "Login or Password not valid", Toast.LENGTH_LONG).show();
	}
	
	//------------------------------------------------------------------------------------------------//
	//               Creation du menu
	//------------------------------------------------------------------------------------------------//
	
    //MŽthode qui se dŽclenchera lorsque vous appuierez sur le bouton menu du tŽlŽphone
    public boolean onCreateOptionsMenu(Menu menu) {
 
        //CrŽation d'un MenuInflater qui va permettre d'instancier un Menu XML en un objet Menu
        MenuInflater inflater = getMenuInflater();
        //Instanciation du menu XML spŽcifier en un objet Menu
        inflater.inflate(R.layout.menu, menu);
        return true;
     }
 
       //MŽthode qui se dŽclenchera au clic sur un item
      public boolean onOptionsItemSelected(MenuItem item) {
         //On regarde quel item a ŽtŽ cliquŽ gr‰ce ˆ son id et on dŽclenche une action
         switch (item.getItemId()) {
            case R.id.menu:
            	alertDialogXMMP.show();
            	return true;
          
         }
         return false;}
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main); //maintest
        m_handler = new Handler();
        m_ListAmis = new ArrayList<String>();
        listEntries = new ArrayList<String>();
       
        CheckBoxRemenber = (CheckBox) findViewById(R.id.remenberCheck);
        EditTextPassword = (EditText) findViewById(R.id.password);
        EditTextUsername = (EditText) findViewById(R.id.username);
       
    	notification_manager_ = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        
        
        //------get sharedPreferences
        settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        //--------modify the value
        LOGIN = settings.getString("username", "");
        PASSWORD = settings.getString("password", "");
        SERVER_HOST = settings.getString("server_host", "talk.google.com");;
    	SERVER_PORT = settings.getInt("server_port", 5222);
    	SERVICE_NAME = settings.getString("service_name", "gmail.com");	
    	

        if (!PASSWORD.isEmpty())
        	CheckBoxRemenber.setChecked(true);
        else
        	CheckBoxRemenber.setChecked(false);
        
        EditTextUsername.setText(LOGIN);
        EditTextPassword.setText(PASSWORD);
        
        /*
        final AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Alert Dialog");
        builder.setMessage("Delete File");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
       */
        
        
       /*
        * Builder 
        * 
        * 
        * */ 
    		
		 //On instancie notre layout en tant que View
        LayoutInflater factory = LayoutInflater.from(this);
        final View alertDialogView = factory.inflate(R.layout.dialogsetting, null);
        //CrŽation de l'AlertDialog
        final AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setView(alertDialogView);  
        ETserverHost = (EditText)alertDialogView.findViewById(R.id.server_host);
        ETserverPort = (EditText)alertDialogView.findViewById(R.id.server_port);
        ETserverName = (EditText)alertDialogView.findViewById(R.id.server_name);
        
        ETserverHost.setText(SERVER_HOST);
       // ETserverPort.setText();
        ETserverPort.setText(Integer.toString(SERVER_PORT));
        ETserverName.setText(SERVICE_NAME);
        adb.setTitle("CONFIG");  
       
        adb.setMessage("Change your config");  
        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {  
               public void onClick(DialogInterface dialog, int which) {  
                   
            	   SERVER_HOST = ETserverHost.getText().toString();
            	   SERVER_PORT =  Integer.parseInt(ETserverPort.getText().toString());
            	   SERVICE_NAME = ETserverName.getText().toString();
	    			settings.edit().putString("server_host", SERVER_HOST).commit();
	    			settings.edit().putInt("server_port", SERVER_PORT).commit();
	    			settings.edit().putString("server_name", SERVICE_NAME).commit();
	    			
                   // messageHandler.sendMessage(android.os.Message.obtain(messageHandler, GMAIL));
                    
    	        	 
                  
             } }); 
        adb.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {  
               public void onClick(DialogInterface dialog, int which) {  
                 
             } }); 
        adb.setNeutralButton("RESET",new DialogInterface.OnClickListener() {  
            public void onClick(DialogInterface dialog, int which) {  
            	settings.edit().putString("server_host", "talk.google.com").commit();
    			settings.edit().putInt("server_port", 5222).commit();
    			settings.edit().putString("server_name",  "gmail.com").commit();
                
          } }); 
        alertDialogXMMP = adb.create();
       	
		final CharSequence[] items ={"Invite to play", "Cancel"};

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Options");
		builder.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				if(item == 0)
				{
					String text = "<puissance4> invitation";
					Message msg = new Message(currentName, Message.Type.chat);
					msg.setBody(text);
					m_connection.sendPacket(msg);
					
				}

			}
		});

		alert=builder.create();
		
        
 	
       
        
        
    	
    	//--------------------------------------------------------------
        //              les boutons
        //--------------------------------------------------------------
        Button playOnPhone = (Button) this.findViewById(R.id.playOnPhone);
        playOnPhone.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Log.v(TAG,"intent lauch to play on phone");
				Intent intent = new Intent(context,PuissanceBisActivity.class);
				startActivity(intent);

			}
		});
        Button playComputer = (Button) this.findViewById(R.id.playComputer);
        playComputer.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Log.v(TAG,"intent launchcomputer");
				Intent intent = new Intent(context,PuissanceComputerActivity.class);
				startActivity(intent);

			}
		});
        
        connectXMMP= (Button) this.findViewById(R.id.connectButton);
        connectXMMP.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				// if is connected, need to disconect
				if(Connected)
				{
					 new Thread(new Runnable() {
		        		    public void run() {
		        		    	m_connection.disconnect();
		        		    	
		        		    	 m_handler.post(new Runnable() {
		        						public void run() {
		        							m_ListAmis.clear();
		        							listEntries.clear();
		        							connectXMMP.setText("Connect");
		        			            	Log.v(TAG,"appel handler");
		        							m_AdapterListAmis.notifyDataSetChanged();
		        							Connected = false;
		        						}
		        					});
		        		     
		        		    }
		        		  }).start();
				}
				else {
					Log.v(TAG,"essaye de se connecter, envoie le handler"); 
					//messageHandler.sendMessage(android.os.Message.obtain(messageHandler, GMAIL));
					 new Thread(new Runnable() {
		        		    public void run() {
		        		    	PASSWORD = EditTextPassword.getText().toString();
		        		    	LOGIN = EditTextUsername.getText().toString();
		        		    	if (!LOGIN.isEmpty() && ! PASSWORD.isEmpty()) 
		        	        	{
		        		    		
		        		    		// save the pref :
		        		    		settings.edit().putString("username", LOGIN).commit();
		        		            if (CheckBoxRemenber.isChecked())
		        		    			settings.edit().putString("password", PASSWORD).commit();
		        		            else
		        		    			settings.edit().putString("password", "").commit();
		        		            
		        		            // launch the connection
	        			        	try {
	        		           			initConnection();
	        		           			messageHandler.sendMessage(android.os.Message.obtain(messageHandler, GMAIL));
	        		           		
	        		           		} catch (XMPPException e) {
	        		           			e.printStackTrace();
	        		           			Log.v(TAG,"ERROR "+e.getMessage());
	        		           			android.os.Message msgError = android.os.Message.obtain(messageHandler, ERRORXMMP);
	        		           			Bundle bundle = new Bundle();
	        		           			bundle.putString("error", "ERROR "+e.getMessage());
	        		           			msgError.setData(bundle);
										messageHandler.sendMessage(msgError);
	        		           		}
	        		                   Log.v(TAG,"la liste" + m_ListAmis.toString());
	        		                   Connected = true;
		        	        	} 
		        		    }
		        		  }).start();
					 	
				} 
			}
		});
        
        
		list = (ListView) this.findViewById(R.id.ListAmis);
		m_AdapterListAmis = new ArrayAdapter<String>(this, R.layout.multi_line_list_item, m_ListAmis);
		list.setAdapter(m_AdapterListAmis);
		list.setOnItemClickListener(new OnItemClickListener() {
		
					@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long arg3) {
						currentName = listEntries.get(position);
						alert.show();
							}
			});
		}
    

    
    private void initConnection() throws XMPPException {
		//Initialisation de la connexion
        ConnectionConfiguration config =
        new ConnectionConfiguration(SERVER_HOST, SERVER_PORT, SERVICE_NAME);
        m_connection = new XMPPConnection(config);
        m_connection.connect();
        
        m_connection.login(LOGIN, PASSWORD);
        Presence presence = new Presence(Presence.Type.available);
        m_connection.sendPacket(presence);
        Roster roster = m_connection.getRoster();
   
        roster.addRosterListener(new RosterListener() {
            public void entriesAdded(Collection<String> addresses) {
            	 //Log.v(TAG,"entries added"+addresses.toString());
            }
            public void entriesDeleted(Collection<String> addresses) {}
            public void entriesUpdated(Collection<String> addresses) {
           	 //Log.v(TAG,"entries update"+addresses.toString());
            }
            public void presenceChanged(final Presence presence) {
            	
               m_handler.post(new Runnable() {
					public void run() {
						String nameBis = presence.getFrom();
		            	String name = nameBis.substring(0, nameBis.indexOf("/"));
		           		int index = listEntries.indexOf(name);
		           		//Log.v(TAG,name + "presence  "+ presence.toString());
		           		if (index==-1)
		            	{
		            		listEntries.add(name);
		            		m_ListAmis.add(name +" "+presence.toString());
		            	}
		            	else
		            	{
		            		Log.v(TAG,"mise a jour liste");
		            		listEntries.set(index, name);
		            		m_ListAmis.set(index,name +" "+presence.toString());
		            	}
		               Log.v(TAG,"Presence changed : " +index + " " + name);
						Log.v(TAG,"appel handler");
						m_AdapterListAmis.notifyDataSetChanged();
					}
				});
               
            }
			
        });


       //enregistrement de l'écouteur de messages
		PacketFilter filter = new MessageTypeFilter(Message.Type.chat);
		m_connection.addPacketListener(new PacketListener() {
				public void processPacket(Packet packet) {
					Message message = (Message) packet;
					if (message.getBody() != null) {
						String fromName = StringUtils.parseBareAddress(message
								.getFrom());
						if (fromName.contentEquals(currentName) && message.getBody().contentEquals("<puissance4> ok"))
						{
							notify_user(0,true,currentName);
							/*Intent intent = new Intent(context,PuissanceInternetActivity.class);
							intent.putExtra("color", rouge);
							intent.putExtra("yourTurn", false);
							intent.putExtra("adversaire", currentName);
							startActivity(intent);
							Log.v(TAG,"le joueur a accepte l invitation" + currentName + "rouge false");
							*/
						}
						else if (fromName.contentEquals(currentName) && message.getBody().contentEquals("<puissance4> Non"))
						{
							notify_user(0,false,currentName);
							Log.v(TAG,"le joueur n a pas accepte l invitation");
						}
						else if(message.getBody().contentEquals("<puissance4> invitation"))
						{
							Log.v(TAG,"recoit une invitation" + fromName); 
							//messageHandler.sendMessage(android.os.Message.obtain(messageHandler, INVITATION));
							notify_user(1,false,fromName);
						}	
							Log.v(TAG,"message  recu" + fromName);
								
					
					}
				}
			}, filter);
			
	}

	/**
	 * API for showing Android notification to users
	 * 
	 * @param text
	 *            the main text to notify
	 * @param description
	 *            the description will be shown in detailed description UI
	 */
	public void notify_user(int confirm0_ou_receive1,boolean ok, String name) {

		if(confirm0_ou_receive1==1)
		{
			Intent intent = new Intent(this, AcceptActivity.class);
			intent.putExtra("currentName",name);
			startActivity(intent);
			//intent.putExtra("Request", description);

			Notification notification = new Notification(R.drawable.jaune, "Received Invitation for FourInRow",
					System.currentTimeMillis());
			notification.setLatestEventInfo(FourInRowActivity.this, "Received Invitation for FourInRow", currentName,
					PendingIntent.getActivity(getBaseContext(), 0, intent,
							PendingIntent.FLAG_CANCEL_CURRENT));
			notification_manager_.notify(NOTIFICATION_APPLICATION_ID++,
					notification);
		}
		else if(confirm0_ou_receive1==0){
			
			if(ok){
				Intent intent = new Intent(context,PuissanceInternetActivity.class);
				intent.putExtra("color", jaune);
				intent.putExtra("yourTurn", true);
				intent.putExtra("adversaire", currentName);
				startActivity(intent);
				Log.v(TAG,"le joueur a accepte l invitation" + currentName + "jaune true");

				Notification notification = new Notification(R.drawable.jaune, "Invitation Accepted",
						System.currentTimeMillis());
				notification.setLatestEventInfo(FourInRowActivity.this, "Invitation Accepted", "FourInRow",
						PendingIntent.getActivity(getBaseContext(), 0, intent,
								PendingIntent.FLAG_CANCEL_CURRENT));
				notification_manager_.notify(NOTIFICATION_APPLICATION_ID++,
						notification);

			}
			else{
				Intent intent = new Intent(this, FourInRowActivity.class);
				Notification notification = new Notification(R.drawable.icon, "Invitation Decline",
						System.currentTimeMillis());
				notification.setLatestEventInfo(FourInRowActivity.this, "Invitation Decline", "FourInRow",
						PendingIntent.getActivity(getBaseContext(), 0, intent,
								PendingIntent.FLAG_CANCEL_CURRENT));
				notification_manager_.notify(NOTIFICATION_APPLICATION_ID++,
						notification);

			}
		}


	}

}

/*
final CharSequence[] items2 ={"Accept", "Decline"};
		AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
		builder2.setTitle("Options");
		builder2.setItems(items2, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				if(item == 0)
				{
					String text = "<puissance4> ok";
					Message msg = new Message(currentName, Message.Type.chat);
					msg.setBody(text);
					m_connection.sendPacket(msg);
					Intent intent = new Intent(context,PuissanceInternetActivity.class);
					intent.putExtra("color", jaune);
					intent.putExtra("yourTurn", true);
					intent.putExtra("adversaire", currentName);
					Log.v(TAG,"le joueur a accepte l invitation" + currentName + "jaune true");
					startActivity(intent);
					Log.v(TAG,"on ccepte l invitation");
					
					
				}
				else
				{
					String text = "<puissance4> Non";
					Message msg = new Message(currentName, Message.Type.chat);
					msg.setBody(text);
					m_connection.sendPacket(msg);
				}

			}
		});

		alert2=builder2.create();

		 //On instancie notre layout en tant que View
     LayoutInflater factory = LayoutInflater.from(this);
     final View alertDialogView = factory.inflate(R.layout.passworddialog, null);
     //CrŽation de l'AlertDialog
     final AlertDialog.Builder adb = new AlertDialog.Builder(this);
     adb.setView(alertDialogView);  
     EditText Username = (EditText)alertDialogView.findViewById(R.id.username);
     Username.setText("gve");
     adb.setTitle("Alert 1");  
     adb.setMessage("This is an alert");  
     adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {  
            public void onClick(DialogInterface dialog, int which) {  
                
                    //Lorsque l'on cliquera sur le bouton "OK", on rŽcup�re l'EditText correspondant ˆ notre vue personnalisŽe (cad ˆ alertDialogView)
         	   CheckBox remenber = (CheckBox)alertDialogView.findViewById(R.id.remenberCheck);
  
                 if (remenber.isChecked()){
                 	int dd= 0;
                 
                 }
                 
                // messageHandler.sendMessage(android.os.Message.obtain(messageHandler, GMAIL));
                 
 	        	if (!EditTextPassword.getText().toString().isEmpty()) 
 	        	{
 			        	   try {
 		           			initConnection();
 		           		} catch (XMPPException e) {
 		           			e.printStackTrace();
 		           		}
 		                   Log.v(TAG,"la liste" + m_ListAmis.toString());
 	        	}
                
               
          } }); 
     adb.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {  
            public void onClick(DialogInterface dialog, int which) {  
              
          } }); 
     alertDialogXMMP = adb.create();
     */
