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
	private String content;
	private int taskIndex;

	// Constructor
	public Logic() {
		store = new Storage();
		parser = new Parser();
	}

	// Methods
	public GuiCommand executeCMD(String s) throws ParseException {
		parser.parse(s);
		command = parser.getCommand();
		content = parser.getContent();
		GuiCommand c = null;
		Task t = null;
		List<Task> list = null;

		switch (command) {

		case ADD:
			t = add();
			c = new GuiCommand(COMMANDS.ADD, "Added " + t.getName(), t);
			break;
		case DELETE:
			int taskId = delete();
			c = new GuiCommand(COMMANDS.DELETE, "Deleted the task", taskId);
			break;
		case EDIT:
			taskIndex = parser.getIndex();
			t = edit(taskIndex, s);
			c = new GuiCommand(COMMANDS.EDIT, "Edited", t);
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

	public Task add() throws ParseException {
		/*
		 * Date date = null; SimpleDateFormat sdf = new SimpleDateFormat(
		 * "dd-M-yyyy hh:mm"); String date_in_string = sdf.format(new Date());
		 * try { date = sdf.parse(date_in_string); } catch (ParseException e) {
		 * // TODO Auto-generated catch block e.printStackTrace(); }
		 */
		Date date = parser.getDate();
		Task newTask = store.add(content, date);
		if (newTask != null) {
			System.out.println("Task added");
			// store.saveFile();
			return newTask;
		} else {
			return null;
		}
	}

	public Task edit(int index, String text){
		return store.edit(index, text);
		// store.saveFile();
	}

	public int delete(){
		int index = Integer.parseInt(content);
		System.out.println("Task deleted");
		return store.delete(index);
		// store.saveFile();
	}

	public List<Task> search(){
		List<Task> list = store.search(content);
		return list;

	}

	public Task acknowledge(int taskIndex){
		return store.acknowledge(taskIndex);
		// store.saveFile();
	}

}
