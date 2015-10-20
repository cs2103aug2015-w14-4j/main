package speedo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import processor.COMMANDS;

public class Parser {

	private ArrayList<String> inputs;

	private String dateString;
	private String details;
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
	private static final String HOME = "home";
	private static final String UNDO = "undo";

	private static final String DATE_FORMAT_1 = "dd-M-yyyy hh:mm";
	private static final String DATE_FORMAT_2 = "dd M yyyy hh:mm";
	private static final String DATE_FORMAT_3 = "dd-MMMM-yyyy hh:mm";
	private static final String DATE_FORMAT_4 = "ddMMyyyy hhmm";

	private static final Logger logger = Logger.getLogger(Parser.class.getName());

	public Parser() {
		inputs = new ArrayList<String>();
		dateVariant1 = new SimpleDateFormat(DATE_FORMAT_1);
		dateVariant2 = new SimpleDateFormat(DATE_FORMAT_2);
		dateVariant3 = new SimpleDateFormat(DATE_FORMAT_3);
		dateVariant4 = new SimpleDateFormat(DATE_FORMAT_4);

	}

	public Boolean parse(String str) {
		Boolean valid = true;
		processDetails(str);
		processTaskName(str);
		// String input = str.replace(details, "");
		// input = str.replace(taskName, "");
		// str.replace("\"", "");
		// str.replace("'", "");
		String remainder = removeExtras(str);
		// input = sort(input);
		String[] parts = remainder.split(" ", 2);
		processCommand(parts[0]);
		System.out.println(remainder+" "+parts.length);
		if (parts.length > 1) {
			// date = new Date();
			String dateCheck = parts[1].replace(" ", "");
			if (!dateCheck.isEmpty()) {
				valid = processDate(parts[1]);
				if (!valid) {
					command = COMMANDS.INVALID;
				}
			}
		} else {
			// No Date
		}

		if (getCommand() == COMMANDS.INVALID) {
			valid = false;
		}
		logger.info("Parsed: "+command+" " + taskName+" "+details+" "+date);
		return valid;
	}

	private String removeExtras(String text) {
		String input = text.replace(details, "");
		input = input.replace(taskName, "");
		input = input.replace("\"", "");
		input = input.replace("'", "");
		return input;
	}

	private void processDetails(String text) {
		if (text.contains("'")) {
			String[] stringPieces = text.split("'", 3);
			details = stringPieces[1];
		} else {
			details = "No detail";
		}
	}

	private void processTaskName(String text) {
		if (text.contains("\"")) {
			String[] stringPieces = text.split("\"", 3);
			taskName = stringPieces[1];
		} else {
			taskName = "No detail";
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
		} else if (HOME.contains(stringCmd)) {
			command = COMMANDS.HOME;
		} else if (UNDO.contains(stringCmd)) {
			command = COMMANDS.UNDO;
		} else {
			command = COMMANDS.INVALID;
		}
		logger.info("Command: " + command);
	}

	private boolean processDate(String dateString) {
		Boolean success = false;
		// Brute force date parsing
		try {
			date = dateVariant1.parse(dateString);
			success = true;
		} catch (ParseException e) {
			System.out.println("1");
		}
		try {
			date = dateVariant2.parse(dateString);
			success = true;
		} catch (ParseException e) {
			System.out.println("2");
		}
		try {
			date = dateVariant3.parse(dateString);
			success = true;
		} catch (ParseException e) {
			System.out.println("3");
		}
		try {
			date = dateVariant4.parse(dateString);
			success = true;
		} catch (ParseException e) {
			System.out.println("4");
		}
		logger.info("Date: " + dateString);
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

	public String getDetails() {
		return details;
	}

	public int getIndex() {
		int index = Integer.parseInt(taskName);
		return index;
	}

	public String sort(String input) {
		String[] stringArray = input.split(" ");
		String StringTemp = "";
		ArrayList<Integer> check = new ArrayList<Integer>();
		for (int i = 0; i < 4; i++) {
			if (ADD.contains(stringArray[i]) || DELETE.contains(stringArray[i]) || EDIT.contains(stringArray[i])
					|| ACK.contains(stringArray[i]) || SEARCH.contains(stringArray[i])) {
				StringTemp = StringTemp + stringArray[i];
				check.add(i);
				// System.out.println(StringTemp+" test1");
				break;
			}
		}
		for (int j = 0; j < 4; j++) {
			if (Character.isLetter(stringArray[j].charAt(0)) && !check.contains(j)) {
				StringTemp = StringTemp + " " + stringArray[j];
				check.add(j);
				// System.out.println(StringTemp+" test2");
				break;
			}
		}
		for (int k = 0; k < 4; k++) {
			if (stringArray[k].length() == 6 && !check.contains(k) && Character.isDigit(stringArray[k].charAt(0))) {
				StringTemp = StringTemp + " " + stringArray[k];
				check.add(k);
				// System.out.println(StringTemp+" test3");
				break;
			}
		}
		for (int l = 0; l < 4; l++) {
			if (stringArray[l].length() == 4 && !check.contains(l) && Character.isDigit(stringArray[l].charAt(0))) {
				StringTemp = StringTemp + " " + stringArray[l];
				check.add(l);
				/*
				 * System.out.println(StringTemp+" test4"); for(int
				 * t=0;t<stringArray.length;t++){
				 * System.out.println(stringArray[t]+" sa"); }
				 */
				break;
			}
		}
		// System.out.println(StringTemp+" testlast");
		// System.out.println(input+" input");
		return StringTemp;
	}
}
