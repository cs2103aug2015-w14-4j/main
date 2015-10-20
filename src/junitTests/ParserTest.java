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
		String test = "add \"task name\" 201015 1000 'detail for testing'";
		parse.parse(test);
		System.out.println(parse.getCommand());
		System.out.println(parse.getTaskName());
		System.out.println(parse.getDate());
		System.out.println(parse.getDetails());
	    assertTrue(parse.getDetails().equals("detail for testing"));
	}
	
	@Test
	public void test1() {
		String test = "add 201015 1000 'detail for test' \"task\"";
		parse.parse(test);
	    assertTrue(parse.getTaskName().equals("task"));
	}
	
	@Test
	public void test2() {
		String test = "add \"task name\" 201015 1000 'detail'";
		parse.parse(test);
	    assertTrue(parse.getDetails().equals("detail"));
	}

}
