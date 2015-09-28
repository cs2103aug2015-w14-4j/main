package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Task {
	
	 private StringProperty name;
	 private StringProperty details;
	 
	 /**
	  * Constructor with some initial data.
	  * 
	  * @param name
	  * @param details
	  */
	 public Task(String name, String details) {
		 this.name = new SimpleStringProperty(name);
		 this.details = new SimpleStringProperty(details);
	 }
	 
	 public StringProperty getNameProperty(){
		return name;
	 }
	 
	 public StringProperty getDetailsProperty(){
		return details;
	 }
}
