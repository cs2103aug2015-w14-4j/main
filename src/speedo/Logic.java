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

	
	//Constructor
	public Logic(){
		store = new Storage();
		parser = new Parser();
	}
		
	//Methods
	public Task executeCMD(String s){
		//parser = new Parser();
		parser.parse(s);
		command = parser.getCommand();
		content = parser.getContent();
		return null;
	}
	
	public Task getTask(int index){
		return store.getTask(index);
	}
	
	public int getNumOfTask(){
		return store.getNumOfTask();
	}
	
	public Task add(){
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm");
		String date_in_string = sdf.format(new Date()); 
		try {
			 date = sdf.parse(date_in_string);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
