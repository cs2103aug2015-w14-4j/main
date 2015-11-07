package controller;

import java.util.Optional;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import logic.GuiCommand;
import logic.Logic;
import parser.DayProcessor;

public class MainApp extends Application {
    
    private Stage primaryStage;
    private BorderPane rootLayout;
    private Logic logic;
    private Popup helpPopup;
        
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Spee-Do");
        initRootLayout();
        initHelpBox();
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
    	rootLayout = new BorderPane();
    	logic = new Logic();
		
		// sets up the list of tasks to display
		TaskListController tlc = new TaskListController();
    	tlc.loadTaskList(logic.getTaskList());
    	rootLayout.setRight(tlc);
    	
    	// sets up the command box
    	CommandBoxController cbc = new CommandBoxController();
    	cbc.setMainApp(this);
    	rootLayout.setBottom(cbc);
    	if(logic.getUser() == null){ 		
    		TextInputDialog dialog = new TextInputDialog("Your Name");
    		dialog.setTitle("Welcome!");
    		dialog.setHeaderText("It seems be your first time here.");
    		dialog.setContentText("Please enter your name:");

    		Optional<String> result = dialog.showAndWait();
    		if (result.isPresent()){
    		    logic.setSettings(result.get(), null);
    		}
    	}
    	// sets up the info panel
    	InfoPanelController ipc = new InfoPanelController(logic.getUser(), 
    			DayProcessor.todayDay(), 
    			DayProcessor.todayDate(), 
    			tlc.getNumOfTaskDue());
    	rootLayout.setLeft(ipc);
    	this.refresh();
    }
    
    public void initHelpBox(){
		helpPopup = new Popup();
		helpPopup.getContent().add(new HelpBoxController());
    }
    
    public void refresh(){
    	TaskListController tlc = (TaskListController) rootLayout.getRight(); 
    	tlc.loadTaskList(logic.getTaskList());
    	InfoPanelController ipc = (InfoPanelController) rootLayout.getLeft();
    	ipc.setUserName(logic.getUser());
    	ipc.setTaskDue(tlc.getNumOfTaskDue());
    }
    
    public void handleUserCommand(String userInput) {
        // Command was entered, do something...
        GuiCommand command = logic.executeCMD(userInput);
        CommandBoxController cbc = (CommandBoxController) rootLayout.getBottom();
        TaskListController tlc = (TaskListController) rootLayout.getRight();
        
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
	        	tlc.loadTaskList(command.getListOfTasks());
	        	break;
	        }
	        case EXPAND: {
	        	tlc.expand(command.getTaskId());
	        }
	        case HELP: {
	        	helpPopup.show(primaryStage);
	        }
	        default:
	        	refresh();
	        	cbc.setErrorFeedback("Invalid command");
			break;
        }
    }
    
    public void parseUserCommand(String userInput) {
    	CommandBoxController cbc = (CommandBoxController) rootLayout.getBottom();
    	InfoPanelController ipc = (InfoPanelController) rootLayout.getLeft();

    	GuiCommand guiCommand = logic.predictCMD(userInput);
    	if(!userInput.equals("")){
    		cbc.setFeedback("");
    	}
    	cbc.setPredictionFeedback(guiCommand.getMsg());
    	String title = guiCommand.getTitle();
    	String taskName = guiCommand.getTaskName();
    	String taskDetails = guiCommand.getTaskDetails();
    	String taskStart = guiCommand.getTaskStart();
    	String taskEnd = guiCommand.getTaskEnd();
    	
    	ipc.displayTaskInfo(title, taskName, taskDetails, taskStart, taskEnd);
    }
        
}
