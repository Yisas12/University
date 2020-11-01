package tp.p1;

import objects.DestroyerShip;

public class DestroyerShipList {

	DestroyerShip[] arrayDestroyer = new DestroyerShip[20];
	private int cont;
	
	public DestroyerShipList() {
		this.cont = 0;
	}
	
	/*public int update() {
		int fin = 0;
		for(int i = 0; i < this.cont && fin == 0; i++) {
			if(this.arrayDestroyer[i].update())
				fin = 1;
		}
		return fin;
	}*/
	
	public void anadirDestroyer(DestroyerShip nuevo) {
		arrayDestroyer[cont] = nuevo;
		cont++;
	}
	
	/*public void moveDer() {
		for(int i = 0; i < this.cont; i++) {
			this.arrayDestroyer[i].moveDer();
		}
	}*/
	
	/*public void moveIzq() {
		for(int i = 0; i < this.cont; i++) {
			this.arrayDestroyer[i].moveIzq();
		}
	}*/
	
	/*public void bajar() {
		for(int i = 0; i < this.cont; i++) {
			this.arrayDestroyer[i].bajar();
		}
	}*/
	
	public void eliminar(DestroyerShip elim) {
		
		int i = buscarPos(elim);
		
		for(; i < cont - 1; i++) {
			arrayDestroyer[i] = arrayDestroyer[i + 1];
		}
		arrayDestroyer[i] = null;
		cont--;
	}
	
	public int buscarPos(DestroyerShip b) {
		int i = 0;
		
		while(i < this.cont && arrayDestroyer[i] != b) 
			i++;
		
		if(i < cont)
			return i;
		else return -1;
	}
	
	public DestroyerShip buscar(int i, int j) {
		DestroyerShip destroyer = null;
		int k = 0;
		
		while(k < this.cont && (arrayDestroyer[k].getX() != i || arrayDestroyer[k].getY() != j))
			k++;
		
		if(k < this.cont)
			destroyer = this.arrayDestroyer[k];
		return destroyer;
	}
	
	/*public void shockwave() {
		int j = this.cont;
		for(int i = 0; i < j && this.cont > 0; i++) {
			if(arrayDestroyer[i].perderVida())
			i--;
		}
	}*/
	
	public int getCont() {
		return this.cont;
	}
	
	public DestroyerShip getDestroyer(int i) {
		return this.arrayDestroyer[i];
	}
}
