import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Daykeeper {

	public static String todayDay(){
		DateFormat dateFormat = new SimpleDateFormat("EEEE");
		Date date = new Date();
		return dateFormat.format(date);
	}
}
