package speedo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import processor.COMMANDS;

public class Parser {

	private ArrayList<String> inputs;

	private String dateString;
	private String content;
	private Date date;
	private COMMANDS command;
	private String taskName;
	private SimpleDateFormat dateVariant1;
	private SimpleDateFormat dateVariant2;
	private SimpleDateFormat dateVariant3;
	private SimpleDateFormat dateVariant4;

	private static final String ADD = "add";
	private static final String DELETE = "remove delete";
	private static final String EDIT = "edit change";
	private static final String ACK = "ack acknowledge";
	private static final String SEARCH = "search find";
	
	private static final String DATE_FORMAT_1 = "dd-M-yyyy hh:mm";
	private static final String DATE_FORMAT_2 = "dd M yyyy hh:mm";
	private static final String DATE_FORMAT_3 = "dd-MMMM-yyyy hh:mm";
	private static final String DATE_FORMAT_4 = "ddmmyy hhmm";

	public Parser() {
		inputs = new ArrayList<String>();
		dateVariant1 = new SimpleDateFormat(DATE_FORMAT_1);
		dateVariant2 = new SimpleDateFormat(DATE_FORMAT_2);
		dateVariant3 = new SimpleDateFormat(DATE_FORMAT_3);
		dateVariant4 = new SimpleDateFormat(DATE_FORMAT_4);
		date = new Date();
	}

	public Boolean parse(String input) {
		Boolean valid = true;
		processDetails(input);
		String[] parts = input.split(" ", 3);
		processCommand(parts[0]);
		taskName = parts[1];
		
		if (parts.length > 2) {
			valid = processDate(parts[2]);
		} else {
			// No Date
		}
		
		if(getCommand() == COMMANDS.INVALID){
			valid = false;
		}
		
		return valid;
	}
	
	private void processDetails(String text){
		if (text.contains("'")) {
			String[] stringPieces = text.split("'", 3);
			content = stringPieces[1];
		} else {
			content = "No detail";
		}
	}

	private void processCommand(String stringCmd) {
		if (ADD.contains(stringCmd)) {
			command = COMMANDS.ADD;
		} else if (DELETE.contains(stringCmd)) {
			command = COMMANDS.DELETE;
		} else if (EDIT.contains(stringCmd)) {
			command = COMMANDS.EDIT;
		} else if (ACK.contains(stringCmd)) {
			command = COMMANDS.ACK;
		} else if (SEARCH.contains(stringCmd)) {
			command = COMMANDS.SEARCH;
		} else {
			command = COMMANDS.INVALID;
		}
	}

	private boolean processDate(String dateString) {
		Boolean success = false;
		// Brute force date parsing
		try {
			date = dateVariant1.parse(dateString);
			success = true;
		} catch (ParseException e) {
		}
		try {
			date = dateVariant2.parse(dateString);
			success = true;
		} catch (ParseException e) {
		}
		try {
			date = dateVariant3.parse(dateString);
			success = true;
		} catch (ParseException e) {
		}
		try {
			date = dateVariant4.parse(dateString);
			success = true;
		} catch (ParseException e) {
		}
		return success;
	}

	public COMMANDS getCommand() {
		return command;
	}

	public String getTaskName() {
		return taskName;
	}

	public Date getDate() {
		return date;
	}

	public String getContent() {
		return content;
	}

	public int getIndex() {
		int index = Integer.parseInt(taskName);
		return index;
	}
}
