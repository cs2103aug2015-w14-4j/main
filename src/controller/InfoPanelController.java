//@@author A0124791A
package controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import utilities.DayProcessor;
import utilities.ErrorProcessor;

/**
 * InfoPanelController controls the information panel 
 * <p>
 * It displays the user's name and today's date
 * It provides a preview of the task being added, deleted or edited 
 *
 * @author Hongchao
 * 
 */
public class InfoPanelController extends VBox{
	
    // ================================================================
    // FXML
    // ================================================================
	private static final String FXML_PATH = "/view/InfoPanelView.fxml";
		
	@FXML 
	private Label userName;
	@FXML 
	private Label todayDay;
	@FXML 
	private Label todayDate;
	@FXML 
	private Label numTasksDue;
	@FXML 
	private VBox taskPreview;
	
    // ================================================================
    // Format of panel messages
    // ================================================================
	private static final String WELCOME_MESSAGE = "Hi %s";
	private static final String TASK_DUE_MESSAGE = "%d Task(s) due";
	
    // ================================================================
    // Format of task preview
    // ================================================================
	private static final String TASK_NAME_DISPLAY = "NAME: %s";
	private static final String TASK_DETAIL_DISPLAY = "INFO: %s";
	private static final String TASK_START_DISPLAY = "START: %s";
	private static final String TASK_END_DISPLAY = "END: %s";
		
	public InfoPanelController(String user, int numTasks){
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(FXML_PATH));
        loader.setRoot(this);
        loader.setController(this);
        try {
        	loader.load();
		} catch (IOException e) {
			ErrorProcessor.alert(e.toString());
		}
        setUserName(user);
        setTaskDue(numTasks);
	}
		
	@FXML
    public void initialize() {
    	refreshDate();
    }
	
    /**
	 * Updates the task preview
	 * 
	 * @param title
	 *            title of the preview (i.e. add, delete, edit)
	 * @param taskName
	 *            name of the task to be previewed
	 * @param taskDetails
	 *            details of the task to be previewed
	 * @param taskStart
	 *            start time of the task to be previewed
	 * @param taskEnd
	 *            end time of the task to be previewed
	 */
	public void displayTaskPreview(String title, String taskName, String taskDetails, String taskStart, String taskEnd){
		clearTaskInfo();
		if(title != null){
			createTaskInfoEntry(title, "%s");
		}
		if(taskName != null){
			createTaskInfoEntry(taskName, TASK_NAME_DISPLAY);
		}
		if(taskDetails != null){
			createTaskInfoEntry(taskDetails, TASK_DETAIL_DISPLAY);
		}
		if(taskStart != null){
			createTaskInfoEntry(taskStart, TASK_START_DISPLAY);
		}
		if(taskEnd != null){
			createTaskInfoEntry(taskEnd, TASK_END_DISPLAY);
		}
	}
	
    /**
	 * Adds a preview entry to be displayed
	 * 
	 * @param entry
	 *            text to be displayed
	 * @param stringFormat
	 *            format of the text
	 */
	private void createTaskInfoEntry(String entry, String stringFormat){
		Label label = new Label(String.format(stringFormat, entry));
		label.setMaxWidth(400);
		label.setId("taskDetails");
		taskPreview.getChildren().add(label);
	}
	
	private void clearTaskInfo(){
		taskPreview.getChildren().clear();
	}
	
	private void refreshDate(){
		todayDay.setText(DayProcessor.todayDay());
    	todayDate.setText(DayProcessor.todayDate());
	}	
	
    // ================================================================
    // Setter methods
    // ================================================================
	public void setUserName(String userName){
		this.userName.setText(String.format(WELCOME_MESSAGE, userName));
		refreshDate();
	}
	
	public void setTaskDue(int numTasks){
		numTasksDue.setText(String.format(TASK_DUE_MESSAGE, numTasks));
		refreshDate();
	}
}
