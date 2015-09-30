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
	public void Logic(){
		// = new Storage();
		//parser = new Parser();
	}
	
	//Methods
	
	public void executeCMD(String s){
		parser = new Parser();
		parser.parse(s);
		command = parser.getCommand();
		content = parser.getContent();
		if(command.equals("add")){
			add();
		}
	}
	
	public void add(){
		store = new Storage();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm");
		String dateInString = "28-09-2015 08:00";
		Date date = null;
		try {
			 date = sdf.parse(dateInString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(store.add(content,  date)){
			System.out.println("Task added");
		}
		store.saveFile();
		
	}
	
	public void edit(){
		store.edit();
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
		
	}
	
	
}
