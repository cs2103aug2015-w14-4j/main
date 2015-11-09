//@@author A0125369Y
package parser;

import java.text.SimpleDateFormat;
import java.util.Date;

import utilities.COMMANDS;

public class Predictive {

	private static final String DATE_FORMAT = "dd MMMM yyyy hh:mm a";
	
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
	// private static final String EXPAND_TIP = "";
	// private static final String HELP_TIP = "";
	private static final String EXIT_TIP = "Exit : Closes the program";
	private static final String EMPTY = "";
	
	private static final String ADD_MSG = "Adding Task...";
	private static final String EDIT_MSG = "Editing Task...";
	private static final String DELETE_MSG = "Delete Task?";
	
	private COMMANDS command;
	private int index;
	private String commandMsg;
	private String taskName;
	private String taskDetails;
	private String taskStart;
	private String taskEnd;

	public Predictive() {

	}

	public String processInput(String input) {
		this.resetValues();
		String[] inputPieces = input.split(" ");
		if (inputPieces.length == 1) {
			command = unstrictCommand(inputPieces[0]);
		} else {
			Parser parser = new Parser();
			parser.parse(input);
			this.setCommand(parser.getCommand());
			this.setIndex(parser.getIndex());
			this.setTaskName(parser.getTaskName());
			this.setTaskDetails(parser.getDetails());
			if (parser.getStartDate() != null) {
				this.setTaskStart(formatDate(parser.getStartDate()));
			}
			if (parser.getEndDate() != null) {
				this.setTaskEnd(formatDate(parser.getEndDate()));
			}
		}

		switch (command) {
		case ACK:
			return ACK_TIP;
		case ADD:
			this.setCommandMsg(ADD_MSG);
			return ADD_TIP;
		case COMPLETED:
			return COMPLETED_TIP;
		case DELETE:
			this.setCommandMsg(DELETE_MSG);
			return DELETE_TIP;
		case EDIT:
			this.setCommandMsg(EDIT_MSG);
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
		case EXIT:
			return EXIT_TIP;
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
			return COMMANDS.SEARCH;
		} else if (Parser.HOME.contains(stringCmd)) {
			return COMMANDS.HOME;
		} else if (Parser.UNDO.contains(stringCmd)) {
			return COMMANDS.UNDO;
		} else if (Parser.COMPLETED.contains(stringCmd)) {
			return COMMANDS.COMPLETED;
		} else if (Parser.HELP.contains(stringCmd)) {
			return COMMANDS.HELP;
		} else if (Parser.NAME.contains(stringCmd)) {
			return COMMANDS.NAME;
		} else if (Parser.FILEPATH.contains(stringCmd)) {
			return COMMANDS.FILEPATH;
		} else if (Parser.EXIT.contains(stringCmd)) {
			return COMMANDS.EXIT;
		}else {
			return COMMANDS.INVALID;
		}
	}
	
	public COMMANDS getCommand(){
		return command;
	}
	
	public int getIndex(){
		return index;
	}
	
	public String getCommandMsg(){
		return commandMsg;
	}

	public String getTaskName() {
		String returnValue = taskName;
		return returnValue;
	}

	private void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDetails() {
		String returnValue = taskDetails;
		return returnValue;
	}

	private void setTaskDetails(String taskDetails) {
		this.taskDetails = taskDetails;
	}

	public String getTaskStart() {
		String returnValue = taskStart;
		return returnValue;
	}

	private void setTaskStart(String taskStart) {
		this.taskStart = taskStart;
	}

	public String getTaskEnd() {
		String returnValue = taskEnd;
		return returnValue;
	}

	private void setTaskEnd(String taskEnd) {
		this.taskEnd = taskEnd;
	}
	
	private void setCommand(COMMANDS cmd) {
		this.command = cmd;
	}

	private void setCommandMsg(String msg) {
		this.commandMsg = msg;
	}

	private void setIndex(int i) {
		this.index = i;
	}

	private void resetValues() {
		this.setIndex(-1);
		this.setCommand(null);
		this.setCommandMsg(null);
		this.setTaskName(null);
		this.setTaskDetails(null);
		this.setTaskStart(null);
		this.setTaskEnd(null);
	}
	
	private String formatDate(Date date){
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		return dateFormat.format(date);
	}
}
