//@@author A0124791A
package controller;

import java.io.IOException;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.VBox;
import storage.Task;
import utilities.ErrorProcessor;

/**
 * TaskListController manages the display of the list of user Task(s) 
 * <p>
 * The user can scroll vertically when the number of tasks to display is larger than the display area 
 * Scrolling horizontally can only be done with keyboard and is only allowed when the Task has a 
 * name that exceeds the display
 *
 * @author Hongchao
 *
 */

public class TaskListController extends ScrollPane {

	// ================================================================
	// FXML
	// ================================================================
	private static final String FXML_PATH = "/view/TaskListView.fxml";
	@FXML
	private VBox containerOfTask;

	// ================================================================
	// Task labels
	// ================================================================
	private static final String NODATE_LABEL = "TO-DO's";
	private static final String OVERDUE_LABEL = "OVERDUE";
	private static final String TODAY_LABEL = "TODAY";
	private static final String TOMORROW_LABEL = "TOMORROW";
	private static final String UPCOMING_LABEL = "UPCOMING";

	// ================================================================
	// Fields
	// ================================================================
	private ObservableList<Node> listOfTask;
	private boolean[] isTaskType;
	private int numOfTaskDue;

	public TaskListController() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(FXML_PATH));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			ErrorProcessor.alert(e.toString());
		}
	}

	@FXML
	public void initialize() {
		listOfTask = containerOfTask.getChildren();
		// prevents the user from scrolling left and right using the mouse
		this.addEventFilter(ScrollEvent.SCROLL, new EventHandler<ScrollEvent>() {
			@Override
			public void handle(ScrollEvent event) {
				if (event.getDeltaX() != 0) {
					event.consume();
				}
			}
		});
	}

	/**
	 * Loads a list of task(s) to display The number of task(s) due will be
	 * reset If there is any old list, it will be deleted Labels will be added
	 * here to act as sections between the different types of task
	 * 
	 * @param list
	 *            list of task(s) that is to be loaded
	 */
	public void loadTaskList(List<Task> list) {
		numOfTaskDue = 0;
		isTaskType = new boolean[6];
		listOfTask.clear();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				generateLabel(list.get(i));
				addTask(list.get(i), i + 1);
			}
		}
	}

	/**
	 * Adds the task to the task list and appends the given index to its name
	 * The number of task(s) due is calculated here
	 * 
	 * @param task
	 *            task to be added
	 * @param index
	 *            task index to be displayed
	 */
	private void addTask(Task task, int index) {
		listOfTask.add(new TaskController(task, index));
		if (task.due() == Task.TODAY && !task.isCompleted()) {
			numOfTaskDue++;
		}
	}

	/**
	 * Checks if a task of particular type exists in the list If false, a label
	 * will be added Otherwise, no label will be added
	 * 
	 * @param task
	 *            task to be checked
	 */
	private void generateLabel(Task task) {
		if (task.due() == Task.NODATE && !isTaskType[Task.NODATE]) {
			addLabel(NODATE_LABEL, Task.NODATE);
		} else if (task.due() == Task.OVERDUE && !isTaskType[Task.OVERDUE]) {
			addLabel(OVERDUE_LABEL, Task.OVERDUE);
		} else if (task.due() == Task.TODAY && !isTaskType[Task.TODAY]) {
			addLabel(TODAY_LABEL, Task.TODAY);
		} else if (task.due() == Task.TOMORROW && !isTaskType[Task.TOMORROW]) {
			addLabel(TOMORROW_LABEL, Task.TOMORROW);
		} else if (task.due() == Task.UPCOMING && !isTaskType[Task.UPCOMING]) {
			addLabel(UPCOMING_LABEL, Task.UPCOMING);
		}
	}

	/**
	 * Adds a label
	 * 
	 * @param title
	 *            the text to display in the label
	 * @param taskType
	 *            enum of the type of task
	 */
	private void addLabel(String title, int taskType) {
		Label label = new Label(title);
		containerOfTask.getChildren().add(label);
		isTaskType[taskType] = true;
	}

	// ================================================================
	// Getter methods
	// ================================================================
	public int getNumOfTaskDue() {
		return numOfTaskDue;
	}

	/**
	 * The methods below are no longer in use, as the TaskListController is
	 * usually refreshed after each user command
	 */

	// ================================================================
	// Depreciated methods
	// ================================================================

	/*
	 * private void addTask(Task t){ addTask(t, listOfTask.size()); }
	 * 
	 * public void removeTask(Task t){ TaskController tc =
	 * taskLookupTable.get(t.getTaskId()); listOfTask.remove(tc);
	 * taskLookupTable.remove(t.getTaskId()); }
	 * 
	 * public void editTask(Task t){ TaskController tc =
	 * taskLookupTable.get(t.getTaskId()); tc.setName(t.getName()); }
	 */
}
