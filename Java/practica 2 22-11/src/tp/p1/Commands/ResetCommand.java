package tp.p1.Commands;

import tp.logic.Game;

public class ResetCommand extends Command {

	public static final String name = "reset";
	public static final String shortcut = "r";
	public static final String details = "";
	public static final String help = "Restart the game";
	
	public ResetCommand() {
		super(name, shortcut, details, help);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Game game) {
		// TODO Auto-generated method stub
		//game.reset();
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		// TODO Auto-generated method stub
		Command comando = null;
		if(commandWords[0].equalsIgnoreCase("reset") || commandWords[0].equalsIgnoreCase("r"))
			comando = new ResetCommand();
		return comando;
	}

}
