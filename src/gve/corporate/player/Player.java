package gve.corporate.player;

import com.gvestyle.FourInRowActivity;

import gve.corporate.game.matricePuissance4;

public abstract class Player {
	String name;
	int colorPlayer;
	int state=0;
	int nb_victoire = 0;
	int nb_defaite =0 ;
	matricePuissance4 board;
	
	
	Player(String name, matricePuissance4 board, int wichPlayer){
		
	
		this.name = name;
		this.colorPlayer=wichPlayer;
		this.board = board;
	}
	public void increaseVictory()
	{
	nb_victoire++;
	}
	public void increaseDefeat()
	{
	nb_defaite++;
	}
	public void setName(String Name){
		this.name = Name;
	}
	public void setDefaite(int def){
		nb_defaite = def;
	}
	public void setVictory(int def){
		nb_victoire = def;
	}
	public abstract int play(int col);
	public abstract int play(int i, int j);

	public int getcolor() {
		// TODO Auto-generated method stub
		return colorPlayer;
	}
	public void setcolor(int c) {
		// TODO Auto-generated method stub
		 this.colorPlayer =c;
	}
}
