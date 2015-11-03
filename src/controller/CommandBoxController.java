package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import processor.ErrorProcessor;
import speedo.MainApp;

public class CommandBoxController extends VBox{
	
	private static final String FXML_PATH = "/view/CommandBoxView.fxml";
	
	// Reference to the main application.
    private MainApp mainApp;
	
	@FXML 
	private Text feedback;
	@FXML 
	private TextField commandBox;
	
	public CommandBoxController(){
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(FXML_PATH));
        loader.setRoot(this);
        loader.setController(this);
        try {
        	loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			ErrorProcessor.alert(e.toString());
		}
	}
	
	@FXML
	public void initialize(){
		feedback.setText("");
	}
	
	@FXML
	public void handleUserInput(){
		String userInput = commandBox.getText();
		mainApp.handleUserCommand(userInput);
		commandBox.clear();
	}
	
	@FXML
	public void parseUserInput(){
		String userInput = commandBox.getText();
		mainApp.parseUserCommand(userInput);
	}
	
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
    	feedback.setFill(Color.GREEN);
    }
    
    public void setErrorFeedback(String txt){
    	feedback.setText(txt);
    	feedback.setFill(Color.RED);
    }
    
    public void setPredictionFeedback(String txt){
    	feedback.setText(txt);
    	feedback.setFill(Color.BLUE);
    }
    
}
