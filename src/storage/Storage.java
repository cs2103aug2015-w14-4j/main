//@@author A0125369Y
package storage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;

import utilities.LogProcessor;

/**
 * Storage is a class that provides the basic add/remove/delete/edit functions.
 * <p>
 * It depends on the FileHandler class for retrieving and storing the data to a
 * file.
 * 
 * @author Barnabas
 *
 */

public class Storage {
	private static List<Task> taskList;
	private static List<Task> completedList;
	private static Stack<Task> recentChanges;
	private static TaskComparator taskComparator;
	private static FileHandler fileHandler;

	private boolean isTestMode;

	// Assert message
	private static final String NULL_ERROR = "Expected non-null Object, Received null Object";

	// Logging, and Log messages
	private static LogProcessor logger;
	private static final String TASK_ADDED = "Task added: \"%1$s\"";
	private static final String TASK_BACKUP = "Backup mode: \"%1$s\"";
	private static final String TASK_DUPLICATE = "Duplicate task, not added: \"%1$s\"";
	private static final String TASK_DELETED = "Task deleted: \"%1$s\"";
	private static final String TASK_UNDO = "Task has been undone ";
	private static final String TASK_NO_UNDO = "Nothing to undo";
	private static final String TASK_SEARCH = "Searching: \"%1$s\"";
	private static final String TASK_EDITED = "Edited: \"%1$s\"";
	private static final String TASK_ACK = "Acked: \"%1$s\"";
	private static final String NAME_SET = "Name Changed: \"%1$s\"";

	/**
	 * Default constructor for a Storage Object.
	 * <p>
	 * The testing parameter is set to false by default.
	 */
	public Storage() {
		this(false);
	}

	/**
	 * Constructor for a Storage Object.
	 * <p>
	 * If the test mode is true, there is testing in progress. The data
	 * processed will not be read from or saved to a file.
	 * 
	 * @param isTestMode
	 *            enter true if testing, false otherwise
	 */
	public Storage(boolean isTestMode) {
		this.isTestMode = isTestMode;
		logger = LogProcessor.getInstance();
		taskList = new ArrayList<Task>();
		completedList = new ArrayList<Task>();
		taskComparator = new TaskComparator();
		recentChanges = new Stack<Task>();
		if (!isTestMode) {
			this.init();
		}
	}

	/**
	 * Method to get the list of tasks
	 * 
	 * @return the list of tasks
	 */
	public List<Task> getTaskList() {
		this.shiftCompleted();
		taskList.sort(taskComparator);
		return taskList;
	}

	/**
	 * Method to get the list of completed tasks
	 * 
	 * @return the list of completed tasks
	 */
	public List<Task> getCompletedList() {
		completedList.sort(taskComparator);
		return completedList;
	}

	/**
	 * Method to read the taskList from a json file
	 * 
	 * @return returns the user name, returns null if in test mode
	 */
	public String readFile() {
		if (!isTestMode) {
			taskList = fileHandler.readTasks();
			this.shiftCompleted();
		}
		return null;
	}

	/**
	 * Method to read the settings from a json file
	 * 
	 * @return returns the user name, returns null if no settings found
	 */
	public String readSettings() {
		if (fileHandler.getSettings() != null) {
			return fileHandler.getSettings().getUserName();
		} else {
			return null;
		}
	}

	/**
	 * Method to set the settings
	 * 
	 * @param userName
	 *            the user name in String
	 * @param filePath
	 *            the file location in string
	 * 
	 * @return the current user name
	 */
	public void setSettings(String userName, String filePath) {
		fileHandler.updateSettings(filePath, userName);
		this.readFile();
	}

	/**
	 * Method to set the user name
	 * 
	 * @param userName
	 *            the user name in String
	 * 
	 * @return the current user name
	 */
	public String setUser(String userName) {
		fileHandler.updateSettings(null, userName);
		logger.log(String.format(NAME_SET, userName));
		return fileHandler.getSettings().getUserName();
	}

	/**
	 * Method to search for tasks
	 * 
	 * @param searchTerm
	 *            the string to find
	 * @return searchList containing the tasks that has the string
	 */
	public List<Task> search(String searchTerm) {
		List<Task> searchList = new ArrayList<Task>();
		for (Task task : taskList) {
			if (task.contains(searchTerm)) {
				searchList.add(task);
			}
		}
		logger.log(String.format(TASK_SEARCH, searchTerm));
		return searchList;
	}

	/**
	 * Method to add a task
	 * 
	 * @param name
	 *            name of the task to add
	 * @param details
	 *            details of the task to add
	 * @param startDate
	 *            starting date of the task to add
	 * @param endDate
	 *            ending date of the task to add
	 * @return Task that was added, if not added, return null
	 */
	public String add(String name, String details, Date startDate, Date endDate) {
		Task newTask = new Task(name, details, startDate, endDate);
		if (isNotDuplicate(newTask)) {
			taskList.add(newTask);
			recentChanges.push(newTask);
			taskList.sort(taskComparator);
			this.saveFile();
			logger.log(String.format(TASK_ADDED, name));
			return newTask.getName();
		} else {
			logger.log(String.format(TASK_DUPLICATE, name));
			return null;
		}
	}

	/**
	 * Method to delete a task
	 * 
	 * @param index
	 *            index of the task to delete
	 * @return Task that was deleted, if not deleted, return null
	 */
	public String delete(int index) {
		if (isValidIndex(index)) {
			recentChanges.push(taskList.get(index));
			this.saveFile();
			logger.log(String.format(TASK_DELETED, taskList.get(index).getName()));
			return taskList.remove(index).getName();
		} else {
			return null;
		}
	}

	/**
	 * Method to edit a task
	 * 
	 * @param index
	 *            index of the task to flag as completed
	 * @param taskName
	 *            the new string to replace the previous name
	 * @param details
	 *            the new string to replace the previous details
	 * @param startDate
	 *            the new starting date to replace the previous starting date
	 * @param endDate
	 *            the new ending date to replace the previous ending date
	 * @return Task that was edited, if index is not valid, return null
	 */
	public String edit(int index, String taskName, String details, Date startDate, Date endDate) {
		if (isValidIndex(index)) {
			Task currTask = backup(index);
			String name = taskList.get(index).getName();
			if (taskName != null) {
				currTask.setName(taskName);
			}
			if (details != null) {
				currTask.setDetails(details);
			}
			if (startDate != null) {
				currTask.setStartDate(startDate);
			}
			if (endDate != null) {
				if (currTask.getStartDate() != null) {
					if (currTask.getStartDate().after(endDate)) {
						Date tempDate = currTask.getStartDate();
						currTask.setEndDate(tempDate);
						currTask.setStartDate(endDate);
					}
				} else {
					currTask.setEndDate(endDate);
				}
			}
			logger.log(String.format(TASK_EDITED, name));
			this.saveFile();
			return name;
		} else {
			return null;
		}
	}

	/**
	 * Method to flag a task as complete
	 * 
	 * @param index
	 *            index of the task to flag as completed
	 * @return nams of task that was flagged as complete, if index is not valid, return
	 *         null
	 */
	public String complete(int index) {
		if (isValidIndex(index)) {
			Task currTask = backup(index);
			completedList.add(currTask);
			currTask.setCompleted(true);
			taskList.remove(index);
			logger.log(String.format(TASK_ACK, currTask.getName()));
			this.saveFile();
			return currTask.getName();
		} else {
			return null;
		}
	}

	/**
	 * Method to Undo the last change made
	 * <p>
	 * This method checks if the recent task in recentChanges exists in taskList
	 * and remove it from taskList (Undo Add). Else checks if task id a task in
	 * taskList and reverts changes(Undo Edit). Else add it to taskList (Undo
	 * DELETE).
	 * 
	 * @return true if there are changes to undo, false otherwise
	 */
	public String undo() {
		if (!recentChanges.isEmpty()) {
			Task oldTask = recentChanges.pop();
			if (!this.isUndoAdd(oldTask)) {
				if (!this.isUndoEdit(oldTask)) {
					if (!this.isUndoAck(oldTask)) {
						this.undoDelete(oldTask);
					}
				}
			}
			this.saveFile();
			logger.log(TASK_UNDO);
			return oldTask.getName();
		}
		logger.log(TASK_NO_UNDO);
		return null;
	}

	// **SUPPORTING FUNCTIONS FOR UNDO**

	private boolean isUndoAdd(Task oldTask) {
		assert oldTask != null : NULL_ERROR;
		if (taskList.contains(oldTask)) {
			taskList.remove(oldTask);
			return true;
		}
		return false;
	}

	private boolean isUndoEdit(Task oldTask) {
		assert oldTask != null : NULL_ERROR;
		for (int i = 0; i < taskList.size(); i++) {
			if (taskList.get(i).getTaskId() == oldTask.getTaskId()) {
				this.clone(taskList.get(i), oldTask);
				return true;
			}
		}
		return false;
	}

	private boolean isUndoAck(Task oldTask) {
		assert oldTask != null : NULL_ERROR;
		for (int i = 0; i < completedList.size(); i++) {
			if (completedList.get(i).getTaskId() == oldTask.getTaskId()) {
				completedList.remove(completedList.get(i));
				taskList.add(oldTask);
				return true;
			}
		}
		return false;
	}

	private void undoDelete(Task oldTask) {
		taskList.add(oldTask);
	}

	private void clone(Task currTask, Task oldTask) {
		assert currTask != null : NULL_ERROR;
		assert oldTask != null : NULL_ERROR;
		currTask.setName(oldTask.getName());
		currTask.setDetails(oldTask.getDetails());
		currTask.setStartDate(oldTask.getStartDate());
		currTask.setEndDate(oldTask.getEndDate());
		currTask.setCompleted(oldTask.isCompleted());
	}

	// **SUPPORTING FUNCTIONS FOR COMPLETE**

	// Backs up a task to recentChanges, returns a new instance of the old task
	// for editing
	private Task backup(int index) {
		Task currTask = taskList.get(index);
		Task oldTask = this.backupTask(currTask);
		recentChanges.push(oldTask);
		assert currTask.getTaskId() == oldTask.getTaskId();
		logger.log(String.format(TASK_BACKUP, oldTask));
		return currTask;
	}

	private Task backupTask(Task currTask) {
		Task oldTask = new Task(currTask.getName(), currTask.getDetails(), currTask.getStartDate(),
				currTask.getEndDate());
		oldTask.setCompleted(currTask.isCompleted());
		oldTask.setTaskId(currTask.getTaskId());
		return oldTask;
	}

	// **SUPPORTING FUNCTION FOR ADD**

	// Compare hash code, then taskId to determine if task is a duplicate
	private boolean isNotDuplicate(Task newTask) {
		for (int i = 0; i < taskList.size(); i++) {
			if (taskList.get(i).getHashCode() == newTask.getHashCode()) {
				return false;
			} else if (taskList.get(i).getTaskId() == newTask.getTaskId()) {
				newTask.resetTaskId();
				return true;
			}
		}
		return true;
	}

	// **SUPPORTING FUNCTION FOR READFILE**

	private void shiftCompleted() {
		for (int i = 0; i < taskList.size(); i++) {
			if (taskList.get(i).isCompleted()) {
				completedList.add(taskList.get(i));
				taskList.remove(i);
			}
		}
	}

	// **SUPPORTING FUNCTIONS FOR CONSTRUCTOR**

	private void init() {
		fileHandler = new FileHandler();
		this.readSettings();
		this.readFile();
	}

	// **SUPPORTING FUNCTIONS FOR ALL**

	private boolean saveFile() {
		if (!isTestMode) {
			return fileHandler.saveTasks(taskList, completedList);
		}
		return false;
	}

	private boolean isValidIndex(int index) {
		if (index < taskList.size() && index >= 0) {
			return true;
		} else {
			return false;
		}
	}

	// The following code have been removed due to changes in the way the dates
	// are handled. They are left here in case there is a need to refer to them.
	// /**
	// * Method to edit a task
	// *
	// * @param index
	// * index of the task to flag as completed
	// * @param newName
	// * the new string to replace the previous name
	// * @return Task that was edited, if index is not valid, return null
	// */
	// public Task edit(int index, String newName) {
	// if (isValidIndex(index)) {
	// Task currTask = taskList.get(index);
	// Task oldTask = new Task(currTask.getName(), currTask.getDetails(),
	// currTask.getStartDate(),
	// currTask.getEndDate());
	// oldTask.setCompleted(currTask.isCompleted());
	// recentChanges.push(oldTask); // Backup old task
	// taskList.get(index).setName(newName); // Edit name
	// assert taskList.get(index).getTaskId() == oldTask.getTaskId();
	// return taskList.get(index);
	// } else {
	// return null;
	// }
	// }
	//
	// /**
	// * Method to edit a task
	// *
	// * @param index
	// * index of the task to flag as completed
	// * @param newName
	// * the new string to replace the previous name
	// * @param name
	// * true if editing name, false if editing details
	// * @return Task that was edited, if index is not valid, return null
	// */
	// public Task edit(int index, String newName, boolean name) {
	// if (isValidIndex(index)) {
	// Task currTask = taskList.get(index);
	// Task oldTask = new Task(currTask.getName(), currTask.getDetails(),
	// currTask.getStartDate(),
	// currTask.getEndDate());
	// oldTask.setCompleted(currTask.isCompleted());
	// recentChanges.push(oldTask); // Backup old task
	// if (name) {
	// taskList.get(index).setName(newName); // Edit name
	// } else {
	// taskList.get(index).setDetails(newName); // Edit details
	// }
	// assert taskList.get(index).getTaskId() == oldTask.getTaskId();
	// return taskList.get(index);
	// } else {
	// return null;
	// }
	// }
	//
	// /**
	// * Method to edit a task
	// *
	// * @param index
	// * index of the task to flag as completed
	// * @param newDate
	// * the new date to replace the previous date
	// * @param startDate
	// * true if replacing the starting date, false otherwise
	// * @return Task that was edited, if index is not valid, return null
	// */
	// public Task edit(int index, Date newDate, boolean startDate) {
	// if (isValidIndex(index)) {
	// Task currTask = taskList.get(index);
	// Task oldTask = new Task(currTask.getName(), currTask.getDetails(),
	// currTask.getStartDate(),
	// currTask.getEndDate());
	// oldTask.setCompleted(currTask.isCompleted());
	// recentChanges.push(oldTask); // Backup old task
	// if (startDate) {
	// taskList.get(index).setStartDate(newDate); // Edit starting date
	// } else {
	// taskList.get(index).setEndDate(newDate); // Edit ending date
	// }
	// assert taskList.get(index).getTaskId() == oldTask.getTaskId();
	// return taskList.get(index);
	// } else {
	// return null;
	// }
	// }

}
