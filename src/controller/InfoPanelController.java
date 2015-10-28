package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class InfoPanelController extends VBox{
	
	private static final String FXML_PATH = "/view/InfoPanelView.fxml";
	
	@FXML 
	private Label userName;
	@FXML 
	private Label todayDay;
	@FXML 
	private Label todayDate;
	@FXML 
	private Label numTasksDue;
		
	public InfoPanelController(String user, String day, String date, int numTasks){
    	FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(FXML_PATH));
        loader.setRoot(this);
        loader.setController(this);
        try {
        	loader.load();
        	userName.setText(user);
        	todayDay.setText(day);
        	todayDate.setText(date);
        	numTasksDue.setText(numTasks + " Task(s) due");
		} catch (IOException e) {
			System.out.println(e);
		}
	}
		
}
