import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Storage {
	private static List<Task> taskList;

	public static void readFile(){
		// FIXME This code is broken. Need to read all the text
		// then trim empty spaces and then split on }
		
		Gson gson = new Gson();

		try {

			BufferedReader br = new BufferedReader(
				new FileReader("task.json"));

			//convert the json string back to object
			Task obj = gson.fromJson(br, Task.class);

			System.out.println(obj);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean saveFile(){
		// TODO need to refactor further and remove sys.o.println
		
		String jsonTasks = "";
		for(Task task: taskList){
			// convert java object to JSON format,
			// and returned as JSON formatted string
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			jsonTasks += gson.toJson(task);
		}

		try {
			//write converted json data to a file named "task.json"
			FileWriter writer = new FileWriter("task.json");
			writer.write(jsonTasks);
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		System.out.println(jsonTasks);
		return true;

	}
		
	
	public static void search(){
		// TODO
	}
	
	public static boolean add(String name, Date date){
		// TODO
		if(taskList == null){
			taskList = new ArrayList<Task>();
		}
		Task newTask = new Task(name, date);
		taskList.add(newTask);
		
		return taskList.contains(newTask);
	}
	
	public static String delete(int index){
		// TODO
		if(taskList == null){
			return null;
		}
		return taskList.remove(index).getName();
	}
	
	public static void edit(){
		// TODO
	}
	
	public static void acknowledge(){
		// TODO
	}

}
