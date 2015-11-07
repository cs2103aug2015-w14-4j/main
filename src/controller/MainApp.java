package controller;

import java.util.List;
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
import storage.Task;

public class MainApp extends Application {
    
    private Stage primaryStage;
    private BorderPane rootLayout;
    private Logic logic;
    private Popup helpPopup;
    private TaskListController taskList;
    private CommandBoxController commandBox;
    private InfoPanelController infoPanel;
        
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Spee-Do");
        initRootLayout();
        initHelpBox();
        Scene scene = new Scene(rootLayout, 850, 500);
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
		taskList = new TaskListController();
    	taskList.loadTaskList(logic.getTaskList());
    	rootLayout.setRight(taskList);
    	
    	// sets up the command box
    	commandBox = new CommandBoxController();
    	commandBox.setMainApp(this);
    	rootLayout.setBottom(commandBox);
    	
		promptForUserName();

    	// sets up the info panel
    	infoPanel = new InfoPanelController(logic.getUser(), 
    			DayProcessor.todayDay(), 
    			DayProcessor.todayDate(), 
    			taskList.getNumOfTaskDue());
    	rootLayout.setLeft(infoPanel);
    }
    
    public void promptForUserName(){
    	while(logic.getUser() == null){ 		
    		TextInputDialog dialog = new TextInputDialog("Your Name");
    		dialog.setTitle("Welcome!");
    		dialog.setHeaderText("It seems be your first time here.");
    		dialog.setContentText("Please enter your name:");

    		Optional<String> result = dialog.showAndWait();
    		if (result.isPresent()){
    		    logic.setSettings(result.get(), null);
    		}
    	}
    }
    
    public void initHelpBox(){
		helpPopup = new Popup();
		helpPopup.getContent().add(new HelpBoxController());
    }
   
    public void refresh(List<Task> listOfTasks){
    	taskList.loadTaskList(listOfTasks);
    	infoPanel.setUserName(logic.getUser());
    	infoPanel.setTaskDue(taskList.getNumOfTaskDue());
    }
    
    public void handleUserCommand(String userInput) {
        // Command was entered, do something...
        GuiCommand command = logic.executeCMD(userInput);
        switch(command.getCmd()){
	        case EXPAND: {
	        	taskList.expand(command.getTaskId());
	        }
	        case HELP: {
	        	helpPopup.show(primaryStage);
	        }
	        case INVALID: {
	        	commandBox.setErrorFeedback(command.getMsg());
	        	break;
	        } 
	        default: {
	        	commandBox.setFeedback(command.getMsg());
	        	refresh(command.getListOfTasks());
	        	break;
	        }
        }
    }
    
    public void parseUserCommand(String userInput) {
    	GuiCommand guiCommand = logic.predictCMD(userInput);
    	if(!userInput.equals("")){
    		commandBox.clearFeedback();
    	}
    	commandBox.setPredictionFeedback(guiCommand.getMsg());
    	String title = guiCommand.getTitle();
    	String taskName = guiCommand.getTaskName();
    	String taskDetails = guiCommand.getTaskDetails();
    	String taskStart = guiCommand.getTaskStart();
    	String taskEnd = guiCommand.getTaskEnd();
    	infoPanel.displayTaskInfo(title, taskName, taskDetails, taskStart, taskEnd);
    }
        
}
