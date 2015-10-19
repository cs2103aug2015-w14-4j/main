//@@author A0125369Y
package storage;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Task is a class representation of a task. It contains the following
 * information:
 * <ul>
 * <li>Task Id</li>
 * <li>Task Name</li>
 * <li>Task Details</li>
 * <li>Starting Date</li>
 * <li>Ending Date</li>
 * <li>Is Completed</li>
 * </ul>
 * 
 * @author Barnabas
 *
 */

public class Task {

	private int taskId;
	private String name;
	private String details;
	private Date startDate;
	private Date endDate;
	private boolean isCompleted;

	// Strings
	private static final String EMPTY = "Empty";
	private static final String DATE_FORMAT = "dd MMMM yyyy";
	private static final String TIME_FORMAT = "hh:mm";
	private static final String TASK_STRING = "Task [name= %1$s, details= %2$s, startDate= %3$s, endDate= %4$s, completed= %5$s]";

	// *************************************** CONSTRUCTOR
	/**
	 * Default constructor for a Task Object.
	 * <p>
	 * All parameters are set to null, refer to
	 * {@link #Task(String, String, Date, Date)}
	 */
	public Task() {
		this(null, null, null, null);
	}

	/**
	 * Constructor to create a Task Object without details and an ending date.
	 * <p>
	 * The ending date and details are set to null, other than that refer to
	 * {@link #Task(String, String, Date, Date)}
	 * 
	 * @param name
	 *            the name of the task in String
	 * @param date
	 *            the date of the task as a Date Object
	 */
	public Task(String name, Date date) {
		this(name, null, date, null);
	}

	/**
	 * Constructor to create a Task Object without any dates.
	 * <p>
	 * The dates is set to null, other than that refer to
	 * {@link #Task(String, String, Date, Date)}
	 * 
	 * @param name
	 *            the name of the task in String
	 * @param details
	 *            the details of the task in String
	 */
	public Task(String name, String details) {
		this(name, details, null, null);
	}

	/**
	 * Constructor to create a Task Object without an ending date.
	 * <p>
	 * The ending date is set to null, other than that refer to
	 * {@link #Task(String, String, Date, Date)}
	 * 
	 * @param name
	 *            the name of the task in String
	 * @param details
	 *            the details of the task in String
	 * @param startDate
	 *            the starting date of the task as a Date Object
	 */
	public Task(String name, String details, Date date) {
		this(name, details, date, null);
	}

	/**
	 * Constructor to create a Task Object.
	 * <p>
	 * Default behavior of a Task Object:
	 * <ul>
	 * <li>completed flag set to false</li>
	 * <li>task id calculated by hashCode</li>
	 * </ul>
	 * 
	 * @param name
	 *            the name of the task in String
	 * @param details
	 *            the details of the task in String
	 * @param startDate
	 *            the starting date of the task as a Date Object
	 * @param endDate
	 *            the ending date of the task as a Date Object
	 */
	public Task(String name, String details, Date startDate, Date endDate) {
		this.setName(name);
		this.setStartDate(startDate);
		this.setEndDate(endDate);
		this.setDetails(details);
		this.setCompleted(false);
		this.setTaskId();
	}

	// *************************************** TASK ID
	/**
	 * Method to get the Id of a Task Object.
	 * <p>
	 * The task id is created based on the initial hash code of the Task Object.
	 * Refer to {@link #getHashCode()} for how the hash code is calculated.
	 * 
	 * @return the Id as a int
	 */
	public int getTaskId() {
		return taskId;
	}

	// Set the task id to the initial hash code
	private void setTaskId() {
		taskId = getHashCode();
	}

	/**
	 * Method to reset the Task Id
	 * <p>
	 * This method should only be called if the task id collides. This method
	 * simply increments the original task id by 1 to prevent collision.
	 */
	public void resetTaskId() {
		taskId += 1;
	}

	/**
	 * Method to get the hash code of a Task Object.
	 * <p>
	 * This method calculates the hash code using the String.hashCode()
	 * function. The hash code is calculate based on the strings of the
	 * following unique elements:
	 * <ul>
	 * <li>Name</li>
	 * <li>Details</li>
	 * <li>Full Staring Date</li>
	 * <li>Full Ending Date</li>
	 * </ul>
	 * 
	 * @return the hash code as a String
	 */
	public int getHashCode() {
		String uniqueElements = this.getName() + this.getDetails() + this.getFullStartDateString()
				+ this.getFullEndDateString();
		return uniqueElements.hashCode();
	}

	// *************************************** NAME
	/**
	 * Method to get the name of a Task Object.
	 * 
	 * @return the name as a String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method to set the name of a Task Object.
	 * 
	 * @param name
	 *            the String representation of the name
	 */
	public void setName(String name) {
		this.name = name;
	}

	// *************************************** DETAILS
	/**
	 * Method to get the details of a Task Object.
	 * 
	 * @return the details as a String
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * Method to set the details of a Task Object.
	 * 
	 * @param details
	 *            the String representation of the details
	 */
	public void setDetails(String details) {
		this.details = details;
	}

	// *************************************** START DATE
	/**
	 * Method to get the starting date of a Task Object.
	 * 
	 * @return the starting date as a Date Object
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * Method to retrieve a the starting date in string form.
	 * <p>
	 * This method makes use of the toString() method of the Date Object to
	 * return a string representation of the starting date. The method does a
	 * check to ensure that the starting date is not null, in the even the start
	 * date is null, the string "Empty" is returned instead
	 * 
	 * @return The string value of the starting date. If no starting date
	 *         exists, returns "Empty"
	 */
	public String getFullStartDateString() {
		if (this.getStartDate() != null) {
			return this.getStartDate().toString();
		} else {
			return EMPTY;
		}
	}

	/**
	 * Method to get the starting date of a Task Object.
	 * <p>
	 * This method returns the starting date in the format <i>"dd MMMM yyyy"</i>
	 * . Where <i>dd</i> denotes day of the month, <i>MMMM</i> denotes month of
	 * the year & <i>yyyy</i> denotes year.
	 * 
	 * @return the starting date as a String, if no starting date, returns
	 *         "Empty"
	 */
	public String getStartDateString() {
		if (this.getStartDate() != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
			return dateFormat.format(this.getStartDate());
		} else {
			return EMPTY;
		}
	}

	/**
	 * Method to get the starting time of a Task Object.
	 * <p>
	 * This method returns the starting time in the format <i>"hh:mm"</i>. Where
	 * <i>hh</i> denotes hour & <i>mm</i> denotes minutes.
	 * 
	 * @return the starting time as a String, if no starting date, returns
	 *         "Empty"
	 */
	public String getStartTimeString() {
		if (this.getStartDate() != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_FORMAT);
			return dateFormat.format(this.getStartDate());
		} else {
			return EMPTY;
		}
	}

	/**
	 * Method to set the starting date of a Task Object.
	 * 
	 * @param startDate
	 *            Date object as the starting date of a Task
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	// *************************************** END DATE

	/**
	 * Method to get the ending date of a Task Object.
	 * 
	 * @return the ending date as a Date Object
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * Method to retrieve a the full ending date in string form.
	 * <p>
	 * This method makes use of the toString() method of the Date Object to
	 * return a string representation of the full ending date. The method does a
	 * check to ensure that the ending date is not null, in the even the end
	 * date is null, the string "Empty" is returned instead
	 * 
	 * @return the string value of the full ending date, "Empty" if no ending
	 *         date exists
	 */
	public String getFullEndDateString() {
		if (this.getEndDate() != null) {
			return this.getEndDate().toString();
		} else {
			return EMPTY;
		}
	}
	
	/**
	 * Method to get the ending date of a Task Object.
	 * <p>
	 * This method returns the ending date in the format <i>"dd MMMM yyyy"</i>
	 * . Where <i>dd</i> denotes day of the month, <i>MMMM</i> denotes month of
	 * the year & <i>yyyy</i> denotes year.
	 * 
	 * @return the ending date as a String, if no ending date, returns
	 *         "Empty"
	 */
	public String getEndDateString() {
		if (this.getEndDate() != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
			return dateFormat.format(this.getEndDate());
		} else {
			return EMPTY;
		}
	}

	/**
	 * Method to get the ending time of a Task Object.
	 * <p>
	 * This method returns the ending time in the format <i>"hh:mm"</i>. Where
	 * <i>hh</i> denotes hour & <i>mm</i> denotes minutes.
	 * 
	 * @return the ending time as a String, if no ending date, returns
	 *         "Empty"
	 */
	public String getEndTimeString() {
		if (this.getEndDate() != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat(TIME_FORMAT);
			return dateFormat.format(this.getEndDate());
		} else {
			return EMPTY;
		}
	}

	/**
	 * Method to set the ending date of a Task Object.
	 * 
	 * @param endDate
	 *            Date object as the ending date of a Task
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	// *************************************** IS COMPLETED

	/**
	 * Method to check the isCompleted flag of a Task Object.
	 * 
	 * @return truth value of the isCompleted flag
	 */
	public boolean isCompleted() {
		return isCompleted;
	}

	/**
	 * Method to set the completed flag of a Task Object.
	 * 
	 * @param isCompleted
	 *            true to indicate completed, false to indicate not completed.
	 */
	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	// *************************************** CONTAINS, SEARCHING

	/**
	 * Method to check if the Task Object contains a search term.
	 * 
	 * @return true if the search term exists
	 * @param searchTerm
	 *            the string to search for
	 */
	public boolean contains(String searchTerm) {
		System.out.println(searchTerm);
		if (this.getName().contains(searchTerm)) {
			return true;
		} else if(this.getDetails() != null){
			if (this.getDetails().contains(searchTerm)) {
				return true;
			} 
		}
		return false;
	}

	// *************************************** TO STRING

	@Override
	public String toString() {
		return String.format(TASK_STRING, name, details, startDate, endDate, isCompleted);
	}
}