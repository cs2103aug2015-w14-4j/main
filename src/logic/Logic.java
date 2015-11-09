//@@author A0121823R
package logic;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import parser.Parser;
import parser.Predictive;
import storage.Storage;
import storage.Task;
import utilities.COMMANDS;
import utilities.DatePair;
import utilities.DayProcessor;

/**
 * @author Donald Logic is dependent on both the storage and parser component
 *         When the GUI sends a user input to the Logic via the
 *         Logic.executeCommand(String input) method, the component will send
 *         the input to Parser to be parsed and receive relevant information to
 *         be used to create a GuiCommand object to inform the GUI display of
 *         the status of the task command. When parser returns the information,
 *         Logic will also instructs the Storage component based on the COMMANDS
 *         type received from parser.
 */
public class Logic {
	// Basic attributes used for creating a GuiCommand object
	// from execution of command
	private Storage store;
	private Parser parser;
	private COMMANDS command;
	private String details;
	private String taskName;
	private Date startDate;
	private Date endDate;
	private String userName;
	private int taskIndex;
	private Predictive predictor;
	private static final Logger logger = Logger.getLogger(Logic.class.getName());

	// Feedback is used to facilitate in forming the message String to be
	// returned to
	// the GUI in the GuiCommand object.
	private static final String ADD_FEEDBACK = "Added \"%1$s\"";
	private static final String DELETE_FEEDBACK = "Deleted \"%1$s\"";
	private static final String EDIT_FEEDBACK = "Edited \"%1$s\"";
	private static final String SEARCH_FEEDBACK = "Searching for \"%1$s\"";
	private static final String ACK_FEEDBACK = "Acknowledged \"%1$s\"";
	private static final String HOME_FEEDBACK = "Displaying list of incomplete items";
	private static final String UNDO_FEEDBACK = "Undo \"%1$s\" successful";
	private static final String COMPLETED_FEEDBACK = "Showing list of completed items";
	private static final String HELP_FEEDBACK = "";
	private static final String FILEPATH_FEEDBACK = "Filepath changed to \"%1$s\"";
	private static final String NAME_FEEDBACK = "Name changed to \"%1$s\"";
	private static final String INVALID_FEEDBACK = "Invalid command, check help for proper command usage";

	public Logic() {
		this(false);
	}

	// Constructor with a true boolean parameter will set the Logic class object
	// into
	// creating a new independent storage only which will not save the
	// activities of the commands used
	// this is to facilitate Logic testing
	public Logic(boolean test) {
		predictor = new Predictive();
		store = new Storage(test);
		if (test == false) {
			userName = store.readSettings();
		}
	}

	// Returns the user name of the application
	public String getUser() {
		userName = store.readSettings();
		return userName;
	}

	// this method will allow the user to set his/her own name as well as
	// creating a new
	// personal .json text file
	public void setSettings(String userName, String filePath) {
		store.setSettings(userName, filePath);
	}

	/**
	 * This method is responsible in communicating between the GUI and Parser
	 * component. when GUI uses this method to send the user input to the Logic
	 * component, it will run the String of inputs to through the Parser and
	 * then performs the relevant methods accordingly based of the COMMANDS type
	 * received. An Invalid command will simply return an GuiCommand with the
	 * Invalid command message to be returned to GUI. When parser does not
	 * receive a proper USER input, the method assumes invalid command by
	 * default.
	 */
	public GuiCommand executeCMD(String s) {
		parser = new Parser();
		Boolean valid = parser.parse(s);
		command = parser.getCommand();
		GuiCommand c = new GuiCommand(COMMANDS.INVALID, INVALID_FEEDBACK);

		List<Task> list = null;

		String name = null;
		if (valid) {
			switch (command) {

			case ADD:
				details = parser.getDetails();
				taskName = parser.getTaskName();
				startDate = parser.getStartDate();
				endDate = parser.getEndDate();
				if (taskName != null) {
					name = add();
				}
				if (name == null) {
					c = new GuiCommand(COMMANDS.INVALID, "Task not added");
				} else {
					c = new GuiCommand(COMMANDS.ADD, String.format(ADD_FEEDBACK, name), this.getTaskList());
					logger.info("Logic added " + name);
				}
				break;
			case DELETE:
				taskIndex = parser.getIndex();
				name = delete();
				if (name == null) {
					c = new GuiCommand(COMMANDS.INVALID, "Task not deleted");
				} else {
					c = new GuiCommand(COMMANDS.DELETE, String.format(DELETE_FEEDBACK, name), this.getTaskList());
					logger.info("Logic deleted " + name);
				}
				break;
			case EDIT:
				taskName = parser.getTaskName();
				details = parser.getDetails();
				taskIndex = parser.getIndex();
				startDate = parser.getStartDate();
				endDate = parser.getEndDate();
				name = edit();
				if (name == null) {
					c = new GuiCommand(COMMANDS.INVALID, "Task not edited");
				} else if (taskName == null && details == null && startDate == null && endDate == null) {
					c = new GuiCommand(COMMANDS.INVALID, "Task not edited");
				} else {
					c = new GuiCommand(COMMANDS.EDIT, String.format(EDIT_FEEDBACK, name), this.getTaskList());
				}
				break;
			case SEARCH:
				taskName = parser.getSearch();
				if (taskName != null) {
					list = search();
					c = new GuiCommand(COMMANDS.SEARCH, String.format(SEARCH_FEEDBACK, taskName), list);
					
				}
				break;
			case ACK:
				taskIndex = parser.getIndex();
				name = acknowledge();
				c = new GuiCommand(COMMANDS.ACK, String.format(ACK_FEEDBACK, name), this.getTaskList());
				break;
			case HOME:
				c = new GuiCommand(COMMANDS.HOME, HOME_FEEDBACK, this.getTaskList());
				logger.info("Logic setting home view");
				break;
			case UNDO:
				String message = undo();
				c = new GuiCommand(COMMANDS.UNDO, message, this.getTaskList());
				logger.info("Logic undo last command");
				break;
			case COMPLETED:
				list = completed();
				c = new GuiCommand(COMMANDS.COMPLETED, COMPLETED_FEEDBACK, list);
				break;
			case HELP:
				c = new GuiCommand(COMMANDS.HELP, HELP_FEEDBACK);
				break;
			case FILEPATH:
				String filePath = parser.getFilePath();
				filePath(filePath);
				c = new GuiCommand(COMMANDS.FILEPATH, String.format(FILEPATH_FEEDBACK, filePath), this.getTaskList());
				break;
			case NAME:
				name = parser.getName();
				name(name);
				c = new GuiCommand(COMMANDS.NAME, String.format(NAME_FEEDBACK, name), this.getTaskList());
				break;
			case EXIT:
				System.exit(0);
				break;
			default:
				c = new GuiCommand(COMMANDS.INVALID, INVALID_FEEDBACK);
				break;
			}
		}

		return c;
	}

	// @@author A0124791A
	public GuiCommand predictCMD(String input) {
		String message = predictor.processInput(input);
		GuiCommand guiCommand = new GuiCommand(null, message);
		int index = predictor.getIndex();

		switch (predictor.getCommand()) {
		case ADD:
			guiCommand.setTitle(predictor.getCommandMsg());
			guiCommand.setTaskName(predictor.getTaskName());
			guiCommand.setTaskDetails(predictor.getTaskDetails());
			guiCommand.setTaskStart(DayProcessor.formatDate(predictor.getTaskStart()));
			guiCommand.setTaskEnd(DayProcessor.formatDate(predictor.getTaskEnd()));
			break;
		case DELETE:
			if (index != -1 && index < store.getTaskList().size()) {
				guiCommand.setTitle(predictor.getCommandMsg());
				Task task = store.getTaskList().get(index);
				guiCommand.setTaskName(task.getName());
				guiCommand.setTaskDetails(task.getDetails());
				guiCommand.setTaskStart(task.getStartDateString());
				guiCommand.setTaskEnd(task.getEndDateString());
			}
			break;
		case EDIT:
			if (index != -1 && index < store.getTaskList().size()) {
				guiCommand.setTitle(predictor.getCommandMsg());
				Task task = store.getTaskList().get(index);
				if (predictor.getTaskName() != null) {
					guiCommand.setTaskName(predictor.getTaskName());
				} else {
					guiCommand.setTaskName(task.getName());
				}

				if (predictor.getTaskDetails() != null) {
					guiCommand.setTaskDetails(predictor.getTaskDetails());
				} else {
					guiCommand.setTaskDetails(task.getDetails());
				}

				Date startDate;
				Date endDate;
				if (predictor.getTaskStart() != null) {
					startDate = predictor.getTaskStart();
				} else {
					startDate = task.getStartDate();
				}

				if (predictor.getTaskEnd() != null) {
					endDate = predictor.getTaskEnd();
				} else {
					endDate = task.getEndDate();
				}
				DatePair orderedDate = DayProcessor.orderDate(startDate, endDate);
				guiCommand.setTaskStart(DayProcessor.formatDate(orderedDate.getDateOne()));
				guiCommand.setTaskEnd(DayProcessor.formatDate(orderedDate.getDateTwo()));
			}
			break;
		default:
			break;
		}
		return guiCommand;
	}

	// @@author A0121823R
	// sets the name of the user to be reflected in GUI's information panel
	// also sets the name to file handler
	private void name(String name) {
		store.setUser(name);

	}

	// sets filepath of the user according to the input String
	private void filePath(String filePath) {
		store.setSettings(this.getUser(), filePath);

	}

	// returns a list of tasks that are marked as completed/ acknowledged
	private List<Task> completed() {
		List<Task> list = store.getCompletedList();
		return list;
	}

	// instructs the storage to undo the action dones
	// as well as returning a message of success/ failure
	// in the GuiCommand object
	private String undo() {
		String name = store.undo();
		if (name != null) {
			return String.format(UNDO_FEEDBACK, name);
		} else {
			return "Nothing to undo";
		}
	}

	// returns the list of uncompleted task stored in the file hanlder
	public List<Task> getTaskList() {
		// TODO Auto-generated method stub
		return store.getTaskList();
	}

	// Sends the relevant tasks information to storage component to create a new
	// task
	private String add() {
		String name = store.add(taskName, details, startDate, endDate);
		System.out.println(name);
		if (name != null) {
			System.out.println("Task added");
			return name;
		} else {
			return null;
		}
	}

	// Sends the relevant tasks information and task list index to storage
	// component to edit a task
	private String edit() {
		return store.edit(taskIndex, taskName, details, startDate, endDate);
	}

	// Sends the task list index to storage to delate a task
	private String delete() {
		System.out.println("Task deleted");
		return store.delete(taskIndex);
	}

	// sends a string of search keyword to store and retrieves a list of
	// searched tasks
	private List<Task> search() {
		List<Task> list = store.search(taskName);
		return list;

	}

	// acknowledges the task by its index
	private String acknowledge() {
		return store.complete(taskIndex);
	}

}
