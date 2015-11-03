package speedo;

import java.io.IOException;
import java.util.Optional;

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
import processor.DayProcessor;

public class MainApp extends Application {
    
	private static final String FXML_PATH = "/view/RootView.fxml";
	private static final String WELCOME = "Hi ";

    private Stage primaryStage;
    private BorderPane rootLayout;
    private Logic logic;
        
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
    	InfoPanelController ipc = new InfoPanelController(WELCOME+logic.getUser(), 
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
        System.out.println(userInput);
        GuiCommand command = logic.executeCMD(userInput);
        CommandBoxController cbc = (CommandBoxController) rootLayout.getBottom(); 
        System.out.println(command.getCmd());
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
    
}
