package com.gve;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class PuissanceComputerActivity extends Activity implements OnClickListener{
	
	private int niveau = 0;
	private matricePuissance4 puissance4;
	private int scoreJaune =0;
	private int scoreRouge = 0;
	int width =7;
	int height = 6;
	private int jaune  = 1;
	private int rouge = -1;
	private int ii;
	private int jj;
	private int statut;
	private Handler m_handler;
	int colorComputer =rouge;
	private int jouable = 0;
	private int nonjouable = -9;
	private int tour = 1 ;// les jaunes commencent 
	private List<Button> listButton = new ArrayList<Button>();
	
	private Button b11;
	private Button b12;
	private Button b13;
	private Button b14;
	private Button b15;
	private Button b16;
	private Button b17;
	
	private Button b21;
	private Button b22;
	private Button b23;
	private Button b24;
	private Button b25;
	private Button b26;
	private Button b27;
	
	private Button b31;
	private Button b32;
	private Button b33;
	private Button b34;
	private Button b35;
	private Button b36;
	private Button b37;
	
	private Button b41;
	private Button b42;
	private Button b43;
	private Button b44;
	private Button b45;
	private Button b46;
	private Button b47;
	
	private Button b51;
	private Button b52;
	private Button b53;
	private Button b54;
	private Button b55;
	private Button b56;
	private Button b57;
	
	private Button b61;
	private Button b62;
	private Button b63;
	private Button b64;
	private Button b65;
	private Button b66;
	private Button b67;
	
	private Button boutonTemp;
	private TextView mTextView;
	private TextView mTextScoreJaune;
	private TextView mTextScoreRouge;
	private Button mButtonReplay;
	private Boolean computer = false;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.puissancetablet);
        m_handler = new Handler();
        
        mTextView = (TextView) findViewById(R.id.text);
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
        //f
        
        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.listniveau, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
        
        for(int k =0; k<42;++k){
        	//listButton.add((Button) findViewById(R.id.button11+k));
        	listButton.get(k).setOnClickListener(this);
        }
               
    }
   
	public void onClick(View v) {
		Button buttonID = null;
		
		int i=-9;
		int j = -9;
		// 1 ligne
		if (mButtonReplay == v)
			innialiserPlateau();
		else if(!computer)
		{
				
			tour = -colorComputer;
			for(int k =0;k<42;++k){
				if (listButton.get(k)==v)
				{
					j = k/width;
					i = k - (k/width * width);
					k=43;
				}
			}
			
			if (puissance4.get(i, j) == jouable){
				computer = true;
				if (tour == jaune)
				{
					statut = puissance4.setColor(i, j, jaune);
					if (j>0)
						listButton.get((j-1)*width+i).setBackgroundResource(R.drawable.etoile);
					
					if (statut == 0)
							{
								v.setBackgroundResource(R.drawable.jaune);
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
				else if (tour == rouge)
				{
					statut = puissance4.setColor(i, j, rouge);
					if (j>0)
						listButton.get((j-1)*width+i).setBackgroundResource(R.drawable.etoile);
					
					if (statut == 0)
							{
								v.setBackgroundResource(R.drawable.rouge);
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
				new Thread(new Runnable() {
        		    public void run() {
        		    	
        		     colorComputer = rouge;
       				 int[] coord = puissance4.coupSuivant(colorComputer, niveau);
       				 jj =coord[1];
       				 ii =coord[0];
       				 statut =-1;
       				 tour = colorComputer;		
        		    	if (puissance4.get(ii, jj) == jouable)
    					{
    						
    						if (colorComputer == jaune)
    						{
    							tour = rouge;
    							statut = puissance4.setColor(ii, jj, jaune);
    							if (jj>0){
    								 m_handler.post(new Runnable() {
  		        						public void run() {
  		        							listButton.get((jj-1)*width+ii).setBackgroundResource(R.drawable.etoile);
  		    								}
  		        					});}
    							if (statut == 0)
    									{
    								m_handler.post(new Runnable() {
  		        						public void run() {
  		        							listButton.get(jj*width+ii).setBackgroundResource(R.drawable.jaune);
  	    									}
  		        					});
    								computer =false;
    									}
    							else
    							{
    								m_handler.post(new Runnable() {
  		        						public void run() {
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
  		        					});
    								
    							}
    						}
    						else if (colorComputer == rouge)
    						{
    							tour = jaune;
    							statut = puissance4.setColor(ii, jj, rouge);
    							
    							if (jj>0){
    								 m_handler.post(new Runnable() {
 		        						public void run() {
 		        							listButton.get((jj-1)*width+ii).setBackgroundResource(R.drawable.etoile);
 		        						}
 		        					});
    								
    							}
    							if (statut == 0)
    									{
    								 m_handler.post(new Runnable() {
  		        						public void run() {
  		    								listButton.get(jj*width+ii).setBackgroundResource(R.drawable.rouge);
  		        						}
  		        					});
    								computer =false;
    									}
    							else{
    								 m_handler.post(new Runnable() {
   		        						public void run() {
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
   		        					});
    						
    							}	
    						}
    						
    				}

        		     
        		    }
        		  }).start();
			
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
		mTextView.setText("let s play");
		for(int i = 0;i<(width*(height-1));++i)
			listButton.get(i).setBackgroundResource(R.drawable.blanc);
		
		for(int i = (width*(height-1));i<(width*height);++i)
			listButton.get(i).setBackgroundResource(R.drawable.etoile);
		puissance4.innitialiserPlateau();
		computer = false;
	}
	
	public class MyOnItemSelectedListener implements OnItemSelectedListener {

		@Override
	    public void onItemSelected(AdapterView<?> parent,
	        View view, int pos, long id) {
			niveau =pos;
		}

	    @Override
	    public void onNothingSelected(AdapterView parent) {
	      // Do nothing.
	    }

	
	}

}


