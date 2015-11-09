//@@author A0124791A
package controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import storage.Task;
import utilities.ErrorProcessor;

/**
 * TaskController controls how each individual task is displayed
 * <p>
 * A user can click on any task to open up a drop-down with the task details
 * There are no getter methods in this class, as there is no need to access any of the fields in the class
 * TaskController is usually reloaded after each user command when the task list gets refreshed in MainApp,
 * hence there is no instance where we have to access each individual task 
 * 
 *  @author Hongchao
 *
 */
public class TaskController extends TitledPane{
	
	// ================================================================
    // FXML
    // ================================================================
	private static final String FXML_PATH = "/view/TaskView.fxml";
	
	@FXML 
	private VBox expandedBox;
	
	@FXML 
	private HBox details;
	@FXML 
	private HBox endDate;
	@FXML 
	private HBox startDate;
	
	@FXML 
	private Label detailsLabel;
	@FXML 
	private Label startDateLabel;
	@FXML 
	private Label endDateLabel;
	
	@FXML 
	private Text detailsText;
	@FXML 
	private Text startDateText;
	@FXML 
	private Text endDateText;
	
	// ================================================================
    // Format of task name displayed
    // ================================================================
	private static final String TASK_NAME_DISPLAY = "%d. %s";
	
	// ================================================================
    // Fields
    // ================================================================
	private int taskIndex;
	
	public TaskController(Task task, int index){
		try {
        	FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(FXML_PATH));
            loader.setRoot(this);
            loader.setController(this);
        	loader.load();
		} catch (IOException e) {
			ErrorProcessor.alert(e.toString());
		}
        initTask(task, index);
	}
	
	@FXML
    public void initialize() {
        this.setExpanded(false);
    }
	
	/**
	 * Initializes a TaskController instance according to the Task object provided
	 * 
	 * @param task
	 *            task which the TaskController is modeled after
	 * @param index
	 *            index of the task
	 */
	private void initTask(Task task, int index){
    	setTaskIndex(index);
		setName(task.getName());
        setDetails(task.getDetails());
        setStartDate(task.getStartDateString());
        setEndDate(task.getEndDateString()); 
        applyStyling(task);
	}
	
	/**
	 * Checks the type of task and applies the relevant styling to it's corresponding TaskController
	 * 
	 * @param task
	 *            task that the style is applied to
	 */
	private void applyStyling(Task task){
        if(task.isCompleted()){
        	this.setId("taskCompleted");
        	this.setOpacity(0.2);
        } else if(task.due() == Task.OVERDUE){
        	this.setId("taskOverdue");
        } else if(task.due() == Task.TODAY){
        	this.setId("taskDueToday");
        } else if(task.due() == Task.TOMORROW){
        	this.setId("taskDueTomorrow");
        } else if(task.due() == Task.NODATE){
        	this.setId("taskToDo");
        	if(task.getDetails() == null){
        		this.setCollapsible(false);
        	}
        	expandedBox.getChildren().remove(endDate);
        }
	}
	
	// ================================================================
    // Setter methods
    // ================================================================
	
	private void setName(String name){
		this.setText(String.format(TASK_NAME_DISPLAY, taskIndex, name));
	}
	
	private void setDetails(String text){
		if(text == null){
			expandedBox.getChildren().remove(details);
		} else {
			detailsText.setText(text);
		}
	}
	
	/**
	 * When there is no start date, the start date label is removed and the end date label is 
	 * renamed to "Date"
	 */
	private void setStartDate(String date){
		if(date == null){
			expandedBox.getChildren().remove(startDate);
			endDateLabel.setText("Date");
		} else {
			startDateText.setText(date);
		}
	}
	
	private void setEndDate(String date){
		endDateText.setText(date);
	}
			
	private void setTaskIndex(int index){
		taskIndex = index;
	}
}
