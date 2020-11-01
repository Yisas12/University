package objects;

import tp.logic.Game;

public class AlienShip extends EnemyShip{
	
	protected boolean direccion;
	
	public AlienShip(Game game, int x, int y, int vida) {
		super(game, x, y, vida);
		this.direccion = false;
	}
	
	public void move() {
		if(this.direccion == false) //derecha
			this.setY(this.getY() + 1);
		else{
			this.setY(this.getY() - 1);
		}
		
	}
	
	public void update() {
		
	}
	
}
