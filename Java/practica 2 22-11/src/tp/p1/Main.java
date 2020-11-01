package tp.p1;

import tp.logic.Controller;
import tp.logic.Game;
import tp.logic.Level;

public class Main {

	public static void main(String[] args) {
		Level level = null;
		int seed = 0;

		if(args.length > 0) {

			level = Level.crearNivel(args[0]);
			seed = Integer.parseInt(args[1]);
			
			Game game = new Game(level, seed);
			
			Controller controller = new Controller(game);
			controller.run();
		}
		
	}
	
	
}

