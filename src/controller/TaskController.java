package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TitledPane;
import javafx.scene.text.Text;

public class TaskController {
	
	@FXML
	private TitledPane tp;
	@FXML 
	private Text details;
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
		tp.setText(name);
	}
	
	public void setDetails(String d){
		details.setText(d);
	}
	
	public void setTaskId(int id){
		taskId = id;
	}
	
	public TitledPane getContainer(){
		return tp;
	}
	
}
