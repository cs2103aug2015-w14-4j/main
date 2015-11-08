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

public class Setting {
	private String fileName;
	private String userName;

	public static final String DEFAULT_FILENAME = "task";

	/**
	 * Default constructor for a Task Object.
	 * <p>
	 * The default file name is set to <i>"task"</i> and the default user
	 * name is set to <i>null</i>.
	 * <p>
	 * Refer to {@link #Settings(String, String)}.
	 */
	public Setting() {
		this(DEFAULT_FILENAME, null);
	}

	/**
	 * Constructor to create a Task Object.
	 * <p>
	 * Takes in the parameters for the file name and user name and sets the file
	 * path and user names accordingly.
	 * <p>
	 * Refer to {@link #getFileName(String)}, {@link #setUserName(String)}.
	 * 
	 * @param taskFileName
	 *            the file name as a String
	 * @param userName
	 *            the name of the user as a String
	 */
	public Setting(String taskFileName, String userName) {
		this.getFileName(taskFileName);
		this.setUserName(userName);
	}

	/**
	 * Method to get the file name
	 * 
	 * @return the file path as a String
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Method to set the file name
	 * 
	 * @param taskFileName
	 *            the file name as a String
	 */
	public void getFileName(String taskFileName) {
		if (taskFileName == null) {
			taskFileName = DEFAULT_FILENAME;
		}
		fileName = taskFileName;
	}

	/**
	 * Method to get the user name
	 * 
	 * @return the user name as a String
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Method to set the user name
	 * 
	 * @param userName
	 *            the user name as a String
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

}
