package controller;

import application.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RootController {
	@FXML
	private Label userName;
	
	// Reference to the main application.
    private MainApp mainApp;
	
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
    	userName.setText("Hi Joe");
    }
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
