package objects;

import tp.logic.Game;

public class Ovni extends EnemyShip{
	
	public Ovni(Game game, int x, int y, int vida) {
		super(game, x, y, vida);
	}

	/*public boolean update1() {
		if(x == 6)
			return true;
		else return false;
		
	}
	
	public int update() {
		int fin = 0;
		for(int i = 0; i < this.cont && fin == 0; i++) {
			if(this.array[i].update1())
				fin = 1;
		}
		return fin;
	}
	
	public void anadirRegular(RegularShip nuevo) {
		array[cont] = nuevo;
		cont++;
	}
	
	public void eliminar(RegularShip elim) {
		
		int i = buscaPos(elim);
		
		for(; i < cont - 1; i++) {
			array[i] = array[i + 1];
		}
		array[i] = null;

		cont--;
	}
	
	public void moveDer() {
		for(int i = 0; i < this.cont; i++) {
			this.array[i].moveDer();
		}
	}
	
	public void moveIzq() {
		for(int i = 0; i < this.cont; i++) {
			this.array[i].moveIzq();
		}
	}
	
	public void bajar() {
		for(int i = 0; i < this.cont; i++) {
			this.array[i].bajar();
		}
	}
	
	public int estaAlFinal() {
		for(int i = 0; i < this.cont; i++) {
			if(this.array[i].getY() == 7) 
				return 1;
			else if(this.array[i].getY() == 0)
				return 2;
		}
		return 0;
	}
	
	public int buscaPos(RegularShip b) {
		int i = 0;
		
		while(i < this.cont && array[i] != b) 
			i++;
		
		return i;
	}
	public void shockwave() {
		int j = this.cont;
		for(int i = 0; i < j && this.cont > 0; i++) {
			if(array[i].perderVida())
				i--;
		}
	}
	
	public int getCont() {
		return this.cont;
	}
	public void moveDer() {
		this.y++;
	}
	
	public void moveIzq() {
		this.y--;
	}
	
	public void bajar() {
		this.x++;
	}
	
	public boolean perderVida() {
		this.vida--;
		
		if(this.vida == 0) {
			game.eliminarElemento("RegularShip", this.x, this.y);
			return true;
		}
		else return false;
	}
	
	public int getVida() {
		return vida;
	}
	public void setVida(int vida) {
		this.vida = vida;
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


	public boolean isNull() {
		return this == null;
	}*/

}
