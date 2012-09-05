package gve.corporate.player;

import gve.corporate.game.matricePuissance4;

public class PlayerOnLine extends Player {
	
	
	public PlayerOnLine(String name, matricePuissance4 board, int wichPlayer) {
		super( name, board, wichPlayer);
		// TODO Auto-generated constructor stub
	}


	

	@Override
	public int play(int col) {
		// TODO Auto-generated method stub
		return 0;
	};

	
	@Override
	public int play(int i, int j) {
		int status = board.setColor(i, j, this.colorPlayer);
		return status;
	};

}