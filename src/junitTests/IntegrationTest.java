package junitTests;

import processor.COMMANDS;
import speedo.GuiCommand;
import speedo.Logic;
import storage.Task;

public class IntegrationTest {
	//testing all components in the software.
	//user interacts with the components from MainApp.handleUserCommand
	

	
	public static void main(String args[]){
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
		
		//test start
		Logic logic = new Logic();
		GuiCommand testOutput = logic.executeCMD(inputAdd1);
		
		//supposed outcome should return guiCommand for output
		Task refereeTask = new Task();
		GuiCommand testReferee = new GuiCommand(COMMANDS.INVALID, "Invalid");
		
		System.out.println("Add testOutput: " + testOutput.getTask().getName() + testOutput.getTask().getDetails() + testOutput.getTask().getStartDateString() + testOutput.getTask().getEndDateString());
		System.out.println("Add testReferee: " + testReferee);
		
		
	}

	
	
	
	
	
	//*************testing delete function
}
