package controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import processor.COMMANDS;
import processor.DayProcessor;
import processor.ErrorProcessor;
import speedo.MainApp;
import speedo.Task;
import speedo.GuiCommand;
import speedo.Logic;

public class TaskListController extends Accordion{
		
    // Maps the Task to its corresponding TaskController
    private Hashtable<Integer, TaskController> taskLookupTable;

    public TaskListController() {
    	taskLookupTable = new Hashtable<Integer, TaskController>();
    	this.setPrefHeight(332.0);
    	this.setPrefWidth(539.0);
    }
        
    private TaskController createTask(Task t){
    	TaskController tc = new TaskController(t);
    	taskLookupTable.put(tc.getTaskId(), tc);
    	return tc;
    }
        
    public void addTask(Task t){
    	this.getPanes().add(createTask(t));
    }
    
    public void removeTask(Task t){
    	TaskController tc = taskLookupTable.get(t.getTaskId());
    	System.out.println(tc.getText() + " was deleted by removeTask");
    	this.getPanes().remove(tc);
    	taskLookupTable.remove(t.getTaskId());
    }
    
    //TODO Can only edit the Task title
    public void editTask(Task t){
    	TaskController tc = taskLookupTable.get(t.getTaskId());
    	tc.setName(t.getName());
    }
        
    public void loadTaskList(List<Task> listOfTasks) {
    	for (int i = 0; i < listOfTasks.size(); i++) {
    		addTask(listOfTasks.get(i));
		}
    }
        
}
