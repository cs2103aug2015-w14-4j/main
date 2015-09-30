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
		store.add(content, date);
		store.saveFile();
	}
	
	public void edit(){
		store.edit();
	}
	
	public void delete(){
		store.delete(Integer.parseInt(content));
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
