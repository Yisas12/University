package tp.logic;

public enum Level {
	
	EASY(4, 2, 0.1, 3, 0.5, 1),
	HARD(8, 2, 0.3, 2, 0.2, 2),
	INSANE(8, 4, 0.5, 1, 0.1, 2);
	
	private int comunes;
	private int destructoras;
	private double frec;
	private int velocidad;
	private double ovni;
	private int filas;

	
	Level (int comunes, int destructoras, double frec, int velocidad, double ovni, int filas) {
		this.comunes = comunes;
		this.destructoras = destructoras;
		this.frec = frec;
		this.velocidad = velocidad;
		this.ovni = ovni;
		this.filas = filas;
	}
	
	public static Level crearNivel(String nivel) {
		Level level = null;
		
		if(nivel.equalsIgnoreCase("EASY")) 
			level = EASY;
		else if(nivel.equalsIgnoreCase("HARD")) 
			level = HARD;
		else 
			level = INSANE;
		
		return level;
	}
	
	public int getComunes() {
		return this.comunes;
	}
	
	public int getDestructoras() {
		return this.destructoras;
	}
	
	public int getVelocidad() {
		return this.velocidad;
	}
	
	public double getFrec() {
		return this.frec;
	}
	
	public int getFilas() {
		return this.filas;
	}

	public double getOvni() {
		return ovni;
	}
}
