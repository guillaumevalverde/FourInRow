package gve.corporate.game;

import gve.corporate.player.PlayerComputer;
import gve.corporate.player.PlayerOnLine;
import gve.corporate.player.PlayerReal;

public class GameOnLine  extends Game {
	
	public GameOnLine(){
		super();
		player1 = new PlayerReal(null, board, state);
		player2 = new PlayerOnLine(null, board, state);
		
	}

}
