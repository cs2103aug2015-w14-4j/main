import java.util.Date;


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
		store.search(content);
	}
	
	public void acknowledge(){
		
	}
}
