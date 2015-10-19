//@@author A0125369Y
package storage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Stack;
import java.util.logging.Logger;

public class Storage {
	private static List<Task> taskList;
	private static TaskComparator taskComparator;
	private static Stack<Task> recentChanges;
	private static final Logger logger = Logger.getLogger(Storage.class.getName());

	public Storage() {
		taskList = new ArrayList<Task>();
		taskComparator = new TaskComparator();
		recentChanges = new Stack<Task>();
		this.readFile();
	}

	public List<Task> getTaskList() {
		// TODO Auto-generated method stub
		taskList.sort(taskComparator);
		return taskList;
	}

	public boolean readFile() {
		taskList = FileHandler.readTasks();
		return true;

	}

	public boolean saveFile() {
		return FileHandler.saveTasks(taskList);

	}

	public List<Task> search(String searchTerm) {
		// TODO
		List<Task> searchList = new ArrayList<Task>();
		for (Task task : taskList) {
			if (task.contains(searchTerm)) {
				searchList.add(task);
			}
		}
		return searchList;
	}

	public Task add(String name, Date date) {
		// TODO
		Task newTask = new Task(name, date);

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

	public Task delete(int index) {
		// TODO
		assert index < taskList.size();
		recentChanges.push(taskList.get(index));
		return taskList.remove(index);
	}

	public Task edit(int index, String newName) {
		// TODO
		Task currTask = taskList.get(index);
		Task oldTask = new Task(currTask.getName(), currTask.getDetails(), currTask.getStartDate(),
				currTask.getEndDate());
		oldTask.setCompleted(currTask.isCompleted());
		recentChanges.push(oldTask);
		taskList.get(index).setName(newName);
		assert taskList.get(index).getTaskId() == oldTask.getTaskId();
		return taskList.get(index);
	}

	/**
	 * Method to flag a task as complete
	 * 
	 * @param index
	 *            index of the task to flag as completed
	 * @return Task that was flagged as complete
	 */
	public Task complete(int index) {
		// TODO
		taskList.get(index).setCompleted(true);
		return taskList.get(index);
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
			} else {
				for (Task currTask : taskList) {
					if (currTask.getTaskId() == oldTask.getTaskId()) {
						currTask.setName(oldTask.getName());
						currTask.setDetails(oldTask.getDetails());
						currTask.setStartDate(oldTask.getStartDate());
						currTask.setEndDate(oldTask.getEndDate());
						currTask.setCompleted(oldTask.isCompleted());
						edited = true;
						break;
					}
				}

				if (!edited) {
					taskList.add(oldTask);
				}
			}
			return true;
		}
		return false;
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

}
