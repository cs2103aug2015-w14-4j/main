//@@author A0125369Y
package processor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DayProcessor {

	private final static String DAY_OF_THE_WEEK = "EEEE";
	private final static String TODAYS_DATE = "dd MMMM";

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
}
