//@@author A0125369Y
package parser;

import processor.COMMANDS;

public class Predictive {

	private static final String ACK_TIP = "Ack/Acknowledge <Item Name> : Marks an item as completed";
	private static final String ADD_TIP = "Add/Insert <Item Name> -d <Starting Date> <Ending Date> -i <Details>";
	private static final String COMPLETED_TIP = "Completed : Displays the list of completed items";
	private static final String DELETE_TIP = "Remove/Delete <Item Index>";
	private static final String EDIT_TIP = "Edit/Change <Item Index> <Task Name> -d <Starting Date> <Ending Date> -i <Details>";
	private static final String FILEPATH_TIP = "Filepath <Directory Path> : Changes file location. Note: Exclude file extension";
	private static final String HOME_TIP = "Home : Displays the original list";
	private static final String SEARCH_TIP = "Find/Search <Keywords> : Displays the list of items containing the keywords";
	private static final String NAME_TIP = "Name <User Name> : Changes the user name";
	private static final String UNDO_TIP = "Undo : Reverts your last changes.";
//	private static final String EXPAND_TIP = "";
//	private static final String HELP_TIP = "";
//	private static final String EXIT_TIP = "";
	private static final String EMPTY = "";
	
	private String taskName;
	private String taskDetails;
	private String taskStart;
	private String taskEnd;
	
	public Predictive(){
		
	}
	
	public String processInput(String input) {
		String[] inputPieces = input.split(" ");
		COMMANDS command;
		if (inputPieces.length == 1){
			command = unstrictCommand(inputPieces[0]);
		} else {
			Parser parser = new Parser();
			parser.parse(input);
			command = parser.getCommand();
			this.setTaskName(parser.getTaskName());
			this.setTaskDetails(parser.getDetails());
			if(parser.getStartDate() != null){
				this.setTaskStart(parser.getStartDate().toString());
			}
			if(parser.getEndDate() != null){
				this.setTaskEnd(parser.getEndDate().toString());
			}
		}

		switch (command) {
		case ACK:
			return ACK_TIP;
		case ADD:
			return ADD_TIP;
		case COMPLETED:
			return COMPLETED_TIP;
		case DELETE:
			return DELETE_TIP;
		case EDIT:
			return EDIT_TIP;
		case FILEPATH:
			return FILEPATH_TIP;
		case HOME:
			return HOME_TIP;
		case SEARCH:
			return SEARCH_TIP;
		case NAME:
			return NAME_TIP;
		case UNDO:
			return UNDO_TIP;
		default:
			return EMPTY;
		}
	}
	
	private COMMANDS unstrictCommand(String stringCmd) {
		stringCmd = stringCmd.toLowerCase();
		stringCmd = stringCmd.trim();
		if (stringCmd.length() == 0) {
			return COMMANDS.INVALID;
		} else if (Parser.ADD.contains(stringCmd)) {
			return COMMANDS.ADD;
		} else if (Parser.DELETE.contains(stringCmd)) {
			return COMMANDS.DELETE;
		} else if (Parser.EDIT.contains(stringCmd)) {
			return COMMANDS.EDIT;
		} else if (Parser.ACK.contains(stringCmd)) {
			return COMMANDS.ACK;
		} else if (Parser.SEARCH.contains(stringCmd)) {
			return  COMMANDS.SEARCH;
		} else if (Parser.HOME.contains(stringCmd)) {
			return  COMMANDS.HOME;
		} else if (Parser.UNDO.contains(stringCmd)) {
			return  COMMANDS.UNDO;
		} else if (Parser.EXPAND.contains(stringCmd)) {
			return  COMMANDS.EXPAND;
		} else if (Parser.COMPLETED.contains(stringCmd)) {
			return  COMMANDS.COMPLETED;
		} else if (Parser.HELP.contains(stringCmd)) {
			return  COMMANDS.HELP;
		} else if (Parser.NAME.contains(stringCmd)) {
			return  COMMANDS.NAME;
		} else if (Parser.FILEPATH.contains(stringCmd)) {
			return  COMMANDS.FILEPATH;
		} else {
			return  COMMANDS.INVALID;
		}
	}

	public String getTaskName() {
		String returnValue = taskName;
		taskName = null;
		return returnValue;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDetails() {
		String returnValue = taskDetails;
		taskDetails = null;
		return returnValue;
	}

	public void setTaskDetails(String taskDetails) {
		this.taskDetails = taskDetails;
	}

	public String getTaskStart() {
		String returnValue = taskStart;
		taskStart = null;
		return returnValue;
	}

	public void setTaskStart(String taskStart) {
		this.taskStart = taskStart;
	}

	public String getTaskEnd() {
		String returnValue = taskEnd;
		taskEnd = null;
		return returnValue;
	}

	public void setTaskEnd(String taskEnd) {
		this.taskEnd = taskEnd;
	}
}
