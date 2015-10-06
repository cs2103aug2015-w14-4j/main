package controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Hashtable;
import java.util.List;

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
import speedo.GuiCommand;
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
    // Maps the Task to its corresponding TaskController
    private Hashtable<Integer, TaskController> taskLookupTable;
    private Logic logic;
	
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	// Initialize the tasks and fetch a list of tasks
    	logic = new Logic();
    	taskLookupTable = new Hashtable<Integer, TaskController>();
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
    
    //TODO Could move this method to TaskController
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
        tc.setTaskId(t.getTaskId());
        taskLookupTable.put(t.getTaskId(), tc);
    	return tp;
    }
    
    private void addTask(Task t){
    	containerOfTasks.getPanes().add(createTitledPane(t));
    }
    
    private void removeTask(int taskID){
    	TaskController tc = taskLookupTable.get(taskID);
    	//System.out.println("removeTask taskID: " + taskID);
    	//System.out.println("removeTask method removed: " + tc.getContainer().getText());
    	containerOfTasks.getPanes().remove(tc.getContainer());
    	taskLookupTable.remove(taskID);
    }
    
    private void initTasksList() {
    	for (int i = 0; i < logic.getNumOfTask(); i++) {
    		addTask(logic.getTask(i));
		}
    }
    
    private void loadTasksList(List<Task> listOfTasks) {
    	for (int i = 0; i < listOfTasks.size(); i++) {
    		addTask(listOfTasks.get(i));
		}
    }
            
    @FXML
    private void handleUserCommand() throws ParseException {
        // Command was entered, do something...
    	String userInput = commandBox.getText();
        System.out.println(userInput);
        //GuiCommand gc = logic.executeCMD(userInput);
        GuiCommand gc = new GuiCommand(COMMANDS.DELETE, "Delete", logic.getTask(0));
        System.out.println(logic.getTask(0).getTaskId());
        System.out.println(logic.getTask(3).getTaskId());
        //System.out.println("Trying to delete: " + logic.getTask(0).getName());
        COMMANDS command = gc.getCmd();
        try{
        	switch(command){
        		case ADD: {
        			addTask(gc.getTask());
        			// TODO set the feedback
        			break;
        		}
        		case DELETE: {
        			removeTask(gc.getTaskId());
        			// TODO set the feedback
        			break;
        		}
    		}
        } catch (Exception e){
        	ErrorProcessor.alert(e.toString());
        }
        commandBox.clear();
    }
        
}
