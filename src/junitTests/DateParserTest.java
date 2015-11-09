//@@author A0126232U
package junitTests;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import parser.DatePair;
import parser.DateParser;

public class DateParserTest {

	private static final String SPACE = " ";
	@Test
	public void testStringToDate() {
		String dateOne = "12 november 2015";
		String dateTwo = "13 11 2015";
		String dateThree = "12 nov 2015";
		String dateFour = "13 nov 2015";
		String dateFive = "12 nov 2015 1234";
		String dateSix = "13 december 2015 2345";
		String dateSeven = "12 11 2015 1234";
		String dateEight = "13 december 2015";
		
		DatePair datePairOne = DateParser.stringToDate(dateOne+SPACE+dateTwo);
		DatePair datePairTwo = DateParser.stringToDate(dateThree+SPACE+dateFour);
		DatePair datePairThree = DateParser.stringToDate(dateFive+SPACE+dateSix);
		DatePair datePairFour = DateParser.stringToDate(dateSeven+SPACE+dateEight);
		
		Date expectedOne = new Date();
		Date expectedTwo = new Date();
		Date expectedThree = new Date();
		Date expectedFour = new Date();
		Date expectedFive = new Date();
		Date expectedSix = new Date();
		Date expectedSeven = new Date();
		Date expectedEight = new Date();
		
		SimpleDateFormat expOneFormat = new SimpleDateFormat("dd MMMM yyyy");
		SimpleDateFormat expTwoFormat = new SimpleDateFormat("dd MM yyyy");
		SimpleDateFormat expThreeFormat = new SimpleDateFormat("dd MMM yyyy");
		SimpleDateFormat expFourFormat = new SimpleDateFormat("dd MMM yyyy hhmm");
		SimpleDateFormat expFiveFormat = new SimpleDateFormat("dd MMMM yyyy hhmm");
		SimpleDateFormat expSixFormat = new SimpleDateFormat("dd MM yyyy hhmm");
		
		try {
			expectedOne = expOneFormat.parse(dateOne);
			expectedTwo = expTwoFormat.parse(dateTwo);
			expectedThree = expThreeFormat.parse(dateThree);
			expectedFour = expThreeFormat.parse(dateFour);
			expectedFive = expFourFormat.parse(dateFive);
			expectedSix = expFiveFormat.parse(dateSix);
			expectedSeven = expSixFormat.parse(dateSeven);
			expectedEight = expOneFormat.parse(dateEight);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
		assertEquals(datePairOne.getDateOne(), expectedOne);
		assertEquals(datePairOne.getDateTwo(), expectedTwo);
		assertEquals(datePairTwo.getDateOne(), expectedThree);
		assertEquals(datePairTwo.getDateTwo(), expectedFour);
		assertEquals(datePairThree.getDateOne(), expectedFive);
		assertEquals(datePairThree.getDateTwo(), expectedSix);
		assertEquals(datePairFour.getDateOne(), expectedSeven);
		assertEquals(datePairFour.getDateTwo(), expectedEight);
	}

}
