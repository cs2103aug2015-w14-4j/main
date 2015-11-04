package controller;

import java.io.IOException;
import java.util.Hashtable;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class InfoPanelController extends VBox{
	
	private static final String FXML_PATH = "/view/InfoPanelView.fxml";
	
	private static final String TASK_DUE_MESSAGE = "%d Task(s) due";
	private static final String TASK_NAME_DISPLAY = "NAME: %s\n";
	private static final String TASK_INFO_DISPLAY = "INFO: %s\n";
	private static final String TASK_START_DISPLAY = "START: %s";
	private static final String TASK_END_DISPLAY = "END: %s";
	
	private static final int TASK_NAME_KEY = 0;
	private static final int TASK_INFO_KEY = 1;
	
	@FXML 
	private Label userName;
	@FXML 
	private Label todayDay;
	@FXML 
	private Label todayDate;
	@FXML 
	private Label numTasksDue;
	@FXML 
	private TextFlow taskDetails;
	
	private Hashtable<Integer, Text> textTable;
		
	public InfoPanelController(String user, String day, String date, int numTasks){
		textTable = new Hashtable<Integer, Text>();
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(FXML_PATH));
        loader.setRoot(this);
        loader.setController(this);
        try {
        	loader.load();
        	userName.setText(user);
        	todayDay.setText(day);
        	todayDate.setText(date);
        	setTaskDue(numTasks);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public void setUserName(String userName){
		this.userName.setText(userName);
	}
	
	public void setTaskDue(int numTasks){
		numTasksDue.setText(String.format(TASK_DUE_MESSAGE, numTasks));
	}
	
	public void clearDetails(){
		taskDetails.getChildren().clear();
		textTable = new Hashtable<Integer, Text>();
	}
	
	public void setTaskName(String name){
		if(textTable.containsKey(TASK_NAME_KEY)){
			textTable.get(TASK_NAME_KEY).setText(String.format(TASK_NAME_DISPLAY, name));
		} else {
			Text txt = new Text(String.format(TASK_NAME_DISPLAY, name));
			taskDetails.getChildren().add(txt);
			textTable.put(TASK_NAME_KEY, txt);
		}
	}
		
	public void setTaskInfo(String info){
		if(textTable.containsKey(TASK_INFO_KEY)){
			textTable.get(TASK_INFO_KEY).setText(String.format(TASK_INFO_DISPLAY, info));
		} else {
			Text txt = new Text(String.format(TASK_INFO_DISPLAY, info));
			taskDetails.getChildren().add(txt);
			textTable.put(TASK_INFO_KEY, txt);
		}
	}
	
	/*
	public void setTaskStart(String time){
		Text txt = new Text(String.format(TASK_START_DISPLAY, time));
		taskDetails.getChildren().add(txt);
	}
	
	public void setTaskEnd(int time){
		Text txt = new Text(String.format(TASK_END_DISPLAY, time));
		taskDetails.getChildren().add(txt);
	}
	*/
}
