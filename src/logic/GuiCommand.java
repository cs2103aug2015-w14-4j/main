package logic;

import java.util.List;

import storage.Task;
import utils.COMMANDS;

public class GuiCommand {
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
	
	public GuiCommand(COMMANDS cmd, String msg, Task task, int taskId, List<Task> listOfTasks){
		this.cmd = cmd;
		this.msg = msg;
		this.task = task;
		this.taskId = taskId;
		this.listOfTasks = listOfTasks;
	}
	
//	public GuiCommand(COMMANDS cmd, String msg, Task task, int index){
//		this(cmd, msg, task, index, null);
//	}
//	
//	public GuiCommand(COMMANDS cmd, String msg, Task task){
//		this(cmd, msg, task, task.getTaskId(), null);
//	}
	
	public GuiCommand(COMMANDS cmd, String msg, List<Task> listOfTasks){
		this(cmd, msg, null, 0, listOfTasks);
	}	
	
	public GuiCommand(COMMANDS cmd, String msg, int index){
		this(cmd, msg, null, index, null);
	}
	
	public GuiCommand(COMMANDS cmd, String msg){
		this(cmd, msg, null, 0, null);
	}

	public COMMANDS getCmd() {
		return cmd;
	}

	public String getMsg() {
		return msg;
	}

//	public Task getTask() {
//		return task;
//	}

	public int getTaskId() {
		return taskId;
	}

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
