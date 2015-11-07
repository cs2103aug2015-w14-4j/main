//@@author A0121823R
package junitTests;

import static org.junit.Assert.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Test;
import logic.GuiCommand;
import logic.Logic;
import processor.COMMANDS;
import storage.Task;



public class Integrated {
	
	static Date date;
	static Date testD;
	static Date testSD;
	static Date testED;
	static SimpleDateFormat dateVariant1 = new SimpleDateFormat("ddMMMMyyyy");
	static SimpleDateFormat dateVariant2 = new SimpleDateFormat("ddMMyyyy");
	static SimpleDateFormat dateVariant3 = new SimpleDateFormat("ddMMMMyyyy hhmm");
	static SimpleDateFormat dateVariant4 = new SimpleDateFormat("ddMMyyyy hhmm");
	
	
	//*************testing add function
	//input
	String inputAdd1 = "add Floating task 1";
	String inputAdd2 = "add Floating task 2 with details -i detail";
	String inputAdd3 = "add Spee-Do floating task -i -i in details";
	String inputAdd4 = "add First non floating task -d 31102015 2015 -i testing input details";
	String inputAdd5 = "add details input before date -i this is input details -d 11-11-2013 16:12";
	String inputAdd6 = "add Task date format1 -d 20-3-2016";
	String inputAdd7 = "add Start date End date -d 02/11/1993 12:10 03/11/1993 16:30";
	String inputAdd8 = "add End date before start date -d 03/11/1993 10:10 02/11/1993 10:10";

	@Test
	public void testAdd() {
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
			Date testSD = dateVariant4.parse("20032016");
			Date testED = ;
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
		GuiCommand testOutput = logic.executeCMD(inputAdd4);
		
		//supposed outcome should return guiCommand for output
		GuiCommand testReferee = new GuiCommand(COMMANDS.ADD, "Added " + outputAdd4.getName(), outputAdd4);
	
		//assert
		System.out.println("testOutput: " + testOutput.getTask());
		System.out.println("testReferee: " + testReferee.getTask());
		assertEquals(testOutput.getCmd(), testReferee.getCmd());
		assertEquals(testOutput.getMsg(), testReferee.getMsg());
		assertEquals(testOutput.getTask().getName(), testReferee.getTask().getName());
		assertEquals(testOutput.getTask().getDetails(), testReferee.getTask().getDetails());
		assertEquals(testOutput.getTask().getEndDate(), testReferee.getTask().getEndDate());
		assertEquals(testOutput.getTask().getStartDate(), testReferee.getTask().getStartDate());
	}
	
	
	//*************testing delete function
	String inputDelete1 = "delete 1";
	String inputDelete2 = "delete 2";
	String inputDelete3 = "delete 7";
	String inputDelete4 = "delete 20";
	
	Task outputDelete1 = new Task("Buy eggs for mum",null, null, null);
	//Task outputDelete2 = ;
	//Task outputDelete3 = ;
	
//	@Test
//	public void testDelete() {
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
 * 	years must be 4 characters
 * 
 * 
 * 
 * 
 */
