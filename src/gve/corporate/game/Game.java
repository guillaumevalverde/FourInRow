package gve.corporate.game;

import gve.corporate.player.Player;

public class Game  extends Thread{
	Player player1;
	Player player2;
	matricePuissance4 board;
	int state;// 1 player1 need to play, 2 player2 need to play 3 player1 won, 4 player2 won, 5 it is a draw;
	
	public void Game(int state){
		board =  new matricePuissance4();
		if(state==1){
			this.state=1;
		}
		else
			this.state=2;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		while(state<3) // means we are still playing
		{
			if(state==1)
				state = player1.play();
			else if(state==2)
				state = player2.play()
		}
	}

}
