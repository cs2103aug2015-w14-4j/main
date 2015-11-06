package controller;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

public class HelpBoxController extends VBox{
	
	private static final String FXML_PATH = "/view/HelpBoxView.fxml";
	
	public HelpBoxController(){
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(FXML_PATH));
        loader.setRoot(this);
        loader.setController(this);
        try {
        	loader.load();
		} catch (IOException e) {
			System.out.println(e);
		}
        this.setId("helpBox");
	}
}
