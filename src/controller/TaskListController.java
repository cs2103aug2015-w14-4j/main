//@@author A0124791A
package controller;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.VBox;
import storage.Task;
import utilities.ErrorProcessor;

public class TaskListController extends ScrollPane{
	
	private static final String FXML_PATH = "/view/TaskListView.fxml";
	
	private static final String NODATE_LABEL = "TO-DO's";
	private static final String OVERDUE_LABEL = "OVERDUE";
	private static final String TODAY_LABEL = "TODAY";
	private static final String TOMORROW_LABEL = "TOMORROW";
	private static final String UPCOMING_LABEL = "UPCOMING";
	
	@FXML 
	private VBox containerOfTask;
	
	private ObservableList<Node> listOfTask;
    private Hashtable<Integer, TaskController> taskLookupTable;		// Maps the Task to its corresponding TaskController
    private boolean[] isTaskTitleDisplayed;
    private int numOfTaskDue;

    public TaskListController() {
        try {
        	FXMLLoader loader = new FXMLLoader();
        	loader.setLocation(getClass().getResource(FXML_PATH));
            loader.setRoot(this);
            loader.setController(this);
        	loader.load();
		} catch (IOException e) {
			ErrorProcessor.alert(e.toString());
		}
    }
    
    @FXML
    public void initialize() {
    	listOfTask = containerOfTask.getChildren();
    	taskLookupTable = new Hashtable<Integer, TaskController>();
        this.addEventFilter(ScrollEvent.SCROLL, new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                if (event.getDeltaX() != 0) {
                    event.consume();
                }
            }
        });
    }
    
    public int getNumOfTaskDue(){
    	return numOfTaskDue;
    }
        
    private TaskController createTask(Task t, int index){
    	TaskController tc = new TaskController(t, index);
    	taskLookupTable.put(tc.getTaskId(), tc);
    	return tc;
    }
    
    public void addTask(Task t){
    	addTask(t, listOfTask.size());
    }
    
    public void addTask(Task t, int index){
    	listOfTask.add(createTask(t, index));
    	if(t.due() == Task.TODAY){
    		numOfTaskDue++;
    	}
    }
            
    public void loadTaskList(List<Task> list) {
    	numOfTaskDue = 0;
    	isTaskTitleDisplayed = new boolean[6];
    	listOfTask.clear(); 
    	for (int i = 0; i < list.size(); i++) {
    		generateLabel(list.get(i));
    		addTask(list.get(i), i + 1);
		}
    }
         
    private void generateLabel(Task t){
    	if(t.due() == Task.NODATE && !isTaskTitleDisplayed[Task.NODATE]){
    		addLabel(NODATE_LABEL, Task.NODATE);
    	} else if(t.due() == Task.OVERDUE && !isTaskTitleDisplayed[Task.OVERDUE]){
    		addLabel(OVERDUE_LABEL, Task.OVERDUE);
    	} else if(t.due() == Task.TODAY && !isTaskTitleDisplayed[Task.TODAY]){
    		addLabel(TODAY_LABEL, Task.TODAY);
    	} else if(t.due() == Task.TOMORROW && !isTaskTitleDisplayed[Task.TOMORROW]){
    		addLabel(TOMORROW_LABEL, Task.TOMORROW);
    	} else if(t.due() == Task.UPCOMING && !isTaskTitleDisplayed[Task.UPCOMING]){
    		addLabel(UPCOMING_LABEL, Task.UPCOMING);
    	}
    }
    
    private void addLabel(String title, int taskType){
		Label label = new Label(title);
		containerOfTask.getChildren().add(label);
		isTaskTitleDisplayed[taskType] = true;
    }

	public void expand(int taskId) {		
		try {
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_TAB);
			r.keyPress(KeyEvent.VK_SPACE);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Expanded!");
	}
	
	/*
	 * DEPRECIATED METHODS
	 * */
	
    public void removeTask(Task t){
    	TaskController tc = taskLookupTable.get(t.getTaskId());
    	listOfTask.remove(tc);
    	taskLookupTable.remove(t.getTaskId());
    }
    
    public void editTask(Task t){
    	TaskController tc = taskLookupTable.get(t.getTaskId());
    	tc.setName(t.getName());
    }
}
