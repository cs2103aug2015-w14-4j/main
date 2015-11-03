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

public class FileHandler {
	private static final String NULL_ERROR = "Expected non-null Object, Received null Object";
	private static final String FILENAME = "task.json";
	private static final String SETTINGS_FILE = "settings.json";
	private static final String EMPTY = "";

	private String fileName;
	private Settings settings;
	private Gson googleJsonBuilder;
	
	
	public FileHandler() {
		// Default filename
		fileName = FILENAME;
		
		googleJsonBuilder = GoogleJsonBuilder();
	}
	

	public List<Task> readTasks() {
		List<Task> taskList = readJsonToList();
		return taskList;
	}

	public boolean saveTasks(List<Task> taskList) {
		assert taskList != null : NULL_ERROR;
		String jsonTasks = listToJson(taskList);
		return writeToFile(fileName, jsonTasks);
	}
	
	public boolean readSettings() {
		if(this.getSettings() != null){
			return true;
		} else {
			return false;
		}
	}
	
	public Settings saveSettings(Settings settings) {
		assert settings != null : NULL_ERROR;
		String jsonTasks = settingsToJson(settings);
		writeToFile(SETTINGS_FILE, jsonTasks);
		return this.getSettings();
	}
	
	public Settings updateSettings(String filePath, String userName) {
		if(settings == null){
			settings = new Settings();
		}
		if(filePath != null){
			settings.setTaskFilePath(filePath);
		}	
		if(userName != null){
			settings.setUserName(userName);
		}
		return this.saveSettings(settings);
		
	}
	
	public Settings getSettings() {
		settings = readSettingJson();
		if (settings != null) {
			fileName = settings.getTaskFilePath();
		}
		return settings;
	}

	private Settings readSettingJson() {
//		Gson googleJsonBuilder = GoogleJsonBuilder();
		JsonStreamParser jsonReader = JsonReader(SETTINGS_FILE);
		if (hasNext(jsonReader)) {
			Settings settingEntry = googleJsonBuilder.fromJson(jsonReader.next(), Settings.class);
			return settingEntry;
		}
		return null;
	}

	private List<Task> readJsonToList() {
//		Gson googleJsonBuilder = GoogleJsonBuilder();
		List<Task> taskList = new ArrayList<Task>();
		JsonStreamParser jsonReader = JsonReader(fileName);
		if (jsonReader != null) {
			while (hasNext(jsonReader)) {
				Task taskEntry = googleJsonBuilder.fromJson(jsonReader.next(), Task.class);
				taskList.add(taskEntry);
			}
		}
		return taskList;
	}

	private JsonStreamParser JsonReader(String fileName) {
		assert fileName != null : NULL_ERROR;
		File fileToRead = new File(fileName);
		try {
			fileToRead.createNewFile();
			return new JsonStreamParser(new FileReader(fileToRead));
		} catch (FileNotFoundException e) {
			// ErrorProcessor.alert(FileHandler.class.getName(),
			// e.getMessage());
			return null;
		} catch (IOException e) {
			ErrorProcessor.alert(FileHandler.class.getName(), e.getMessage());
			return null;
		}
	}

	private boolean hasNext(JsonStreamParser jsonReader) {
		assert jsonReader != null : NULL_ERROR;
		try {
			return jsonReader.hasNext();
		} catch (com.google.gson.JsonIOException e) {
			// This exception will always be thrown if the json file is empty.
			// ErrorProcessor.alert(FileHandler.class.getName(),
			// e.getMessage());
			return false;
		}
	}

	private String listToJson(List<Task> taskList) {
		assert taskList != null : NULL_ERROR;
//		Gson googleJsonBuilder = GoogleJsonBuilder();
		String jsonString = EMPTY;
		for (int x = 0; x < taskList.size(); x++) {
			Task task = taskList.get(x);
			jsonString += googleJsonBuilder.toJson(task);
		}
		return jsonString;
	}
	
	private String settingsToJson(Settings settings) {
		assert settings != null : NULL_ERROR;
//		Gson googleJsonBuilder = GoogleJsonBuilder();
		String jsonString = googleJsonBuilder.toJson(settings);
		return jsonString;
	}

	private static boolean writeToFile(String fileName, String content) {
		assert fileName != null : NULL_ERROR;
		assert content != null : NULL_ERROR;
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
		return new GsonBuilder().setPrettyPrinting().create();
	}




}
