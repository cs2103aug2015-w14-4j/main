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



public class IntegrationTest {
	private static SimpleDateFormat dateVariant2 = new SimpleDateFormat("ddMMyyyy");
	private static SimpleDateFormat dateVariant4 = new SimpleDateFormat("ddMMyyyy hhmm");
	
	
	//The following tests will be testing the functionality of both logic and parser functions to ensure
	//Both components are working under the sasme input
	
	//*************testing add function
	@Test
	public void testAdd() {
		//a string of user inputs for adding functions
		String inputAdd1 = "add Floating task 1";
		String inputAdd2 = "add Floating task 2 with details -i details";
		String inputAdd3 = "add Spee-Do floating task -i -i in details";
		String inputAdd4 = "add First non floating task -d 31102015 2015 -i testing input details";
		
		//creating new Logic and Parser class objects for testing
		Logic logic = new Logic(true);
		Parser parser = new Parser();
		
		//executing string input 1*****
		parser.parse(inputAdd1);
		GuiCommand testOutput1 = logic.executeCMD(inputAdd1);
		COMMANDS cmd1 = parser.getCommand();
		String name1 = parser.getTaskName();
		String detail1 = parser.getDetails();		
		List<Task> L1 = testOutput1.getListOfTasks();
		
		//creating a GuiCommand and relevant information types to compare
		// with Parser and Logic
		GuiCommand testReferee1 = new GuiCommand(COMMANDS.ADD, "Added \"Floating task 1\"", L1);
		COMMANDS cmdReferee1 = COMMANDS.ADD;
		String nameReferee1 = "Floating task 1";
		String detailReferee1 = null;
		
	
		//Comparing the generated supped information with the generated information from the
		//execution of the logic and parser
		assertEquals(testOutput1.getCmd(), testReferee1.getCmd());
		assertEquals(testOutput1.getMsg(), testReferee1.getMsg());
		assertEquals(name1, nameReferee1);
		assertEquals(cmd1, cmdReferee1);
		assertEquals(detail1, detailReferee1);
		
		
		//Logic and Parser now evaluates user input 2****
		parser.parse(inputAdd2);
		GuiCommand testOutput2 = logic.executeCMD(inputAdd2);
		COMMANDS cmd2 = parser.getCommand();
		String name2 = parser.getTaskName();
		String detail2 = parser.getDetails();		
		List<Task> list2 = testOutput2.getListOfTasks();
		
		//supposed output test information from user input 2
		GuiCommand testReferee2 = new GuiCommand(COMMANDS.ADD, "Added \"Floating task 2 with details\"", list2);
		String nameReferee2 = "Floating task 2 with details";
		String detailReferee2 = "details";
		
	
		//Comparing the information types between supposed output test and logic and parser
		// generated test cases
		assertEquals(testOutput2.getCmd(), testReferee2.getCmd());
		assertEquals(testOutput2.getMsg(), testReferee2.getMsg());
		assertEquals(name2, nameReferee2);
		assertEquals(cmd2, cmdReferee1);
		assertEquals(detail2, detailReferee2);
		
		//Logic and Parser now evaluates user input 3*****
		parser.parse(inputAdd3);
		GuiCommand testOutput3 = logic.executeCMD(inputAdd3);
		COMMANDS cmd3 = parser.getCommand();
		String name3 = parser.getTaskName();
		String detail3 = parser.getDetails();		
		List<Task> list3 = testOutput3.getListOfTasks();
		
		//supposed output test information from user input 3
		GuiCommand testReferee3 = new GuiCommand(COMMANDS.ADD, "Added \"Spee-Do floating task\"", list3);
		COMMANDS cmdReferee3 = COMMANDS.ADD;
		String nameReferee3 = "Spee-Do floating task";
		String detailReferee3 = "-i in details";
		
	
		//comparing the information types with supposed output results
		assertEquals(testOutput3.getCmd(), testReferee3.getCmd());
		assertEquals(testOutput3.getMsg(), testReferee3.getMsg());
		assertEquals(name3, nameReferee3);
		assertEquals(cmd3, cmdReferee3);
		assertEquals(detail3, detailReferee3);
		
		
		//Logic and parser evaluates input 4*****
		//gets the relevant information such as GuiCommand object
		// date, details, and task lists
		parser.parse(inputAdd4);
		GuiCommand testOutput4 = logic.executeCMD(inputAdd4);
		COMMANDS cmd4 = parser.getCommand();
		String name4 = parser.getTaskName();
		String detail4 = parser.getDetails();
		Date date4 = parser.getEndDate();
		List<Task> list4 = testOutput4.getListOfTasks();
		
		//created supposed outcome from test input 4
		GuiCommand testReferee4 = new GuiCommand(COMMANDS.ADD, "Added \"First non floating task\"", list4);
		COMMANDS cmdReferee4 = COMMANDS.ADD;
		String nameReferee4 = "First non floating task";
		String detailReferee4 = "testing input details";
		Date dateReferee4 = null;
		try{
			dateReferee4 = dateVariant4.parse("31102015 2015");
		} catch (Exception e){
			
		}
		
	
		//comparing the information types with supposed output results
		assertEquals(testOutput4.getCmd(), testReferee4.getCmd());
		assertEquals(testOutput4.getMsg(), testReferee4.getMsg());
		assertEquals(name4, nameReferee4);
		assertEquals(cmd4, cmdReferee4);
		System.out.println(detail4);
		System.out.println(detailReferee4);
		assertEquals(detail4, detailReferee4);
		assertEquals(date4, dateReferee4);
	}
	
	
	//integration testing begins from the logic component and will test through
	//the storage component and file handler component.
	//at the same time, utilises the parser component to break down inputs
	//by begining the testing from the logic component and checking with the task list,
	// we will be able to test
	@Test
	public void testIntegration(){
		Logic logic = new Logic(true);
		
		//input test cases to be tested from the logic component
		String input1 = "add First New task -i new task details -d 02Jan2016";
		String input2 = "add Second new task to be added in -d 30112015 2030 02122015 0530 -i task has both start and end date";
		String input3 = "edit 2 Third task replacing first task";
		String input4 = "delete 1";
		String input5 = "undo";
		
		//task list is read from the json file
		//hence by checking the task list in the GuiCommand, integration test can be conducted
		
		
		
		//testing input 1 and 2 ******************
		//size of task list recorded
		//task also retrieved to compare the informations of the task objects
		//to ensure that storage component correctly stores the task information
		GuiCommand Output1 = logic.executeCMD(input1);
		List<Task> List1 = Output1.getListOfTasks();
		int size1 = List1.size();
		Task task1 = List1.get(0);
		
		GuiCommand Output2 = logic.executeCMD(input2);
		List<Task> List2 = Output2.getListOfTasks();
		int size2 = List2.size();
		Task task2 = List1.get(0);
		
	
		//comparing the size of list in file handler after every command
		//the number of completed and uncompleted tasks should be coherent
		assertEquals(size1, 1);
		assertEquals(size2, 2);

		//creating supposed task information to compare with actual tasks in the list
		String nameReferee1 = "First New task";
		String detailsReferee1 = "new task details";
		Date dateReferee1 = null;
		try{
			dateReferee1 = dateVariant2.parse("02012016");
		} catch (Exception e){
			
		}
		
		String nameReferee2 = "Second new task to be added in";
		String detailsReferee2 = "task has both start and end date";
		Date startDateReferee2 = null;
		Date endDateReferee2 = null;
		try{
			startDateReferee2 = dateVariant4.parse("30112015 2030");
			endDateReferee2 = dateVariant4.parse("02122015 0530");
		} catch (Exception e){
			
		}
		
		//comparing the supposed task information with the actual task information
		//comparing output from input1 test case with manual created information
		assertEquals(task1.getName(), nameReferee1);
		assertEquals(task1.getDetails(), detailsReferee1);
		assertEquals(task1.getEndDate(), dateReferee1);
	
		//comparing input 2
		assertEquals(task2.getName(), nameReferee2);
		assertEquals(task2.getDetails(), detailsReferee2);
		assertEquals(task2.getStartDate(), startDateReferee2);
		assertEquals(task2.getEndDate(), endDateReferee2);
		
		
		
		//testing input 3*******************
		GuiCommand Output3 = logic.executeCMD(input3);
		List<Task> List3 = Output3.getListOfTasks();
		Task task3 = List3.get(1);
		int size3 = List3.size();
		
		//compare size of list of tasks. For this case, it should be the same
		assertEquals(size3, 2);
		
		//creating supposed task information to compare with actual tasks in the list
		String nameReferee3 = "Third task replacing first task";
		String detailsReferee3 = "new task details";
		Date dateReferee3 = null;
		try{
			dateReferee3 = dateVariant2.parse("02012016");
		} catch (Exception e){
			
		}
		//comparing input 3
		assertEquals(task3.getName(), nameReferee3);
		assertEquals(task3.getDetails(), detailsReferee3);
		assertEquals(task3.getEndDate(), dateReferee3);
				
		
		
		//testing input 4 ****************************
		GuiCommand Output4 = logic.executeCMD(input4);
		List<Task> List4 = Output4.getListOfTasks();
		Task task4 = List4.get(0);
		int size4 = List4.size();
		
		//compare size of list of tasks. For this case, it should be the one lesser than previously
		assertEquals(size4, 1);
		
		//creating supposed task information to compare with actual tasks in the list
		String nameReferee4 = "Third task replacing first task";
		String detailsReferee4 = "new task details";
		Date dateReferee4 = null;
		try{
			dateReferee4 = dateVariant2.parse("02012016");
		} catch (Exception e){
			
		}
		//comparing input 4
		assertEquals(task4.getName(), nameReferee4);
		assertEquals(task4.getDetails(), detailsReferee4);
		assertEquals(task4.getEndDate(), dateReferee4);
		
		
		
		//testing input 5*****************
		GuiCommand Output5 = logic.executeCMD(input5);
		List<Task> List5 = Output5.getListOfTasks();
		Task task5 = List5.get(1);
		
		//compare size of list of tasks. For this case, it should be the same
		assertEquals(size3, 2);
		
		//creating supposed task information to compare with actual tasks in the list
		String nameReferee5 = "Third task replacing first task";
		String detailsReferee5 = "new task details";
		Date dateReferee5 = null;
		try{
			dateReferee5 = dateVariant2.parse("02012016");
		} catch (Exception e){
			
		}
		//comparing input 5
		assertEquals(task5.getName(), nameReferee5);
		assertEquals(task5.getDetails(), detailsReferee5);
		assertEquals(task5.getEndDate(), dateReferee5);
	}
	
	//testing for the list of completed tasks
	@Test
	public void completedTaskIntegrationTest(){
		Logic logic = new Logic(true);
		
		//input test cases to be tested from the logic component
		//first 3 user input test cases adds 3 different tasks into the the tasks list
		//next 3 user input will test the acknowledgement of the task
		//when the task is acknowledged, the size of uncompleted task list --
		//while size of completed task list will ++
		String input1 = "add First New task -i new task details -d 02Jan2016";
		String input2 = "add Second new task -d 30112016 2030 02122016 0530 -i task has both start and end date";
		String input3 = "add Third new task to the list";
		String input4 = "ack 1";
		String input5 = "ack 1";
		String input6 = "ack 1";
		
		//adding new tasks to be acknowledged by executing input 1 - 3
		logic.executeCMD(input1);
		logic.executeCMD(input2);
		logic.executeCMD(input3);
		
		//Saving task list of uncompleted task list
		List<Task> uncompletedTasks = logic.getTaskList();
		
		//checking size of uncompleted list
		assertTrue(uncompletedTasks.size() == 3);
		
		
		//***** input 4 ***************
		//acknowledging the first task and getting completed task list
		logic.executeCMD(input4);
		GuiCommand complete = logic.executeCMD("completed");
		List<Task> completedTasks1 = complete.getListOfTasks(); 
		
		//comparing the size of list in file handler after every command
		//the number of completed and uncompleted tasks should be coherent
		assertTrue(uncompletedTasks.size() == 2);
		assertTrue(completedTasks1.size() == 1);
		
		
		//***** input 5 ***************
		//acknowledging the first task and getting completed task list
		logic.executeCMD(input5);
		List<Task> completedTasks2 = complete.getListOfTasks(); 
		
		//checking size of completed and uncompleted task lists
		assertTrue(uncompletedTasks.size() == 1);
		assertTrue(completedTasks2.size() == 2);
		
		
		//***** input 6 ***************
		//acknowledging the first task and getting completed task list
		logic.executeCMD(input6);
		List<Task> completedTasks3 = complete.getListOfTasks(); 
		
		//checking size of completed and uncompleted task lists
		assertTrue(uncompletedTasks.size() == 0);
		assertTrue(completedTasks3.size() == 3);
		
	}
	
}





/* Current Issues:
 * 
 * 
 * 
 * 
 * 
 * 
 */
