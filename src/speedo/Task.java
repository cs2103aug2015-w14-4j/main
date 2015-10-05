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

	public Task() {
		this(null, null, null, null);
	}

	public Task(String name, Date date) {
		this(name, null, date, null);
	}

	public Task(String name, String details) {
		this(name, details, null, null);
	}

	public Task(String name, String details, Date date) {
		this(name, details, date, null);
	}

	public Task(String name, String details, Date startDate, Date endDate) {
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
		return "Task [name=" + name + ", details=" + details + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", acknowledged=" + isAcknowledged + "]";
	}

	/**
	 * Method to check if the Task Object contains a search term.
	 * 
	 * @return True if the search term exists
	 */
	public boolean contains(String searchTerm) {
		if (name.contains(searchTerm) || details.contains(searchTerm)) {
			return true;
		}
		return false;
	}

}