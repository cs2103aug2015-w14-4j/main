package storage;

import java.io.File;
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

	public static List<Task> readTasks() {;
		List<Task> taskList = readJsonToList();
		return taskList;
	}

	public static boolean saveTasks(List<Task> taskList) {
		String jsonTasks = listToJsonString(taskList);
		return writeToFile(FILENAME, jsonTasks);
	}
	
	private static List<Task> readJsonToList(){
		Gson googleJsonBuilder = GoogleJsonBuilder();
		List<Task> taskList = new ArrayList<Task>();
		JsonStreamParser jsonReader = JsonReader();
		if (jsonReader != null) {
			while (hasNext(jsonReader)) {
				Task taskEntry = googleJsonBuilder.fromJson(jsonReader.next(), Task.class);
				taskList.add(taskEntry);
			}
		}
		return taskList;	
	}

	private static JsonStreamParser JsonReader() {
		File fileToRead = new File(FILENAME);
		try {
			fileToRead.createNewFile();
			return new JsonStreamParser(new FileReader(fileToRead));
		} catch (FileNotFoundException e) {
			ErrorProcessor.alert(FileHandler.class.getName(), e.getMessage());
			return null;
		} catch (IOException e) {
			ErrorProcessor.alert(FileHandler.class.getName(), e.getMessage());
			return null;
		}
	}

	private static boolean hasNext(JsonStreamParser jsonReader) {
		try {
			return jsonReader.hasNext();
		} catch (com.google.gson.JsonIOException e) {
			ErrorProcessor.alert(FileHandler.class.getName(), e.getMessage());
			return false;
		}
	}

	private static String listToJsonString(List<Task> taskList) {
		Gson googleJsonBuilder = GoogleJsonBuilder();
		String jsonString = "";
		for (int x = 0; x < taskList.size(); x++) {
			Task task = taskList.get(x);
			jsonString += googleJsonBuilder.toJson(task);
		}
		return jsonString;
	}

	private static boolean writeToFile(String fileName, String content) {
		try {
			FileWriter writer;
			writer = new FileWriter(fileName);
			writer.write(content);
			writer.close();
			return true;
		} catch (IOException e) {
			ErrorProcessor.alert(FileHandler.class.getName(), e.getMessage());
			return false;
		}

	}
	
	private static Gson GoogleJsonBuilder() {
		return new GsonBuilder().create();
	}

}
