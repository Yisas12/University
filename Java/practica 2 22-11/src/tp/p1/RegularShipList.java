package tp.p1;

import objects.RegularShip;

public class RegularShipList {

	RegularShip[] arrayRegular = new RegularShip[20];
	private int cont;
	
	public RegularShipList() {
		this.cont = 0;
	}
	
	/*public int update() {
		int fin = 0;
		for(int i = 0; i < this.cont && fin == 0; i++) {
			if(this.arrayRegular[i].update())
				fin = 1;
		}
		return fin;
	}*/
	
	public void anadirRegular(RegularShip nuevo) {
		arrayRegular[cont] = nuevo;
		cont++;
	}
	
	public void eliminar(RegularShip elim) {
		
		int i = buscaPos(elim);
		
		for(; i < cont - 1; i++) {
			arrayRegular[i] = arrayRegular[i + 1];
		}
		arrayRegular[i] = null;

		cont--;
	}
	
	/*public void moveDer() {
		for(int i = 0; i < this.cont; i++) {
			this.arrayRegular[i].moveDer();
		}
	}
	
	public void moveIzq() {
		for(int i = 0; i < this.cont; i++) {
			this.arrayRegular[i].moveIzq();
		}
	}
	
	public void bajar() {
		for(int i = 0; i < this.cont; i++) {
			this.arrayRegular[i].bajar();
		}
	}*/
	
	public int estaAlFinal() {
		for(int i = 0; i < this.cont; i++) {
			if(this.arrayRegular[i].getY() == 7) 
				return 1;
			else if(this.arrayRegular[i].getY() == 0)
				return 2;
		}
		return 0;
	}
	
	public int buscaPos(RegularShip b) {
		int i = 0;
		
		while(i < this.cont && arrayRegular[i] != b) 
			i++;
		
		return i;
	}
	
	public RegularShip buscar(int i, int j) {
		RegularShip regular = null;
		int k = 0;
		
		while(k < this.cont && (arrayRegular[k].getX() != i || arrayRegular[k].getY() != j))
			k++;
		
		if(k < this.cont)
			regular = this.arrayRegular[k];
		return regular;
			
	}
	
	/*public void shockwave() {
		int j = this.cont;
		for(int i = 0; i < j && this.cont > 0; i++) {
			if(arrayRegular[i].perderVida())
				i--;
		}
	}*/
	
	public int getCont() {
		return this.cont;
	}
	
	public RegularShip getRegular(int i) {
		return this.arrayRegular[i];
	}
}
