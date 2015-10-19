package speedo;

import java.util.Date;
import java.util.List;
import processor.COMMANDS;
import java.util.logging.Logger;

import storage.Storage;
import storage.Task;

public class Logic {
	// Attributes
	private Storage store;
	private Parser parser;
	private COMMANDS command;
	private String details;
	private String taskName;
	private Date date;
	private int taskIndex;
	private static final Logger logger = Logger.getLogger(Logic.class.getName());

	// Constructor
	public Logic() {
		store = new Storage();
	}

	// Methods
	public GuiCommand executeCMD(String s) {
		parser = new Parser();
		parser.parse(s);
		command = parser.getCommand();
		GuiCommand c = null;
		Task t = null;
		List<Task> list = null;

		
		switch (command) {

		case ADD:
			details = parser.getDetails();
			taskName = parser.getTaskName();
			date = parser.getDate();
			t = add();
			c = new GuiCommand(COMMANDS.ADD, "Added " + t.getName(), t);
			logger.info("Logic added " + t.getName());
			break;
		case DELETE:
			taskIndex = parser.getIndex();
			t = delete();
			c = new GuiCommand(COMMANDS.DELETE, "Deleted the task", t);
			logger.info("Logic deleted " + t.getName());
			break;
		case EDIT:
			details = parser.getDetails();
			taskIndex = parser.getIndex();
			t = edit();
			c = new GuiCommand(COMMANDS.EDIT, "Edited", t, taskIndex);
			break;
		case SEARCH:
			details = parser.getDetails();
			list = search();
			c = new GuiCommand(COMMANDS.SEARCH, "Searched tasks", list);
			break;
		case ACK:
			taskIndex = parser.getIndex();
			t = acknowledge();
			c = new GuiCommand(COMMANDS.ACK, "Acknowledged", t);
			break;
		case HOME:
			list = store.getTaskList();
			c = new GuiCommand(COMMANDS.HOME, "Showing Original task list", list);
			logger.info("Logic setting home view");
			break;
		case INVALID:
			c = new GuiCommand(COMMANDS.INVALID, "Invalid command");
			break;
		default:
			c = new GuiCommand(COMMANDS.INVALID, "Invalid command");
			break;			
		}
		return c;
	}
	
	public List<Task> getTaskList() {
		// TODO Auto-generated method stub
		return store.getTaskList();
	}

	private Task add() {
		/*
		 * Date date = null; SimpleDateFormat sdf = new SimpleDateFormat(
		 * "dd-M-yyyy hh:mm"); String date_in_string = sdf.format(new Date());
		 * try { date = sdf.parse(date_in_string); } catch (ParseException e) {
		 * // TODO Auto-generated catch block e.printStackTrace(); }
		 */
		Task newTask = store.add(taskName, date);
		System.out.println(newTask);
		if (newTask != null) {
			System.out.println("Task added");
			store.saveFile();
			return newTask;
		} else {
			return null;
		}
	}

	private Task edit(){
		return store.edit(taskIndex, details);
		// store.saveFile();
	}

	private Task delete(){
		System.out.println("Task deleted");
		return store.delete(taskIndex);
		// store.saveFile();
	}

	private List<Task> search(){
		List<Task> list = store.search(details);
		return list;

	}

	private Task acknowledge(){
		return store.acknowledge(taskIndex);
		// store.saveFile();
	}

}
