import java.util.Date;
// Basic Task Object
public class Task {
 private String name;
 private String details;
 private Date date;
 
 public Task(){
	 this.setName(null);
	 this.setDate(null);
	 this.setDetails(null);
 }
 
 public Task(String name, Date date){
	 this.setName(name);
	 this.setDate(date);
	 this.setDetails(null);
 }
 
 public Task(String name, String details, Date date){
	 this.setName(name);
	 this.setDate(date);
	 this.setDetails(details);
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

public Date getDate() {
	return date;
}

public void setDate(Date date) {
	this.date = date;
} 
 
}
