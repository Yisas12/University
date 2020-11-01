package objects;

import objects.GameObject;

public class GameObjectBoard {

	private GameObject[] list = new GameObject[30];
	private int cont;
	
	public GameObjectBoard() {
		this.cont = 0;
	}
	
	private int getCurrentObjects () {
			return this.cont;
	}
	
	public void add (GameObject object) {
		this.list[this.cont] = object;
		this.cont++;
	}
		
	public GameObject getObjectInPosition (int x, int y) {
		int i = 0;
		//faltan cosas en la condicion
		while(i < this.cont && (x != this.list[i].x || y != this.list[i].y))
			i++;
		
		if(i < this.cont)
			return this.list[i];
		
		return null;
	}
	private int getIndex(int x, int y) {
		return y;
	// TODO implement
			
	}
	
	private void remove (GameObject object) {
	// TODO implement
		int i = pos(object);
		
		if(i >= 0) {
			for(; i < cont - 1; i++) {
				this.list[i] = this.list[i + 1];
			}
			this.list[i] = null;
			this.cont--;	
		}
	}
	
	private int pos (GameObject object) {
		int i = 0;
		
		while(i < this.cont && this.list[i] != object) 
			i++;
		
		if(i < this.cont)
			return i;
		else return -1;
	}
	
	/*public void update() {
	// TODO implement
		for(int i = 0; i < this.cont; i++) {
			this.list[i].update();
		}
	}*/
	
	public void move() {
		for(int i = 0; i < this.cont; i++) {
			if(list[i].getX() == 7) {
				list[i].setDireccion(true);
				i = this.cont;
			}
			else if(list[i].getX() == 0) {
				list[i].setDireccion(false);
				i = this.cont;
			}
		}
		
		for(int i = 0; i < this.cont; i++)
			this.list[i].move();
	}
	
	public void comprobar() {
		
	}
	private void checkAttacks(GameObject object) {
	// TODO implement
			
	}
	public void computerAction() {
	// TODO implement
			
	}
	private void removeDead() {
	// TODO implement
			
	}

	public int getCont() {
		return cont;
	}

	public void setCont(int cont) {
		this.cont = cont;
	}
	
	
		
}
