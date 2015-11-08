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

import utilities.ErrorProcessor;

/**
 * FileHandler is a class designed to handle all the read/write/save events.
 * <p>
 * The two types of data that are handled here are:
 * <ul>
 * <li>Settings</li>
 * <li>Tasks</li>
 * </ul>
 * 
 * @author Barnabas
 *
 */
public class FileHandler {

	private String fileName;
	private Setting settings;
	private Gson googleJsonBuilder;

	private static final String FILEPATH = "%1$s.json";
	private static final String NULL_ERROR = "Expected non-null Object, Received null Object";
	private static final String SETTINGS_FILE = "settings.json";
	private static final String EMPTY = "";

	/**
	 * Default constructor for a FileHandler Object.
	 * <p>
	 * Here the GoogleJsonBuiler is initialized.
	 */
	public FileHandler() {
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
	 * @param taskList
	 *            list of tasks
	 * @param completedList
	 *            list of completed tasks
	 * 
	 * @return true if the file is successfully written, false otherwise
	 */
	public boolean saveTasks(List<Task> taskList, List<Task> completedList) {
		assert taskList != null : NULL_ERROR;
		assert completedList != null : NULL_ERROR;
		String jsonTasks = listToJson(taskList);
		jsonTasks += listToJson(completedList);
		return writeToFile(filePath(fileName), jsonTasks);
	}

	/**
	 * Method to read settings.
	 * 
	 * Refer to {@link #getSettings()} for more details.
	 * 
	 * @return true if settings can be read, false otherwise
	 */
	public boolean readSettings() {
		if (this.getSettings() != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method to save settings.
	 * <p>
	 * Refer to {@link #getSettings()} for more details on what is returned.
	 * 
	 * @return returns the settings that have been saved
	 */
	public Setting saveSettings(Setting settings) {
		assert settings != null : NULL_ERROR;
		String jsonTasks = settingsToJson(settings);
		writeToFile(SETTINGS_FILE, jsonTasks);
		return this.getSettings();
	}

	/**
	 * Method to get settings.
	 * <p>
	 * This method get and returns the Settings Object that encapsulates the
	 * settings. At the same time this method records the file name that is
	 * currently being used.
	 * 
	 * @return returns the Settings Object containing the settings
	 */
	public Setting getSettings() {
		settings = readSettingJson();
		if (settings != null) {
			fileName = settings.getFileName();
		} else {
			fileName =Setting.DEFAULT_FILENAME;
		}
		return settings;
	}

	/**
	 * Method to update settings.
	 * <p>
	 * Refer to {@link #saveSettings()} and {@link #getSettings()} for more
	 * details.
	 * 
	 * @param fileName
	 *            name of settings file
	 * @param userName
	 *            the user name of the user in String
	 * @return returns the settings that have been saved
	 */
	public Setting updateSettings(String fileName, String userName) {
		if (settings == null) {
			settings = new Setting();
		}
		if (fileName != null) {
			settings.setFileName(fileName);
		} else {
			settings.setFileName(this.fileName);
		}
		if (userName != null) {
			settings.setUserName(userName);
		}
		return this.saveSettings(settings);

	}

	// Writes to file, return true if successfully, false otherwise
	private static boolean writeToFile(String fileName, String content) {
		assert fileName != null : NULL_ERROR;
		assert content != null : NULL_ERROR;
		try {
			FileWriter writer = new FileWriter(fileName);
			writer.write(content);
			writer.close();
			return true;
		} catch (IOException e) {
			ErrorProcessor.alert(FileHandler.class.getName(), e.getMessage());
			return false;
		}
	}

	// Use GSON API to read from settings json file
	private Setting readSettingJson() {
		JsonStreamParser jsonReader = JsonReader(SETTINGS_FILE);
		if (hasNext(jsonReader)) {
			Setting settingEntry = googleJsonBuilder.fromJson(jsonReader.next(), Setting.class);
			return settingEntry;
		}
		return null;
	}

	// Use GSON API to read from tasks json file
	private List<Task> readJsonToList() {
		List<Task> taskList = new ArrayList<Task>();
		JsonStreamParser jsonReader = JsonReader(filePath(fileName));
		if (jsonReader != null) {
			while (hasNext(jsonReader)) {
				Task taskEntry = googleJsonBuilder.fromJson(jsonReader.next(), Task.class);
				taskList.add(taskEntry);
			}
		}
		return taskList;
	}

	// Attempt to create a new file and returns JsonStreamParser
	private JsonStreamParser JsonReader(String fileName) {
		assert fileName != null : NULL_ERROR;
		File fileToRead = new File(fileName);
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

	private boolean hasNext(JsonStreamParser jsonReader) {
		assert jsonReader != null : NULL_ERROR;
		try {
			return jsonReader.hasNext();
		} catch (com.google.gson.JsonIOException e) {
			// Thrown when json file empty
			return false;
		}
	}

	private String listToJson(List<Task> taskList) {
		assert taskList != null : NULL_ERROR;
		String jsonString = EMPTY;
		for (int i = 0; i < taskList.size(); i++) {
			Task task = taskList.get(i);
			jsonString += googleJsonBuilder.toJson(task);
		}
		return jsonString;
	}

	private String settingsToJson(Setting settings) {
		assert settings != null : NULL_ERROR;
		String jsonString = googleJsonBuilder.toJson(settings);
		return jsonString;
	}

	private String filePath(String fileName){
		return String.format(FILEPATH, fileName);
	}
	private static Gson GoogleJsonBuilder() {
		return new GsonBuilder().setPrettyPrinting().create();
	}

}
