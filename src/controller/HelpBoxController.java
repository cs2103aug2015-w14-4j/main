package controller;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class HelpBoxController extends VBox{
	
	private static final String FXML_PATH = "/view/HelpBoxView.fxml";
	
	public HelpBoxController(){
        try {
    		FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(FXML_PATH));
            loader.setRoot(this);
            loader.setController(this);
        	loader.load();
		} catch (IOException e) {
			System.out.println(e);
		}
        initHelpEntry();
        this.setId("helpBox");
	}
	
	private void initHelpEntry(){
		createEntry("add");
		createEntry("edit");
		createEntry("delete");
		createEntry("ack");
		createEntry("delete");
	}
	
	private void createEntry(String text){
		Text entry = new Text(text);
		entry.setId("helpEntry");
		this.getChildren().add(entry);
	}
}
