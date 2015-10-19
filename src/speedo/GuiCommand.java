package speedo;

import java.util.List;

import processor.COMMANDS;
import storage.Task;

public class GuiCommand {
	private COMMANDS cmd;
	private String msg;
	private Task task;
	private int taskId;
	private List<Task> listOfTasks; 
	
	public GuiCommand(COMMANDS cmd, String msg, Task task, int taskId, List<Task> listOfTasks){
		this.cmd = cmd;
		this.msg = msg;
		this.task = task;
		this.taskId = taskId;
		this.listOfTasks = listOfTasks;
	}
	
	public GuiCommand(COMMANDS cmd, String msg, Task task, int index){
		this(cmd, msg, task, index, null);
	}
	
	public GuiCommand(COMMANDS cmd, String msg, Task task){
		this(cmd, msg, task, task.getTaskId(), null);
	}
	
	public GuiCommand(COMMANDS cmd, String msg, List<Task> listOfTasks){
		this(cmd, msg, null, 0, listOfTasks);
	}	
	
	public GuiCommand(COMMANDS cmd, String msg, int taskId){
		this(cmd, msg, null, taskId, null);
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

	public Task getTask() {
		return task;
	}

	public int getTaskId() {
		return taskId;
	}

	public List<Task> getListOfTasks() {
		return listOfTasks;
	}
		
}
