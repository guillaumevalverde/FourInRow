package gve.corporate.player;
import gve.corporate.game.matricePuissance4;

public class PlayerComputer extends Player {
	
	int level;
	public PlayerComputer(String name, matricePuissance4 board, int wichPlayer, int level) {
		super( name, board, wichPlayer);
		this.level=level;
		// TODO Auto-generated constructor stub
	}


	@Override
	public int play(int col) {
		// TODO Auto-generated method stub
		return 0;
	};
	
	@Override
	public int play(int i, int j) {
		int[] coord = board.coupSuivant(colorPlayer, level);
		int ii,jj;
		ii=coord[0];
		jj=coord[1];
		int status = board.setColor(ii, jj, this.colorPlayer);
		return status;
	};


}