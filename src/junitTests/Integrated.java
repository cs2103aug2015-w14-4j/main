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
	
	@Test
	public void testAdd() {
		//test start
		Logic logic = new Logic(true);
		GuiCommand testOutput = logic.executeCMD(inputAdd1);
		
		//supposed outcome should return guiCommand for output
		Task refereeTask = new Task();
		GuiCommand testReferee = new GuiCommand(COMMANDS.INVALID, "Invalid");
	
		//assert
		assertEquals(testOutput, testReferee);
	}
	
	
	//*************testing delete function


}
