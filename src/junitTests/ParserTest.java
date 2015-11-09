//@@author A0126232U
package junitTests;

import static org.junit.Assert.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Test;
import junit.framework.*;
import parser.Parser;
import utilities.COMMANDS;

/**
 * Test case for parser
 * 
 * @author Qianonn
 *
 */

public class ParserTest extends TestCase{
	private static final String input1="add task -d 08112015 1200 09112015 1300 -i detail";
	private static final String input2="add task -d 08112015 1200 09112015 1300";
	private static final String input3="add task -d 09112015 1300";
	private static final String input4="add task";
	private static final String input5="add";
	private static final String input6="edit 3 newtask -d 08112015 1200 09112015 1300 -i newdetail";
	private static final String input7="edit 3 newtask -d 08112015 1200 09112015 1300";
	private static final String input8="edit 3 newtask -d 09112015 1300";
	private static final String input9="edit 3 -i newdetail";
	private static final String input10="edit 1475091874590147124727490273597517174";
	private static final String input11="edit -1";
	private static final String input12="delete -1";
	private static final String input13="delete ewfoiwen";
	private static final String input14="name testing";
	private static final String input15="filepath testing";
	private static final String input16="search testing";
	private Date startDate,endDate;
	private SimpleDateFormat dateVariant;
	protected Parser parse;
	   
	/**
	 * To set up this test
	 */
	protected void setUp(){
	  	parse = new Parser();
	}

	@Test
	public void testAdd() throws ParseException {
		parse.parse(input1);
		//print();
		dateVariant = new SimpleDateFormat("ddMMyyyy hhmm");
		startDate=dateVariant.parse("08112015 1200");
		endDate=dateVariant.parse("09112015 1300");
	    assertTrue(parse.getCommand().equals(COMMANDS.ADD));
	    assertTrue(parse.getTaskName().equals("task"));
	    assertTrue(parse.getStartDate().equals(startDate));
	    assertTrue(parse.getEndDate().equals(endDate));
	    assertTrue(parse.getDetails().equals("detail"));
	    assertTrue(parse.getIndex()==-1);
	    parse.reset();
	    parse.parse(input2);
	    assertTrue(parse.getCommand().equals(COMMANDS.ADD));
	    assertTrue(parse.getTaskName().equals("task"));
	    assertTrue(parse.getStartDate().equals(startDate));
	    assertTrue(parse.getEndDate().equals(endDate));
	    assertTrue(parse.getDetails()==null);
	    assertTrue(parse.getIndex()==-1);
	    parse.reset();
	    parse.parse(input3);
	    assertTrue(parse.getCommand().equals(COMMANDS.ADD));
	    assertTrue(parse.getTaskName().equals("task"));
	    assertTrue(parse.getStartDate()==null);
	    assertTrue(parse.getEndDate().equals(endDate));
	    assertTrue(parse.getDetails()==null);
	    assertTrue(parse.getIndex()==-1);
	    parse.reset();
	    parse.parse(input4);
	    assertTrue(parse.getCommand().equals(COMMANDS.ADD));
	    assertTrue(parse.getTaskName().equals("task"));
	    assertTrue(parse.getStartDate()==null);
	    assertTrue(parse.getEndDate()==null);
	    assertTrue(parse.getDetails()==null);
	    assertTrue(parse.getIndex()==-1);
	    parse.reset();
	    parse.parse(input5);
	    assertTrue(parse.getCommand().equals(COMMANDS.ADD));
	    assertTrue(parse.getTaskName()==null);
	    assertTrue(parse.getStartDate()==null);
	    assertTrue(parse.getEndDate()==null);
	    assertTrue(parse.getDetails()==null);
	    assertTrue(parse.getIndex()==-1);
	}

	private void print() {
		System.out.println("command "+parse.getCommand());
		System.out.println("taskname "+parse.getTaskName());
		System.out.println("start date "+parse.getStartDate());
		System.out.println("end date "+parse.getEndDate());
		System.out.println("detail "+parse.getDetails());
		System.out.println("index "+parse.getIndex());
		System.out.println("search "+parse.getSearch());
		System.out.println("name "+parse.getName());
		System.out.println("filepath "+parse.getFilePath());
	}
	
	@Test
	public void testEdit() throws ParseException {
		dateVariant = new SimpleDateFormat("ddMMyyyy hhmm");
		startDate=dateVariant.parse("08112015 1200");
		endDate=dateVariant.parse("09112015 1300");
		parse.parse(input6);
		assertTrue(parse.getCommand().equals(COMMANDS.EDIT));
	    assertTrue(parse.getTaskName().equals("newtask"));
	    assertTrue(parse.getStartDate().equals(startDate));
	    assertTrue(parse.getEndDate().equals(endDate));
	    assertTrue(parse.getDetails().equals("newdetail"));
	    assertTrue(parse.getIndex()==2);
	    parse.reset();
	    parse.parse(input7);
	    assertTrue(parse.getCommand().equals(COMMANDS.EDIT));
	    assertTrue(parse.getTaskName().equals("newtask"));
	    assertTrue(parse.getStartDate().equals(startDate));
	    assertTrue(parse.getEndDate().equals(endDate));
	    assertTrue(parse.getDetails()==null);
	    assertTrue(parse.getIndex()==2);
	    parse.reset();
	    parse.parse(input8);
	    assertTrue(parse.getCommand().equals(COMMANDS.EDIT));
	    assertTrue(parse.getTaskName().equals("newtask"));
	    assertTrue(parse.getStartDate()==null);
	    assertTrue(parse.getEndDate().equals(endDate));
	    assertTrue(parse.getDetails()==null);
	    assertTrue(parse.getIndex()==2);
	    parse.reset();
	    parse.parse(input9);
	    assertTrue(parse.getCommand().equals(COMMANDS.EDIT));
	    assertTrue(parse.getTaskName()==null);
	    assertTrue(parse.getStartDate()==null);
	    assertTrue(parse.getEndDate()==null);
	    assertTrue(parse.getDetails().equals("newdetail"));
	    assertTrue(parse.getIndex()==2);
	    parse.reset();
	    parse.parse(input10);
	    assertTrue(parse.getCommand().equals(COMMANDS.EDIT));
	    assertTrue(parse.getTaskName()==null);
	    assertTrue(parse.getStartDate()==null);
	    assertTrue(parse.getEndDate()==null);
	    assertTrue(parse.getDetails()==null);
	    assertTrue(parse.getIndex()==-1);
	    //print();
	    parse.reset();
	    parse.parse(input11);
	    assertTrue(parse.getCommand().equals(COMMANDS.EDIT));
	    assertTrue(parse.getTaskName()==null);
	    assertTrue(parse.getStartDate()==null);
	    assertTrue(parse.getEndDate()==null);
	    assertTrue(parse.getDetails()==null);
	    assertTrue(parse.getIndex()==-1);
	    //print();
	}
	
	@Test
	public void testDelete() {
		parse.parse(input12);
	    assertTrue(parse.getCommand().equals(COMMANDS.DELETE));
	    assertTrue(parse.getIndex()==-1);
	    //print();
	    parse.reset();
	    parse.parse(input13);
	    assertTrue(parse.getCommand().equals(COMMANDS.DELETE));
	    assertTrue(parse.getIndex()==-1);
	    //print();
	    parse.reset();
	    parse.parse(input14);
	    assertTrue(parse.getCommand().equals(COMMANDS.NAME));
	    assertTrue(parse.getIndex()==-1);
	    assertTrue(parse.getName().equals("testing"));
	    //print();
	    parse.reset();
	    parse.parse(input15);
	    assertTrue(parse.getCommand().equals(COMMANDS.FILEPATH));
	    assertTrue(parse.getIndex()==-1);
	    assertTrue(parse.getFilePath().equals("testing"));
	    //print();
	    parse.reset();
	    parse.parse(input16);
	    assertTrue(parse.getCommand().equals(COMMANDS.SEARCH));
	    assertTrue(parse.getIndex()==-1);
	    assertTrue(parse.getSearch().equals("testing"));
	    //print();
	}

}
