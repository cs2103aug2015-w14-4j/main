package junitTests;

import static org.junit.Assert.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Test;
import junit.framework.*;
import speedo.Parser;

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
		String test = "edit 2 -d 01112015 1000 -i lol";
		parse.parse(test);
		System.out.println(parse.getCommand());
		System.out.println(parse.getTaskName());
		System.out.println(parse.getStartDate());
		System.out.println(parse.getEndDate());
		System.out.println(parse.getDetails());
		System.out.println(parse.getIndex());
	    assertTrue(parse.getDetails().equals("detail for testing"));
	}
	
	@Test
	public void test1() {
		String test = "add test -d 241015 0306 -i detail for testing";
		parse.parse(test);
	    assertTrue(parse.getTaskName().equals("test"));
	}
	
	@Test
	public void test2() {
		String test = "add test -i detail for testing -d 241015 0306";
		parse.parse(test);
	    assertTrue(parse.getDetails().equals("detail for testing"));
	}

}
