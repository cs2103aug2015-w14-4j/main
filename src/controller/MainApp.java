//@@author A0124791A
package controller;

import java.util.List;
import java.util.Optional;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.PopupControl;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import logic.GuiCommand;
import logic.Logic;
import storage.Task;

/**
 * MainApp is the starting point of the application 
 * <p>
 * It initializes the components that the user interacts with  
 *
 * @author Hongchao
 * 
 */
public class MainApp extends Application {
    
    // ================================================================
    // Window Properties
    // ================================================================
	private static final String CSS_PATH = "application.css";
	private static final String ICON_PATH = "img/speedo.png";
	private static final String WINDOW_TITLE = "Spee-Do";
	private static final int WINDOW_WIDTH = 950;
	private static final int WINDOW_HEIGHT = 600;
	
    // ================================================================
    // Dialog box for user's name
    // ================================================================
	private static final String TITLE_TEXT = "Welcome!";
    private static final String HEADER_TEXT = "It seems be your first time here.";
    private static final String CONTENT_TEXT = "Please enter your name:";
    
    // ================================================================
    // Fields
    // ================================================================
    private Logic logic;
	private Stage primaryStage;
	private BorderPane rootLayout;
    private PopupControl helpPopup;
    private TaskListController taskList;
    private CommandBoxController commandBox;
    private InfoPanelController infoPanel;
        
	@Override
	public void start(Stage primaryStage) {
        initRootLayout();
        initHelpBox();
        Scene scene = new Scene(rootLayout, WINDOW_WIDTH, WINDOW_HEIGHT);
		scene.getStylesheets().add(getClass().getResource(CSS_PATH).toExternalForm());
		
		this.primaryStage = primaryStage;
        primaryStage.setTitle(WINDOW_TITLE);
		primaryStage.getIcons().add(new Image(ICON_PATH));
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
    /**
     * Initializes the root layout
     * Logic is instantiated here
     * All the controllers will be instantiated here and set as children of rootLayout
     * User will be asked to enter their name here if it is their first time using the application
     */
    private void initRootLayout() {
    	logic = new Logic();
    	
    	rootLayout = new BorderPane();
    	rootLayout.setId("rootLayout");
		
		taskList = new TaskListController();
    	taskList.loadTaskList(logic.getTaskList());
    	rootLayout.setCenter(taskList);
    	
    	commandBox = new CommandBoxController();
    	commandBox.setMainApp(this);
    	rootLayout.setBottom(commandBox);
    	
		promptForUserName();

    	infoPanel = new InfoPanelController(logic.getUser(), taskList.getNumOfTaskDue());
    	rootLayout.setLeft(infoPanel);
    }
        
    // initializes the help box
    private void initHelpBox(){
		helpPopup = new PopupControl();
		helpPopup.setOpacity(0.9);
		helpPopup.setAutoHide(true);
		helpPopup.getScene().setRoot(new HelpBoxController());
    }
    
    /**
	 * Initializes the dialog box that prompts for the user's name
	 * @return the dialog box 
	 * 
	 */ 
    private TextInputDialog initPromptDialogBox(){
		TextInputDialog dialog = new TextInputDialog("Your Name");
		dialog.setTitle(TITLE_TEXT);
		dialog.setHeaderText(HEADER_TEXT);
		dialog.setContentText(CONTENT_TEXT);
		return dialog;
    }
    
    /**
	 * Prompts the user for their name if it is their first time using the application
	 * A name has to be given in order to continue
	 *  
	 */
    private void promptForUserName(){
    	while(logic.getUser() == null){ 		
    		TextInputDialog dialog = initPromptDialogBox();
    		Optional<String> name = dialog.showAndWait();
    		if (name.isPresent()){
    		    logic.setSettings(name.get(), null);
    		}
    	}
    }
    
    /**
	 * Refresh the InfoPanel and loads a list of Task 
	 * @param listOfTasks
	 *            list of tasks that is to be loaded
	 *            
	 */
    private void refresh(List<Task> listOfTasks){
    	taskList.loadTaskList(listOfTasks);
    	infoPanel.setUserName(logic.getUser());
    	infoPanel.setTaskDue(taskList.getNumOfTaskDue());
    }
	
    /**
	 * Takes in input that the user enters into command box and calls Logic's executeCMD
	 * The GUI will update itself according to the GuiCommand returned 
	 * By default, the GUI will refresh itself and set the feedback message
	 * 
	 * @param userInput
	 *            input that the user enters into command box
	 *            
	 */
    public void handleUserCommand(String userInput) {
        GuiCommand command = logic.executeCMD(userInput);
        switch(command.getCmd()){
	        case HELP: {
	        	displayHelp();
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
    
    /**
	 * Takes in input that the user enters into command box and calls Logic's predictCMD
	 * The GUI will dynamically update the InfoPanel and feedback according to what the user is typing
	 * 
	 * @param userInput
	 *            input that the user enters into command box
	 *            
	 */
    public void parseUserCommand(String userInput) {
    	GuiCommand guiCommand = logic.predictCMD(userInput);
    	if(!userInput.equals("")){
    		commandBox.clearFeedback();
    	}
    	commandBox.setPredictionFeedback(guiCommand.getMsg());

    	infoPanel.displayTaskPreview(guiCommand.getTitle(), 
    							  guiCommand.getTaskName(), 
    							  guiCommand.getTaskDetails(), 
    							  guiCommand.getTaskStart(), 
    							  guiCommand.getTaskEnd());
    }
    
    //@@author A0125369Y
    private void displayHelp(){
    	helpPopup.show(primaryStage);	
    	helpPopup.setX(primaryStage.getX() + primaryStage.getWidth() / 2 - helpPopup.getWidth() / 2);
    	helpPopup.setY(primaryStage.getY() + primaryStage.getHeight() / 2 - helpPopup.getHeight() / 2);
    }        
}
