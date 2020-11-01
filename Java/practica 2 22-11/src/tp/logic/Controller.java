package tp.logic;


import java.util.Scanner;

import tp.p1.GamePrinter;
import tp.p1.Commands.Command;
import tp.p1.Commands.CommandGenerator;

public class Controller {
	private Game game;
	private Scanner in;
	
	public Controller(Game game) {
		this.game = game;
		this.in = new Scanner(System.in);
	}
	
	public void run() {
		int exit = 0;
		int ganador = 0;
		String comando[] = null;
		Command com;
		
		while(/*!game.getFin() &&*/ exit != 1) {
			//pintas
			dibujar();
			System.out.println(game.numNaves());
			//preguntas al usuario
			System.out.println("Comando >");
			//lees el comando
			comando = in.nextLine().toLowerCase().trim().split(" ");
			
			com = CommandGenerator.parseCommmand(comando);
			
			com.execute(game);
			//exit = userCommand();
			//if(exit == 0) {
				//ejecuta la cpu
				//game.computerAction();
				//haces el update
				ganador = game.update();
			//}
			//else if(exit == 1)
				//System.out.println("Game Over");
		}
		
		/*if(game.getFin()) {
			dibujar();
			if(ganador == 2)
				System.out.println("Gana UCMShip");
			else
				System.out.println("Ganan los aliens");
		}*/
	}
	
	public void dibujar() {
		game.info();
		GamePrinter printer;
		printer = new GamePrinter(game, 7, 8);
		System.out.println(printer.toString());
	}
	
	@SuppressWarnings("null")
	public int userCommand() {
		String comando = null;
		String[] array = null;
		boolean valido = false;
		int exit = 0;
		
		array = new String[2];
		
		while(valido != true) {
			System.out.println("Comando >");
			//lees el comando
			comando = in.next();
			comando.toLowerCase();
			if(comando.equalsIgnoreCase("move") || comando.equalsIgnoreCase("m")) { 
				//vuelves a leer para la direccion y las casillas
				array[0] = in.next();
				array[1] = in.next();
				//game.move(array);
				valido = true;
			}
			else {
				//es otro comando
				if(comando.equalsIgnoreCase("shoot") || comando.equalsIgnoreCase("s")){
					//game.shoot();
					valido = true;
				}
				else if(comando.equalsIgnoreCase("shockwave") || comando.equalsIgnoreCase("w")) {
					//game.shockwave();
					valido = true;
				}
				else if(comando.equalsIgnoreCase("reset") || comando.equalsIgnoreCase("r")){
					//game.reset();
					valido = true;
					exit = 2;
				}
				else if(comando.equalsIgnoreCase("list") || comando.equalsIgnoreCase("l")) {
					game.list();
					valido = true;
				}
				else if(comando.equalsIgnoreCase("none") || comando.equalsIgnoreCase("n") || comando.equalsIgnoreCase("")) {
					valido = true;
				}
				else if(comando.equalsIgnoreCase("exit") || comando.equalsIgnoreCase("e")) {
					exit = 1;
					valido = true;
				}
				else if(comando.equalsIgnoreCase("help") || comando.equalsIgnoreCase("h")) {
					game.help();
				}
				else 
					System.out.println("El comando no existe");
			}
		}
		
		return exit;
	}

}
