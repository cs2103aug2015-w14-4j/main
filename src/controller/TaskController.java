package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TitledPane;
import javafx.scene.text.Text;

public class TaskController {
	
	@FXML
	private TitledPane tp;
	@FXML 
	private Text details;
	@FXML 
	private Text date;
	@FXML 
	private Text time;
	
	private int taskId;
	
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the Tasks List
    }
	
	public void setName(String name){
		this.tp.setText(name);
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
	
	public TitledPane getContainer(){
		return tp;
	}
	
}
