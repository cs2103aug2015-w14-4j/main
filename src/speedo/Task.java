package speedo;

import java.util.Date;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

// Basic Task Object
public class Task {
 private String name;
 private String details;
 private Date startDate;
 private Date endDate;
 private boolean isAcknowledged;
 
 public Task(){
	 this.setName(null);
	 this.setStartDate(null);
	 this.setEndDate(null);
	 this.setDetails(null);
	 this.setAcknowledged(false);
 }
 
 public Task(String name, Date date){
	 this.setName(name);
	 this.setStartDate(date);
	 this.setEndDate(null);
	 this.setDetails(null);
	 this.setAcknowledged(false);
 }
 
 public Task(String name, String details){
	 this.setName(name);
	 this.setStartDate(null);
	 this.setEndDate(null);
	 this.setDetails(details);
	 this.setAcknowledged(false);
 }
 
 public Task(String name, String details, Date date){
	 this.setName(name);
	 this.setStartDate(date);
	 this.setEndDate(null);
	 this.setDetails(details);
	 this.setAcknowledged(false);
 }
 
 public Task(String name, String details, Date startDate, Date endDate){
	 this.setName(name);
	 this.setStartDate(startDate);
	 this.setEndDate(endDate);
	 this.setDetails(details);
	 this.setAcknowledged(false);
 }

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getDetails() {
	return details;
}

public void setDetails(String details) {
	this.details = details;
}

public Date getStartDate() {
	return startDate;
}

public void setStartDate(Date date) {
	this.startDate = date;
} 

public Date getEndDate() {
	return endDate;
}

public void setEndDate(Date endDate) {
	this.endDate = endDate;
}

public boolean isAcknowledged() {
	return isAcknowledged;
}

public void setAcknowledged(boolean isAcknowledged) {
	this.isAcknowledged = isAcknowledged;
}

@Override
public String toString() {
   return "Task [name=" + name + ", details=" + details + ", startDate="
	+ startDate + ", endDate=" + endDate + ", acknowledged=" 
		   + isAcknowledged +"]";
}

/**
 * Method to check if the Task Object contains a search term.
 * 
 * @return True if the search term exists
 */
public boolean contains(String searchTerm){
	if(name.contains(searchTerm) || details.contains(searchTerm)){
		return true;
	}
	return false;
}

public StringProperty getNameProperty(){
	return new SimpleStringProperty(name);
}

public StringProperty getDetailsProperty(){
	return new SimpleStringProperty(details);
}

}