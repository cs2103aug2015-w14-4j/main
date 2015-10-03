package controller;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import speedo.Daykeeper;
import speedo.MainApp;
import speedo.Storage;
import speedo.Task;

public class RootController {
	@FXML
	private Label userName;
	@FXML
	private Label todayDay;
	@FXML
	private Label todayDate;
	@FXML
	private Accordion listOfTasks;
	@FXML
	private TextField commandBox;
	
	// Reference to the main application.
    private MainApp mainApp;
	
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	
    	// Initialize the tasks and fetch a list of tasks
    	Storage storage = new Storage();
    	showTasksList((ArrayList<Task>) storage.taskList);
            	
    	userName.setText("Hi Joe");
    	todayDay.setText("Today's "+ Daykeeper.todayDay());
    	todayDate.setText(Daykeeper.todayDate());
    }
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
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
    
    private void addTask(String title){
    	listOfTasks.getPanes().add(createTask(new Task(title, "Some information")));
    }
    
    private void showTasksList(ArrayList<Task> tasksList) {
		for (int i = 0; i < tasksList.size(); i++) {
			listOfTasks.getPanes().add(createTask(tasksList.get(i)));
		}
    }
    
    @FXML
    private void handleUserCommand() {
        // Command was entered, do something...
        System.out.println(commandBox.getText());
        addTask(commandBox.getText());
        commandBox.clear();
        
    }
    
}
