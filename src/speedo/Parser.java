package speedo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import processor.COMMANDS;

public class Parser {
	
	private ArrayList<String> inputs;
	private SimpleDateFormat sdf;
	private String dateString;
	private String content;
	private Date date;
	
	private static final String ADD = "add";
	private static final String DELETE = "remove delete";
	private static final String EDIT = "edit change";
	private static final String ACK = "ack acknowledge";
	private static final String SEARCH = "search find";
	
	public Parser() {
		inputs = new ArrayList<String>();
		sdf = new SimpleDateFormat("dd-M-yyyy hh:mm");
		date = new Date();
	}
	
	public void parse(String input){
		String temp = input;
		String temp1 = "";
		int counter = 0;
		if(input.contains("'")){
			temp = temp.substring(temp.indexOf("'") + 1);
			temp = temp.substring(0, temp.indexOf("'"));
			content = temp;
			for(int i = 0;i < input.length();i++){
				if(Character.toString(input.charAt(i)).equals("'")){
					counter++;
				}
				if(counter==0){
					temp1 = temp1 + Character.toString(input.charAt(i));
				}else{
					counter = 0;
				}
			}
		}else{
			content = "No detail";
			temp1 = input;
		}
		String[] parts = temp1.split(" ");
		for(int i = 0;i < parts.length;i++){
			inputs.add(parts[i]);
		}
	}
	
	public COMMANDS getCommand(){
		if(ADD.contains(inputs.get(0))){
			return COMMANDS.ADD;
		} else if (DELETE.contains(inputs.get(0))){
			return COMMANDS.DELETE;
		} else if (EDIT.contains(inputs.get(0))){
			return COMMANDS.EDIT;
		} else if (ACK.contains(inputs.get(0))){
			return COMMANDS.ACK;
		}else if (SEARCH.contains(inputs.get(0))){
			return COMMANDS.SEARCH;
		}
		return COMMANDS.INVALID;
	}
	
	public String getTaskName(){
		return inputs.get(1);
	}
	
	public Date getDate() throws ParseException{
		String dateTemp = inputs.get(2);
		String timeTemp = inputs.get(3);
		String[] dateTemps = new String[3];
		String[] timeTemps = new String[2];
		int countDate = 0;
		int countTime = 0;
		for(int i = 0;i<6;i=i+2){
			dateTemps[i-countDate] = Character.toString(dateTemp.charAt(i))+Character.toString(dateTemp.charAt(i+1));
			countDate++; 
		}
		for(int j = 0;j<4;j=j+2){
			timeTemps[j-countTime] = Character.toString(timeTemp.charAt(j))+Character.toString(timeTemp.charAt(j+1));
			countTime++;
		}	
		dateString = dateTemps[0]+"-"+dateTemps[1]+"-"+"20"+dateTemps[2]+" "+timeTemps[0]+":"+timeTemps[1];
		date = sdf.parse(dateString);
		return date;
	}
	
	public String getContent(){
		return content;
	}
	
	public int getIndex(){
		int index = Integer.parseInt(inputs.get(1)); 
		return index;
	}
}
