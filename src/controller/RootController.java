package controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
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
	@FXML
	private TextFlow feedbackToUser;
	
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
    	userName.setText("Hi Jim");
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
        tc.setDate(t.getDate());
        tc.setTime(t.getTime());
        tc.setDetails(t.getDetails());
        tc.setTaskId(t.getTaskId());
        taskLookupTable.put(t.getTaskId(), tc);
    	return tp;
    }
    
    private void addTask(Task t){
    	System.out.println(t);
    	containerOfTasks.getPanes().add(createTitledPane(t));
    }
    
    private void removeTask(int taskID){
    	TaskController tc = taskLookupTable.get(taskID);
    	containerOfTasks.getPanes().remove(tc.getContainer());
    	taskLookupTable.remove(taskID);
    }
    
    //TODO Can only edit the Task title
    private void editTask(Task t, int index){
    	System.out.println(t);
    	containerOfTasks.getPanes().get(index).setText(t.getName());
    	//TaskController tc = taskLookupTable.get(t.getTaskId());
    	//tc.setName(t.getName());
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
        
        //GuiCommand gc = new GuiCommand(COMMANDS.DELETE, "Delete", logic.getTask(0));
        //logic.getTask(0).setName(userInput);
        //GuiCommand command = new GuiCommand(COMMANDS.EDIT, "Edited", logic.getTask(0));
       // GuiCommand gc = new GuiCommand(COMMANDS.ADD, "Added", new Task(userInput, new Date())); 
        GuiCommand command = logic.executeCMD(userInput);
//        try{
        	switch(command.getCmd()){
        		case ADD: {
        			addTask(command.getTask());
        			Text feedback = new Text(command.getMsg());
        		    feedback.setFill(Color.GREEN);
        		    //feedbackToUser.getChildren().remove();
        			feedbackToUser.getChildren().add(feedback);
        			break;
        		}
        		case DELETE: {
        			removeTask(command.getTaskId());
        			Text feedback = new Text(command.getMsg());
        		    feedback.setFill(Color.GREEN);
        		    //feedbackToUser.getChildren().remove();
        			feedbackToUser.getChildren().add(feedback);
        			break;
        		}
        		case EDIT: {

        			System.out.println(command.getTask());
        			editTask(command.getTask(), command.getTaskId());
        			// TODO set the feedback
        			break;
        		}
    		}
//        } catch (Exception e){
//        	System.out.println(e.getStackTrace());
//        }
        commandBox.clear();
    }
        
}
