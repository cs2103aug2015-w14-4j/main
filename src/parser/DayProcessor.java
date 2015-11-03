//@@author A0125369Y
package parser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DayProcessor {
	private static SimpleDateFormat dateStringMth;
	private static SimpleDateFormat dateIntMth;
	private static SimpleDateFormat dateStringMthTime;
	private static SimpleDateFormat dateIntMthTime;
	//private static SimpleDateFormat dateStringMthTimeShort;
	//private static SimpleDateFormat dateIntMthTimeShort;

	private final static String DAY_OF_THE_WEEK = "EEEE";
	private final static String TODAYS_DATE = "dd MMMM";

	/*
	private static final String JAN_STRING = "jan january";
	private static final String FEB_STRING = "feb february";
	private static final String MAR_STRING = "mar march";
	private static final String APR_STRING = "apr april";
	private static final String MAY_STRING = "may may";
	private static final String JUN_STRING = "jun june";
	private static final String JUL_STRING = "jul july";
	private static final String AUG_STRING = "aug august";
	private static final String SEP_STRING = "sep september";
	private static final String OCT_STRING = "oct october";
	private static final String NOV_STRING = "nov november";
	private static final String DEC_STRING = "dec december";

	private static final String JAN_INT = "01";
	private static final String FEB_INT = "02";
	private static final String MAR_INT = "03";
	private static final String APR_INT = "04";
	private static final String MAY_INT = "05";
	private static final String JUN_INT = "06";
	private static final String JUL_INT = "07";
	private static final String AUG_INT = "08";
	private static final String SEP_INT = "09";
	private static final String OCT_INT = "10";
	private static final String NOV_INT = "11";
	private static final String DEC_INT = "12";
	*/

	private static final String DATE_STRING_MONTH = "ddMMMMyyyy";
	private static final String DATE_INT_MONTH = "ddMMyyyy";
	private static final String DATE_STRING_MONTH_TIME = "ddMMMMyyyy hhmm";
	private static final String DATE_INT_MONTH_TIME = "ddMMyyyy hhmm";
	//private static final String DATE_STRING_MONTH_TIME_SHORT = "ddMMMMyy hhmm";
	//private static final String DATE_INT_MONTH_TIME_SHORT = "ddMMyy hhmm";

	private static final String SPACE = " ";

	// Index of various date components
	private static final int DAY = 0;
	private static final int MTH1 = 1;
	private static final int YR2 = 2;

	private static final int DAY4 = 4;
	private static final int MTH5 = 5;
	private static final int YR6 = 6;

	private static final int DAY1 = 1;
	private static final int MTH2 = 2;
	private static final int YR3 = 3;

	private static final int DAY2 = 2;
	private static final int MTH3 = 3;
	private static final int YR4 = 4;

	private static final int DAY3 = 3;
	private static final int MTH4 = 4;
	private static final int YR5 = 5;

	private static final int DATE = 0;
	private static final int DATE1 = 1;
	private static final int DATE2 = 2;
	private static final int DATE3 = 3;
	private static final int DATE4 = 4;

	private static final int TIME1 = 1;
	private static final int TIME3 = 3;
	private static final int TIME4 = 4;
	private static final int TIME5 = 5;
	private static final int TIME6 = 6;
	private static final int TIME7 = 7;

	// Length of strings
	private static final int DATE_LEN = 6;
	private static final int TIME_LEN = 4;
	private static final int DAY_LEN = 2;
	private static final int YEAR_LEN = 4;

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

	/**
	 * Method to process a string of 2 or less dates
	 * 
	 * The following formats will be processed
	 * <p>
	 * 
	 * <ul>
	 * Date:
	 * <li><b>dd-MM-yyyy</b> E.g. 27-12-2015</li>
	 * <li><b>dd/MM/yyyy</b> E.g. 27/12/2015</li>
	 * <li><b>dd MM yyyy</b> E.g. 27 12 2015</li>
	 * </ul>
	 * <ul>
	 * Time:
	 * <li><b>hh:mm</b> E.g. 08:00</li>
	 * <li><b>hhmm</b> E.g. 0800</li>
	 * </ul>
	 * 
	 * @param dateText
	 *            string containing 2 or less dates
	 * 
	 * @return DatePair object holding two dates
	 */
	public static DatePair stringToDate(String dateText) {
		/*
		 * TODO |12 Hour Format|: hh:mm am/pm e.g. 08:00 pm hhmm e.g. 0800 pm
		 * 
		 */

		dateStringMth = new SimpleDateFormat(DATE_STRING_MONTH);
		dateIntMth = new SimpleDateFormat(DATE_INT_MONTH);
		dateStringMthTime = new SimpleDateFormat(DATE_STRING_MONTH_TIME);
		dateIntMthTime = new SimpleDateFormat(DATE_INT_MONTH_TIME);
		//dateStringMthTimeShort = new SimpleDateFormat(DATE_STRING_MONTH_TIME_SHORT);
		//dateIntMthTimeShort = new SimpleDateFormat(DATE_INT_MONTH_TIME_SHORT);

		dateText = dateText.trim().replaceAll(" +", " ");
		String[] pieces = dateText.split(" ");

		Date dateOne = null;
		Date dateTwo = null;

		String stringDateOne = "";
		String stringDateTwo = "";

		if (pieces.length == 8) {
			// Length 8: two dates with spaces E.g. 12 oct 2015 08:00
			stringDateOne = pieces[DAY] + pieces[MTH1] + pieces[YR2] + SPACE + processTime(pieces[TIME3]);
			stringDateTwo = pieces[DAY4] + pieces[MTH5] + pieces[YR6] + SPACE + processTime(pieces[TIME7]);
		} else if (pieces.length == 7) {
			if (pieces[TIME3].length() >= TIME_LEN && pieces[DAY4].length() <= DAY_LEN) {
				// Length 7: Mixture, date with spaces with time first & date with spaces without time second
				stringDateOne = pieces[DAY] + pieces[MTH1] + pieces[YR2] + SPACE + processTime(pieces[TIME3]);
				stringDateTwo = pieces[DAY4] + pieces[MTH5] + pieces[YR6];
			} else if (pieces[YR5].length() >= YEAR_LEN && pieces[TIME6].length() >= TIME_LEN) {
				// Length 7: Mixture, date with spaces with time second & date with spaces without time first
				stringDateOne = pieces[DAY] + pieces[MTH1] + pieces[YR2];
				stringDateTwo = pieces[DAY3] + pieces[MTH4] + pieces[YR5] + SPACE + processTime(pieces[TIME6]);
			}
		} else if (pieces.length == 6) {
			if (pieces[DATE].length() >= DATE_LEN) {
				// Length 6: Mixture, date without spaces with time first & date with spaces with time second
				stringDateOne = processDate(pieces[DATE]) + SPACE + processTime(pieces[TIME1]);
				stringDateTwo = pieces[DAY2] + pieces[MTH3] + pieces[YR4] + SPACE + processTime(pieces[TIME5]);
			} else if (pieces[DATE4].length() >= DATE_LEN) {
				// Length 6: Mixture, date without spaces with time second & date with spaces with time first
				stringDateOne = pieces[DAY] + pieces[MTH1] + pieces[YR2] + SPACE + processTime(pieces[TIME3]);
				stringDateTwo = processDate(pieces[DATE4]) + SPACE + processTime(pieces[TIME5]);
			} else {
				// Length 6: Two dates with spaces without time
				stringDateOne = pieces[DAY] + pieces[MTH1] + pieces[YR2];
				stringDateTwo = pieces[DAY3] + pieces[MTH4] + pieces[YR5];
			}
		} else if (pieces.length == 5) {
			if (pieces[DATE].length() >= DATE_LEN && pieces[TIME4].length() >= TIME_LEN) {
				// Length 5: Mixture, date with spaces with time second & date
				// without spaces without time first
				stringDateOne = processDate(pieces[DATE]);
				stringDateTwo = pieces[DAY1] + pieces[MTH2] + pieces[YR3] + SPACE + processTime(pieces[TIME4]);
			} else if (pieces[DATE4].length() >= DATE_LEN && pieces[3].length() >= TIME_LEN) {
				// Length 5: Mixture, date with spaces with time first & without
				// spaces without time second
				stringDateOne = pieces[DAY] + pieces[MTH1] + pieces[YR2] + SPACE + processTime(pieces[TIME3]);
				stringDateTwo = processDate(pieces[DATE4]);
			} else if (pieces[DATE].length() >= DATE_LEN && pieces[TIME1].length() >= TIME_LEN) {
				// Length 5: Mixture, date with spaces without time second &
				// date without spaces with time first
				stringDateOne = processDate(pieces[DATE]) + SPACE + processTime(pieces[TIME1]);
				stringDateTwo = pieces[DAY2] + pieces[MTH3] + pieces[YR4];
			} else if (pieces[DATE3].length() >= DATE_LEN && pieces[4].length() >= TIME_LEN) {
				// Length 5: Mixture, date with spaces without time first & date
				// without spaces with time second
				stringDateOne = pieces[DAY] + pieces[MTH1] + pieces[YR2];
				stringDateTwo = processDate(pieces[DATE3]) + SPACE + processTime(pieces[TIME4]);
			}
		} else if (pieces.length == 4) {
			if (pieces[DATE].length() >= DATE_LEN && pieces[DATE2].length() >= DATE_LEN) {
				// Length 4: Two dates with time
				stringDateOne = processDate(pieces[DATE]) + SPACE + processTime(pieces[TIME1]);
				stringDateTwo = processDate(pieces[DATE2]) + SPACE + processTime(pieces[TIME3]);
			} else if (pieces[DATE].length() >= DATE_LEN && pieces[DAY1].length() <= DAY_LEN) {
				// Length 4: Mixture, date without spaces first & date with
				// spaces second, both without time
				stringDateOne = processDate(pieces[DATE]);
				stringDateTwo = pieces[DAY1] + pieces[MTH2] + pieces[YR3];
			} else if (pieces[DAY].length() <= DAY_LEN && pieces[DATE3].length() >= DATE_LEN) {
				// Length 4: Mixture, date without spaces second & date with
				// spaces first, both without time
				stringDateOne = pieces[DAY] + pieces[MTH1] + pieces[YR2];
				stringDateTwo = processDate(pieces[DATE3]);
			} else {
				// Length 4: One date with spaces with time
				stringDateTwo = pieces[DAY] + pieces[MTH1] + pieces[YR2] + SPACE + processTime(pieces[TIME3]);
			}
		} else if (pieces.length == 3) {
			// Length 3: One date with spaces
			stringDateTwo = pieces[DAY] + pieces[MTH1] + pieces[YR2];

		} else if (pieces.length == 2) {
			if (pieces[DATE].length() >= DATE_LEN && pieces[DATE1].length() >= DATE_LEN) {
				// Length 2: Two dates without spaces without time
				stringDateOne = processDate(pieces[DATE]);
				stringDateTwo = processDate(pieces[DATE1]);
			} else {
				// Length 2: One date with spaces with time
				stringDateTwo = pieces[DAY] + pieces[MTH1] + pieces[YR2] + SPACE + processTime(pieces[TIME3]);
			}
		} else if (pieces.length == 1) {
			// Length 1: One date without spaces
			stringDateTwo = pieces[DATE];
		}

		try {
			dateOne = dateStringMthTime.parse(stringDateOne);
		} catch (ParseException e) {
			try {
				dateOne = dateStringMth.parse(stringDateOne);
			} catch (ParseException f) {
			}
		}
		try {
			dateOne = dateIntMthTime.parse(stringDateOne);
		} catch (ParseException e) {
			try {
				dateOne = dateIntMth.parse(stringDateOne);
			} catch (ParseException f) {
			}
		}

		try {
			dateTwo = dateStringMthTime.parse(stringDateTwo);
		} catch (ParseException e) {
			try {
				dateTwo = dateStringMth.parse(stringDateTwo);
			} catch (ParseException f) {
			}
		}
		try {
			dateTwo = dateIntMthTime.parse(stringDateTwo);
		} catch (ParseException e) {
			try {
				dateTwo = dateIntMth.parse(stringDateTwo);
			} catch (ParseException f) {
			}
		}

		return new DatePair(dateOne, dateTwo);
	}

	private static String processTime(String time) {
		time = time.replaceAll(":", "");
		time = time.trim();
		return time;
	}

	private static String processDate(String date) {
		date = date.replaceAll("[:/-]", "");
		date = date.trim();
		return date;
	}

	/*
	 * private static String processMonth(String month) {

		month = month.replaceAll("[^a-zA-Z]", "");
		month = month.trim();
		month = month.toLowerCase();

		if (JAN_STRING.contains(month)) {
			return JAN_INT;
		} else if (FEB_STRING.contains(month)) {
			return FEB_INT;
		} else if (MAR_STRING.contains(month)) {
			return MAR_INT;
		} else if (APR_STRING.contains(month)) {
			return APR_INT;
		} else if (MAY_STRING.contains(month)) {
			return MAY_INT;
		} else if (JUN_STRING.contains(month)) {
			return JUN_INT;
		} else if (JUL_STRING.contains(month)) {
			return JUL_INT;
		} else if (AUG_STRING.contains(month)) {
			return AUG_INT;
		} else if (SEP_STRING.contains(month)) {
			return SEP_INT;
		} else if (OCT_STRING.contains(month)) {
			return OCT_INT;
		} else if (NOV_STRING.contains(month)) {
			return NOV_INT;
		} else if (DEC_STRING.contains(month)) {
			return DEC_INT;
		} else {
			return null;
		}
		
	}
	*/
}
