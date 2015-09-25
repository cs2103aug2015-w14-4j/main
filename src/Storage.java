import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Storage {
	private static List<Task> taskList;

	public static void readFile(){
		// TODO
	}
	
	public static void saveFile(){
		// TODO
	}
	
	public static void search(){
		// TODO
	}
	
	public static boolean add(String name, Date date){
		// TODO
		if(taskList == null){
			taskList = new ArrayList<Task>();
		}
		Task newTask = new Task(name, date);
		taskList.add(newTask);
		
		return taskList.contains(newTask);
	}
	
	public static String delete(int index){
		// TODO
		if(taskList == null){
			return null;
		}
		return taskList.remove(index).getName();
	}
	
	public static void edit(){
		// TODO
	}
	
	public static void acknowledge(){
		// TODO
	}

}
