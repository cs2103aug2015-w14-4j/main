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
		String test = "add task 061015 'detail'";
		parse.parse(test);
	    assertTrue(parse.getContent().equals("detail"));
	}
	
	@Test
	public void test1() {
		String test = "add task 061015 'detail'";
		parse.parse(test);
	    assertTrue(parse.getTaskName().equals("task"));
	}
	
	@Test
	public void test2() throws ParseException {
		String test = "add task 071015 1000 'detail 123 456 789'";
		parse.parse(test);
		System.out.println(parse.getDate());
	    assertTrue(parse.getDate().equals("07-10-2015 10:00"));
	}

}
