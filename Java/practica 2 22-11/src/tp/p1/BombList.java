package tp.p1;

import objects.Bomb;

public class BombList {

	Bomb[] arrayBomb = new Bomb[20];
	private int cont;
	
	public BombList() {
		this.cont = 0;
	}
	
	/*public boolean update(int x, int y) {
		boolean ataca = false;
		for(int i = 0; i < this.cont; i++) {
			if(!this.arrayBomb[i].update()) {
				if(this.arrayBomb[i].bombAtaca(x, y)) {
					i--;
					ataca = true;
				}
			}
		}
		return ataca;
	}*/
	
	public void anadirBomb(Bomb nuevo) {
		arrayBomb[cont] = nuevo;
		cont++;
	}
	
	public void eliminar(Bomb elim) {
		
		int i = buscarPos(elim);
		
		for(; i < this.cont - 1; i++) {
			arrayBomb[i] = arrayBomb[i + 1];
		}
		arrayBomb[i] = null;
		cont--;
	}
	
	public int buscarPos(Bomb b) {
		int i = 0;
		
		while(i < this.cont && arrayBomb[i] != b) 
			i++;
		return i;
	}
	
	public Bomb buscar(int i, int j) {
		int k = 0;
		
		while(k < this.cont && (arrayBomb[k].getX() != i || arrayBomb[k].getY() != j))
			k++;
		
		if(k < this.cont)
			return arrayBomb[k];
		else return null;
	}
	
	/*public boolean yaHay(int j) {
		for(int i = 0; i < this.cont; i++) {
			if(this.arrayBomb[i].getDes() == j)
				return true;
		}
		return false;
	}*/
	
	public int getCont() {
		return this.cont;
	}
	
	public Bomb getBomb(int i) {
		return this.arrayBomb[i];
	}
}
