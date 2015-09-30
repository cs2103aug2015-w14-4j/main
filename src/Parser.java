
import java.util.*;

public class Parser {
	
	private ArrayList<String> inputs;
	
	public Parser() {
		inputs = new ArrayList<String>();
	}
	
	public void parse(String input){
		String[] parts = input.split(" ");
		for(int i = 0;i < parts.length;i++){
			inputs.set(i, parts[i]);
		}
	}
	
	public String getCommand(){
		return inputs.get(0);
	}
	
	public String getContent(){
		String content = "";
		for(int i = 1;i < inputs.size();i++){
			content = content + inputs.get(i); 
		}
		return content;
	}

}
