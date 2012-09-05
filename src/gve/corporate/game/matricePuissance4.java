package gve.corporate.game;

import gve.corporate.util.Constante;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.util.Log;

public class matricePuissance4 {

	
	private static final String TAG = "matricePuissance4";
	int[] plateau ;//= new int[42] ;// innitialisation du plateau a zero
	int width = 7;
	int height = 6;
	int[] indiceX ;
	int[] indiceY;
	int x;
	int y;
	private Random nb = new Random();
	
	List<Integer> coordX = new ArrayList<Integer>();
	List<Integer> coordY = new ArrayList<Integer>();
	List<Integer> coord2X = new ArrayList<Integer>();
	List<Integer> coord2Y = new ArrayList<Integer>();
	List<Integer> coord3X = new ArrayList<Integer>();
	List<Integer> coord3Y = new ArrayList<Integer>();
	
	public matricePuissance4(){
		plateau = new int[42];
		indiceX = new int[4];
		indiceY = new int[4];
		innitialiserPlateau() ;
		
		
	}
	
	int get(int i, int j){
		if (i>=0 && i< width && j>=0 && j< height)
			return plateau[(j*width + i)];
		else
			return -9;
	}
	
	
	
	public int setColor(int i, int j, int color){
		if (get(i, j) == Constante.nonjouable)
			return Constante.unchange;
		
		 	plateau[(j*width + i)] = color;
		 	coordY.set(i, coordY.get(i)-1);
		 	if(coordY.get(i)-1 <0)
	 		{
	 			int ll=7;
	 			ll=7;
	 		}
	 		
		 	if(j>0)
		 	{
		 		plateau[((j-1)*width + i)] = 0; // on met la case au dessus de laquel on vient de jouer en mode jouable
		 		
		 		//coord2Y.set(i, coord2Y.get(i)-1);
		 		//coord3Y.set(i, coord3Y.get(i)-1);
		 		
		 	}
		 
		 	if (CheckLineHo(i,j,color)) 
		 		return Constante.hori;
		 	else if ( CheckLineVert(i,j,color) )
		 		return Constante.verti;
		 	else if (CheckLineDiag1(i,j,color) )
		 		return Constante.diag1;
		 	else if (  CheckLineDiag2(i,j,color)   )
		 		return Constante.diag2;
		 	else 
		 		return Constante.change;
			//boolean win  =(CheckLineHo(i,j,-1) || CheckLineVert(i,j,-1) || CheckLineDiag1(i,j,-1) || CheckLineDiag2(i,j,-1)  ); 
			//return win;
		}
	
	
	 boolean CheckLineHo(int i, int j, int color){
		int ligne = 0;
		for(int k = i-3; k <= i+3; ++k){
			if (get(k,j) == color){
				indiceX[ligne]=k;
				indiceY[ligne]=j;
				++ligne;
				if (ligne ==4)
					k=i+4;
			}
			else 
				ligne = 0;
		}
		if (ligne==4)
			return true;
		else
			return false;
	}

	boolean CheckLineVert(int i, int j, int color){
		int ligne = 0;
		//int start = j - 3 - (j-3);
		//int end = j+3 + (j+3);
		for(int k = j-3; k <= j+3; ++k){
			if (get(i,k) == color){
				
				indiceX[ligne]=i;
				indiceY[ligne]=k;
				++ligne;
				if (ligne ==4)
					k=j+4;
			}
			else
				ligne=0;
		}
		if (ligne==4)
			return true;
		else
			return false;
	}

	 boolean CheckLineDiag1(int i, int j, int color){
		int ligne=0;
		for(int k=-3;k<=3;++k){
			if (get(i+k,j+k) == color){
				indiceX[ligne]=i+k;
				indiceY[ligne]=j+k;
				++ligne;
				if (ligne ==4)
					k=4;
			}
			else
				ligne = 0;
		}
		if (ligne==4)
			return true;
		else
			return false;
	}
	 boolean CheckLineDiag2(int i, int j, int color){
		int ligne=0;
		for(int k=-3;k<=3;++k){
			if (get(i+k,j-k) == color){
				indiceX[ligne]=i+k;
				indiceY[ligne]=j-k;
				++ligne;
				if (ligne ==4)
					k=4;
			}
			else
				ligne=0;
			
		}
		if (ligne==4)
			return true;
		else
			return false;
	}

	 //  check pour faire des lignes de 3 :
	 
	 boolean CheckLineHoNb(int i, int j, int color, int nb){
			int ligne = 0;
			for(int k = i-(nb-1); k <= i+(nb-1); ++k){
				if (get(k,j) == color){
					indiceX[ligne]=k;
					indiceY[ligne]=j;
					++ligne;
					if (ligne ==nb)
						k=i+nb;
				}
				else 
					ligne = 0;
			}
			if (ligne==nb)
				return true;
			else
				return false;
		}

		boolean CheckLineVertNb(int i, int j, int color, int nb){
			int ligne = 0;
			//int start = j - 3 - (j-3);
			//int end = j+3 + (j+3);
			for(int k = j-(nb-1); k <= j+(nb-1); ++k){
				if (get(i,k) == color){
					
					indiceX[ligne]=i;
					indiceY[ligne]=k;
					++ligne;
					if (ligne ==nb)
						k=j+nb;
				}
				else
					ligne=0;
			}
			if (ligne==nb)
				return true;
			else
				return false;
		}

		 boolean CheckLineDiag1Nb(int i, int j, int color, int nb){
			int ligne=0;
			for(int k=-(nb-1);k<=(nb-1);++k){
				if (get(i+k,j+k) == color){
					indiceX[ligne]=i+k;
					indiceY[ligne]=j+k;
					++ligne;
					if (ligne ==nb)
						k=nb;
				}
				else
					ligne = 0;
			}
			if (ligne==nb)
				return true;
			else
				return false;
		}
		 boolean CheckLineDiag2Nb(int i, int j, int color,int nb){
			int ligne=0;
			for(int k=-(nb-1);k<=(nb-1);++k){
				if (get(i+k,j-k) == color){
					indiceX[ligne]=i+k;
					indiceY[ligne]=j-k;
					++ligne;
					if (ligne ==nb)
						k=nb;
				}
				else
					ligne=0;
				
			}
			if (ligne==nb)
				return true;
			else
				return false;
		}

	public int[] getIndiceX(){
		return indiceX;
	}
	
	public int[] getIndiceY(){
		return indiceY;
	}

	public void Agagner() {
		// on met tous les boutons en mode non jouables
		for(int i = 0;i<(width*height);++i)
			plateau[i]=-9;
		// TODO Auto-generated method stub
		
	}

	public void innitialiserPlateau() {
		// TODO Auto-generated method stub
		coordX.clear();
		coordY.clear();
		coord2X.clear();
		coord2Y.clear();
		coord3X.clear();
		coord3Y.clear();
		for(int i = 0;i<4;++i){
			indiceY[i]=0;
			indiceX[i]=0;
			}
		for(int i = 0;i<width;++i){
			coordX.add(i);
			coordY.add(height-1);
			coord2X.add(i);
			coord2Y.add(height-1);
			coord3X.add(i);
			coord3Y.add(height-1);
			}
		
		for(int i = 0;i<(width*(height-1));++i)
			plateau[i]=-9;
		for(int i = (width*(height-1));i<(width*height);++i)
			plateau[i]=0;
		
	}
	
	public int[] coupSuivant(int color, int level)
	{
		int[] coord = new int[2];
		boolean joue;
		switch (level) {
			case 0:
				
	
				Log.v(TAG,"random");
				joue = true;
				while(joue){
					coord[0] = nb.nextInt(width-1);
					coord[1] = coordY.get(coord[0] );
					
					if (coord[1]>=0)
						joue =false;
				}
				break;
			case 1:
				if (coupNiveau1(color)){
					Log.v(TAG,"niveau1");
					coord[0]=x;
					coord[1]=y;
				}
				else if(coupNiveau1(-color)){

					Log.v(TAG,"niveau1 autre");
					coord[0]=x;
					coord[1]=y;
				}
				else {

					Log.v(TAG,"random");
					joue = true;
					while(joue){
						coord[0] = nb.nextInt(width-1);
						coord[1] = coordY.get(coord[0] );
						
						if (coord[1]>=0)
							joue =false;
					}
				}
				break;
			case 3:
				if (coupNiveau1(color)){

					Log.v(TAG,"niveau1");
					coord[0]=x;
					coord[1]=y;
				}
				else if(coupNiveau1(-color)){

					Log.v(TAG,"niveauautre1");
					coord[0]=x;
					coord[1]=y;
				}
				else if(coupNiveau3(-color)){

					Log.v(TAG,"niveau3 " + x +" "+y);
					coord[0]=x;
					coord[1]=y;
				}
				else {

					Log.v(TAG,"random");
					joue = true;
					while(joue){
						coord[0] = nb.nextInt(width-1);
						coord[1] = coordY.get(coord[0] );
						
						if (coord[1]>=0)
							joue =false;
					}
					
				}
				break;
			case 4:
				if (coupNiveau1(color)){

					Log.v(TAG,"niveau1");
					coord[0]=x;
					coord[1]=y;
				}
				else if(coupNiveau1(-color)){

					Log.v(TAG,"niveauautre1");
					coord[0]=x;
					coord[1]=y;
				}
				else if(coupNiveau3(color)){

					Log.v(TAG,"niveau3 " + x +" "+y);
					coord[0]=x;
					coord[1]=y;
				}
				else {

					Log.v(TAG,"random");
					joue = true;
					while(joue){
						coord[0] = nb.nextInt(width-1);
						coord[1] = coordY.get(coord[0] );
						
						if (coord[1]>=0)
							joue =false;
					}
					
				}
				break;
			case 5:
				if (coupNiveau1(color)){

					Log.v(TAG,"niveau1");
					coord[0]=x;
					coord[1]=y;
				}
				else if(coupNiveau1(-color)){

					Log.v(TAG,"niveauautre1");
					coord[0]=x;
					coord[1]=y;
				}
				else if(coupNiveau3(color)){

					Log.v(TAG,"niveau3 " + x +" "+y);
					coord[0]=x;
					coord[1]=y;
				}
				else if(coupNiveau3(-color)){

					Log.v(TAG,"niveau3 " + x +" "+y);
					coord[0]=x;
					coord[1]=y;
				}
				else {

					Log.v(TAG,"random");
					joue = true;
					while(joue){
						coord[0] = nb.nextInt(width-1);
						coord[1] = coordY.get(coord[0] );
						
						if (coord[1]>=0)
							joue =false;
					}
					
				}
				break;
				
				
		}
		return coord;
		
	}
	



	private Boolean coupNiveau1(int color) {
		for(int i=0;i<width;i++)
		{
			for(int j=0;j<height;j++)
			{
				if(plateau[(j*width + i)]==0)
				{
					plateau[(j*width + i)]=color;
					if ( CheckLineVert( i, j, color) || CheckLineHo( i, j, color)|| CheckLineDiag1( i, j, color)|| CheckLineDiag2( i, j, color))
					{	
						plateau[(j*width + i)]=0;
						x=i;
						y=j;
						return true;
					}

					plateau[(j*width + i)]=0;
				}
		}
			 
		}
		return false;
	}
	
	private Boolean coupNiveau3(int color) {
		int gagne =0;
		boolean  boolet=false;
		int nbColorCheck =0;
		int i,j,i2,j2,i3,j3;
		int plateauTemp1;
		int plateauTemp2;
		int plateauTemp3;
		for(int k=0;k<coordY.size();k++)
			
		{
			Log.v(TAG,"indice" + k);
			gagne = 0;
			nbColorCheck=0;
			
			j = coordY.get(k);
			{
			if(j>=0)
			{
				Log.v(TAG,"coord Y 1 :"+k+" "  +coordY.toString());
				coordY.set(k, coordY.get(k)-1);
				plateauTemp1 =plateau[(j*width + k)];
				plateau[(j*width + k)]=color;
				nbColorCheck=0;
				for(int k2=0;k2<coordY.size();k2++)
				{
	
					Log.v(TAG,"coord Y 2 :"+k+" "  +coordY.toString());
					j2 = coordY.get(k);
					if(j2>=0)
					{
						coordY.set(k2, coordY.get(k2)-1);
						plateauTemp2=plateau[(j2*width + k2)];
						plateau[(j2*width + k2)]=-color;
						nbColorCheck++;
						for(int k3=0;k3<coordY.size();k3++)
						{
							Log.v(TAG,"coord Y 3 :"+k+" "  +coordY.toString());
							j3 = coordY.get(k3);
							if(j3>=0){
								plateauTemp3 = plateau[(j3*width + k3)];
								plateau[(j3*width + k3)]=color;
								if ( CheckLineVert( k3, j3, color) || CheckLineHo( k3, j3, color)|| CheckLineDiag1( k3, j3, color)|| CheckLineDiag2( k3, j3, color))
								{	
									plateau[(j3*width + k3)]=0;
									gagne++;
									plateau[(j3*width + k3)]=plateauTemp3;
									k3 = coordY.size();
								}
								else
									plateau[(j3*width + k3)]=plateauTemp3;
							}
						}
						coordY.set(k2, coordY.get(k2)+1);
						plateau[(j2*width + k2)]=plateauTemp2;
					}
				}
				coordY.set(k, coordY.get(k)+1);
				plateau[(j*width + k)]=plateauTemp1;

			}
			}
			if ( gagne == nbColorCheck && j>=0)
			{	y=j;
				x=k;
				boolet = true;
				return true;
			}
		}
				
		if(boolet)
			return true;
		else
			return false;
	}
	

	private Boolean coupNiveau2(int color) {
		int gagne =0;
		int nbColorCheck =0;
		for(int i=0;i<width;i++)
		{
			for(int j=0;j<height;j++)
			{
				if(plateau[(j*width + i)]==0)
				{
					plateau[(j*width + i)]=color;
					nbColorCheck=0;
					for(int i2=0;i2<width;i2++)
					{
						for(int j2=0;j2<height;j2++)
						{
							if(plateau[(j2*width + i2)]==0)
							{
								plateau[(j2*width + i2)]=-color;
								nbColorCheck++;
								for(int i3=0;i<width;i3++)
								{
									for(int j3=0;j3<height;j3++)
									{
										if(plateau[(j3*width + i3)]==0)
										{
											plateau[(j3*width + i3)]=color;
											if ( CheckLineVert( i3, j3, color) || CheckLineHo( i3, j3, color)|| CheckLineDiag1( i3, j3, color)|| CheckLineDiag2( i3, j3, color))
											{	
												plateau[(j3*width + i3)]=0;
												gagne++;
												j3 =height;
												i3 = width;
											}
											plateau[(j3*width + i3)]=0;
										}
									}
								}
								plateau[(j2*width + i2)]=0;
							}
						}
						plateau[(j*width + i)]=0;
						if ( gagne == nbColorCheck)
						{	x=j;
							y=i;
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public int playCol(int col) {
		boolean ok=false;
		for(int i=col; i<(width*height);i+=col){
			if(plateau[i]==0){
				x=i;
				y=col;
				ok=true;
				break;
			}
		}
		
		// TODO Auto-generated method stub
		return 0;
	}
	
}

