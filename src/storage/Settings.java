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

	private static final String DEFAULT_FILENAME = "task";
	private static final String DEFAULT_EXTENSION = "%1$s.json";

	public static final String DEFAULT_FILEPATH = String.format(DEFAULT_EXTENSION, DEFAULT_FILENAME);

	/**
	 * Default constructor for a Task Object.
	 * <p>
	 * The default file path is set to <i>"task.json"</i> and the default user
	 * name is set to <i>null</i>.
	 * <p>
	 * Refer to {@link #Settings(String, String)}.
	 */
	public Settings() {
		this(DEFAULT_FILENAME, null);
	}

	/**
	 * Constructor to create a Task Object.
	 * <p>
	 * Takes in the parameters for the file name and user name and sets the file
	 * path and user names accordingly.
	 * <p>
	 * Refer to {@link #setTaskFilePath(String)}, {@link #setUserName(String)}.
	 * 
	 * @param taskFileName
	 *            the file name as a String
	 * @param userName
	 *            the name of the user as a String
	 */
	public Settings(String taskFileName, String userName) {
		this.setTaskFilePath(taskFileName);
		this.setUserName(userName);
	}

	/**
	 * Method to get the file path
	 * 
	 * @return the file path as a String
	 */
	public String getTaskFilePath() {
		return TaskFilePath;
	}

	/**
	 * Method to set the file path
	 * <p>
	 * This methods appends the file extension <i>.json</i> at the back of the
	 * given file name. If the given file name is null, the file path will be
	 * set to the <i>DEFAULT_FILEPATH</i> which is <i>task.json</i>.
	 * 
	 * @param taskFileName
	 *            the file name as a String
	 */
	public void setTaskFilePath(String taskFileName) {
		if (taskFileName == null) {
			taskFileName = DEFAULT_FILENAME;
		}
		TaskFilePath = String.format(DEFAULT_EXTENSION, taskFileName);
	}

	/**
	 * Method to get the user name
	 * 
	 * @return the user name as a String
	 */
	public String getUserName() {
		return UserName;
	}

	/**
	 * Method to set the user name
	 * 
	 * @param userName
	 *            the user name as a String
	 */
	public void setUserName(String userName) {
		UserName = userName;
	}

}
