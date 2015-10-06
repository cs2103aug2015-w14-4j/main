package processor;

import java.util.List;

import speedo.Task;

public class CmdPackage {
	private COMMANDS cmd;
	private Task task;
	private List<Task> taskList;
	
	public CmdPackage(COMMANDS cmd) {
		this.cmd = cmd;
	}
	
	public CmdPackage(COMMANDS cmd, List<Task> taskList) {
		this.cmd = cmd;
		this.taskList = taskList;
	}
	
	public CmdPackage(COMMANDS cmd, Task task){
		this.cmd = cmd;
		this.task = task;
	}
	
	public COMMANDS getCmd(){
		return cmd;
	}
	
	public Task getTask(){
		return task;
	}
	
	public List<Task> getTaskList(){
		return taskList;
	}
}
