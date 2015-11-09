//@@author A0121823R
package junitTests;

import static org.junit.Assert.*;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import logic.GuiCommand;
import logic.Logic;
import storage.Task;
import utilities.COMMANDS;

public class LogicTest {
	//contructor of logic is set to true to avoid test caeses adding into actual json task file
	Logic logic = new Logic(true);
	
	//*************************Testing Add****************************************************
	@Test
	public void testAdd() {
		//generating String test cases to be used as input
		String inputAdd1 = "add Floating task 1";
		String inputAdd2 = "add Floating task 2 with details -i detail";
		String inputAdd3 = "add Spee-Do floating task -i -i in details";
		String inputAdd4 = "add First non floating task -d 31102015 2015 -i testing input details";
		String inputAdd5 = "add details input before date -i this is input details -d 11-11-2013 16:12";
		String inputAdd6 = "add Task date format1 -d 20-3-2016";
		String inputAdd7 = "add Start date End date -d 02/11/1993 12:10 03/11/1993 16:30";
		String inputAdd8 = "add End date before start date -d 03/11/1993 10:10 02/11/1993 10:10";
		String inputAdd9 = "add -i details withough task name";
		
		/*GuiCommand object is returned after the add user input
		  is keyed into the logic.execute method
		  the list of tasks added is also kept in track
		*/
		GuiCommand outAdd1 = logic.executeCMD(inputAdd1);
		List<Task> listAdd1 = outAdd1.getListOfTasks();
		
		GuiCommand outAdd2 = logic.executeCMD(inputAdd2);
		List<Task> listAdd2 = outAdd2.getListOfTasks();
		
		GuiCommand outAdd3 = logic.executeCMD(inputAdd3);
		List<Task> listAdd3 = outAdd3.getListOfTasks();
		
		GuiCommand outAdd4 = logic.executeCMD(inputAdd4);
		List<Task> listAdd4 = outAdd4.getListOfTasks();
		
		GuiCommand outAdd5 = logic.executeCMD(inputAdd5);
		List<Task> listAdd5 = outAdd5.getListOfTasks();
		
		GuiCommand outAdd6 = logic.executeCMD(inputAdd6);
		List<Task> listAdd6 = outAdd6.getListOfTasks();
		
		GuiCommand outAdd7 = logic.executeCMD(inputAdd7);
		List<Task> listAdd7 = outAdd7.getListOfTasks();
		
		GuiCommand outAdd8 = logic.executeCMD(inputAdd8);
		List<Task> listAdd8 = outAdd8.getListOfTasks();
		
		GuiCommand outAdd9 = logic.executeCMD(inputAdd9);
		List<Task> listAdd9 = outAdd9.getListOfTasks();
		
		//Generating supposed test outputs to compare with the output tests
		//In this case, GuiCommand object is created to compare the informations in the object
		//All inputs should be added successfully
		GuiCommand testAdd1 = new GuiCommand(COMMANDS.ADD, "Added \"Floating task 1\"", listAdd1);
		GuiCommand testAdd2 = new GuiCommand(COMMANDS.ADD, "Added \"Floating task 2 with details\"", listAdd2);
		GuiCommand testAdd3 = new GuiCommand(COMMANDS.ADD, "Added \"Spee-Do floating task\"", listAdd3);
		GuiCommand testAdd4 = new GuiCommand(COMMANDS.ADD, "Added \"First non floating task\"", listAdd4);
		GuiCommand testAdd5 = new GuiCommand(COMMANDS.ADD, "Added \"details input before date\"", listAdd5);
		GuiCommand testAdd6 = new GuiCommand(COMMANDS.ADD, "Added \"Task date format1\"", listAdd6);
		GuiCommand testAdd7 = new GuiCommand(COMMANDS.ADD, "Added \"Start date End date\"", listAdd7);
		GuiCommand testAdd8 = new GuiCommand(COMMANDS.ADD, "Added \"End date before start date\"", listAdd8);
		GuiCommand testAdd9 = new GuiCommand(COMMANDS.INVALID, "Invalid command, check help for proper command usage");
		
		
		//comparing test outputs with supposed test outputs
		//comparing a the COMMANDS type and String message stored in the GuiCommand object
		assertEquals(outAdd1.getCmd(), testAdd1.getCmd());
		assertEquals(outAdd1.getMsg(), testAdd1.getMsg());
		
		assertEquals(outAdd2.getCmd(), testAdd2.getCmd());
		assertEquals(outAdd2.getMsg(), testAdd2.getMsg());
		
		
		assertEquals(outAdd3.getCmd(), testAdd3.getCmd());
		assertEquals(outAdd3.getMsg(), testAdd3.getMsg());
		
		assertEquals(outAdd4.getCmd(), testAdd4.getCmd());
		assertEquals(outAdd4.getMsg(), testAdd4.getMsg());
		
		assertEquals(outAdd5.getCmd(), testAdd5.getCmd());
		assertEquals(outAdd5.getMsg(), testAdd5.getMsg());
		
		assertEquals(outAdd6.getCmd(), testAdd6.getCmd());
		assertEquals(outAdd6.getMsg(), testAdd6.getMsg());
		
		assertEquals(outAdd7.getCmd(), testAdd7.getCmd());
		assertEquals(outAdd7.getMsg(), testAdd7.getMsg());
		
		assertEquals(outAdd8.getCmd(), testAdd8.getCmd());
		assertEquals(outAdd8.getMsg(), testAdd8.getMsg());
		
		assertEquals(outAdd9.getCmd(), testAdd9.getCmd());
		assertEquals(outAdd9.getMsg(), testAdd9.getMsg());
	}
	
	//*************************Testing Delete****************************************************
	
	
	@Test
	public void testDelete() {
		//creating inputs to generate a list of tasks
		String inputAdd1 = "add Floating task 1";
		String inputAdd2 = "add Floating task 2 with details -i detail";
		String inputAdd3 = "add Spee-Do floating task -i -i in details";
		String inputAdd4 = "add First non floating task -d 31102015 2015 -i testing input details";
		String inputAdd5 = "add details input before date -i this is input details -d 11-11-2013 16:12";
		String inputAdd6 = "add Task date format1 -d 20-3-2016";
		String inputAdd7 = "add Start date End date -d 02/11/1993 12:10 03/11/1993 16:30";
		String inputAdd8 = "add End date before start date -d 03/11/1993 10:10 02/11/1993 10:10";
		String inputAdd9 = "add -i details withough task name";
		
		//added task first into the task lists in storage
		logic.executeCMD(inputAdd1);
		logic.executeCMD(inputAdd2);
		logic.executeCMD(inputAdd3);
		logic.executeCMD(inputAdd4);
		logic.executeCMD(inputAdd5);
		logic.executeCMD(inputAdd6);
		logic.executeCMD(inputAdd7);
		logic.executeCMD(inputAdd8);
		logic.executeCMD(inputAdd9);
		
		//String of user input to delete a task
		String inputDel = "delete 1";
		//Task list now should have 8 items
		
		
		//generating output from test cases and also checking remaining size of task list
		GuiCommand outDel1 = logic.executeCMD(inputDel);
		int listDelete1 = logic.getTaskList().size();
		
		logic.executeCMD(inputDel);
		int listDelete2 = logic.getTaskList().size();
		
		logic.executeCMD(inputDel);
		int listDelete3 = logic.getTaskList().size();
		
		logic.executeCMD(inputDel);
		int listDelete4 = logic.getTaskList().size();
		
		logic.executeCMD(inputDel);
		int listDelete5 = logic.getTaskList().size();
	
		logic.executeCMD(inputDel);	
		int listDelete6 = logic.getTaskList().size();
		
		logic.executeCMD(inputDel);
		int listDelete7 = logic.getTaskList().size();
		
		logic.executeCMD(inputDel);
		int listDelete8 = logic.getTaskList().size();
		
		logic.executeCMD(inputDel);
		int listDelete9 = logic.getTaskList().size();
		
		
		//checking list size
		//the size of remaining tasks should keep decreasing
		//as each delete will delete a single task from the list
		assertEquals(listDelete1, 8);
		assertEquals(listDelete2, 7);
		assertEquals(listDelete3, 6);
		assertEquals(listDelete4, 5);
		assertEquals(listDelete5, 4);
		assertEquals(listDelete6, 3);
		assertEquals(listDelete7, 2);
		assertEquals(listDelete8, 1);
		assertEquals(listDelete9, 0);
	}
	
	
	//*************************Testing Edit****************************************************
	@Test
	public void testEdit(){
		//a list of user inputs to be keyed into logic and adding the tasks
		String Add1 = "add Floating task 1";
		String Add2 = "add Floating task 2 with details -i detail";
		String Add3 = "add Spee-Do floating task -i -i in details";
		String Add4 = "add First non floating task -d 31102015 2015 -i testing input details";
		String Add5 = "add details input before date -i this is input details -d 11-11-2013 16:12";
		String Add6 = "add Task date format1 -d 20-3-2016";
		String Add7 = "add Start date End date -d 02/11/1993 12:10 03/11/1993 16:30";
		String Add8 = "add End date before start date -d 03/11/1993 10:10 02/11/1993 10:10";
		
		//executing the user input Strings above to create a new list of tasks
		logic.executeCMD(Add1);
		logic.executeCMD(Add2);
		logic.executeCMD(Add3);
		logic.executeCMD(Add4);
		logic.executeCMD(Add5);
		logic.executeCMD(Add6);
		logic.executeCMD(Add7);
		logic.executeCMD(Add8);
		
		//a list of user input to edit current tasks in the list
		String inputEdit1 = "edit 1 -i added details -d 10112015 2000";
		String inputEdit2 = "edit 1 New Name";
		String inputEdit3 = "edit 2 Changed name -d Hi";
		String inputEdit4 = "edit 4";
		
		//executing the user input and storing the GuiCommand object to refer to
		//its message and commands after for testing
		GuiCommand editOut1 = logic.executeCMD(inputEdit1);
		List<Task> LE1 = editOut1.getListOfTasks();
		GuiCommand editOut2 = logic.executeCMD(inputEdit2);
		List<Task> LE2 = editOut1.getListOfTasks();
		GuiCommand editOut3 = logic.executeCMD(inputEdit3);
		List<Task> LE3 = editOut1.getListOfTasks();
		GuiCommand editOut4 = logic.executeCMD(inputEdit4);
		
		//creating supposed GuiCommand objects to compare with the actual tested GuiCommand objects
		GuiCommand testEdit1 = new GuiCommand(COMMANDS.EDIT, "Edited \"Floating task 1\"", LE1);
		GuiCommand testEdit2 = new GuiCommand(COMMANDS.EDIT, "Edited \"Floating task 2 with details\"", LE2);
		GuiCommand testEdit3 = new GuiCommand(COMMANDS.EDIT, "Edited \"Spee-Do floating task\"", LE3);
		GuiCommand testEdit4 = new GuiCommand(COMMANDS.INVALID, "Task not edited");
		
		//for each command input and GuiCommand returned
		//, we will compare the object's COMMANDS type and message String
		assertEquals(editOut1.getCmd(), testEdit1.getCmd());
		assertEquals(editOut1.getMsg(), testEdit1.getMsg());
		
		assertEquals(editOut2.getCmd(), testEdit2.getCmd());
		assertEquals(editOut2.getMsg(), testEdit2.getMsg());
		
		assertEquals(editOut3.getCmd(), testEdit3.getCmd());
		assertEquals(editOut3.getMsg(), testEdit3.getMsg());
		
		assertEquals(editOut4.getCmd(), testEdit4.getCmd());
		assertEquals(editOut4.getMsg(), testEdit4.getMsg());
	}
	
	@Test
	public void testSearch(){
		//adding tasks to task list for searching simulation
		String Add1 = "add Floating task 1";
		String Add2 = "add Floating task 2 with details -i detail";
		String Add3 = "add Spee-Do floating task -i -i in details";
		String Add4 = "add First non floating task -d 31102015 2015 -i testing input details";
		String Add5 = "add details input before date -i this is input details -d 11-11-2013 16:12";
		String Add6 = "add Task date format1 -d 20-3-2016";
		String Add7 = "add Start date End date -d 02/11/1993 12:10 03/11/1993 16:30";
		String Add8 = "add End date before start date -d 03/11/1993 10:10 02/11/1993 10:10";
		
		//adding the user inputs to generate the list of tasks
		logic.executeCMD(Add1);
		logic.executeCMD(Add2);
		logic.executeCMD(Add3);
		logic.executeCMD(Add4);
		logic.executeCMD(Add5);
		logic.executeCMD(Add6);
		logic.executeCMD(Add7);
		logic.executeCMD(Add8);
		
		//search input test cases
		String inputSearch1 = "search task";
		String inputSearch2 = "search date";
		String inputSearch3 = "search details";
		
		//executing the search user input test cases above to return a GuiCommand
		//by getting the list of tasks stored in the GuiCommand object, we can
		//manually compare the amount of searched task items it should contain
		//in the searched task list
		GuiCommand output1 = logic.executeCMD(inputSearch1);
		int size1 = output1.getListOfTasks().size();
		GuiCommand output2 = logic.executeCMD(inputSearch2);
		int size2 = output2.getListOfTasks().size();
		GuiCommand output3 = logic.executeCMD(inputSearch3);
		int size3 = output3.getListOfTasks().size();
		
		//checking the search accuracy by comparing with number of tasks searched
		assertEquals(size1, 5);
		assertEquals(size2, 4);
		assertEquals(size3, 4);
	}
}

/*
Current Issues:

*/