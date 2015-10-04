package controller;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    
    private ObservableList<Task> tasks;
	
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	
    	// Initialize the tasks and fetch a list of tasks
    	Storage storage = new Storage();
    	tasks = FXCollections.observableList(storage.taskList);
    	
    	initTasksList();
        
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
    
    private TitledPane createTitledPane(Task t){
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
        tc.setName(t.getName());
        tc.setDetails(t.getDetails());
        
    	return tp;
    }
    
    private void addTask(Task t){
		// listening for changes to name
		t.getNameProperty().addListener((v, oldValue, newValue) -> {
			System.out.println("Change in name!");
			// Update the display
			//listOfTasks.getPanes().get(0).setText(newValue);
		});
    	listOfTasks.getPanes().add(createTitledPane(t));
    }
    
    
    private void initTasksList() {
    	for (int i = 0; i < tasks.size(); i++) {
    		addTask(tasks.get(i));
		}
    }
            
    @FXML
    private void handleUserCommand() {
        // Command was entered, do something...
        System.out.println(commandBox.getText());
        //addTask(commandBox.getText());
        tasks.get(0).setName(commandBox.getText());
        System.out.println(tasks.get(0).getNameProperty());
        commandBox.clear();
    }
    
}
