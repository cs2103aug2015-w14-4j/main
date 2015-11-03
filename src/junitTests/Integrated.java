package junitTests;

import static org.junit.Assert.*;

import org.junit.Test;

import processor.COMMANDS;
import speedo.GuiCommand;
import speedo.Logic;
import storage.Task;

public class Integrated {
	
	//*************testing add function
	String inputAdd1 = "add Floating task 1";
	String inputAdd2 = "add Floating task 2";
	String inputAdd3 = "add Spee-Do floating task -t adding a task";
	String inputAdd4 = "add First non floating task -d 31102015 2015 -t testing input details";
	String inputAdd5 = "add Non floating task with details input before date -t this is input details -d 11-11-2013 16:12";
	String inputAdd6 = "add Task date format1 -d 20-3-2016 23:20";
	String inputAdd7 = "add Start date End date -d 02/11/1993 12:10 03/11/1993 16:30";
	String inputAdd8 = "add End date before start date -d 03/11/1993 10:10 02/11/1993 10:10";
	String inputAdd9 = "add Testing  dd-M-yyyy hh:mm dd-M-yyyy hh:mm";
	
	Task outputAdd1 = new Task("Floating task 1", null);
	Task outputAdd2 = new Task("Floating task 1", "");
	Task outputAdd3 = new Task("Floating task 1", "");
	Task outputAdd4 = new Task("Floating task 1", "");
	Task outputAdd5 = new Task("Floating task 1", "");
	Task outputAdd6 = new Task("Floating task 1", "");
	Task outputAdd7 = new Task("Floating task 1", "");
	Task outputAdd8 = new Task("Floating task 1", "");
	Task outputAdd9 = new Task("Floating task 1", "");
	
	@Test
	public void testAdd() {
		//test start
		Logic logic = new Logic(true);
		GuiCommand testOutput = logic.executeCMD(inputAdd1);
		
		//supposed outcome should return guiCommand for output
		GuiCommand testReferee = new GuiCommand(COMMANDS.ADD, "Added " + outputAdd1.getName(), outputAdd1);
	
		//assert
		assertEquals(testOutput.getCmd(), testReferee.getCmd());
		assertEquals(testOutput.getMsg(), testReferee.getMsg());
		assertEquals(testOutput.getTask().getName(), testReferee.getTask().getName());
		assertEquals(testOutput.getTask().getDetails(), testReferee.getTask().getDetails());
		assertEquals(testOutput.getTask().getEndDate(), testReferee.getTask().getEndDate());
		assertEquals(testOutput.getTask().getStartDate(), testReferee.getTask().getStartDate());
	}
	
	
	//*************testing delete function


}
