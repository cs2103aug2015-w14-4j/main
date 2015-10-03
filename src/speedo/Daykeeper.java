package speedo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Daykeeper {
	private final static String DAY_OF_THE_WEEK = "EEEE";
	private final static String TODAYS_DATE = "dd MMMM yyyy";

	public static String todayDay(){
		DateFormat dateFormat = new SimpleDateFormat(DAY_OF_THE_WEEK);
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public static String todayDate(){
		DateFormat dateFormat = new SimpleDateFormat(TODAYS_DATE);
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public static Date stringToDate(String dateText){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm");
		try {
			return sdf.parse(dateText);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
