//@@author A0125369Y
package storage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;
import java.util.logging.Logger;

public class Storage {
	private static List<Task> taskList;
	private static List<Task> completedList;
	private static TaskComparator taskComparator;
	private static Stack<Task> recentChanges;
	private static final Logger logger = Logger.getLogger(Storage.class.getName());

	private boolean isTestMode;

	public Storage() {
		this(false);
	}

	public Storage(boolean isTestMode) {
		taskList = new ArrayList<Task>();
		completedList = new ArrayList<Task>();
		taskComparator = new TaskComparator();
		recentChanges = new Stack<Task>();
		this.isTestMode = isTestMode;
		this.readFile();
	}

	public List<Task> getTaskList() {
		taskList.sort(taskComparator);
		return taskList;
	}

	public List<Task> getCompletedList() {
		completedList.sort(taskComparator);
		return completedList;
	}

	public boolean readFile() {
		if (!isTestMode) {
			taskList = FileHandler.readTasks();
			for (int x = 0; x < taskList.size(); x++) {
				if (taskList.get(x).isCompleted()) {
					completedList.add(taskList.get(x));
					taskList.remove(x);
				}
			}
			return true;
		}
		return false;

	}

	public boolean saveFile() {
		if (!isTestMode) {
			return FileHandler.saveTasks(taskList);
		}
		return false;
	}

	public List<Task> search(String searchTerm) {
		List<Task> searchList = new ArrayList<Task>();
		for (Task task : taskList) {
			if (task.contains(searchTerm)) {
				searchList.add(task);
			}
		}
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
	public Task add(String name, String details, Date startDate, Date endDate) {
		Task newTask = new Task(name, details, startDate, endDate);
		if (isNotDuplicate(newTask)) {
			taskList.add(newTask);
			recentChanges.push(newTask);
			logger.info("Added: " + name);
		} else {
			logger.info("Duplicate, Not Added: " + name);
		}
		taskList.sort(taskComparator);
		if (taskList.contains(newTask)) {
			return newTask;
		} else {
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
	public Task delete(int index) {
		if (isValidIndex(index)) {
			recentChanges.push(taskList.get(index));
			logger.info("Deleted: "+index+" " + taskList.get(index).getName());
			return taskList.remove(index);
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
	public Task edit(int index, String taskName, String details, Date startDate, Date endDate) {
		if (isValidIndex(index)) {
			Task currTask = backup(index);
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
			return taskList.get(index);
		} else {
			return null;
		}
	}

	/**
	 * Method to flag a task as complete
	 * 
	 * @param index
	 *            index of the task to flag as completed
	 * @return Task that was flagged as complete, if index is not valid, return
	 *         null
	 */
	public Task complete(int index) {
		if (isValidIndex(index)) {
			Task currTask = backup(index);
			completedList.add(currTask);
			currTask.setCompleted(true);
			taskList.remove(index);
			return taskList.get(index);
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
	public boolean undo() {
		if (!recentChanges.isEmpty()) {
			Task oldTask = recentChanges.pop();
			boolean edited = false;

			if (taskList.contains(oldTask)) {
				taskList.remove(oldTask);
				logger.info("Undo: add");
			} else {
				for (Task currTask : taskList) {
					if (currTask.getTaskId() == oldTask.getTaskId()) {
						currTask.setName(oldTask.getName());
						currTask.setDetails(oldTask.getDetails());
						currTask.setStartDate(oldTask.getStartDate());
						currTask.setEndDate(oldTask.getEndDate());
						currTask.setCompleted(oldTask.isCompleted());
						edited = true;
						logger.info("Undo: edit");
						break;
					}
				}

				if (!edited) {
					for (Task completedTask : completedList) {
						if (completedTask.getTaskId() == oldTask.getTaskId()) {
							completedList.remove(completedTask);
							break;
						}
					}
					taskList.add(oldTask);
				}
			}
			return true;
		}
		logger.info("Undo: nothing to undo");
		return false;
	}

	// Backs up a task to recentChanges, returns a new instance of the old task
	// for editing
	private Task backup(int index) {
		Task currTask = taskList.get(index);
		Task oldTask = new Task(currTask.getName(), currTask.getDetails(), currTask.getStartDate(),
				currTask.getEndDate());
		oldTask.setCompleted(currTask.isCompleted());
		oldTask.setTaskId(currTask.getTaskId());
		recentChanges.push(oldTask);
		assert currTask.getTaskId() == oldTask.getTaskId();
		logger.info("Backup Made: " + oldTask);
		return currTask;
	}

	private boolean isValidIndex(int index) {
		if (index < taskList.size() && index >= 0) {
			return true;
		} else {
			return false;
		}
	}

	// Compare hash code, then taskId to determine if task is a duplicate
	private boolean isNotDuplicate(Task newTask) {
		for (int x = 0; x < taskList.size(); x++) {
			if (taskList.get(x).getHashCode() == newTask.getHashCode()) {
				return false;
			} else if (taskList.get(x).getTaskId() == newTask.getTaskId()) {
				newTask.resetTaskId();
				return true;
			}
		}
		return true;
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
