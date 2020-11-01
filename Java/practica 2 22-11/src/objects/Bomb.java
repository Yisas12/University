package objects;

import tp.logic.Game;

public class Bomb extends Weapon{

	public Bomb(Game game, int x, int y, int vida) {
		super(game, x, y, vida);
	}
	
	public void update() {
		this.y++;
		//falta
	}
	
}
