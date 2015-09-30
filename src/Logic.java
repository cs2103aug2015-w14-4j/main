import java.util.Date;
import java.util.List;


class Logic{
	//Attributes
	Storage store;
	Parser parser;
	String command;
	String content;
	Date date;
	
	//Constructor
	public void Logic(){
		store = new Storage();
		parser = new Parser();
	}
	
	//Methods
	
	public void executeCMD(String s){
		parser.parse(s);
		command = parser.getCommand();
		content = parser.getContent();
	}
	
	public void add(){
		if(store.add(content, date)){
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
