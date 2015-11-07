//@@author A0121823R
package logic;

import java.util.Date;
import java.util.List;
import processor.COMMANDS;
import java.util.logging.Logger;

import parser.Parser;
import parser.Predictive;
import storage.Storage;
import storage.Task;

public class Logic {
	// Attributes
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

	
	// Constructor
	public Logic() {
		this(false);
	}
	
	public Logic(boolean test){
		predictor = new Predictive();
		store = new Storage(test);
		if(test == false){
			userName = store.readSettings();
		}
	}
	
	public String getUser(){
		userName = store.readSettings();
		return userName;
	}
	
	public void setSettings(String userName, String filePath){
		store.setSettings(userName, filePath);
	}

	// Methods
	public GuiCommand executeCMD(String s) {
		parser = new Parser();
		parser.parse(s);
		command = parser.getCommand();
		GuiCommand c = null;
		Task t = null;
		List<Task> list = null;

		String name;
		switch (command) {

		case ADD:
			details = parser.getDetails();
			taskName = parser.getTaskName();
			startDate = parser.getStartDate();
			endDate = parser.getEndDate();
			 name = add();
			if(name == null){
				c =new GuiCommand(COMMANDS.INVALID, "Task not added");
			} else {
				c = new GuiCommand(COMMANDS.ADD, "Added " + name, this.getTaskList());
				logger.info("Logic added " + name);
			}
			break;
		case DELETE:
			taskIndex = parser.getIndex();
			 name = delete();
			if (name == null){
				c = new GuiCommand(COMMANDS.INVALID, "Task not deleted");
			} else {
				c = new GuiCommand(COMMANDS.DELETE, "Deleted "+name, this.getTaskList());
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
			if (name == null){
				c = new GuiCommand(COMMANDS.INVALID, "Task not edited");
			} else if(taskName == null && details == null && startDate == null && endDate == null){
				c = new GuiCommand(COMMANDS.INVALID, "Task not deleted");
			}else {
				c = new GuiCommand(COMMANDS.EDIT, "Edited "+name, this.getTaskList());
			}
			break;
		case SEARCH:
			taskName = parser.getTaskName();
			list = search();
			c = new GuiCommand(COMMANDS.SEARCH, "Searched tasks", list);
			break;
		case ACK:
			taskIndex = parser.getIndex();
			name = acknowledge();
			c = new GuiCommand(COMMANDS.ACK, "Acknowledged", this.getTaskList());
			break;
		case HOME:
			c = new GuiCommand(COMMANDS.HOME, "Showing Original task list", this.getTaskList());
			logger.info("Logic setting home view");
			break;
		case UNDO:
			String message = undo();
			c = new GuiCommand(COMMANDS.UNDO, message, this.getTaskList());
			logger.info("Logic undo last command");
			break;
		case EXPAND:
			taskIndex = parser.getIndex();
			int taskId = store.getTaskList().get(taskIndex).getTaskId();
			c = new GuiCommand(COMMANDS.EXPAND, "Displaying details of task " + taskIndex, taskId);
			break;
		case COMPLETED:
			list = completed();
			c = new GuiCommand(COMMANDS.COMPLETED, "Showing Completed tasks", list);
			break;
		case HELP:
			c = new GuiCommand(COMMANDS.HELP, "Displaying help screen");
			break;
		case FILEPATH:
			taskName = parser.getTaskName();
			filePath();
			c = new GuiCommand(COMMANDS.FILEPATH, "Changed Filepath");
			break;
		case NAME:
			taskName = parser.getTaskName();
			name();
			c = new GuiCommand(COMMANDS.FILEPATH, "Changed Filepath");
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
	
	//@@author A0124791A
	public GuiCommand predictCMD(String input){
		String message = predictor.processInput(input);
		GuiCommand guiCommand = new GuiCommand(null, message);
		guiCommand.setTitle(predictor.getCommandMsg());
		int index = predictor.getIndex();
		
		switch (predictor.getCommand()) {
			case ADD:
				guiCommand.setTaskName(predictor.getTaskName());
				guiCommand.setTaskDetails(predictor.getTaskDetails());
				guiCommand.setTaskStart(predictor.getTaskStart());
				guiCommand.setTaskEnd(predictor.getTaskEnd());
				break;
			case DELETE:
				if(index != -1){
					Task task = store.getTaskList().get(index);
					guiCommand.setTaskName(task.getName());
					guiCommand.setTaskDetails(task.getDetails());
					guiCommand.setTaskStart(task.getStartDateString());
					guiCommand.setTaskEnd(task.getEndDateString());
				}
				break;
			case EDIT:
				if(index != -1){
					Task task = store.getTaskList().get(index);
					if(predictor.getTaskName() != null){
						guiCommand.setTaskName(predictor.getTaskName());
					} else {
						guiCommand.setTaskName(task.getName());
					}
					
					if(predictor.getTaskDetails() != null){
						guiCommand.setTaskDetails(predictor.getTaskDetails());
					} else {
						guiCommand.setTaskDetails(task.getDetails());
					}
					
					if(predictor.getTaskStart() != null){
						guiCommand.setTaskStart(predictor.getTaskStart());
					} else {
						guiCommand.setTaskStart(task.getStartDateString());
					}
					
					if(predictor.getTaskEnd() != null){
						guiCommand.setTaskEnd(predictor.getTaskEnd());
					} else {
						guiCommand.setTaskEnd(task.getEndDateString());
					}
				}
				break;
			default:
				break;
		}
		return guiCommand;
	}
	
	private void name() {
		store.setUser(taskName);
		
	}

	private void filePath() {
		store.setSettings(this.getUser(), taskName);
		
	}

	private List<Task> completed() {
		List<Task> list = store.getCompletedList();
		return list;
	}

	private String undo() {
		String name = store.undo();
		if(name != null){
			return "Undo \""+ name+"\" Successful";
		} else{
			return "Nothing to undo";
		}
	}

	public List<Task> getTaskList() {
		// TODO Auto-generated method stub
		return store.getTaskList();
	}

	private String add() {
		/*
		 * Date date = null; SimpleDateFormat sdf = new SimpleDateFormat(
		 * "dd-M-yyyy hh:mm"); String date_in_string = sdf.format(new Date());
		 * try { date = sdf.parse(date_in_string); } catch (ParseException e) {
		 * // TODO Auto-generated catch block e.printStackTrace(); }
		 */
		String name = store.add(taskName, details, startDate, endDate);
		System.out.println(name);
		if (name != null) {
			System.out.println("Task added");
			return name;
		} else {
			return null;
		}
	}

	private String edit(){
		return store.edit(taskIndex, taskName,details, startDate, endDate);
	}

	private String delete(){
		System.out.println("Task deleted");
		return store.delete(taskIndex);
	}

	private List<Task> search(){
		List<Task> list = store.search(taskName);
		return list;

	}

	private String acknowledge(){
		return store.complete(taskIndex);
	}

}
