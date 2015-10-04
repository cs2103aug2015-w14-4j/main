package speedo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


class Logic{
	//Attributes
	Storage store;
	Parser parser;
	String command;
	String content;

	
	//Constructor
	public Logic(){
		store = new Storage();
		parser = new Parser();
	}
	
	//Methods
	
	public void executeCMD(String s){
		//parser = new Parser();
		parser.parse(s);
		command = parser.getCommand();
		content = parser.getContent();
		if(command.equals("add")){
			add();
		}
	}
	
	public void add(){
		//store = new Storage();
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm");
		String date_in_string = sdf.format(new Date()); 
		System.out.println(date);
		try {
			 date = sdf.parse(date_in_string);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(store.add(content,  date)){
			System.out.println("Task added");
		}
		store.saveFile();
		
	}
	
	public void edit(int index, String text){
		store.edit(index, text);
	}
	
	public void delete(){
		int index = Integer.parseInt(content);
		System.out.println("Task deleted");
		store.delete(index);
		store.saveFile();
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
		store.saveFile();
	}
	
	
}
