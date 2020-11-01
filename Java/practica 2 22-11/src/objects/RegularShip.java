package objects;

import tp.logic.Game;

public class RegularShip extends AlienShip{

	public RegularShip(Game game, int x, int y, int vida) {
		super(game, x, y, vida);
	}

	//Funcion que tenga metodos de bomb
	
	public String toString() {
		return "R[" + this.vida + "]";
	}
}
