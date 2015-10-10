package speedo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import processor.COMMANDS;

import processor.COMMANDS;
import storage.Storage;

public class Logic {
	// Attributes
	private Storage store;
	private Parser parser;
	private COMMANDS command;
	private String details;
	private String taskName;
	private int taskIndex;

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
			taskName = parser.getTaskName();
			details = parser.getDetails();
			t = add();
			c = new GuiCommand(COMMANDS.ADD, "Added " + t.getName(), t);
			break;
		case DELETE:
			t = delete();
			c = new GuiCommand(COMMANDS.DELETE, "Deleted the task", t);
			break;
		case EDIT:
			taskIndex = parser.getIndex();
			details = parser.getDetails();
			t = edit(taskIndex, details);
			c = new GuiCommand(COMMANDS.EDIT, "Edited", t, taskIndex);
			break;
		case SEARCH:
			list = search();
			c = new GuiCommand(COMMANDS.SEARCH, "Searched tasks", list);
			break;
		case ACK:
			taskIndex = parser.getIndex();
			t = acknowledge(taskIndex);
			c = new GuiCommand(COMMANDS.ACK, "Acknowledged", t);
		case INVALID:
			c = new GuiCommand(COMMANDS.INVALID, "Invalid command");
			break;
		}
		return c;
	}

	public Task getTask(int index) {
		return store.getTask(index);
	}

	public int getNumOfTask() {
		return store.getNumOfTask();
	}

	private Task add() {
		/*
		 * Date date = null; SimpleDateFormat sdf = new SimpleDateFormat(
		 * "dd-M-yyyy hh:mm"); String date_in_string = sdf.format(new Date());
		 * try { date = sdf.parse(date_in_string); } catch (ParseException e) {
		 * // TODO Auto-generated catch block e.printStackTrace(); }
		 */
		Date date = parser.getDate();
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

	private Task edit(int index, String text){
		return store.edit(index, text);
		// store.saveFile();
	}

	private Task delete(){
		int index = parser.getIndex();
		System.out.println("Task deleted");
		return store.delete(index);
		// store.saveFile();
	}

	private List<Task> search(){
		List<Task> list = store.search(details);
		return list;

	}

	private Task acknowledge(int taskIndex){
		return store.acknowledge(taskIndex);
		// store.saveFile();
	}

}
