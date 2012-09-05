package gve.corporate.game;

import gve.corporate.player.PlayerReal;

public class GameOnPhone extends Game {
	
	public GameOnPhone(){
		super();
		player1 = new PlayerReal(null, board, state);
		player2 = new PlayerReal(null, board, state);
		
	}

}
