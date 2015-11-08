package junitTests;

import static org.junit.Assert.*;
import org.junit.Test;
import parser.Predictive;
import processor.COMMANDS;
import storage.Storage;

public class PredictiveTest {
	
	private static final String ADD_MSG = "Adding Task...";
	private static final String EDIT_MSG = "Editing Task...";
	private static final String DELETE_MSG = "Deleting Task...";
	
	private static final String RANDOM_INPUT = "fjdfkdlj jdlkjdskfj   dssdsdd 	jsljlkasjdlkajdslk";
	
	private static final String ADD_WITHOUT_NAME_INPUT = "add";
	private static final String INSERT_WITHOUT_NAME_INPUT = "insert";
	private static final String ADD_NAME_ONLY_INPUT = "add some - Task";
	private static final String INSERT_NAME_ENDDATE_INPUT = "insert some - Task -d 30 nov 2013";
	private static final String INSERT_NAME_ENDDATE_STARTDATE_INPUT = "insert some - Task -d 30 nov 2013	31 jan 2012";
	private static final String ADD_NAME_STARTDATE_ENDDATE_INPUT = "insert task123 -d 4 nov 2013 4 nov 2015";
	
	private static final String EDIT_INPUT_TEST_1 = "edit";
	private static final String EDIT_INPUT_TEST_2 = "change";
	private static final String EDIT_INPUT_TEST_3 = "edit -2 kjdfldkjlkdsf";
	private static final String EDIT_INPUT_TEST_4 = "change 1 hello -d";
	
	/*
	public Storage initStorage(){
		Storage storage = new Storage(true);
		storage.add(name, details, startDate, endDate);
	}
	*/
	
	@Test
	public void testEmptyInput() {
		Predictive predictor = new Predictive();
		predictor.processInput("");
		assertEquals(predictor.getCommand(), COMMANDS.INVALID);
		assertEquals(predictor.getCommandMsg(), null);
		assertEquals(predictor.getIndex(), -1);
		assertEquals(predictor.getTaskName(), null);
		assertEquals(predictor.getTaskDetails(), null);
		assertEquals(predictor.getTaskStart(), null);
		assertEquals(predictor.getTaskEnd(), null);
	}
	
	@Test
	public void testRandomInput(){
		Predictive predictor = new Predictive();
		predictor.processInput(RANDOM_INPUT);
		assertEquals(predictor.getCommand(), COMMANDS.INVALID);
		assertEquals(predictor.getCommandMsg(), null);
		assertEquals(predictor.getIndex(), -1);
		assertEquals(predictor.getTaskName(), null);
		assertEquals(predictor.getTaskDetails(), null);
		assertEquals(predictor.getTaskStart(), null);
		assertEquals(predictor.getTaskEnd(), null);
	}
	
	@Test
	public void testAddWithoutName() {
		Predictive predictor = new Predictive();
		predictor.processInput(ADD_WITHOUT_NAME_INPUT);
		assertEquals(predictor.getCommand(), COMMANDS.ADD);
		assertEquals(predictor.getCommandMsg(), ADD_MSG);
		assertEquals(predictor.getIndex(), -1);
		assertEquals(predictor.getTaskName(), null);
		assertEquals(predictor.getTaskDetails(), null);
		assertEquals(predictor.getTaskStart(), null);
		assertEquals(predictor.getTaskEnd(), null);
	}
	
	@Test
	public void testInsertWithoutName() {
		Predictive predictor = new Predictive();
		predictor.processInput(INSERT_WITHOUT_NAME_INPUT);
		assertEquals(predictor.getCommand(), COMMANDS.ADD);
		assertEquals(predictor.getCommandMsg(), ADD_MSG);
		assertEquals(predictor.getIndex(), -1);
		assertEquals(predictor.getTaskName(), null);
		assertEquals(predictor.getTaskDetails(), null);
		assertEquals(predictor.getTaskStart(), null);
		assertEquals(predictor.getTaskEnd(), null);
	}
	
	@Test
	public void testAddWithNameOnly() {
		Predictive predictor = new Predictive();
		predictor.processInput(ADD_NAME_ONLY_INPUT);
		assertEquals(predictor.getCommand(), COMMANDS.ADD);
		assertEquals(predictor.getCommandMsg(), ADD_MSG);
		assertEquals(predictor.getIndex(), -1);
		assertEquals(predictor.getTaskName(), "some - Task");
		assertEquals(predictor.getTaskDetails(), null);
		assertEquals(predictor.getTaskStart(), null);
		assertEquals(predictor.getTaskEnd(), null);
	}
	
	/*
	@Test
	public void testAddWithNameEnddate() {
		Predictive predictor = new Predictive();
		predictor.processInput(ADD_NAME_ONLY_INPUT);
		assertEquals(predictor.getCommand(), COMMANDS.ADD);
		assertEquals(predictor.getCommandMsg(), ADD_MSG);
		assertEquals(predictor.getIndex(), -1);
		assertEquals(predictor.getTaskName(), "some - Task");
		assertEquals(predictor.getTaskDetails(), null);
		assertEquals(predictor.getTaskStart(), null);
		assertEquals(predictor.getTaskEnd(), null);
	}
	*/
			
}
