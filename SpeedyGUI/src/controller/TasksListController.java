package controller;

import java.io.IOException;
import java.util.ArrayList;

import application.MainApp;
import application.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TitledPane;

public class TasksListController {
	
	// Reference to the main application.
    private MainApp mainApp;
    
	/**
     * The data as an observable list of Task.
     */
    private ObservableList<Task> tasksList = FXCollections.observableArrayList();
	
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the Tasks List
        // Add some sample data
    	tasksList.add(new Task("Task 1", "Details about task 1"));
    	tasksList.add(new Task("Task 2", "Details about task 2"));
    	tasksList.add(new Task("Task 3", "Details about task 3"));
    }
    
    private TitledPane createTask(Task t){

    	FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/TaskView.fxml"));
        TitledPane tp = null;
        try {
			tp = (TitledPane) loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        TaskController tc = loader.getController();
        tc.setName(t.getNameProperty().get());
        tc.setDetails(t.getDetailsProperty().get());
        
    	return tp;
    }
    
    /**
     * Returns the data as an observable list of Task. 
     * @return
     */
    public ArrayList<TitledPane> getTasksList() {    	
    	ArrayList<TitledPane> arrList = new ArrayList<TitledPane>();
    	for(int i = 0; i < tasksList.size(); i++){
    		arrList.add(createTask(tasksList.get(i)));
    	}
        return arrList;
    }
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
	
}
