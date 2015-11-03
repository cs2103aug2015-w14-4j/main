//@@author A0125369Y
package storage;

/**
 * Settings is a class representation of settings. It contains the following
 * information:
 * <ul>
 * <li>Task Filepath: Indicates where the list of tasks are stored.</li>
 * <li>User Name</li>
 * </ul>
 * 
 * @author Barnabas
 *
 */

public class Settings {
	private String TaskFilePath;
	private String UserName;
	
	// Default values for testing purposes
	public static final String DEFAULT_FILEPATH = "task.json";
	
	public Settings(){
		this(DEFAULT_FILEPATH, null);
	}
	
	public Settings(String taskFilePath, String userName){
		this.setTaskFilePath(taskFilePath);
		this.setUserName(userName);
	}
	
	public String getTaskFilePath() {
		return TaskFilePath;
	}
	
	public void setTaskFilePath(String taskFilePath) {
		if(taskFilePath == null){
			taskFilePath = DEFAULT_FILEPATH;
		}
		TaskFilePath = taskFilePath;
	}
	
	public String getUserName() {
		return UserName;
	}
	
	public void setUserName(String userName) {
		UserName = userName;
	}

}
