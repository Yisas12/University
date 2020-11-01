package tp.logic;

import java.util.Random;

import objects.GameObject;
import objects.GameObjectBoard;
import objects.UCMShip;



public class Game {

	private GameObjectBoard objects;
	private UCMShip ucmShip;
	private BoardInitializer ini;
	Level nivel;
	int semilla;
	Random rand;
	private int puntos;
	private int ciclos;
	private int n;
	private int fin;
	//GamePrinter printer;
	final int ROWS = 7;
	final int COLS = 8;
	
	public Game(Level level, int seed) {
		this.objects = new GameObjectBoard();
		this.ini = new BoardInitializer();
		this.nivel = level;
		this.semilla = seed;
		this.puntos = 0;
		this.ciclos = 0;
		this.n = 0;
		this.fin = 0;
		this.rand = new Random();
		
		//inicializaGame();
		
		objects = ini.initialize(this, level);
		
		this.ucmShip = new UCMShip(this, 6, 3, 3);
		this.objects.add(this.ucmShip);
	}
	
	/*public void inicializaGame() {
		int x = 1; int y = 2; int i = 0; 
		
		while(this.regularList.getCont() < nivel.getComunes()) {
				this.regularList.anadirRegular(new RegularShip(2, x, y, 5, this));
				y++;
				i++;
				if(i == 4 && nivel.getComunes() > i) {
					x++;
					y = 2;
				}
		}
		x++;

		if(nivel.toString().equalsIgnoreCase("INSANE"))
			y = 2;
		else
			y = 3;
		while(this.destroyerList.getCont() < nivel.getDestructoras()) {
			this.destroyerList.anadirDestroyer(new DestroyerShip(1, x, y, 1, 10, this));
			y++;
		}
	}*/
	
	public int update() {
		//move();
		this.objects.move();
		return 1;
	}
		/*
		//update bombUCM
		if(bombUCM != null) {
			if(!bombUCM.update()) {				
				DestroyerShip dest;
				RegularShip reg;
				Bomb bomb;
						
					if((dest = destroyerList.buscar(bombUCM.getX(), bombUCM.getY())) != null) {
						if(dest.perderVida())
							this.puntos += dest.getPuntos();
							bombUCM = null;
						}
						else if((reg = regularList.buscar(bombUCM.getX(), bombUCM.getY())) != null) {
							if(reg.perderVida())
								this.puntos += reg.getPuntos();
							bombUCM = null;
						}
						else if((bomb = bombList.buscar(bombUCM.getX() - 1, bombUCM.getY())) != null || (bomb = bombList.buscar(bombUCM.getX(), bombUCM.getY())) != null) {
							bombList.eliminar(bomb);
							bombUCM = null;
						}
						else if(this.alienShip != null) {
							if(alienShip.getX() == bombUCM.getX() && alienShip.getY() == bombUCM.getY()) {
								bombUCM = null;
								int puntos = alienShip.getPuntos();
								if(alienShip.perderVida()) {
									this.puntos += puntos;
									ucmShip.setShockwave(true);
								}
							}
						}
					}
					else
						bombUCM = null;
		}
		
		//update regularList
		this.fin = this.regularList.update();
		
		//update destroyerList
		if(this.fin == 0)
		this.fin = this.destroyerList.update();
	
		//update ovni
		if(this.fin == 0) {
		if(alienShip != null)
			alienShip.update();
		}
		
		if(this.bombList.update(ucmShip.getX(), ucmShip.getY())) {
			if(ucmShip.perderVida())
				this.fin = 1;
		}
		
		
		
		if(regularList.getCont() + destroyerList.getCont() == 0)
			fin = 2;
		
		aumentarCiclos();
		return fin;
	
	}
	
	public boolean getFin() {
		
		if(fin == 0)
			return false;
		else return true;
	}
	
	public void move(String[] array) {
		ucmShip.move(array);
	}
	
	public void shoot() {
		if(this.bombUCM == null)
		this.bombUCM = new bombUCM(ucmShip.getX(), ucmShip.getY());
		else System.out.println("Ya hay un misil lanzado, no puedes disparar");
	}*/
	
	/*public void shockwave() {
		if(ucmShip.isShockwave()) {
			regularList.shockwave();
			destroyerList.shockwave();
			if(alienShip != null)
			alienShip.perderVida();
			ucmShip.setShockwave(false);
		}
	}*/
	
	/*public void reset() {
		this.regularList = new RegularShipList();
		this.destroyerList = new DestroyerShipList();
		this.bombList = new BombList();
		this.ucmShip = new UCMShip(3, 6, 3, 1, this);
		this.alienShip = null;
		this.puntos = 0;
		this.ciclos = 0;
		this.fin = 0;
		inicializaGame();
	}*/
	
	public void list() {
		System.out.println("[R]egular ship: Points: 5 - Harm: 0 - Shield: 2");
		System.out.println("[D]estroyer ship: Points: 10 - Harm: 1 - Shield: 1");
		System.out.println("[O]vni: Points: 25 - Harm: 0 - Shield: 1");
		System.out.println("^__^: Harm: 1 - Shield: 3");
	}
	
	public void help() {
		System.out.println("move <left|right><1|2>: Moves UCM-Ship to the indicated direction");
		System.out.println("shoot: UCM-Ship launches a missile.");
		System.out.println("shockWave: UCM-Ship releases a shock wave.");
		System.out.println("list: Prints the list of available ships.");
		System.out.println("reset: Starts a new game.");
		System.out.println("help: Prints this help message.");
		System.out.println("exit: Terminates the program.");
		System.out.println("[none]: Skips one cycle.\")");
	}

	public String toStringObjectAt(int i, int j) {
		GameObject obj = null;
		String casilla = " ";
		
		obj = this.objects.getObjectInPosition(i, j);
		
		if(obj != null)
			casilla = obj.toString();
		//buscar el objeto y piintarlo
		return casilla;
	}
	
	public int numNaves() {
		return objects.getCont();
	}
/*
	//como el computerAction se realiza antes que el update, puede ocurrir que 
	//si la nave dispara justo cuando tiene que moverse hacia abajo, tanto el misil
	//como la nave esten en la misma casilla durante un ciclo
	public void computerAction() {
		for(int i = 0; i < destroyerList.getCont(); i++) {
			if(puedeDisparar() && !this.bombList.yaHay(i)) {
				this.bombList.anadirBomb(new Bomb(destroyerList.arrayDestroyer[i].getX(), destroyerList.arrayDestroyer[i].getY(), i, this));
			}
		}
		if(puedeOvni() && this.alienShip == null)
			this.alienShip = new AlienShip(1, 0, 8, 25, this);
		
	}

	public boolean puedeDisparar() {
		if(this.nivel.toString().equalsIgnoreCase("EASY")) {
			int n = this.rand.nextInt(5);
			if(n < 1) {
				return true;
			}			
			else return false;
		}
		else if(this.nivel.toString().equalsIgnoreCase("HARD")) {
			int n = this.rand.nextInt(10);
			if(n < 4) {
				return true;
			}
			else return false;
		}
		else {
			int n = this.rand.nextInt(20);
			if(n < 11) {
				return true;
			}
			else return false;
		}
	}
	
	public boolean puedeOvni() {
		if(this.nivel.toString().equalsIgnoreCase("EASY")) {
			int n = this.rand.nextInt(20);
			if(n < 11) {
				return true;
			}			
			else return false;
		}
		else if(this.nivel.toString().equalsIgnoreCase("HARD")) {
			int n = this.rand.nextInt(5);
			if(n < 1) {
				return true;
			}
			else return false;
		}
		else {
			int n = this.rand.nextInt(10);
			if(n < 1) {
				return true;
			}
			else return false;
		}
	}
	
	public boolean eliminarElemento(String elemento, int x, int y) {
		RegularShip regular;
		DestroyerShip destroyer;
		Bomb bomb;
		
		if(elemento.equalsIgnoreCase("RegularShip")) {
			regular = this.regularList.buscar(x, y);
			this.regularList.eliminar(regular);
		}
		else if(elemento.equalsIgnoreCase("DestroyerShip")) {
			destroyer = this.destroyerList.buscar(x,  y);
			this.destroyerList.eliminar(destroyer);
		}
		else if(elemento.equalsIgnoreCase("bomb")) {
			bomb = this.bombList.buscar(x,  y);
			this.bombList.eliminar(bomb);
		}
		else if(elemento.equalsIgnoreCase("UCMShip")) {
			return true;
		}
		else if(elemento.equalsIgnoreCase("AlienShip")) {
			this.alienShip = null;
		}
		return false;
		
	}
	
	public int avanza() {
		if(this.ciclos % nivel.getVelocidad() == 0) {
			if(this.regularList.estaAlFinal() == 1 || this.regularList.estaAlFinal() == 2) {
				if(n % 2 == 0)
					avanzaIzq();
				else avanzaDer();
				bajar();
				this.n++;
			}
			else {
				if(n % 2 == 0)
					avanzaDer();
				else avanzaIzq();
			}
		}
		return n;
	}
	
	public void avanzaDer() {
		this.regularList.moveDer();
		this.destroyerList.moveDer();
	}
	
	public void avanzaIzq() {
		this.regularList.moveIzq();
		this.destroyerList.moveIzq();
	}
	
	public void bajar() {
		this.regularList.bajar();
		this.destroyerList.bajar();
	}
	
	public int aumentarCiclos() {
		return this.ciclos++;
	}*/
	
	public void info() {
		//System.out.println("Life: " + ucmShip.getVida());
		//System.out.println("Number of cicles: " + this.ciclos);
		//System.out.println("Points: " + this.puntos);
		//int n = this.regularList.getCont() + this.destroyerList.getCont();
		//System.out.println("Remaining aliens: " + n);
		/*if(ucmShip.isShockwave())
			System.out.println("Shockwave: SI");
		else
			System.out.println("Shockwave: NO");*/
	}

}
