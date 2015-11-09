//@@author A0124791A
package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import utilities.ErrorProcessor;

/**
 * CommandBoxController controls the text field where the user keys in commands
 * <p>
 * It displays feedback to user and passes the input in the text field to MainApp
 * 
 * @author Hongchao
 *
 */
public class CommandBoxController extends VBox{
	
	// ================================================================
	// FXML
	// ================================================================
	private static final String FXML_PATH = "/view/CommandBoxView.fxml";
		
	@FXML 
	private Label feedback;
	@FXML 
	private Label prediction;
	@FXML 
	private TextField commandBox;
	
	// ================================================================
	// Fields
	// ================================================================
    private MainApp mainApp;	// Reference to the main application
	
	public CommandBoxController(){
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(FXML_PATH));
        loader.setRoot(this);
        loader.setController(this);
        try {
        	loader.load();
		} catch (IOException e) {
			ErrorProcessor.alert(e.toString());
		}
	}
	
	@FXML
	public void initialize(){
		clearFeedback();
		clearPredictionFeedback();
	}
	
	/**
	 * handleUserInput is called when the user presses enter     
	 * The user input will be passed to MainApp for processing
	 * The command box will be cleared after each call
	 */
	@FXML
	public void handleUserInput(){
		String userInput = commandBox.getText();
		mainApp.handleUserCommand(userInput);
		commandBox.clear();
	}
	
	/**
	 * parseUserInput is called when the user keys in something   
	 * The user input will be passed to MainApp for processing
	 * 
	 */
	@FXML
	public void parseUserInput(){
		String userInput = commandBox.getText();
		mainApp.parseUserCommand(userInput);
	}
	
	public void clearFeedback() {
		feedback.setText("");
	}
	
	public void clearPredictionFeedback() {
		prediction.setText("");
	}
	
	// ================================================================
    // Setter methods
    // ================================================================
    
	/**
     * Is called by the main application to give a reference back to itself
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    	
    public void setFeedback(String txt){
    	feedback.setText(txt);
    	feedback.setId("successMsg");
    }
    
    public void setErrorFeedback(String txt){
    	feedback.setText(txt);
    	feedback.setId("errorMsg");
    }
    
    public void setPredictionFeedback(String txt){
    	prediction.setText(txt);
    	prediction.setId("predictionMsg");
    }
}
