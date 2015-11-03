package junitTests;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import processor.DatePair;
import processor.DayProcessor;

public class DayProcessorTest {

	@Test
	public void testStringToDate() {
		DatePair gg = DayProcessor.stringToDate(" 12 nov 2015       0800");
		System.out.println(gg.getDateOne()+" "+gg.getDateTwo());
		//System.out.println(DayProcessor.stringToDate(""));
		String a = "12:/ oc-t /no -d v: 2015";
		System.out.println(a.replaceAll("[:/-]", " "));
		System.out.println(a.split(" -d ").length);
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy hhmm");
		try {
			System.out.println(sdf.parse("121115 0800"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
		}
	}

}
