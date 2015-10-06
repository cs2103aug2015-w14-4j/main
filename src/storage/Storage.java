package storage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import speedo.Task;
import speedo.TaskComparator;

public class Storage {
	private static List<Task> taskList;
	private static TaskComparator taskComparator;

	public Storage() {
		taskList = new ArrayList<Task>();
		taskComparator = new TaskComparator();
		this.readFile();
	}
	
	public Task getTask(int index){
		return taskList.get(index);
	}
	
	public int getNumOfTask(){
		return taskList.size();
	}

	public boolean readFile() {
		taskList = FileHandler.readTasks();
		return true;

	}

	public boolean saveFile() {
		return FileHandler.saveTasks(taskList);

	}
	
	public List<Task> search(String searchTerm) {
		// TODO
		List<Task> searchList = new ArrayList<Task>();
		for (Task task : taskList) {
			if (task.contains(searchTerm)) {
				searchList.add(task);
			}
		}
		return searchList;
	}

	public Task add(String name, Date date) {
		// TODO
		Task newTask = new Task(name, date);
		
		if (isNotDuplicate(newTask)) {
			taskList.add(newTask);
		}
		taskList.sort(taskComparator);
		if(taskList.contains(newTask)){
			return newTask;
		} else {
			return null;
		}
	}

	public int delete(int index) {
		// TODO
		int taskId = taskList.get(index).getTaskId();
		taskList.remove(index);
		return taskId;
	}

	public Task edit(int index, String newName) {
		// TODO
		taskList.get(index).setName(newName);
		return taskList.get(index);
	}

	public void acknowledge(int index) {
		// TODO
		taskList.get(index).setAcknowledged(true);;
	}
	
	private boolean isNotDuplicate(Task newTask){
		for(int x=0; x<taskList.size(); x++){
			if(taskList.get(x).getTaskId() == newTask.getTaskId()){
				return false;
			}
		}
		return true;
	}

}
