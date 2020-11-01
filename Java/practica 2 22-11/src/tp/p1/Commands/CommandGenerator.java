package tp.p1.Commands;

public class CommandGenerator {
	
	private static Command[] availableCommands = {
			new ListCommand(),
			new HelpCommand(),
			new ResetCommand(),
			new ExitCommand(),
			//new UpdateCommmand(),
			new MoveCommand(),
			new ShockwaveCommand()
	};
	
	public static Command parseCommmand(String[] commandWords) {
		Command comand;
		
		if(commandWords.length == 0 || commandWords.length > 4)
			return null;
		else {
			for(Command c : availableCommands) {
				comand = c.parse(commandWords);
				if(comand != null)
					return comand;
			}
			return null;
		}
	}
	
	public static String commmandHelp() {
		StringBuilder str = new StringBuilder();
		for (Command c : availableCommands) {
			str.append(c.helpText() + System.lineSeparator());
		}
		return str.toString();
	}
}
