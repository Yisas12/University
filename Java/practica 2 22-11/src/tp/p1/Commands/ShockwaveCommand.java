package tp.p1.Commands;

import tp.logic.Game;

public class ShockwaveCommand extends Command {

	public static final String name = "shockwave";
	public static final String shortcut = "s";
	public static final String details = "";
	public static final String help = "Produce 1p dammage to each ship";
	
	public ShockwaveCommand() {
		super(name, shortcut, details, help);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Game game) {
		// TODO Auto-generated method stub
		//game.shockwave();
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		// TODO Auto-generated method stub
		Command comando = null;
		if(commandWords[0].equalsIgnoreCase("Shockwave") || commandWords[0].equalsIgnoreCase("w"))
			comando = new ShockwaveCommand();
		return comando;
	}

}
