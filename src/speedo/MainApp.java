package speedo;

import java.io.IOException;
import java.util.Optional;
import java.util.Date;


import controller.CommandBoxController;
import controller.InfoPanelController;
import controller.TaskListController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import parser.DayProcessor;
import parser.Parser;
import parser.Predictive;

public class MainApp extends Application {
    
	private static final String FXML_PATH = "/view/RootView.fxml";
	private static final String WELCOME = "Hi ";

    private Stage primaryStage;
    private BorderPane rootLayout;
    private Logic logic;
    //private Parser parser;
    private Predictive predictor;
        
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("SpeeDo");
        initRootLayout();
        Scene scene = new Scene(rootLayout,850,500);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
    	FXMLLoader loader = new FXMLLoader();         
        loader.setLocation(getClass().getResource(FXML_PATH));
		try {
			rootLayout = (BorderPane) loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	logic = new Logic();
    	predictor = new Predictive();
		
		// sets up the list of tasks to display
		TaskListController tlc = new TaskListController();
    	tlc.loadTaskList(logic.getTaskList());
    	rootLayout.setRight(tlc);
    	
    	// sets up the command box
    	CommandBoxController cbc = new CommandBoxController();
    	cbc.setMainApp(this);
    	rootLayout.setBottom(cbc);
    	if(logic.getUser() == null){
    		//TODO prompt user to set name
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error Dialog");
    		alert.setHeaderText("NEED TO SET USERNAME");
    		alert.setContentText("NEED TO SET USERNAME");

    		alert.showAndWait();
    		
    		TextInputDialog dialog = new TextInputDialog("Your Name");
    		dialog.setTitle("Welcome!");
    		dialog.setHeaderText("It seems be your first time here.");
    		dialog.setContentText("Please enter your name:");

    		// Traditional way to get the response value.
    		Optional<String> result = dialog.showAndWait();
    		if (result.isPresent()){
    		    logic.setSettings(result.get(), null);
    		}
    	}
    	// sets up the info panel
    	InfoPanelController ipc = new InfoPanelController(WELCOME + logic.getUser(), 
    			DayProcessor.todayDay(), 
    			DayProcessor.todayDate(), 
    			tlc.getNumOfTaskDue());
    	rootLayout.setLeft(ipc);
    	this.refresh();
    }
    
    public void refresh(){
    	TaskListController tlc = (TaskListController) rootLayout.getRight(); 
    	tlc.loadTaskList(logic.getTaskList());
    	InfoPanelController ipc = (InfoPanelController) rootLayout.getLeft();
    	ipc.setTaskDue(tlc.getNumOfTaskDue());
    }
    
    public void handleUserCommand(String userInput) {
        // Command was entered, do something...
        GuiCommand command = logic.executeCMD(userInput);
        CommandBoxController cbc = (CommandBoxController) rootLayout.getBottom();
        switch(command.getCmd()){
	        case ADD: {
	        	cbc.setFeedback(command.getMsg());
	        	refresh();
	        	break;
	        }
	        case DELETE: {
	        	cbc.setFeedback(command.getMsg());
	        	refresh();
	        	break;
	        }
	        case EDIT: {
	        	cbc.setFeedback(command.getMsg());
	        	refresh();
	        	break;
	        }
	        case SEARCH: { // barny: Search will reload with a list of searched items
	        	cbc.setFeedback(command.getMsg());
	        	TaskListController tlc = (TaskListController) rootLayout.getRight(); 
	        	tlc.loadTaskList(command.getListOfTasks());
	        	break;
	        }
	        case HOME: { // barny: Home will revert to original list of tasks
	        	cbc.setFeedback(command.getMsg());
	        	refresh();
	        	break;	        	
	        }
	        case UNDO: { // barny: Undo will revert one change
	        	cbc.setFeedback(command.getMsg());
	        	refresh();
	        	break;	        	
	        }
	        case ACK: {
	        	cbc.setFeedback(command.getMsg());
	        	refresh();
	        	break;
	        }
	        case COMPLETED: {
	        	cbc.setFeedback(command.getMsg());
	        	TaskListController tlc = (TaskListController) rootLayout.getRight(); 
	        	tlc.loadTaskList(command.getListOfTasks());
	        	break;
	        }
	        default:
	        	cbc.setErrorFeedback("Invalid command");
			break;
        }
    }
    
    //@@author A0125369Y
    public void parseUserCommand(String userInput) {
    	CommandBoxController cbc = (CommandBoxController) rootLayout.getBottom();
    	InfoPanelController ipc = (InfoPanelController) rootLayout.getLeft();

    	cbc.setPredictionFeedback(predictor.processInput(userInput));
    	String taskName = predictor.getTaskName();
    	String taskDetails = predictor.getTaskDetails();
    	String taskStart = predictor.getTaskStart();
    	String taskEnd = predictor.getTaskEnd();
    	if(!(taskName == null)){
    		ipc.setTaskName(taskName);
    	}
    	if(!(taskDetails == null)){
    		ipc.setTaskInfo(taskDetails);
    	}
    	
    	if(!(taskStart == null)){
    		ipc.setTaskInfo(taskStart);
    	}
    	if(!(taskEnd == null)){
    		ipc.setTaskName(taskEnd);
    	}
    	if(taskName == null && taskDetails == null 
    			&& taskStart == null && taskEnd == null){
            ipc.clearDetails();
    	}
//    	System.out.println("From parseUserCommand: " + userInput);
//    	Parser parser = new Parser();
//    	parser.parse(userInput);
//    	switch(parser.getCommand()){
//	        case ADD: {
//	        	cbc.setPredictionFeedback("add <Task name> -d <date> -i <info>");
//	        	String taskName = parser.getTaskName();
//	        	String taskDetails = parser.getDetails();
//	        	//String taskStart = parser.getStartDate().toString();
//	        	//String taskEnd = parser.getEndDate().toString();
//	        	if(!(taskName == null)){
//	        		ipc.setTaskName(taskName);
//	        	}
//	        	if(!(taskDetails == null)){
//	        		ipc.setTaskInfo(taskDetails);
//	        	}
//	        	/*
//	        	if(!taskStart.equals("")){
//	        		ipc.setTaskInfo(taskStart);
//	        	}
//	        	if(!taskEnd.equals("")){
//	        		ipc.setTaskName(taskEnd);
//	        	}
//	        	*/
//	        	break;
//	        }
//	        case DELETE: {
//	        	cbc.setPredictionFeedback("delete <Task index>");
//	        	break;
//	        }
//	        case EDIT: {
//	        	cbc.setPredictionFeedback("edit <Task index>");
//	        	break;
//	        }
//	        default:
//	        	ipc.clearDetails();
//	        	if(userInput.equals("")){
//	        		// do nothing
//	        	} else {
//	        		cbc.setFeedback("");
//		        	break;
//	        	}
//	    }
    }
    
    //TODO Remember to delete this later 
    public void displayCommandBoxText(String input){
    	CommandBoxController cbc = (CommandBoxController) rootLayout.getBottom();
    	cbc.setPredictionFeedback(input);
    	System.out.println(input);
    }
    
}
