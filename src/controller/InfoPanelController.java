package controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class InfoPanelController extends VBox{
	
	private static final String FXML_PATH = "/view/InfoPanelView.fxml";
	
	private static final String WELCOME_MESSAGE = "Hi %s";
	private static final String TASK_DUE_MESSAGE = "%d Task(s) due";
	
	private static final String TASK_NAME_DISPLAY = "NAME: %s";
	private static final String TASK_DETAIL_DISPLAY = "INFO: %s";
	private static final String TASK_START_DISPLAY = "START: %s";
	private static final String TASK_END_DISPLAY = "END: %s";
	
	@FXML 
	private Label userName;
	@FXML 
	private Label todayDay;
	@FXML 
	private Label todayDate;
	@FXML 
	private Label numTasksDue;
	@FXML 
	private VBox taskInfo;
		
	public InfoPanelController(String user, String day, String date, int numTasks){
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(FXML_PATH));
        loader.setRoot(this);
        loader.setController(this);
        try {
        	loader.load();
		} catch (IOException e) {
			System.out.println(e);
		}
    	setUserName(user);
    	todayDay.setText(day);
    	todayDate.setText(date);
    	setTaskDue(numTasks);
	}
	
	public void setUserName(String userName){
		this.userName.setText(String.format(WELCOME_MESSAGE, userName));
	}
	
	public void setTaskDue(int numTasks){
		numTasksDue.setText(String.format(TASK_DUE_MESSAGE, numTasks));
	}
	
	
	/*
	 * All code beyond this point deals with the prediction
	 * */
		
	public void displayTaskInfo(String title, String taskName, String taskDetails, String taskStart, String taskEnd){
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
	
	private void createTaskInfoEntry(String entry, String stringFormat){
		Text txt = new Text(String.format(stringFormat, entry));
		txt.setWrappingWidth(240);
		taskInfo.getChildren().add(txt);
	}
	
	public void clearTaskInfo(){
		taskInfo.getChildren().clear();
	}	
}
