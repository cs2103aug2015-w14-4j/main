package controller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import storage.Task;
import utils.ErrorProcessor;

public class TaskController extends TitledPane{
	
	private static final String FXML_PATH = "/view/TaskView.fxml";
	private static final String TASK_NAME_DISPLAY = "%d. %s";
	
	@FXML 
	private VBox expandedBox;
	
	@FXML 
	private HBox details;
	@FXML 
	private HBox endDate;
	@FXML 
	private HBox startDate;
	
	@FXML 
	private Label detailsLabel;
	@FXML 
	private Label startDateLabel;
	@FXML 
	private Label endDateLabel;
	
	@FXML 
	private Text detailsText;
	@FXML 
	private Text startDateText;
	@FXML 
	private Text endDateText;
	
	private int taskId;
	private int taskIndex;
	
	public TaskController(Task task, int index){
        try {
        	FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(FXML_PATH));
            loader.setRoot(this);
            loader.setController(this);
        	loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			ErrorProcessor.alert(e.toString());
		}
        initTask(task, index);
	}
	
	@FXML
    public void initialize() {
        this.setExpanded(false);
    }
	
	private void initTask(Task task, int index){
        setTaskId(task.getTaskId());
    	setTaskIndex(index);
		setName(task.getName());
        setDetails(task.getDetails());
        setStartDate(task.getStartDateString());
        setEndDate(task.getEndDateString()); 
        applyStyling(task);
	}
	
	private void applyStyling(Task task){
        if(task.isCompleted()){
        	this.setId("taskCompleted");
        	this.setOpacity(0.2);
        } else if(task.due() == Task.OVERDUE){
        	this.setId("taskOverdue");
        } else if(task.due() == Task.TODAY){
        	this.setId("taskDueToday");
        } else if(task.due() == Task.TOMORROW){
        	this.setId("taskDueTomorrow");
        } else if(task.due() == Task.NODATE && task.getDetails() == null){
        	this.setCollapsible(false);
        }
	}
	
	public void setName(String name){
		this.setText(String.format(TASK_NAME_DISPLAY, taskIndex, name));
	}
	
	public void setDetails(String text){
		if(text == null){
			expandedBox.getChildren().remove(details);
		} else {
			detailsText.setText(text);
		}
	}
	
	public void setStartDate(String date){
		if(date == null){
			expandedBox.getChildren().remove(startDate);
			endDateLabel.setText("Date");
		} else {
			startDateText.setText(date);
		}
	}
	
	public void setEndDate(String d){
		endDateText.setText(d);
	}
	
	public void setTaskId(int id){
		taskId = id;
	}
	
	public int getTaskId(){
		return taskId;
	}
	
	public void setTaskIndex(int index){
		taskIndex = index;
	}
	
}
