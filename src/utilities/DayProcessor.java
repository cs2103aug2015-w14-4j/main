//@@author A0125369Y
package utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DayProcessor {

	private final static String DAY_OF_THE_WEEK = "EEEE";
	private final static String TODAYS_DATE = "dd MMMM";
	private static final String DATE_FORMAT = "dd MMMM yyyy hh:mm a";

	public static String todayDay() {
		DateFormat dateFormat = new SimpleDateFormat(DAY_OF_THE_WEEK);
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static String todayDate() {
		DateFormat dateFormat = new SimpleDateFormat(TODAYS_DATE);
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public static String formatDate(Date date){
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		return dateFormat.format(date);
	}
	
	public static DatePair orderDate(Date date1, Date date2){
		Date firstDate = date1;
		Date secondDate = date2;
		if (firstDate != null && secondDate != null) {
			if (firstDate.after(secondDate)) {
				firstDate = date2;
				secondDate = date1;
			}
		}
		return new DatePair(firstDate, secondDate);
	}
}
