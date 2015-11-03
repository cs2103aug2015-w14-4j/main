//@@author A0125369Y
package junitTests;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import processor.DatePair;
import processor.DayProcessor;

public class DayProcessorTest {

	private static final String SPACE = " ";
	@Test
	public void testStringToDate() {
		String dateOne = "12 november 2015";
		String dateTwo = "13 11 2015";
		DatePair datePair = DayProcessor.stringToDate(dateOne+SPACE+dateTwo);

		Date expectedOne = new Date();
		Date expectedTwo = new Date();
		SimpleDateFormat expOneFormat = new SimpleDateFormat("dd MMM yyyy");
		SimpleDateFormat expTwoFormat = new SimpleDateFormat("dd MM yyyy");
		try {
			expectedOne = expOneFormat.parse(dateOne);
			expectedTwo = expTwoFormat.parse(dateTwo);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
		assertEquals(datePair.getDateOne(), expectedOne);
		assertEquals(datePair.getDateTwo(), expectedTwo);
	}

}
