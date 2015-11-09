//@@author A0121823R
package junitTests;

import static org.junit.Assert.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import logic.GuiCommand;
import logic.Logic;
import parser.Parser;
import storage.Task;
import utilities.COMMANDS;



public class Integrated {
	
	private static Date date;
	private static Date testD;
	private static Date testSD;
	private static Date testED;
	private static SimpleDateFormat dateVariant1 = new SimpleDateFormat("ddMMMMyyyy");
	private static SimpleDateFormat dateVariant2 = new SimpleDateFormat("ddMMyyyy");
	private static SimpleDateFormat dateVariant3 = new SimpleDateFormat("ddMMMMyyyy hhmm");
	private static SimpleDateFormat dateVariant4 = new SimpleDateFormat("ddMMyyyy hhmm");
	
	//*************testing add function
	@Test
	public void testAdd() {
		//input
		String inputAdd1 = "add Floating task 1";
		String inputAdd2 = "add Floating task 2 with details -i detail";
		String inputAdd3 = "add Spee-Do floating task -i -i in details";
		String inputAdd4 = "add First non floating task -d 31102015 2015 -i testing input details";
		String inputAdd5 = "add details input before date -i this is input details -d 11-11-2013 16:12";
//		String inputAdd6 = "add Task date format1 -d 20-3-2016";
//		String inputAdd7 = "add Start date End date -d 02/11/1993 12:10 03/11/1993 16:30";
//		String inputAdd8 = "add End date before start date -d 03/11/1993 10:10 02/11/1993 10:10";
		
		//output
		Task outputAdd1 = new Task("Floating task 1", null, null, null);
		Task outputAdd2 = new Task("Floating task 2 with details", "-i in details");
		Task outputAdd3 = new Task("Spee-Do floating task", "adding a task");
		try{
			testD = dateVariant4.parse("31102015 2015");
		} catch (Exception e){
			
		}
		Task outputAdd4 = new Task("First non floating task", "testing input detail", testD);
		try{
			testD = dateVariant4.parse("11112013 1612");
		} catch (Exception e){
			
		}
		Task outputAdd5 = new Task("details input before date", "this is input details", testD);
		try{
			testD = dateVariant2.parse("20032016");
		} catch (Exception e){
			
		}
		Task outputAdd6 = new Task("Task date format1", testD);
		try{
			Date testD = dateVariant4.parse("20032016");
		} catch (Exception e){
			
		}
		Task outputAdd7 = new Task("add Start date End date", "");
		Task outputAdd8 = new Task("Floating task 1", "");
		Task outputAdd9 = new Task("Floating task 1", "");
		
		try{
			date = dateVariant1.parse("02Jan2015");
		} catch (Exception e){
				
		}
		//test start
		Logic logic = new Logic(true);
		Parser parser = new Parser();
		
		//input 1*****
		parser.parse(inputAdd1);
		GuiCommand testOutput1 = logic.executeCMD(inputAdd1);
		COMMANDS c1 = parser.getCommand();
		String name1 = parser.getTaskName();
		String detail1 = parser.getDetails();		
		List<Task> L1 = testOutput1.getListOfTasks();
		
		//supposed outcome
		GuiCommand testReferee1 = new GuiCommand(COMMANDS.ADD, "Added \"Floating task 1\"", L1);
		COMMANDS cmdReferee1 = COMMANDS.ADD;
		String nameReferee1 = "Floating task 1";
		String detailReferee1 = null;
		
	
		//assert
		assertEquals(testOutput1.getCmd(), testReferee1.getCmd());
		System.out.println(testOutput1.getMsg());
		System.out.println(testReferee1.getMsg());
		assertEquals(testOutput1.getMsg(), testReferee1.getMsg());
		assertEquals(name1, nameReferee1);
		assertEquals(c1, cmdReferee1);
		assertEquals(detail1, detailReferee1);
		
		
		//input 2*****
		parser.parse(inputAdd2);
		GuiCommand testOutput2 = logic.executeCMD(inputAdd2);
		COMMANDS c2 = parser.getCommand();
		String name2 = parser.getTaskName();
		String detail2 = parser.getDetails();		
		List<Task> L2 = testOutput2.getListOfTasks();
		
		//supposed outcome
		GuiCommand testReferee2 = new GuiCommand(COMMANDS.ADD, "Added \"Floating task 2 with details\"", L2);
		COMMANDS cmdReferee2 = COMMANDS.ADD;
		String nameReferee2 = "Floating task 2 with details";
		String detailReferee2 = "details";
		
	
		//assert
		assertEquals(testOutput2.getCmd(), testReferee2.getCmd());
		System.out.println(testOutput2.getMsg());
		System.out.println(testReferee2.getMsg());
		assertEquals(testOutput2.getMsg(), testReferee2.getMsg());
		assertEquals(name2, nameReferee1);
		assertEquals(c2, cmdReferee1);
		assertEquals(detail2, detailReferee1);
		
		//input 3*****
		parser.parse(inputAdd3);
		GuiCommand testOutput3 = logic.executeCMD(inputAdd3);
		COMMANDS c3 = parser.getCommand();
		String name3 = parser.getTaskName();
		String detail3 = parser.getDetails();		
		List<Task> L3 = testOutput3.getListOfTasks();
		
		//supposed outcome
		GuiCommand testReferee3 = new GuiCommand(COMMANDS.ADD, "Added \"Spee-Do floating task\"", L3);
		COMMANDS cmdReferee3 = COMMANDS.ADD;
		String nameReferee3 = "Spee-Do floating task";
		String detailReferee3 = "-i in details";
		
	
		//assert
		assertEquals(testOutput3.getCmd(), testReferee3.getCmd());
		System.out.println(testOutput3.getMsg());
		System.out.println(testReferee3.getMsg());
		assertEquals(testOutput3.getMsg(), testReferee3.getMsg());
		assertEquals(name3, nameReferee3);
		assertEquals(c3, cmdReferee3);
		assertEquals(detail3, detailReferee3);
	}

	
//	@Test
//	public void testDelete() {
//		//*************testing delete function
//		String inputDelete1 = "delete 1";
//		String inputDelete2 = "delete 2";
//		String inputDelete3 = "delete 7";
//		String inputDelete4 = "delete 20";
//		
//		Task outputDelete1 = new Task("Buy eggs for mum",null, null, null);
//		//Task outputDelete2 = ;
//		//Task outputDelete3 = ;
	
//		//checking number of tasks left is one less than original
//		Logic logic = new Logic(true);
//		GuiCommand testOutput = logic.executeCMD(inputDelete1);
//		
//		//supposed outcome should return guiCommand for output
//		GuiCommand testReferee = new GuiCommand(COMMANDS.DELETE, "Deleted the task", outputDelete1);
//		
//		//assert
//		System.out.println(testOutput.getTask().getDetails());
//		System.out.println(testOutput.getMsg());
//		System.out.println(testOutput.getTask().getName());
//		System.out.println(testOutput.getTask().getFullEndDateString());
//		System.out.println(testOutput.getTask().getEndDateString());
//		System.out.println(testOutput.getTask().getEndTimeString());
//		assertEquals(testOutput.getCmd(), testReferee.getCmd());
//		assertEquals(testOutput.getMsg(), testReferee.getMsg());
//		assertEquals(testOutput.getTask().getName(), testReferee.getTask().getName());
//		assertEquals(testOutput.getTask().getDetails(), testReferee.getTask().getDetails());
//		assertEquals(testOutput.getTask().getEndDate(), testReferee.getTask().getEndDate());
//		assertEquals(testOutput.getTask().getStartDate(), testReferee.getTask().getStartDate());
//	}
//	
//	
//	//*******************testing search function
//	
//	
//	@Test
//	public void testSearch(){
//		//check if the searched task list length is coherent
//		String inputDelete1 = "delete 1";
//		
//	}
}





/* Current Issues:
 * Adding:
 * 	if "details" is in the title, it will be converted to "s"
 * 
 * 
 * 
 * 
 */
