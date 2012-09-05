package com.gvestyle;

import gve.corporate.game.matricePuissance4;
import gve.corporate.player.Player;
import gve.corporate.player.PlayerReal;
import gve.corporate.util.Constante;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class PuissanceBisActivity extends Activity implements OnClickListener{
	
	private matricePuissance4 puissance4;
	private int scoreJaune =0;
	private int scoreRouge = 0;
	int width =7;
	int height = 6;
	int i,j;

	private Handler m_handler;
	private int status;
	private int tour = Constante.jaune ;// les jaunes commencent 
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
	
	private TextView mTextView;
	private TextView mTextScoreJaune;
	private TextView mTextScoreRouge;
	private Button mButtonReplay;
	private Player[] players = null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Player player1 = new PlayerReal("Guillaume", puissance4, Constante.jaune);
        Player player2 = new PlayerReal("Arthur", puissance4, Constante.rouge);
        players[0] = player1;
        players[1] = player2;
        tour = 0;
        setContentView(R.layout.puissancetablet);
        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        spinner.setVisibility(View.INVISIBLE);
        mTextView = (TextView) findViewById(R.id.text);
        mTextView.setText("Yellow turn");
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
   
	public void onClick(View v) {
		Button buttonID = null;
		int statut;
		
		// 1 ligne
		if (mButtonReplay == v)
			innialiserPlateau();
		else
		{
			for(int k =0;k<42;++k){
				if (listButton.get(k)==v)
				{
					j = k/width;
					i = k - (k/width * width);
					
					status = players[tour].play(i, j);
					changeUI(status);
					k=43;
				}
			}
		}
	}

	private void changeUI(int status2) {
		if (status>=1 && status<=4)
		{
			int[] indiceX = puissance4.getIndiceX();
			int[] indiceY = puissance4.getIndiceY();
			if(players[tour].getcolor()==Constante.rouge)
			{
				for(int p =0; p<indiceX.length;p++)
				{
					if(status == 1 )
						listButton.get(indiceY[p]*width+indiceX[p]).setBackgroundResource(R.drawable.rbarho);
					if(status == 2 )
						listButton.get(indiceY[p]*width+indiceX[p]).setBackgroundResource(R.drawable.rvert);
					if(status == 3 )
						listButton.get(indiceY[p]*width+indiceX[p]).setBackgroundResource(R.drawable.rougediag1);
					if(status == 4 )
						listButton.get(indiceY[p]*width+indiceX[p]).setBackgroundResource(R.drawable.rougediag2);
				}
			}
			else if(players[tour].getcolor()==Constante.jaune)
				{
				for(int p =0; p<indiceX.length;p++)
					{
						if(status == 1 )
							listButton.get(indiceY[p]*width+indiceX[p]).setBackgroundResource(R.drawable.jaunehori);
						if(status == 2 )
							listButton.get(indiceY[p]*width+indiceX[p]).setBackgroundResource(R.drawable.jauneverti);
						if(status == 3 )
							listButton.get(indiceY[p]*width+indiceX[p]).setBackgroundResource(R.drawable.jaunediag1);
						if(status == 4 )
							listButton.get(indiceY[p]*width+indiceX[p]).setBackgroundResource(R.drawable.jaunediag2);
					}
				}		
			
			puissance4.Agagner(); // met les boutons en mode non jouables
			Agagner(players[tour].getcolor());
		}
		else if(status==Constante.change)
			{
			tour=(tour+1)/2;
			if (j>0){
				 m_handler.post(new Runnable() {
						public void run() {
							listButton.get((j-1)*width+i).setBackgroundResource(R.drawable.etoile);
							}
					});}
			if(players[tour].getcolor()==Constante.rouge)
				m_handler.post(new Runnable() {
						public void run() {
							listButton.get(j*width+i).setBackgroundResource(R.drawable.rouge);
							} });
			else
				m_handler.post(new Runnable() {
					public void run() {
						listButton.get(j*width+i).setBackgroundResource(R.drawable.jaune);
						}});
		}

	}

	private void Agagner(int color) {
		if (color==Constante.jaune)
		{
			++scoreJaune;
			mTextView.setText("Yellow won");
			mTextScoreJaune.setText("Yellow score : "+scoreJaune);
			}
		if (color==Constante.rouge)
		{
			++scoreRouge;
			mTextView.setText("Red won");
			mTextScoreRouge.setText("Red score : "+scoreRouge);
		}
	}
	
	private void innialiserPlateau()
	{
		mTextView.setText("let s play");
		for(int i = 0;i<(width*(height-1));++i)
			listButton.get(i).setBackgroundResource(R.drawable.blanc);
		
		for(int i = (width*(height-1));i<(width*height);++i)
			listButton.get(i).setBackgroundResource(R.drawable.etoile);
		puissance4.innitialiserPlateau();
	}

}

