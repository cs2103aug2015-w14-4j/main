
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
		
	}
	
	public void edit(){
		
	}
	
	public void delete(){
		
	}
	
	public void search(){
		
	}
	
	public void acknowledge(){
		
	}
}
