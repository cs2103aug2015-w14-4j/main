package storage;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonStreamParser;

import processor.ErrorProcessor;
import speedo.Task;

public class FileHandler {
	private static final String FILENAME = "task.json";
	
	public static List<Task> readTasks() {

		Gson gson = GoogleJsonBuilder();
		List<Task> taskList = new ArrayList<Task>();
		JsonStreamParser jsonReader = JsonReader();
		if(jsonReader != null){
			while (hasNext(jsonReader)) {
				Task taskEntry = gson.fromJson(jsonReader.next(), Task.class);
				taskList.add(taskEntry);
			}			
		}

		for (Task task : taskList) {
			// convert java object to JSON format,
			// and returned as JSON formatted string
			System.out.println(task.toString());
		}
		return taskList;

	}
	
	private static Gson GoogleJsonBuilder (){
		return new GsonBuilder().create();
	}
	
	private static JsonStreamParser JsonReader (){
		try {
			return new JsonStreamParser(new FileReader(FILENAME));
		} catch (FileNotFoundException e) {
			ErrorProcessor.alert(FileHandler.class.getName(), e.getMessage());
			return null;
		}
	}
	
	private static boolean hasNext(JsonStreamParser jsonReader){
		try{
			return jsonReader.hasNext();
		}catch(com.google.gson.JsonIOException e){
			ErrorProcessor.alert(FileHandler.class.getName(), e.getMessage());
			return false;
		}
	}
	

	public static boolean saveTasks(List<Task> taskList) {
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
			ErrorProcessor.alert(e.getClass().getName(), e.getMessage());
			return false;
		}

		System.out.println(jsonTasks);
		return true;

	}
	
}
