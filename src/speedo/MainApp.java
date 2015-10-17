package speedo;

import java.io.IOException;

import controller.CommandBoxController;
import controller.TaskListController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {
    
	private static final String FXML_PATH = "/view/RootView.fxml";

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
		System.out.println(rootLayout.getBottom().getScene().widthProperty());
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
    	
    	//userName.setText("Hi Jim");
    	//todayDay.setText(DayProcessor.todayDay());
    	//todayDate.setText(DayProcessor.todayDate());
    }
    
    public void handleUserCommand(String userInput) {
        // Command was entered, do something...
        System.out.println(userInput);
        GuiCommand command = logic.executeCMD(userInput);
        TaskListController tlc = (TaskListController) rootLayout.getRight(); 
        CommandBoxController cbc = (CommandBoxController) rootLayout.getBottom(); 
        
        switch(command.getCmd()){
	        case ADD: {
	        	tlc.addTask(command.getTask());
	        	cbc.setFeedback(command.getMsg());
	        	break;
	        }
	        case DELETE: {
	        	tlc.removeTask(command.getTask());
	        	cbc.setFeedback(command.getMsg());
	        	break;
	        }
	        case EDIT: {
	        	tlc.editTask(command.getTask());
	        	cbc.setFeedback(command.getMsg());
	        	break;
	        }
	        default:
	        	cbc.setFeedback("Invalid command");
			break;
        }
    }
    
}
