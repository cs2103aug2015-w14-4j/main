// @@author A0126232U
package parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import utilities.COMMANDS;

public class Parser {

	private String details;
	private Date startDate, endDate;
	private COMMANDS command;
	private String taskName;
	private String name;
	private String filePath;
	private String searchSentence;
	private int index;
	private String inputString;
	private boolean errorIndex = false;

	public static final String UNDO = "undo";
	public static final String NAME = "name";
	public static final String COMPLETED = "completed";
	public static final String DELETE = "remove delete";
	public static final String FILEPATH = "filepath";
	public static final String SEARCH = "search find";
	public static final String EXPAND = "expand display show";
	public static final String ADD = "add insert";
	public static final String HOME = "home";
	public static final String HELP = "help";
	public static final String EDIT = "edit change";
	public static final String ACK = "ack acknowledge";
	public static final String EXIT = "exit";

	private static final String DATE_DELIMITER = " -d ";
	private static final String DETAIL_DELIMITER = " -i ";
	private static final String DUAL_DELIMITER = " -d | -i ";
	private static final String INT_REGEX = "\\d+";
	private static final String BOUNDARY = ".*\\b%1$s\\b.*";

	private static final Logger logger = Logger.getLogger(Parser.class.getName());
	private static final String EMPTY = "";
	private static final int INVALID_INDEX = -1;

	public Parser() {
	}

	
	public Boolean parse(String str) {
		inputString = str;
		Boolean valid = true;
		// String[] inputsSplitDash = str.split("-");
		// String[] inputsSplitSpace = inputsSplitDash[0].split(" ");
		// commandString = inputsSplitSpace[0];
		str = str.trim().replaceAll(" +", " ");
		String[] inputPieces = str.split(" ", 2);
		processCommand(inputPieces[0]);

		String remaining = null;
		if (inputPieces.length == 2) {
			processDetails(inputPieces);
			String unprocessedIndex = processIndex(inputPieces);
			checkForDate(inputPieces);
			String[] remainingPieces = inputPieces[1].split(DUAL_DELIMITER);
			remaining = remainingPieces[0];
			remaining = remaining.replaceFirst(unprocessedIndex, "");
			if (remaining.trim().length() == 0) {
				remaining = null;
			}
		}
		if (getCommand() == COMMANDS.EDIT && remaining != null && !errorIndex) {
			taskName = removeSpace(remaining);
		} else if (getCommand() == COMMANDS.NAME) {
			name = remaining;
		} else if (getCommand() == COMMANDS.FILEPATH) {
			filePath = remaining;
		} else if (getCommand() == COMMANDS.SEARCH) {
			searchSentence = remaining;
		} else if (getCommand() == COMMANDS.DELETE||getCommand() == COMMANDS.ACK||getCommand() == COMMANDS.EXPAND) {
			taskName = null;
		} else if (errorIndex) {
			taskName = null;
			errorIndex = false;
			//System.out.println("error");
		} else {
			taskName = remaining;
		}
		// if(DELETE.contains(commandString)||ACK.contains(commandString)||EXPAND.contains(commandString)
		// ||COMPLETED.contains(commandString)){
		// //this.isIndex(inputsSplitSpace[1]);
		// }else if(NAME.contains(commandString)){
		// for (int i = 1; i < inputsSplitSpace.length; i++) {
		// name = name + " " + inputsSplitSpace[i];
		// }
		// name = removeSpace(name);
		// }else if(FILEPATH.contains(commandString)){
		// for (int i = 1; i < inputsSplitSpace.length; i++) {
		// filePath = filePath + " " + inputsSplitSpace[i];
		// }
		// filePath = removeSpace(filePath);
		// }else if(SEARCH.contains(commandString)){
		// for (int i = 1; i < inputsSplitSpace.length; i++) {
		// searchSentence = searchSentence + " " + inputsSplitSpace[i];
		// }
		// searchSentence = removeSpace(searchSentence);
		// }else{
		// for (int i = 1; i < inputsSplitSpace.length; i++) {
		// taskName = taskName + " " + inputsSplitSpace[i];
		// }
		// if (EDIT.contains(commandString)) {
		// if (inputsSplitSpace.length > 1) {
		// //this.isIndex(inputsSplitSpace[1]);
		// taskName = "";
		// for (int i = 2; i < inputsSplitSpace.length; i++) {
		// taskName = taskName + " " + inputsSplitSpace[i];
		// }
		// }
		// }
		// if (inputsSplitSpace.length > 1) {
		// if (taskName.equals("")) {
		// taskName = null;
		// } else {
		// taskName = removeSpace(taskName);
		// }
		//
		// }
		// if (inputsSplitDash.length == 1) {
		// startDate = null;
		// endDate = null;
		// details = null;
		// if (inputsSplitSpace.length == 2) {
		// this.isIndex(inputsSplitSpace[1]);
		// }
		// } else if (inputsSplitDash.length == 2) {
		// if (inputsSplitDash[1].charAt(0) == 'd' ||
		// inputsSplitDash[1].charAt(0) == 'D') {
		// String correctInfo = removeSpace(removeFirst(inputsSplitDash[1]));
		// processDate(correctInfo);
		// details = null;
		// } else if (inputsSplitDash[1].charAt(0) == 'i' ||
		// inputsSplitDash[0].charAt(0) == 'I') {
		// startDate = null;
		// endDate = null;
		// details = removeSpace(removeFirst(inputsSplitDash[1]));
		// } else {
		// startDate = null;
		// endDate = null;
		// details = null;
		// }
		// } else {
		// if ((inputsSplitDash[1].charAt(0) == 'd' ||
		// inputsSplitDash[1].charAt(0) == 'D')
		// && (inputsSplitDash[2].charAt(0) == 'i' ||
		// inputsSplitDash[2].charAt(0) == 'I')) {
		// String correctInfo = removeSpace(removeFirst(inputsSplitDash[1]));
		// processDate(correctInfo);
		// details = removeSpace(removeFirst(inputsSplitDash[2]));
		// } else if ((inputsSplitDash[1].charAt(0) == 'i' ||
		// inputsSplitDash[1].charAt(0) == 'I')
		// && (inputsSplitDash[2].charAt(0) == 'd' ||
		// inputsSplitDash[2].charAt(0) == 'D')) {
		// String correctInfo = removeSpace(removeFirst(inputsSplitDash[2]));
		// processDate(correctInfo);
		// details = removeSpace(removeFirst(inputsSplitDash[1]));
		// } else {
		// startDate = null;
		// endDate = null;
		// details = null;
		// }
		// }
		// }

		if (getCommand() == COMMANDS.INVALID) {
			valid = false;
			taskName = null;
		}
		logger.info("Parsed: " + command + " " + taskName + " " + details + " " + endDate);
		return valid;
	}

	// private String removeFirst(String text) {
	// return text.substring(1);
	// }
	//
	private String removeSpace(String text) {
		String[] inputwithSpace = text.split(" ");
		String output = "";
		for (int i = 1; i < inputwithSpace.length; i++) {
			if (i == 1) {
				output = inputwithSpace[1];
			} else {
				output = output + " " + inputwithSpace[i];
			}
		}
		return output;
	}

	private void processCommand(String stringCmd) {
		stringCmd = stringCmd.toLowerCase();
		stringCmd = stringCmd.trim();

		if (stringCmd.length() != 0) {
			stringCmd = String.format(BOUNDARY, stringCmd);
			if (ADD.matches(stringCmd)) {
				command = COMMANDS.ADD;
			} else if (DELETE.matches(stringCmd)) {
				command = COMMANDS.DELETE;
			} else if (EDIT.matches(stringCmd)) {
				command = COMMANDS.EDIT;
			} else if (ACK.matches(stringCmd)) {
				command = COMMANDS.ACK;
			} else if (SEARCH.matches(stringCmd)) {
				command = COMMANDS.SEARCH;
			} else if (HOME.matches(stringCmd)) {
				command = COMMANDS.HOME;
			} else if (UNDO.matches(stringCmd)) {
				command = COMMANDS.UNDO;
			} else if (EXPAND.matches(stringCmd)) {
				command = COMMANDS.EXPAND;
			} else if (COMPLETED.matches(stringCmd)) {
				command = COMMANDS.COMPLETED;
			} else if (HELP.matches(stringCmd)) {
				command = COMMANDS.HELP;
			} else if (NAME.matches(stringCmd)) {
				command = COMMANDS.NAME;
			} else if (FILEPATH.matches(stringCmd)) {
				command = COMMANDS.FILEPATH;
			} else if (EXIT.matches(stringCmd)) {
				command = COMMANDS.EXIT;
			} else {
				command = COMMANDS.INVALID;
			}
		} else {
			command = COMMANDS.INVALID;
		}
		logger.info("Command: " + command);
	}

	private String processIndex(String[] input) {
		if (input.length > 1) {
			String possibleIndex = input[1];
			String[] indexPiece = possibleIndex.split(" ");
			if (indexPiece[0].matches(INT_REGEX)) {
				try{
					index = Integer.parseInt(indexPiece[0]);
					index--;
					return indexPiece[0];
				}catch(NumberFormatException e){
					errorIndex = true;
				}
			}else{
				if(command == COMMANDS.EDIT){
					return indexPiece[0];
				}
			}
		}
		index = -1;
		return EMPTY;
	}

	private boolean isRequireIndex(COMMANDS cmd) {
		if (cmd == COMMANDS.DELETE || cmd == COMMANDS.EDIT || cmd == COMMANDS.ACK) {
			return true;
		} else {
			return false;
		}
	}

	// @@author A0125369Y
	private void checkForDate(String[] input) {
		String[] inputPieces = input[1].split(DATE_DELIMITER);
		if (inputPieces.length == 2) {
			String[] datePieces = inputPieces[1].split(DETAIL_DELIMITER);
			processDate(datePieces[0]);
		} else {
			// Invalid Format
		}
	}

	// @@author A0125369Y
	private void processDetails(String[] input) {
		String[] inputPieces = input[1].split(DETAIL_DELIMITER);
		if (inputPieces.length == 2) {
			String[] detailPieces = inputPieces[1].split(DATE_DELIMITER);
			if (detailPieces[0].trim().length() > 0) {
				details = detailPieces[0];
			} else {
				details = null;
			}
		} else {
			// Invalid Format
			details = null;
		}
	}

	// @@author A0125369Y
	private void processDate(String dateString) {
		DatePair datePair = DateParser.stringToDate(dateString);
		Date firstDate = datePair.getDateOne();
		Date secondDate = datePair.getDateTwo();
		if (firstDate != null && secondDate != null) {
			if (firstDate.after(secondDate)) {
				firstDate = datePair.getDateTwo();
				secondDate = datePair.getDateOne();
			}
		}
		startDate = firstDate;
		endDate = secondDate;
	}
	
	// @@author A0126232U
	public COMMANDS getCommand() {
		return command;
	}

	public String getTaskName() {
		return taskName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public String getDetails() {
		return details;
	}

	public String getName() {
		return name;
	}

	public String getFilePath() {
		return filePath;
	}

	public String getSearch() {
		return searchSentence;
	}

	public int getIndex() {
		String[] temp = inputString.split(" ");
		if (temp.length < 2 || !isRequireIndex(getCommand())) {
			return -1;
		} else {
			return index;
		}

	}

	public void reset() {
		command = COMMANDS.INVALID;
		taskName = null;
		index = -1;
		details = null;
		startDate = null;
		endDate = null;
		name = null;
		filePath = null;
		searchSentence = null;
	}

	/*
	 * private String removeExtras(String text) { String input =
	 * text.replace(details, ""); input = input.replace(taskName, ""); input =
	 * input.replace("\"", ""); input = input.replace("'", ""); return input; }
	 * 
	 * private boolean isIndex(String text){ if
	 * (!SEARCH.contains(commandString)){ // Is a task index
	 * if(text.matches("\\d+")){ index = Integer.parseInt(text); index--; return
	 * true; } else { return false; } }else if(SEARCH.contains(commandString)){
	 * taskName = text; return true; }else{ return false; } }
	 * 
	 * private void processDetailsOld(String text) { if (text.contains("'")) {
	 * String[] stringPieces = text.split("'", 3); details = stringPieces[1]; }
	 * else { details = "No detail"; } }
	 * 
	 * private void processTaskName(String text) { if (text.contains("\"")) {
	 * String[] stringPieces = text.split("\"", 3); taskName = stringPieces[1];
	 * } else { taskName = "No detail"; } }
	 */

	/*
	 * public String sort(String input) { String[] stringArray = input.split(" "
	 * ); String StringTemp = ""; ArrayList<Integer> check = new
	 * ArrayList<Integer>(); for (int i = 0; i < 4; i++) { if
	 * (ADD.contains(stringArray[i]) || DELETE.contains(stringArray[i]) ||
	 * EDIT.contains(stringArray[i]) || ACK.contains(stringArray[i]) ||
	 * SEARCH.contains(stringArray[i])) { StringTemp = StringTemp +
	 * stringArray[i]; check.add(i); // System.out.println(StringTemp+" test1");
	 * break; } } for (int j = 0; j < 4; j++) { if
	 * (Character.isLetter(stringArray[j].charAt(0)) && !check.contains(j)) {
	 * StringTemp = StringTemp + " " + stringArray[j]; check.add(j); //
	 * System.out.println(StringTemp+" test2"); break; } } for (int k = 0; k <
	 * 4; k++) { if (stringArray[k].length() == 6 && !check.contains(k) &&
	 * Character.isDigit(stringArray[k].charAt(0))) { StringTemp = StringTemp +
	 * " " + stringArray[k]; check.add(k); // System.out.println(StringTemp+
	 * " test3"); break; } } for (int l = 0; l < 4; l++) { if
	 * (stringArray[l].length() == 4 && !check.contains(l) &&
	 * Character.isDigit(stringArray[l].charAt(0))) { StringTemp = StringTemp +
	 * " " + stringArray[l]; check.add(l); /* System.out.println(StringTemp+
	 * " test4"); for(int t=0;t<stringArray.length;t++){
	 * System.out.println(stringArray[t]+" sa"); }
	 */
	/*
	 * break; } } // System.out.println(StringTemp+" testlast"); //
	 * System.out.println(input+" input"); return StringTemp; }
	 */

	/*
	 * private Date processDate(String dateString, int identity) { Date
	 * firstDate = new Date(), secondDate = new Date(), tempDate; String dates[]
	 * = dateString.split(" "); // Brute force date parsing try { if
	 * (dates.length == 4) { firstDate = dateVariant1.parse(dates[0] + " " +
	 * dates[1]); secondDate = dateVariant1.parse(dates[2] + " " + dates[3]); if
	 * (firstDate.after(secondDate)) { tempDate = firstDate; firstDate =
	 * secondDate; secondDate = tempDate; } } else if (dates.length == 2) {
	 * firstDate = null; secondDate = dateVariant1.parse(dates[0] + " " +
	 * dates[1]); } else { firstDate = null; secondDate = null; }
	 * 
	 * } catch (ParseException e) { System.out.println("1"); firstDate = null;
	 * secondDate = null; } try { if (dates.length == 4) { firstDate =
	 * dateVariant2.parse(dates[0] + " " + dates[1]); secondDate =
	 * dateVariant2.parse(dates[2] + " " + dates[3]); if
	 * (firstDate.after(secondDate)) { tempDate = firstDate; firstDate =
	 * secondDate; secondDate = tempDate; } } else if (dates.length == 2) {
	 * firstDate = null; secondDate = dateVariant2.parse(dates[0] + " " +
	 * dates[1]); } else { firstDate = null; secondDate = null; } } catch
	 * (ParseException e) { System.out.println("2"); firstDate = null;
	 * secondDate = null; } try { if (dates.length == 4) { firstDate =
	 * dateVariant3.parse(dates[0] + " " + dates[1]); secondDate =
	 * dateVariant3.parse(dates[2] + " " + dates[3]); if
	 * (firstDate.after(secondDate)) { tempDate = firstDate; firstDate =
	 * secondDate; secondDate = tempDate; } } else if (dates.length == 2) {
	 * firstDate = null; secondDate = dateVariant3.parse(dates[0] + " " +
	 * dates[1]); } else { firstDate = null; secondDate = null; } } catch
	 * (ParseException e) { System.out.println("3"); firstDate = null;
	 * secondDate = null; } try { if (dates.length == 4) { firstDate =
	 * dateVariant4.parse(dates[0] + " " + dates[1]); secondDate =
	 * dateVariant4.parse(dates[2] + " " + dates[3]); if
	 * (firstDate.after(secondDate)) { tempDate = firstDate; firstDate =
	 * secondDate; secondDate = tempDate; } } else if (dates.length == 2) {
	 * firstDate = null; secondDate = dateVariant4.parse(dates[0] + " " +
	 * dates[1]); } else { firstDate = null; secondDate = null; } } catch
	 * (ParseException e) { System.out.println("4"); firstDate = null;
	 * secondDate = null; } logger.info("Date: " + dateString); if (identity ==
	 * 0) { return firstDate; } else { return secondDate; } }
	 */

}
