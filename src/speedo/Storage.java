package speedo;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonStreamParser;

import storage.FileHandler;

public class Storage {
	private static final String FILENAME = "task.json";

	private static List<Task> taskList;
	private static TaskComparator taskComparator;

	public Storage() {
		taskList = new ArrayList<Task>();
		taskComparator = new TaskComparator();

		taskList = FileHandler.readTasks();
	}
	
	public Task getTask(int index){
		return taskList.get(index);
	}
	
	public int getNumOfTask(){
		return taskList.size();
	}

	public static boolean readFile() {
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

	public String delete(int index) {
		// TODO
		String name = taskList.get(index).getName();
		taskList.remove(index);
		return name;
	}

	public void edit(int index, String newName) {
		// TODO
		taskList.get(index).setName(newName);
		
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
