package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Background;
import javafx.scene.text.Text;
import processor.ErrorProcessor;
import storage.Task;

public class TaskController extends TitledPane{
	
	private static final String FXML_PATH = "/view/TaskView.fxml";
	
	@FXML 
	private Text details;
	@FXML 
	private Text date;
	@FXML 
	private Text time;
	
	private int taskId;
	
	public TaskController(Task t){
    	FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(FXML_PATH));
        loader.setRoot(this);
        loader.setController(this);
        try {
        	loader.load();
        	this.getStylesheets().add("/controller/taskcontroller.css");
	        setName(t.getName());
	        setDate(t.getStartDateString());
	        setTime(t.getStartTimeString());
	        setDetails(t.getDetails());
	        setTaskId(t.getTaskId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			ErrorProcessor.alert(e.toString());
		}
        this.setExpanded(false);
        if(t.isCompleted()){
        	this.setId("taskCompleted");
        	//this.setStyle("-fx-strikethrough: true;");
        	//this.setStyle("{ -fx-underline: true; -fx-text-fill: white; fx-font-size: 30;}");
        }
	}
	
	public void setName(String name){
		this.setText(name);
	}
	
	public void setDetails(String d){
		this.details.setText(d);
	}
	
	public void setDate(String d){
		this.date.setText(d);
	}
	
	public void setTime(String time){
		this.time.setText(time);
	}
	
	public void setTaskId(int id){
		this.taskId = id;
	}
	
	public int getTaskId(){
		return taskId;
	}
	
}
