package storage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class Storage {
	private static List<Task> taskList;
	private static TaskComparator taskComparator;
	private static final Logger logger = Logger.getLogger(Storage.class.getName());

	public Storage() {
		taskList = new ArrayList<Task>();
		taskComparator = new TaskComparator();
		this.readFile();
	}
	
	public List<Task> getTaskList() {
		// TODO Auto-generated method stub
		return taskList;
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
			logger.info("Added: "+name);
		} else {
			logger.info("Duplicate, Not Added: "+name);
		}
		taskList.sort(taskComparator);
		if(taskList.contains(newTask)){
			return newTask;
		} else {
			return null;
		}
	}

	public Task delete(int index) {
		// TODO
		assert index < taskList.size();
		return taskList.remove(index);
	}

	public Task edit(int index, String newName) {
		// TODO
		taskList.get(index).setName(newName);
		return taskList.get(index);
	}

	public Task acknowledge(int index) {
		// TODO
		taskList.get(index).setCompleted(true);
		return taskList.get(index);
	}
	
	private boolean isNotDuplicate(Task newTask){
		for(int x=0; x<taskList.size(); x++){
			if(taskList.get(x).getHashCode() == newTask.getHashCode()){
				return false;
			} else if(taskList.get(x).getTaskId() == newTask.getTaskId()){
				newTask.resetTaskId();
				return true;
			}
		}
		return true;
	}

}
