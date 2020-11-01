package objects;

import tp.logic.Game;

public class UCMMissile extends Weapon{
	
	public UCMMissile(Game game, int x, int y, int vida) {
		super(game, x, y, vida);
	}
	
	public void update() {
		this.y--;
	}
}
