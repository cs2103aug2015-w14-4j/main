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

public class Storage {
	private static final String FILENAME = "task.json";

	private static List<Task> taskList;
	private static TaskComparator taskComparator;

	public Storage() {
		taskList = new ArrayList<Task>();
		taskComparator = new TaskComparator();

		readFile();
	}
	
	public Task getTask(int index){
		return taskList.get(index);
	}
	
	public int getNumOfTask(){
		return taskList.size();
	}

	public static boolean readFile() {
		// TODO need to refactor further and remove sys.o.println

		Gson gson = new GsonBuilder().create();
		JsonStreamParser parser;
		if (taskList == null) {
			taskList = new ArrayList<Task>();
		}
		try {
			parser = new JsonStreamParser(new FileReader(FILENAME));
			while (parser.hasNext()) {
				Task taskEntry = gson.fromJson(parser.next(), Task.class);
				taskList.add(taskEntry);
			}
		} catch (FileNotFoundException e) {
			return false;
		} catch (com.google.gson.JsonIOException e) {
			return false;
		}

		for (Task task : taskList) {
			// convert java object to JSON format,
			// and returned as JSON formatted string
			System.out.println(task.toString());
		}
		return true;

	}

	public boolean saveFile() {
		// TODO need to refactor further and remove sys.o.println

		String jsonTasks = "";
		for (Task task : taskList) {
			// convert java object to JSON format,
			// and returned as JSON formatted string
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			jsonTasks += gson.toJson(task);
		}

		try {
			// write converted json data to a file named "task.json"
			FileWriter writer = new FileWriter(FILENAME);
			writer.write(jsonTasks);
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		System.out.println(jsonTasks);
		return true;

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
		if (!taskList.contains(newTask)) {
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

}
