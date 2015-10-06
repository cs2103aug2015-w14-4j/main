package speedo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import processor.COMMANDS;

import processor.COMMANDS;
import storage.Storage;


public class Logic{
	//Attributes
	private Storage store;
	private Parser parser;
	private COMMANDS command;
	private String content;
	private int taskIndex;

	
	//Constructor
	public Logic(){
		store = new Storage();
		parser = new Parser();
	}
		
	//Methods
	public String executeCMD(String s) throws ParseException{
		parser.parse(s);
		command = parser.getCommand();
		content = parser.getContent();
		String confirmation = null;
		
		switch(command){
		case ADD: add();
				  s = "Task " + content + "successfully added"; 
				  break;
		case DELETE: delete();
					 s = "Task " + content + "has been deleted";
					 break;
		case EDIT: taskIndex = parser.getIndex(); edit(taskIndex, content);
				   s = "Task " + taskIndex + "can be edited"; 
				   break;
		case SEARCH: ; break;
		case ACK: ; break;
		case INVALID: ; break;
		}
		return confirmation;
	}
	
	public Task getTask(int index){
		return store.getTask(index);
	}
	
	public int getNumOfTask(){
		return store.getNumOfTask();
	}
	
	public Task add() throws ParseException{
		/*
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm");
		String date_in_string = sdf.format(new Date()); 
		try {
			 date = sdf.parse(date_in_string);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		Date date = parser.getDate();
		Task newTask = store.add(content, date);
		if(newTask != null){
			System.out.println("Task added");
			//store.saveFile();
			return newTask;
		} else {
			return null;
		}
	}
	
	public void edit(int index, String text){
		store.edit(index, text);
		//store.saveFile();
	}
	
	public void delete(){
		int index = Integer.parseInt(content);
		System.out.println("Task deleted");
		store.delete(index);
		//store.saveFile();
	}
	
	public void search(){
		List<Task> list = store.search(content);
		while (!list.isEmpty()){
			System.out.println(list.get(0));
			list.remove(0);
		}
	}
	
	public void acknowledge(){
		store.acknowledge(Integer.parseInt(content));
		//store.saveFile();
	}
	
}
