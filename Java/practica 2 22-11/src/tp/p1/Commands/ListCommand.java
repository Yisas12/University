package tp.p1.Commands;

import tp.logic.Game;

public class ListCommand extends Command {

	public static final String name = "list";
	public static final String shortcut = "l";
	public static final String details = "";
	public static final String help = "List the types of ships";
	
	public ListCommand() {
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
		if(commandWords[0].equalsIgnoreCase("list") || commandWords[0].equalsIgnoreCase("l"))
			comando = new ListCommand();
		return comando;
	}

}
