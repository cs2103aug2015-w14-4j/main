package controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import processor.COMMANDS;
import processor.DayProcessor;
import processor.ErrorProcessor;
import speedo.MainApp;
import storage.Task;
import speedo.GuiCommand;
import speedo.Logic;

public class TaskListController extends ScrollPane{
	
	private static final String FXML_PATH = "/view/TaskListView.fxml";
	
	@FXML 
	private VBox containerOfTask;
		
    // Maps the Task to its corresponding TaskController
    private Hashtable<Integer, TaskController> taskLookupTable;

    public TaskListController() {
    	taskLookupTable = new Hashtable<Integer, TaskController>();
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
        this.setBackground(null);
        this.addEventFilter(ScrollEvent.SCROLL, new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                if (event.getDeltaX() != 0) {
                    event.consume();
                }
            }
        });
    }
        
    private TaskController createTask(Task t){
    	TaskController tc = new TaskController(t);
    	taskLookupTable.put(tc.getTaskId(), tc);
    	return tc;
    }
        
    public void addTask(Task t){
    	containerOfTask.getChildren().add(createTask(t));
    }
    
    public void removeTask(Task t){
    	TaskController tc = taskLookupTable.get(t.getTaskId());
    	System.out.println(tc.getText() + " was deleted by removeTask");
    	containerOfTask.getChildren().remove(tc);
    	taskLookupTable.remove(t.getTaskId());
    }
    
    //TODO Can only edit the Task title
    public void editTask(Task t){
    	TaskController tc = taskLookupTable.get(t.getTaskId());
    	tc.setName(t.getName());
    }
        
    public void loadTaskList(List<Task> listOfTasks) {
    	containerOfTask.getChildren().clear(); // barny: Testing redrawing to show sorted order
    	for (int i = 0; i < listOfTasks.size(); i++) {
    		addTask(listOfTasks.get(i));
		}
    }
        
}
