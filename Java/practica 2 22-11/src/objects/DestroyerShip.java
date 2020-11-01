package objects;

import tp.logic.Game;

public class DestroyerShip extends AlienShip{
	

	
	public DestroyerShip(Game game, int x, int y, int vida) {
		super(game, x, y, vida);
	}

	//Funcion que tenga metodos de bomb
	
	public String toString() {
		return "D[" + this.vida + "]";
	}
	
}
