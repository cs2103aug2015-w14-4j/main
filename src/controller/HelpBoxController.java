//@@author A0124791A
package controller;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import utilities.ErrorProcessor;

/**
 * HelpBoxController controls the help menu
 * <p> 
 * It displays to the user all possible commands that can be used 
 *
 * @author Hongchao
 * 
 */
public class HelpBoxController extends VBox{
	
	// ================================================================
    // FXML
    // ================================================================
	private static final String FXML_PATH = "/view/HelpBoxView.fxml";
	
	// ================================================================
    // Help messages
    // ================================================================
	private static final String ADD_TIP = "Add/Insert <Item Name> -d <Starting Date> <Ending Date> -i <Details>";
	private static final String ADD_DETAILS = "This command adds a new item to the list.\n";
	private static final String ACK_TIP = "Ack/Acknowledge <Item Name> : Marks an item as completed";
	private static final String ACK_DETAILS = "This command marks an item as completed and hides it\n";
	private static final String COMPLETED_TIP = "Completed : Displays the list of completed items";
	private static final String COMPLETED_DETAILS = "\n";
	private static final String DELETE_TIP = "Remove/Delete <Item Index>";
	private static final String DELETE_DETAILS = "This command removes an item from the list permenantly\n";
	private static final String EDIT_TIP = "Edit/Change <Item Index> <Task Name> -d <Starting Date> <Ending Date> -i <Details>";
	private static final String EDIT_DETAILS = "This command edits an item identified by it's index, and changes the content accordingly\n";
	private static final String FILEPATH_TIP = "Filepath <Directory Path> : Changes file location. Note: Exclude file extension";
	private static final String FILEPATH_DETAILS = "\n";
	private static final String HOME_TIP = "Home : Displays the original list";
	private static final String HOME_DETAILS = "\n";
	private static final String SEARCH_TIP = "Find/Search <Keywords> : Displays the list of items containing the keywords";
	private static final String SEARCH_DETAILS = "\n";
	private static final String NAME_TIP = "Name <User Name> : Changes the user name";
	private static final String NAME_DETAILS = "\n";
	private static final String UNDO_TIP = "Undo: Reverts your last changes.";
	private static final String UNDO_DETAILS = "";

	public HelpBoxController(){
        try {
    		FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(FXML_PATH));
            loader.setRoot(this);
            loader.setController(this);
        	loader.load();
		} catch (IOException e) {
			ErrorProcessor.alert(e.toString());
		}
        initHelpEntry();
        this.setId("helpBox");
	}
	
	private void initHelpEntry(){
		createEntry(ADD_TIP);
		createEntry(ADD_DETAILS);
		createEntry(ACK_TIP);
		createEntry(ACK_DETAILS);
		createEntry(COMPLETED_TIP);
		createEntry(COMPLETED_DETAILS);
		createEntry(DELETE_TIP);
		createEntry(DELETE_DETAILS);
		createEntry(EDIT_TIP);
		createEntry(EDIT_DETAILS);
		createEntry(FILEPATH_TIP);
		createEntry(FILEPATH_DETAILS);
		createEntry(HOME_TIP);
		createEntry(HOME_DETAILS);
		createEntry(SEARCH_TIP);
		createEntry(SEARCH_DETAILS);
		createEntry(NAME_TIP);
		createEntry(NAME_DETAILS);
		createEntry(UNDO_TIP);	
		createEntry(UNDO_DETAILS);	
	}
	
	private void createEntry(String text){
		Text entry = new Text(text);
		entry.setId("helpEntry");
		entry.setWrappingWidth(700);
		this.getChildren().add(entry);
	}
}
