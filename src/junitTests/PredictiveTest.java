//@@author A0124791A
package junitTests;

import static org.junit.Assert.*;
import org.junit.Test;
import logic.GuiCommand;
import logic.Logic;
import parser.Predictive;
import utilities.COMMANDS;

public class PredictiveTest {
	
	private static final String ADD_MSG = "Adding Task...";
	private static final String EDIT_MSG = "Editing Task...";
	private static final String DELETE_MSG = "Delete Task?";
	
	private static final String RANDOM_INPUT = "fjdfkdlj jdlkjdskfj   dssdsdd 	jsljlkasjdlkajdslk";
	private static final String RANDOM_WITH_SYMBOLS_INPUT = "!@#$%^&*()_-+ -d -i";
	
	private static final String ADD_WITHOUT_NAME_INPUT = "add";
	private static final String INSERT_WITHOUT_NAME_INPUT = "insert";
	private static final String ADD_NAME_ONLY_INPUT = "add some Task";
	private static final String INSERT_NAME_ENDDATE_INPUT = "insert some Task -d 30 nov 2013";
	private static final String INSERT_NAME_ENDDATE_STARTDATE_INPUT = "insert some Task -d 30 nov 2013 31 jan 2012";
	private static final String ADD_NAME_STARTDATE_ENDDATE_INPUT = "insert task123 -d 4 nov 2013 0930 4 nov 2015 2359";
	private static final String ADD_NAME_STARTDATE_ENDDATE_INFO_INPUT = "insert task123 -d 10 nov 2013 30 nov 2015 -i this is some info";
	
	private static final String EDIT_WITHOUT_INDEX_INPUT = "edit";
	private static final String CHANGE_WITHOUT_INDEX_INPUT = "change";
	private static final String EDIT_WITH_INVALID_INDEX_INPUT = "edit -2 new name";
	private static final String CHANGE_WITH_INVALID_INDEX_INPUT = "change fdfdjskljfklsdjf";
	private static final String EDIT_WITH_NOCHANGE_INPUT = "edit 1";
	private static final String CHANGE_NAME_ONLY_INPUT = "change 1 hello";
	private static final String EDIT_NAME_ENDDATE_INPUT = "edit 3 bye -d 10 jan 2099";
	private static final String CHANGE_NAME_STARTDATE_ENDDATE_INPUT = "change 3 new name -d 15 nov 2015 0800 15 nov 2015 2359";
	private static final String EDIT_NAME_ENDDATE_STARTDATE_INFO_INPUT = "edit 3 new name -d 15 nov 2015 2359 15 nov 2015 0800 -i edited info for task 3...";
	
	private static final String DELETE_WITHOUT_INDEX_INPUT = "delete";
	private static final String REMOVE_WITHOUT_INDEX_INPUT = "remove";
	private static final String DELETE_WITH_INVALID_INDEX_INPUT = "delete -2";
	private static final String REMOVE_WITH_INVALID_INDEX_INPUT = "remove jlksdjlkajkl";
	private static final String DELETE_WITH_VALID_INDEX_INPUT = "delete 2";
	
	private static final String ADD_FLOATING_TASK_INPUT = "add Task 1";
	private static final String ADD_TASK_WITH_ENDDATE_INPUT = "add Task 2 -d 10 jul 2018 1200";
	private static final String ADD_TASK_WITH_STARTDATE_ENDDATE_INFO_INPUT = "add Task 3 -d 10 jul 2018 20 feb 2020 -i task 3 info...";
	
	private Logic initLogic(){
		Logic logic = new Logic(true);
		logic.executeCMD(ADD_FLOATING_TASK_INPUT);
		logic.executeCMD(ADD_TASK_WITH_ENDDATE_INPUT);
		logic.executeCMD(ADD_TASK_WITH_STARTDATE_ENDDATE_INFO_INPUT);
		return logic;
	}
	
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
	public void testRandomInputWithSymbols(){
		Predictive predictor = new Predictive();
		predictor.processInput(RANDOM_WITH_SYMBOLS_INPUT);
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
		assertEquals(predictor.getTaskName(), "some Task");
		assertEquals(predictor.getTaskDetails(), null);
		assertEquals(predictor.getTaskStart(), null);
		assertEquals(predictor.getTaskEnd(), null);
	}
	
	@Test
	public void testInsertWithNameEndDate() {
		Predictive predictor = new Predictive();
		predictor.processInput(INSERT_NAME_ENDDATE_INPUT);
		assertEquals(predictor.getCommand(), COMMANDS.ADD);
		assertEquals(predictor.getCommandMsg(), ADD_MSG);
		assertEquals(predictor.getIndex(), -1);
		assertEquals(predictor.getTaskName(), "some Task");
		assertEquals(predictor.getTaskDetails(), null);
		assertEquals(predictor.getTaskStart(), null);
		assertEquals(predictor.getTaskEnd(), "30 November 2013 12:00 AM");
	}
	
	@Test
	public void testInsertWithNameEndStartDate() {
		Predictive predictor = new Predictive();
		predictor.processInput(INSERT_NAME_ENDDATE_STARTDATE_INPUT);
		assertEquals(predictor.getCommand(), COMMANDS.ADD);
		assertEquals(predictor.getCommandMsg(), ADD_MSG);
		assertEquals(predictor.getIndex(), -1);
		assertEquals(predictor.getTaskName(), "some Task");
		assertEquals(predictor.getTaskDetails(), null);
		assertEquals(predictor.getTaskStart(), "31 January 2012 12:00 AM");
		assertEquals(predictor.getTaskEnd(), "30 November 2013 12:00 AM");
	}
	
	@Test
	public void testAddWithNameStartEndDate() {
		Predictive predictor = new Predictive();
		predictor.processInput(ADD_NAME_STARTDATE_ENDDATE_INPUT);
		assertEquals(predictor.getCommand(), COMMANDS.ADD);
		assertEquals(predictor.getCommandMsg(), ADD_MSG);
		assertEquals(predictor.getIndex(), -1);
		assertEquals(predictor.getTaskName(), "task123");
		assertEquals(predictor.getTaskDetails(), null);
		assertEquals(predictor.getTaskStart(), "04 November 2013 09:30 AM");
		assertEquals(predictor.getTaskEnd(), "04 November 2015 11:59 PM");
	}
	
	@Test
	public void testAddWithNameStartEndDateInfo() {
		Predictive predictor = new Predictive();
		predictor.processInput(ADD_NAME_STARTDATE_ENDDATE_INFO_INPUT);
		assertEquals(predictor.getCommand(), COMMANDS.ADD);
		assertEquals(predictor.getCommandMsg(), ADD_MSG);
		assertEquals(predictor.getIndex(), -1);
		assertEquals(predictor.getTaskName(), "task123");
		assertEquals(predictor.getTaskDetails(), "this is some info");
		assertEquals(predictor.getTaskStart(), "10 November 2013 12:00 AM");
		assertEquals(predictor.getTaskEnd(), "30 November 2015 12:00 AM");
	}
	
	@Test
	public void editWithoutIndex() {
		Logic logic = initLogic();
		GuiCommand commandToGui = logic.predictCMD(EDIT_WITHOUT_INDEX_INPUT);
		assertEquals(commandToGui.getTitle(), EDIT_MSG);
		assertEquals(commandToGui.getTaskName(), null);
		assertEquals(commandToGui.getTaskDetails(), null);
		assertEquals(commandToGui.getTaskStart(), null);
		assertEquals(commandToGui.getTaskEnd(), null);
	}
	
	@Test
	public void changeWithoutIndex() {
		Logic logic = initLogic();
		GuiCommand commandToGui = logic.predictCMD(CHANGE_WITHOUT_INDEX_INPUT);
		assertEquals(commandToGui.getTitle(), EDIT_MSG);
		assertEquals(commandToGui.getTaskName(), null);
		assertEquals(commandToGui.getTaskDetails(), null);
		assertEquals(commandToGui.getTaskStart(), null);
		assertEquals(commandToGui.getTaskEnd(), null);
	}
	
	@Test
	public void editWithInvalidIndex() {
		Logic logic = initLogic();
		GuiCommand commandToGui = logic.predictCMD(EDIT_WITH_INVALID_INDEX_INPUT);
		assertEquals(commandToGui.getTitle(), null);
		assertEquals(commandToGui.getTaskName(), null);
		assertEquals(commandToGui.getTaskDetails(), null);
		assertEquals(commandToGui.getTaskStart(), null);
		assertEquals(commandToGui.getTaskEnd(), null);
	}
	
	@Test
	public void changeWithInvalidIndex() {
		Logic logic = initLogic();		
		GuiCommand commandToGui = logic.predictCMD(CHANGE_WITH_INVALID_INDEX_INPUT);
		assertEquals(commandToGui.getTitle(), null);
		assertEquals(commandToGui.getTaskName(), null);
		assertEquals(commandToGui.getTaskDetails(), null);
		assertEquals(commandToGui.getTaskStart(), null);
		assertEquals(commandToGui.getTaskEnd(), null);
	}
	
	@Test
	public void editWithNoChange() {
		Logic logic = initLogic();
		GuiCommand commandToGui = logic.predictCMD(EDIT_WITH_NOCHANGE_INPUT);
		assertEquals(commandToGui.getTitle(), EDIT_MSG);
		assertEquals(commandToGui.getTaskName(), "Task 1");
		assertEquals(commandToGui.getTaskDetails(), null);
		assertEquals(commandToGui.getTaskStart(), null);
		assertEquals(commandToGui.getTaskEnd(), null);
	}
	
	@Test
	public void changeNameOnly() {
		Logic logic = initLogic();
		GuiCommand commandToGui = logic.predictCMD(CHANGE_NAME_ONLY_INPUT);
		assertEquals(commandToGui.getTitle(), EDIT_MSG);
		assertEquals(commandToGui.getTaskName(), "hello");
		assertEquals(commandToGui.getTaskDetails(), null);
		assertEquals(commandToGui.getTaskStart(), null);
		assertEquals(commandToGui.getTaskEnd(), null);
	}
	
	@Test
	public void editNameEndDate() {
		Logic logic = initLogic();
		GuiCommand commandToGui = logic.predictCMD(EDIT_NAME_ENDDATE_INPUT);
		assertEquals(commandToGui.getTitle(), EDIT_MSG);
		assertEquals(commandToGui.getTaskName(), "bye");
		assertEquals(commandToGui.getTaskDetails(), "task 3 info...");
		assertEquals(commandToGui.getTaskStart(), "10 July 2018 12:00 AM");
		assertEquals(commandToGui.getTaskEnd(), "10 January 2099 12:00 AM");
	}
	
	@Test
	public void changeNameStartEndDate() {
		Logic logic = initLogic();
		GuiCommand commandToGui = logic.predictCMD(CHANGE_NAME_STARTDATE_ENDDATE_INPUT);
		assertEquals(commandToGui.getTitle(), EDIT_MSG);
		assertEquals(commandToGui.getTaskName(), "new name");
		assertEquals(commandToGui.getTaskDetails(), "task 3 info...");
		assertEquals(commandToGui.getTaskStart(), "15 November 2015 08:00 AM");
		assertEquals(commandToGui.getTaskEnd(), "15 November 2015 11:59 PM");
	}
	
	@Test
	public void editNameEndStartDate() {
		Logic logic = initLogic();
		GuiCommand commandToGui = logic.predictCMD(EDIT_NAME_ENDDATE_STARTDATE_INFO_INPUT);
		assertEquals(commandToGui.getTitle(), EDIT_MSG);
		assertEquals(commandToGui.getTaskName(), "new name");
		assertEquals(commandToGui.getTaskDetails(), "edited info for task 3...");
		assertEquals(commandToGui.getTaskStart(), "15 November 2015 08:00 AM");
		assertEquals(commandToGui.getTaskEnd(), "15 November 2015 11:59 PM");
	}
	
	@Test
	public void deleteWithoutIndex() {
		Logic logic = initLogic();
		GuiCommand commandToGui = logic.predictCMD(DELETE_WITHOUT_INDEX_INPUT);
		assertEquals(commandToGui.getTitle(), DELETE_MSG);
		assertEquals(commandToGui.getTaskName(), null);
		assertEquals(commandToGui.getTaskDetails(), null);
		assertEquals(commandToGui.getTaskStart(), null);
		assertEquals(commandToGui.getTaskEnd(), null);
	}
	
	@Test
	public void removeWithoutIndex() {
		Logic logic = initLogic();
		GuiCommand commandToGui = logic.predictCMD(REMOVE_WITHOUT_INDEX_INPUT);
		assertEquals(commandToGui.getTitle(), DELETE_MSG);
		assertEquals(commandToGui.getTaskName(), null);
		assertEquals(commandToGui.getTaskDetails(), null);
		assertEquals(commandToGui.getTaskStart(), null);
		assertEquals(commandToGui.getTaskEnd(), null);
	}
	
	@Test
	public void deleteWithInvalidIndex() {
		Logic logic = initLogic();
		GuiCommand commandToGui = logic.predictCMD(DELETE_WITH_INVALID_INDEX_INPUT);
		assertEquals(commandToGui.getTitle(), null);
		assertEquals(commandToGui.getTaskName(), null);
		assertEquals(commandToGui.getTaskDetails(), null);
		assertEquals(commandToGui.getTaskStart(), null);
		assertEquals(commandToGui.getTaskEnd(), null);
	}
	
	@Test
	public void removeWithInvalidIndex() {
		Logic logic = initLogic();
		GuiCommand commandToGui = logic.predictCMD(REMOVE_WITH_INVALID_INDEX_INPUT);
		assertEquals(commandToGui.getTitle(), null);
		assertEquals(commandToGui.getTaskName(), null);
		assertEquals(commandToGui.getTaskDetails(), null);
		assertEquals(commandToGui.getTaskStart(), null);
		assertEquals(commandToGui.getTaskEnd(), null);
	}
	
	@Test
	public void deleteWithValidIndex() {
		Logic logic = initLogic();
		GuiCommand commandToGui = logic.predictCMD(DELETE_WITH_VALID_INDEX_INPUT);
		assertEquals(commandToGui.getTitle(), DELETE_MSG);
		assertEquals(commandToGui.getTaskName(), "Task 2");
		assertEquals(commandToGui.getTaskDetails(), null);
		assertEquals(commandToGui.getTaskStart(), null);
		assertEquals(commandToGui.getTaskEnd(), "10 July 2018 12:00 AM");
	}
}
