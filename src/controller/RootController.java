package controller;

import java.io.IOException;
import java.util.Hashtable;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import processor.COMMANDS;
import processor.DayProcessor;
import processor.ErrorProcessor;
import speedo.MainApp;
import speedo.Task;
import speedo.Logic;

public class RootController {
	@FXML
	private Label userName;
	@FXML
	private Label todayDay;
	@FXML
	private Label todayDate;
	@FXML
	private Accordion containerOfTasks;
	@FXML
	private TextField commandBox;
	
	// Reference to the main application.
    private MainApp mainApp;
    // Maps the Task to its corresponding TitledPane
    private Hashtable<Task, TitledPane> taskLookupTable;
    private Logic logic;
	
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	// Initialize the tasks and fetch a list of tasks
    	logic = new Logic();
    	taskLookupTable = new Hashtable<Task, TitledPane>();
    	initTasksList();
    	userName.setText("Hi Joe");
    	todayDay.setText(DayProcessor.todayDay());
    	todayDate.setText(DayProcessor.todayDate());
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
			ErrorProcessor.alert(e.toString());
		}
        TaskController tc = loader.getController();
        tc.setName(t.getName());
        tc.setDetails(t.getDetails());
        taskLookupTable.put(t, tp);
    	return tp;
    }
    
    private void addTask(Task t){
    	containerOfTasks.getPanes().add(createTitledPane(t));
    }
    
    private void initTasksList() {
    	for (int i = 0; i < logic.getNumOfTask(); i++) {
    		addTask(logic.getTask(i));
		}
    }
            
    @FXML
    private void handleUserCommand() {
        // Command was entered, do something...
    	String userInput = commandBox.getText();
        System.out.println(userInput);
        COMMANDS c = logic.executeCMD(userInput);
        System.out.println(c);
        try{
        //addTask(t);
        } catch (Exception e){
        	ErrorProcessor.alert(e.toString());
        }
        commandBox.clear();
    }
    
}
