//@@author A0125369Y
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
	private static final String SETTINGS_FILE = "settings.json";
	private static final String EMPTY = "";

	private String fileName;
	private Settings settings;
	private Gson googleJsonBuilder;
	
	
	public FileHandler() {
		// Default filename
		fileName = Settings.DEFAULT_FILEPATH;
		
		googleJsonBuilder = GoogleJsonBuilder();
	}
	
	/**
	 * Method to read the list of tasks.
	 * 
	 * @return list of tasks
	 */
	public List<Task> readTasks() {
		List<Task> taskList = readJsonToList();
		return taskList;
	}

	/**
	 * Method to save the list of tasks.
	 * 
	 * @return true if the file is successfully written, false otherwise
	 */
	public boolean saveTasks(List<Task> taskList) {
		assert taskList != null : NULL_ERROR;
		String jsonTasks = listToJson(taskList);
		return writeToFile(fileName, jsonTasks);
	}
	
	/**
	 * Method to read settings.
	 * 
	 * Refer to {@link #getSettings()} for more details.
	 * 
	 * @return true if settings can be read, false otherwise
	 */
	public boolean readSettings() {
		if(this.getSettings() != null){
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method to save settings.
	 * 
	 * Refer to {@link #getSettings()} for more details.
	 * 
	 * @return returns the settings that have been saved
	 */
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
			System.out.println(fileName);
		}
		return settings;
	}

	public static boolean writeToFile(String fileName, String content) {
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
	
	private Settings readSettingJson() {
		JsonStreamParser jsonReader = JsonReader(SETTINGS_FILE);
		if (hasNext(jsonReader)) {
			Settings settingEntry = googleJsonBuilder.fromJson(jsonReader.next(), Settings.class);
			return settingEntry;
		}
		return null;
	}

	private List<Task> readJsonToList() {
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
		String jsonString = EMPTY;
		for (int x = 0; x < taskList.size(); x++) {
			Task task = taskList.get(x);
			jsonString += googleJsonBuilder.toJson(task);
		}
		return jsonString;
	}
	
	private String settingsToJson(Settings settings) {
		assert settings != null : NULL_ERROR;
		String jsonString = googleJsonBuilder.toJson(settings);
		return jsonString;
	}

	private static Gson GoogleJsonBuilder() {
		return new GsonBuilder().setPrettyPrinting().create();
	}

}
