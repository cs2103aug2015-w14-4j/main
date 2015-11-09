//@@author A0121823R
package logic;

import java.util.List;

import storage.Task;
import utilities.COMMANDS;

public class GuiCommand {
	//necessary data type to be stored in GuiCommand object
	private COMMANDS cmd;
	private String msg;
	private Task task;
	private int taskId;
	private List<Task> listOfTasks; 
	
	// For dynamic task display
	private String title;
	private String taskName;
	private String taskDetails;
	private String taskStart;
	private String taskEnd;
	
	public GuiCommand(COMMANDS cmd, String msg, Task task, List<Task> listOfTasks){
		this.cmd = cmd;
		this.msg = msg;
		this.task = task;
		this.listOfTasks = listOfTasks;
	}
	
	//contains type of COMMANDS object, message to be sent to Gui and list of tasks in the to do list
	//to be generated by the logic's add, edit, delete, search, name, filepath and complete command
	public GuiCommand(COMMANDS cmd, String msg, List<Task> listOfTasks){
		this(cmd, msg, null, listOfTasks);
	}	
	
//	public GuiCommand(COMMANDS cmd, String msg,){
//		this(cmd, msg, null, null);
//	}
	
	//contains only type of COMMANDS object and message to be sent to Gui
	//object to be created by logic's help, exit and invalid commands
	public GuiCommand(COMMANDS cmd, String msg){
		this(cmd, msg, null, null);
	}

	public COMMANDS getCmd() {
		return cmd;
	}

	public String getMsg() {
		return msg;
	}

	//no longer necessary
//	public int getTaskId() {
//		return taskId;
//	}

	public List<Task> getListOfTasks() {
		return listOfTasks;
	}
	
	public String getTitle(){
		return title;
	}

	public String getTaskName() {
		return taskName;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDetails() {
		return taskDetails;
	}

	public void setTaskDetails(String taskDetails) {
		this.taskDetails = taskDetails;
	}

	public String getTaskStart() {
		return taskStart;
	}

	public void setTaskStart(String taskStart) {
		this.taskStart = taskStart;
	}

	public String getTaskEnd() {
		return taskEnd;
	}

	public void setTaskEnd(String taskEnd) {
		this.taskEnd = taskEnd;
	}
		
}
