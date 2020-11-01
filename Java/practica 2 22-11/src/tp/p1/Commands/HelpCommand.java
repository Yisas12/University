package tp.p1.Commands;

import tp.logic.Game;

public class HelpCommand extends Command {
	
	public static final String name = "help";
	public static final String shortcut = "h";
	public static final String details = "";
	public static final String help = "Print the help message";

	public HelpCommand() {
		super(name, shortcut, details, help);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Game game) {
		// TODO Auto-generated method stub
		game.help();
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		// TODO Auto-generated method stub
		Command comando = null;
		if(commandWords[0].equalsIgnoreCase("help") || commandWords[0].equalsIgnoreCase("h"))
			comando = new HelpCommand();
		return comando;
	}

}
