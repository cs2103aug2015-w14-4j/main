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
	
	public static boolean readFile(){
		// TODO need to refactor further and remove sys.o.println
		
	    Gson gson = new GsonBuilder().create();
	    JsonStreamParser parser;
		if(taskList == null){
			taskList = new ArrayList<Task>();
		}
		try {
			parser = new JsonStreamParser(new FileReader(FILENAME));
		    while(parser.hasNext())
		    {
		    	Task taskEntry = gson.fromJson(parser.next(), Task.class);
		    	taskList.add(taskEntry);
		    }
		} catch (FileNotFoundException e) {
			return false;
		}
		
		for(Task task: taskList){
			// convert java object to JSON format,
			// and returned as JSON formatted string
			System.out.println(task.toString());
		}
		return true;

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
