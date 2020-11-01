package tp.p1.Commands;

import tp.logic.Game;

public class MoveCommand extends Command {
	
	public static final String name = "move";
	public static final String shortcut = "m";
	public static final String details = "";
	public static final String help = "Moves the ship";
	private String[] variables = new String[2];

	public MoveCommand() {
		super(name, shortcut, details, help);

		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute(Game game) {
		// TODO Auto-generated method stub
		//game.move(variables);
		return false;
	}

	@Override
	public Command parse(String[] commandWords) {
		// TODO Auto-generated method stub
		MoveCommand comando = null;
		if(commandWords[0].equalsIgnoreCase("move") || commandWords[0].equalsIgnoreCase("m")) {
			comando = new MoveCommand();
			comando.setVariables(commandWords);
			
		}
		return comando;
	}

	public String[] getVariables() {
		return variables;
	}

	public void setVariables(String[] variables) {
		this.variables[0] = variables[1];
		this.variables[1] = variables[2];
	}
	
}
