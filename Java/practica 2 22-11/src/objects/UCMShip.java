package objects;

import tp.logic.Game;

public class UCMShip extends Ship{
	//el move de esta clase es distinto de las demás clases que extienden de Ship ya que hay que 
	// tener en cuenta los comandos y los movimientos no son los mismos
	
	
	public UCMShip(Game game, int x, int y, int vida){
		super(game, x, y, vida);
	}
	
	public boolean perderVida() {
		this.vida--;
		
		if(this.vida == 0) {
			//game.eliminarElemento("UCMShip", this.x, this.y);
			return true;
		}
		else return false;
	}
	
	public void move(String[] array) {
		int pos = Integer.parseInt(array[1]);
		
		if(pos >= 1 && pos <= 2) {
			if(array[0].equalsIgnoreCase("left") || array[0].equalsIgnoreCase("l")) {
				if(this.getY() - pos >= 0) {
						this.y -= pos;
				}
				else
					System.out.println("No se puede mover");
			}
			else if(array[0].equalsIgnoreCase("right") || array[0].equalsIgnoreCase("r")) {
				if(this.getY() + pos <= 7) {
					this.y += pos;	
			}
			else
				System.out.println("No se puede mover");
			}
		}
		else
			System.out.println("Pos incorrecta");
	}
	
	public String toString() {
		return "-.-[" + this.vida + "]";
	}


	public String dead() {
		return "!xx!";
	}
}
