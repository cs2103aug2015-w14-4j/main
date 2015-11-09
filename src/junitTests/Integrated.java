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
	
	
//	//The following tests will be testing the functionality of both logic and parser functions to ensure
//	//Both components are working under the sasme input
//	
//	//*************testing add function
//	@Test
//	public void testAdd() {
//		//input
//		String inputAdd1 = "add Floating task 1";
//		String inputAdd2 = "add Floating task 2 with details -i details";
//		String inputAdd3 = "add Spee-Do floating task -i -i in details";
//		String inputAdd4 = "add First non floating task -d 31102015 2015 -i testing input details";
//		String inputAdd5 = "add Start date End date -d 02/11/1993 12:10 03/11/1993 16:30";
//		String inputAdd6 = "add End date before start date -d 03/11/1993 10:10 02/11/1993 10:10";
//		
//		//output
//		
//		try{
//			date = dateVariant1.parse("02Jan2015");
//		} catch (Exception e){
//				
//		}
//		//test start
//		Logic logic = new Logic(true);
//		Parser parser = new Parser();
//		
//		//input 1*****
//		parser.parse(inputAdd1);
//		GuiCommand testOutput1 = logic.executeCMD(inputAdd1);
//		COMMANDS cmd1 = parser.getCommand();
//		String name1 = parser.getTaskName();
//		String detail1 = parser.getDetails();		
//		List<Task> L1 = testOutput1.getListOfTasks();
//		
//		//supposed outcome
//		GuiCommand testReferee1 = new GuiCommand(COMMANDS.ADD, "Added \"Floating task 1\"", L1);
//		COMMANDS cmdReferee1 = COMMANDS.ADD;
//		String nameReferee1 = "Floating task 1";
//		String detailReferee1 = null;
//		
//	
//		//assert
//		assertEquals(testOutput1.getCmd(), testReferee1.getCmd());
//		System.out.println(testOutput1.getMsg());
//		System.out.println(testReferee1.getMsg());
//		assertEquals(testOutput1.getMsg(), testReferee1.getMsg());
//		assertEquals(name1, nameReferee1);
//		assertEquals(cmd1, cmdReferee1);
//		assertEquals(detail1, detailReferee1);
//		
//		
//		//input 2*****
//		parser.parse(inputAdd2);
//		GuiCommand testOutput2 = logic.executeCMD(inputAdd2);
//		COMMANDS cmd2 = parser.getCommand();
//		String name2 = parser.getTaskName();
//		String detail2 = parser.getDetails();		
//		List<Task> list2 = testOutput2.getListOfTasks();
//		
//		//supposed outcome
//		GuiCommand testReferee2 = new GuiCommand(COMMANDS.ADD, "Added \"Floating task 2 with details\"", list2);
//		COMMANDS cmdReferee2 = COMMANDS.ADD;
//		String nameReferee2 = "Floating task 2 with details";
//		String detailReferee2 = "details";
//		
//	
//		//assert
//		assertEquals(testOutput2.getCmd(), testReferee2.getCmd());
//		assertEquals(testOutput2.getMsg(), testReferee2.getMsg());
//		assertEquals(name2, nameReferee2);
//		assertEquals(cmd2, cmdReferee1);
//		assertEquals(detail2, detailReferee2);
//		
//		//input 3*****
//		parser.parse(inputAdd3);
//		GuiCommand testOutput3 = logic.executeCMD(inputAdd3);
//		COMMANDS cmd3 = parser.getCommand();
//		String name3 = parser.getTaskName();
//		String detail3 = parser.getDetails();		
//		List<Task> list3 = testOutput3.getListOfTasks();
//		
//		//supposed outcome
//		GuiCommand testReferee3 = new GuiCommand(COMMANDS.ADD, "Added \"Spee-Do floating task\"", list3);
//		COMMANDS cmdReferee3 = COMMANDS.ADD;
//		String nameReferee3 = "Spee-Do floating task";
//		String detailReferee3 = "-i in details";
//		
//	
//		//assert
//		assertEquals(testOutput3.getCmd(), testReferee3.getCmd());
//		assertEquals(testOutput3.getMsg(), testReferee3.getMsg());
//		assertEquals(name3, nameReferee3);
//		assertEquals(cmd3, cmdReferee3);
//		assertEquals(detail3, detailReferee3);
//		
//		
//		//input 4*****
//		parser.parse(inputAdd4);
//		GuiCommand testOutput4 = logic.executeCMD(inputAdd4);
//		COMMANDS cmd4 = parser.getCommand();
//		String name4 = parser.getTaskName();
//		String detail4 = parser.getDetails();
//		Date date4 = parser.getEndDate();
//		List<Task> list4 = testOutput4.getListOfTasks();
//		
//		//supposed outcome
//		GuiCommand testReferee4 = new GuiCommand(COMMANDS.ADD, "Added \"First non floating task\"", list4);
//		COMMANDS cmdReferee4 = COMMANDS.ADD;
//		String nameReferee4 = "First non floating task";
//		String detailReferee4 = "testing input details";
//		Date dateReferee4 = null;
//		try{
//			dateReferee4 = dateVariant4.parse("31102015 2015");
//		} catch (Exception e){
//			
//		}
//		
//	
//		//assert
//		assertEquals(testOutput4.getCmd(), testReferee4.getCmd());
//		assertEquals(testOutput4.getMsg(), testReferee4.getMsg());
//		assertEquals(name4, nameReferee4);
//		assertEquals(cmd4, cmdReferee4);
//		System.out.println(detail4);
//		System.out.println(detailReferee4);
//		assertEquals(detail4, detailReferee4);
//		assertEquals(date4, dateReferee4);
//	}
	
	//integration testing begins from the logic component and will test through
	//the storage component and file handler component.
	//at the same time, utilises the parser component to break down inputs
	@Test
	public void testIntegration(){
		Logic logic = new Logic(true);
		
		//input test cases to be tested from the logic component
		String input1 = "add First New task -i new task details -i 02Jan2016";
		String input2 = "add Second new task to be added in -d 30112015 2030 02122015 0530 "
				+ "-i task has both start and end date";
//		String input3 = "edit 2 Third task replacing first task";
//		String input4 = "delete 1";
		
		//task list is read from the json file
		//hence by checking the task list in the GuiCommand, integration test can be conducted
		GuiCommand Output1 = logic.executeCMD(input1);
		List<Task> List1 = Output1.getListOfTasks();
		int size1 = List1.size();
		Task task1 = List1.get(0);
		
		GuiCommand Output2 = logic.executeCMD(input2);
		List<Task> List2 = Output2.getListOfTasks();
		int size2 = List2.size();
		
//		GuiCommand Output3 = logic.executeCMD(input3);
//		List<Task> List3 = Output3.getListOfTasks();
//		int size3 = List3.size();
//		
//		GuiCommand Output4 = logic.executeCMD(input4);
//		List<Task> List4 = Output4.getListOfTasks();
//		int size4 = List4.size();
		
		
		//comparing the size of list in file handler after every command
		assertEquals(size1, 1);
		assertEquals(size2, 2);
//		assertEquals(size3, 2);
//		assertEquals(size4, 1);
//		
		//creating supposed task information to compare with actual tasks in the list
		String nameReferee1 = "First New task";
		String detailsReferee1 = "new task details";
		Date dateReferee1 = null;
		try{
			dateReferee1 = dateVariant2.parse("02012016");
		} catch (Exception e){
			
		}
		
		
		//comparing the supposed task information with the actual task information
		//testing input 1
		assertEquals(task1.getName(), nameReferee1);
		System.out.println(task1);
		assertEquals(task1.getDetails(), detailsReferee1);
		assertEquals(task1.getEndDate(), dateReferee1);
		
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
