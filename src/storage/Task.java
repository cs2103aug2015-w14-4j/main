//@@author A0125369Y
package storage;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
	private static final String EMPTY = null;
	private static final String SPACE = "%1$s ";
	private static final String DATE_FORMAT = "dd MMMM yyyy hh:mm a";
	private static final String TASK_STRING = "Task [name= %1$s, details= %2$s, startDate= %3$s, endDate= %4$s, completed= %5$s]";

	// Due tags
	public static final int NODATE = 0;
	public static final int OVERDUE = 1;
	public static final int TODAY = 2;
	public static final int TOMORROW = 3;
	public static final int UPCOMING = 4;

	private static final int DAYS_IN_YEAR = 365;
	private static final int ZERO_DAYS = 0;
	private static final int ONE_DAY = 1;

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
	 * The starting date and details are set to null, other than that refer to
	 * {@link #Task(String, String, Date, Date)}.
	 * 
	 * @param name
	 *            the name of the task in String
	 * @param date
	 *            the due date of the task as a Date Object
	 */
	public Task(String name, Date date) {
		this(name, null, null, date);
	}

	/**
	 * Constructor to create a Task Object without any dates.
	 * <p>
	 * The dates is set to null, other than that refer to
	 * {@link #Task(String, String, Date, Date)}.
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
	 * The starting date is set to null, other than that refer to
	 * {@link #Task(String, String, Date, Date)}.
	 * 
	 * @param name
	 *            the name of the task in String
	 * @param details
	 *            the details of the task in String
	 * @param date
	 *            the due date of the task as a Date Object
	 */
	public Task(String name, String details, Date date) {
		this(name, details, null, date);
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
	 * Method to set the Task Id.
	 * 
	 * @param taskId
	 *            the int representation of the Task Id
	 */
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	/**
	 * Method to reset the Task Id.
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
		String uniqueElements = this.getName() + this.getDetails() + this.getStartDateString()
				+ this.getEndDateString();
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
	 * This method returns the starting date in the format <i>
	 * "dd MMMM yyyy hh:mm a"</i> . Where <i>dd</i> denotes day of the month,
	 * <i>MMMM</i> denotes month of the year, <i>yyyy</i> denotes year,
	 * <i>hh</i> denotes hour, <i>mm</i> denotes minutes & <i>a</i> denotes
	 * am/pm.
	 * 
	 * @return The string value of the starting date. If no starting date
	 *         exists, returns "Empty"
	 */
	public String getStartDateString() {
		Date startDate = this.getStartDate();
		if (startDate != null) {
			return this.getDateString(startDate);
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
	 * Method to retrieve a the ending date in string form.
	 * <p>
	 * This method returns the ending date in the format <i>
	 * "dd MMMM yyyy hh:mm a"</i> . Where <i>dd</i> denotes day of the month,
	 * <i>MMMM</i> denotes month of the year, <i>yyyy</i> denotes year,
	 * <i>hh</i> denotes hour, <i>mm</i> denotes minutes & <i>a</i> denotes
	 * am/pm.
	 * 
	 * @return the string value of the full ending date, "Empty" if no ending
	 *         date exists
	 */
	public String getEndDateString() {
		Date endDate = this.getEndDate();
		if (endDate != null) {
			return this.getDateString(endDate);
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
		searchTerm = searchTerm.toLowerCase();
		String searchString = this.toStringNoNull();
		if (searchString.contains(searchTerm)) {
			return true;
		}
		return false;
	}

	// *************************************** DUE

	/**
	 * Method to check when the task Object is due relative to today
	 * 
	 * @return -1 if the task is overdue, 0 if the task is due today, 1 if the
	 *         task is due tomorrow, 2 any other task is due within a week, 3
	 *         remaining tasks
	 */
	public int due() {
		if (this.getEndDate() != null) {
			Calendar endDate = Calendar.getInstance();
			endDate.setTime(this.getEndDate());
			int endDayInt = endDate.get(Calendar.DAY_OF_YEAR);
			endDayInt += endDate.get(Calendar.YEAR) * DAYS_IN_YEAR;

			// current date
			Calendar toDay = Calendar.getInstance();
			int toDayInt = toDay.get(Calendar.DAY_OF_YEAR);
			toDayInt += toDay.get(Calendar.YEAR) * DAYS_IN_YEAR;

			int diff = endDayInt - toDayInt;

			if (diff < ZERO_DAYS) {
				return OVERDUE;
			} else if (diff == ZERO_DAYS) {
				return TODAY;
			} else if (diff == ONE_DAY) {
				return TOMORROW;
			} else {
				return UPCOMING;
			}
		} else {
			return NODATE;
		}
	}

	// *************************************** LOW LEVEL FUNCTIONS

	private String toStringNoNull() {

		String fullString = "";

		if (this.getName() != null) {
			fullString += String.format(SPACE, this.getName().toLowerCase());
		}
		if (this.getDetails() != null) {
			fullString += String.format(SPACE, this.getDetails().toLowerCase());
		}
		if (this.getStartDateString() != null) {
			fullString += String.format(SPACE, this.getStartDateString().toLowerCase());
		}
		if (this.getEndDateString() != null) {
			fullString += String.format(SPACE, this.getEndDateString().toLowerCase());
		}
		return fullString;
	}

	private String getDateString(Date date) {
		assert date != null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		return dateFormat.format(date);
	}

	@Override
	public String toString() {
		return String.format(TASK_STRING, name, details, startDate, endDate, isCompleted);
	}
}