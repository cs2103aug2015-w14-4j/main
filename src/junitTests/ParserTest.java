package junitTests;

import static org.junit.Assert.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Test;
import junit.framework.*;
import parser.Parser;

public class ParserTest extends TestCase{
	protected int value1, value2;
	protected Parser parse;
	   // assigning the values
	protected void setUp(){
		value1=3;
	  	value2=3;
	  	parse = new Parser();
	}

	@Test
	public void test() {
		String test = "grwgwrfwf fwfw wff";
		parse.parse(test);
		System.out.println("command "+parse.getCommand());
		System.out.println("taskname "+parse.getTaskName());
		System.out.println("start date "+parse.getStartDate());
		System.out.println("end date "+parse.getEndDate());
		System.out.println("detail "+parse.getDetails());
		System.out.println("index "+parse.getIndex());
		System.out.println("search "+parse.getSearch());
		System.out.println("name "+parse.getName());
		System.out.println("filepath "+parse.getFilePath());
	    assertTrue(parse.getDetails().equals("iwbi"));
	}
	
	@Test
	public void test1() throws ParseException {
		String test = "add test -d 241015 0306 -i detail for testing";
		Date date;
		SimpleDateFormat dateVariant;
		dateVariant = new SimpleDateFormat("ddMMyyyy hhmm");
		date=dateVariant.parse("241015 0306");
		parse.parse(test);
	    assertTrue(parse.getEndDate().equals(date));
	}
	
	@Test
	public void test2() {
		String test = "add test -i detail for testing -d 241015 0306";
		parse.parse(test);
	    assertTrue(parse.getDetails().equals("detail for testing"));
	}

}
