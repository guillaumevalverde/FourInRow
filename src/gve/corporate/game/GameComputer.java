package gve.corporate.game;

import gve.corporate.player.PlayerComputer;
import gve.corporate.player.PlayerReal;

public class GameComputer extends Game {
	
	public GameComputer(){
		super();
		player1 = new PlayerReal("", board, state);
		player2 = new PlayerComputer("", board, state);
		
	}

}
