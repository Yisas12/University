package objects;

import tp.logic.Game;

public abstract class GameObject {
	protected Game game;
	protected int x;
	protected int y;
	protected int vida;
	protected boolean direccion;
	//private int cycles;
	//private int remainingCycles;


	//con el ucm tiene una direccion, en el controller le ponemos move left, en el gameobject tendria dir y velocidad y le pasamos al ucm lo que necesite
	//direccion enumerado si es derecha le sumo uno a la columna y en el enemyship le ponemos direccino izq
	public GameObject(Game game, int x, int y, int vida) {
		this.game = game;
		this.x = x;
		this.y = y;
		this.vida = vida;
		this.direccion = false; // derecha
		
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public int getVida() {
		return this.vida;
	}
	
	public void setVida(int vida) {
		this.vida = vida;
	}
		
	public boolean isOnPosition(int x, int y) {
	return this.x == x && this.y == y;
	}
	
	/*public boolean isOut() {
	return !game.isOnBoard(x, y);
	}*/
	
	
	public abstract void computerAction();
	
	public boolean isDireccion() {
		return direccion;
	}

	public void setDireccion(boolean direccion) {
		this.direccion = direccion;
	}

	public abstract void onDelete();
	public abstract void move();
	public abstract String toString();

}
