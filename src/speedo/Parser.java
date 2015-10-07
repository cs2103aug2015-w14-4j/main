package speedo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import processor.COMMANDS;

public class Parser {

	private ArrayList<String> inputs;
	private SimpleDateFormat sdf;
	private String dateString;
	private String content;
	private Date date;
	private COMMANDS command;
	private String taskName;

	private static final String ADD = "add";
	private static final String DELETE = "remove delete";
	private static final String EDIT = "edit change";
	private static final String ACK = "ack acknowledge";
	private static final String SEARCH = "search find";

	public Parser() {
		inputs = new ArrayList<String>();
		sdf = new SimpleDateFormat("ddmmyy hhmm");
		date = new Date();
	}

	public void parse(String input) {
		if (input.contains("'")) {
			String[] stringPieces = input.split("'", 3);
			content = stringPieces[1];
		} else {
			content = "No detail";
		}
		System.out.println(input);
		String[] parts = input.split(" ", 3);
		processCommand(parts[0]);
		taskName = parts[1];
		
		if (parts.length > 2) {
			processDate(parts[2]);
		} else {
			// No Date
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
		try {
			this.date = sdf.parse(dateString);
		} catch (ParseException e) {
			// invalid date format
			return false;
		}
		return true;
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
