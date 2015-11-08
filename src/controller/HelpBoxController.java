//@@author A0124791A
package controller;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class HelpBoxController extends VBox{
	
	private static final String FXML_PATH = "/view/HelpBoxView.fxml";
	
	private static final String ADD_TIP = "Add/Insert <Item Name> -d <Starting Date> <Ending Date> -i <Details>";
	private static final String ACK_TIP = "Ack/Acknowledge <Item Name> : Marks an item as completed";
	private static final String COMPLETED_TIP = "Completed : Displays the list of completed items";
	private static final String DELETE_TIP = "Remove/Delete <Item Index>";
	private static final String EDIT_TIP = "Edit/Change <Item Index> <Task Name> -d <Starting Date> <Ending Date> -i <Details>";
	private static final String FILEPATH_TIP = "Filepath <Directory Path> : Changes file location. Note: Exclude file extension";
	private static final String HOME_TIP = "Home : Displays the original list";
	private static final String SEARCH_TIP = "Find/Search <Keywords> : Displays the list of items containing the keywords";
	private static final String NAME_TIP = "Name <User Name> : Changes the user name";
	private static final String UNDO_TIP = "Undo : Reverts your last changes.";
	// private static final String EXPAND_TIP = "";
	// private static final String EXIT_TIP = "";
	
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
		createEntry(ADD_TIP);
		createEntry(ACK_TIP);
		createEntry(COMPLETED_TIP);
		createEntry(DELETE_TIP);
		createEntry(EDIT_TIP);
		createEntry(FILEPATH_TIP);
		createEntry(HOME_TIP);
		createEntry(SEARCH_TIP);
		createEntry(NAME_TIP);
		createEntry(UNDO_TIP);	
	}
	
	private void createEntry(String text){
		Text entry = new Text(text);
		entry.setId("helpEntry");
		this.getChildren().add(entry);
	}
}
