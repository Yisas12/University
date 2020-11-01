package tp.logic;

import objects.DestroyerShip;
import objects.GameObjectBoard;
import objects.RegularShip;

public class BoardInitializer {
	
	protected Level level;
	protected GameObjectBoard board;
	protected Game game;
	
	public GameObjectBoard initialize(Game game, Level level) {
		this.level = level;
		this. game = game;
		board = new GameObjectBoard();
		//initializeOvni();
		initializeRegularAliens();
		initializeDestroyerAliens();
		return board;
	}
	private void initializeOvni () {
		//crear un ovni
	}
	private void initializeRegularAliens () {
		int i = 0;
		int filas = 0;
		int x = 1; int y = 2;
		
		while(i < level.getComunes() /*&& filas < level.getFilas()*/) {
			//aniadir
			board.add(new RegularShip(game, x, y, 1));
			y++;
			i++;
			//falta la segunda fila si el nivel es chungo
		}
	}
	private void initializeDestroyerAliens () {
		int i = 0;
		int x = 2; int y = 3;
		
		while(i < level.getDestructoras()) {
			board.add(new DestroyerShip(game, x, y, 1));
			i++;
			y++;
		}
	}
	
	
}
