package com.gve;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
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
	private int jaune  = 1;
	private int rouge = -1;
	private int jouable = 0;
	private int nonjouable = -9;
	private int tour = jaune ;// les jaunes commencent 
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
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
		int i=-9;
		int j = -9;
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
					
					if (puissance4.get(i, j) == jouable){
						if (tour == jaune)
						{
							tour = rouge;

							mTextView.setText("Red turn");
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
								int ggg = indiceX.length;
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
							tour = jaune;
							mTextView.setText("Yellow turn");
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
								int ggg = indiceX.length;
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
				k=43;
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
		mTextView.setText("let s play");
		for(int i = 0;i<(width*(height-1));++i)
			listButton.get(i).setBackgroundResource(R.drawable.blanc);
		
		for(int i = (width*(height-1));i<(width*height);++i)
			listButton.get(i).setBackgroundResource(R.drawable.etoile);
		puissance4.innitialiserPlateau();
	}

}




// in case


/*b11 = (Button) findViewById(R.id.button11);
b11.setOnClickListener(this);
b12 = (Button) findViewById(R.id.button12);
b12.setOnClickListener(this);
b13 = (Button) findViewById(R.id.button13);
b13.setOnClickListener(this);
b14 = (Button) findViewById(R.id.button14);
b14.setOnClickListener(this);
b15 = (Button) findViewById(R.id.button15);
b15.setOnClickListener(this);
b16 = (Button) findViewById(R.id.button16);
b16.setOnClickListener(this);
b17 = (Button) findViewById(R.id.button17);
b17.setOnClickListener(this);

b21 = (Button) findViewById(R.id.button21);
b21.setOnClickListener(this);
b22 = (Button) findViewById(R.id.button22);
b22.setOnClickListener(this);
b23 = (Button) findViewById(R.id.button23);
b23.setOnClickListener(this);
b24 = (Button) findViewById(R.id.button24);
b24.setOnClickListener(this);
b25 = (Button) findViewById(R.id.button25);
b25.setOnClickListener(this);
b26 = (Button) findViewById(R.id.button26);
b26.setOnClickListener(this);
b27 = (Button) findViewById(R.id.button27);
b27.setOnClickListener(this);

b31 = (Button) findViewById(R.id.button31);
b31.setOnClickListener(this);
b32 = (Button) findViewById(R.id.button32);
b32.setOnClickListener(this);
b33 = (Button) findViewById(R.id.button33);
b33.setOnClickListener(this);
b34 = (Button) findViewById(R.id.button34);
b34.setOnClickListener(this);
b35 = (Button) findViewById(R.id.button35);
b35.setOnClickListener(this);
b36 = (Button) findViewById(R.id.button36);
b36.setOnClickListener(this);
b37 = (Button) findViewById(R.id.button37);
b37.setOnClickListener(this);

b41 = (Button) findViewById(R.id.button41);
b41.setOnClickListener(this);
b42 = (Button) findViewById(R.id.button42);
b42.setOnClickListener(this);
b43 = (Button) findViewById(R.id.button43);
b43.setOnClickListener(this);
b44 = (Button) findViewById(R.id.button44);
b44.setOnClickListener(this);
b45 = (Button) findViewById(R.id.button45);
b45.setOnClickListener(this);
b46 = (Button) findViewById(R.id.button46);
b46.setOnClickListener(this);
b47 = (Button) findViewById(R.id.button47);
b47.setOnClickListener(this);

b51 = (Button) findViewById(R.id.button51);
b51.setOnClickListener(this);
b52 = (Button) findViewById(R.id.button52);
b52.setOnClickListener(this);
b53 = (Button) findViewById(R.id.button53);
b53.setOnClickListener(this);
b54 = (Button) findViewById(R.id.button54);
b54.setOnClickListener(this);
b55 = (Button) findViewById(R.id.button55);
b55.setOnClickListener(this);
b56 = (Button) findViewById(R.id.button56);
b56.setOnClickListener(this);
b57 = (Button) findViewById(R.id.button57);
b57.setOnClickListener(this);

b61 = (Button) findViewById(R.id.button61);
b61.setOnClickListener(this);
b62 = (Button) findViewById(R.id.button62);
b62.setOnClickListener(this);
b63 = (Button) findViewById(R.id.button63);
b63.setOnClickListener(this);
b64 = (Button) findViewById(R.id.button64);
b64.setOnClickListener(this);
b65 = (Button) findViewById(R.id.button65);
b65.setOnClickListener(this);
b66 = (Button) findViewById(R.id.button66);
b66.setOnClickListener(this);
b67 = (Button) findViewById(R.id.button67);
b67.setOnClickListener(this);

*/


/*if (v == b11){
buttonID = b11;
i=0;
j=0;
}
else if( v == b12){
buttonID = b12;
i=0;
j=1;
}
else if( v == b13){
buttonID = b13;
i=0;
j=2;
}
else if( v == b14){
buttonID = b14;

i=0;
j=3;
}
else if( v == b15){
buttonID = b15;
i=0;
j=4;
}
else if( v == b16){
buttonID = b16;
i=0;
j=5;	
}
else if( v == b17){
buttonID = b17;
i=0;
j=6;}
//ligne2
else if (v == b21){
buttonID = b21;
i=1;
j=0;
}
else if( v == b22){
buttonID = b22;
i=1;
j=1;
}
else if( v == b23){
buttonID = b23;
i=1;
j=2;
}
else if( v == b24){
buttonID = b24;

i=1;
j=3;
}
else if( v == b25){
buttonID = b25;
i=1;
j=4;
}
else if( v == b26){
buttonID = b26;
i=1;
j=5;	
}
else if( v == b27){
buttonID = b27;
i=1;
j=6;}
//ligne3
else if (v == b31){
buttonID = b31;
i=2;
j=0;
}
else if( v == b32){
buttonID = b32;
i=2;
j=1;
}
else if( v == b33){
buttonID = b33;
i=2;
j=2;
}
else if( v == b34){
buttonID = b34;

i=2;
j=3;
}
else if( v == b35){
buttonID = b35;
i=2;
j=4;
}
else if( v == b36){
buttonID = b36;
i=2;
j=5;	
}
else if( v == b37){
buttonID = b37;
i=2;
j=6;}
//ligne4
else if (v == b41){
buttonID = b41;
i=3;
j=0;
}
else if( v == b42){
buttonID = b42;
i=3;
j=1;
}
else if( v == b43){
buttonID = b43;
i=3;
j=2;
}
else if( v == b44){
buttonID = b44;

i=3;
j=3;
}
else if( v == b45){
buttonID = b45;
i=3;
j=4;
}
else if( v == b46){
buttonID = b46;
i=3;
j=5;	
}
else if( v == b47){
buttonID = b47;
i=3;
j=6;}
// ligne 5
else if (v == b51){
buttonID = b51;
i=4;
j=0;
}
else if( v == b52){
buttonID = b52;
i=4;
j=1;
}
else if( v == b53){
buttonID = b53;
i=4;
j=2;
}
else if( v == b54){
buttonID = b54;

i=4;
j=3;
}
else if( v == b55){
buttonID = b55;
i=4;
j=4;
}
else if( v == b56){
buttonID = b56;
i=4;
j=5;	
}
else if( v == b57){
buttonID = b57;
i=4;
j=6;}
//ligne6
else if (v == b61){
	buttonID = b61;
	i=5;
	j=0;
}
else if( v == b62){
	buttonID = b62;
	i=5;
	j=1;
	}
else if( v == b63){
	buttonID = b63;
	i=5;
	j=2;
	}
else if( v == b64){
	buttonID = b64;
	
	i=5;
	j=3;
	}
else if( v == b65){
	buttonID = b65;
	i=5;
	j=4;
	}
else if( v == b66){
	buttonID = b66;
	i=5;
	j=5;	
}
else if( v == b67){
	buttonID = b67;
	i=5;
	j=6;}
	*/
