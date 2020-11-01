package tp.p1.Commands;

import tp.logic.Game;

public class ExitCommand extends Command {
	
	public static final String name = "exit";
	public static final String shortcut = "e";
	public static final String details = "";
	public static final String help = "Terminates the program";

	public ExitCommand() {
		super(name, shortcut, details, help);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Game game) {
		// TODO Auto-generated method stub
		game.list();
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		// TODO Auto-generated method stub
		Command comando = null;
		if(commandWords[0].equalsIgnoreCase("exit") || commandWords[0].equalsIgnoreCase("e"))
			comando = new ExitCommand();
		return comando;
	}

}
