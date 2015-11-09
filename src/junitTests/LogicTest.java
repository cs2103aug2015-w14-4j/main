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
	Logic l = new Logic(true);
	static String details = "detail";
	static String name = "task1";
	static Date date;
	static Task testT;
	static 	GuiCommand testGCommand;
	
	//*************************Testing Add****************************************************
	@Test
	public void testAdd() {
		//Input test cases
		String inputAdd1 = "add Floating task 1";
		String inputAdd2 = "add Floating task 2 with details -i detail";
		String inputAdd3 = "add Spee-Do floating task -i -i in details";
		String inputAdd4 = "add First non floating task -d 31102015 2015 -i testing input details";
		String inputAdd5 = "add details input before date -i this is input details -d 11-11-2013 16:12";
		String inputAdd6 = "add Task date format1 -d 20-3-2016";
		String inputAdd7 = "add Start date End date -d 02/11/1993 12:10 03/11/1993 16:30";
		String inputAdd8 = "add End date before start date -d 03/11/1993 10:10 02/11/1993 10:10";
		String inputAdd9 = "add -i details withough task name";
		
		//Output from test cases
		GuiCommand outAdd1 = l.executeCMD(inputAdd1);
		List<Task> LA1 = outAdd1.getListOfTasks();
		
		GuiCommand outAdd2 = l.executeCMD(inputAdd2);
		List<Task> LA2 = outAdd2.getListOfTasks();
		
		GuiCommand outAdd3 = l.executeCMD(inputAdd3);
		List<Task> LA3 = outAdd3.getListOfTasks();
		
		GuiCommand outAdd4 = l.executeCMD(inputAdd4);
		List<Task> LA4 = outAdd4.getListOfTasks();
		
		GuiCommand outAdd5 = l.executeCMD(inputAdd5);
		List<Task> LA5 = outAdd5.getListOfTasks();
		
		GuiCommand outAdd6 = l.executeCMD(inputAdd6);
		List<Task> LA6 = outAdd6.getListOfTasks();
		
		GuiCommand outAdd7 = l.executeCMD(inputAdd7);
		List<Task> LA7 = outAdd7.getListOfTasks();
		
		GuiCommand outAdd8 = l.executeCMD(inputAdd8);
		List<Task> LA8 = outAdd8.getListOfTasks();
		
		GuiCommand outAdd9 = l.executeCMD(inputAdd9);
		List<Task> LA9 = outAdd9.getListOfTasks();
		
		//Supposed test outputs
		GuiCommand testAdd1 = new GuiCommand(COMMANDS.ADD, "Added \"Floating task 1\"", LA1);
		GuiCommand testAdd2 = new GuiCommand(COMMANDS.ADD, "Added \"Floating task 2 with details\"", LA2);
		GuiCommand testAdd3 = new GuiCommand(COMMANDS.ADD, "Added \"Spee-Do floating task\"", LA3);
		GuiCommand testAdd4 = new GuiCommand(COMMANDS.ADD, "Added \"First non floating task\"", LA4);
		GuiCommand testAdd5 = new GuiCommand(COMMANDS.ADD, "Added \"details input before date\"", LA5);
		GuiCommand testAdd6 = new GuiCommand(COMMANDS.ADD, "Added \"Task date format1\"", LA6);
		GuiCommand testAdd7 = new GuiCommand(COMMANDS.ADD, "Added \"Start date End date\"", LA7);
		GuiCommand testAdd8 = new GuiCommand(COMMANDS.ADD, "Added \"End date before start date\"", LA8);
		GuiCommand testAdd9 = new GuiCommand(COMMANDS.ADD, "Added \"-i details withough task name\"", LA9);
		
		
		//comparing test outputs with supposed test outputs
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
		//added task first to facilitate delete
		String inputAdd1 = "add Floating task 1";
		String inputAdd2 = "add Floating task 2 with details -i detail";
		String inputAdd3 = "add Spee-Do floating task -i -i in details";
		String inputAdd4 = "add First non floating task -d 31102015 2015 -i testing input details";
		String inputAdd5 = "add details input before date -i this is input details -d 11-11-2013 16:12";
		String inputAdd6 = "add Task date format1 -d 20-3-2016";
		String inputAdd7 = "add Start date End date -d 02/11/1993 12:10 03/11/1993 16:30";
		String inputAdd8 = "add End date before start date -d 03/11/1993 10:10 02/11/1993 10:10";
		String inputAdd9 = "add -i details withough task name";
		
		l.executeCMD(inputAdd1);
		l.executeCMD(inputAdd2);
		l.executeCMD(inputAdd3);
		l.executeCMD(inputAdd4);
		l.executeCMD(inputAdd5);
		l.executeCMD(inputAdd6);
		l.executeCMD(inputAdd7);
		l.executeCMD(inputAdd8);
		l.executeCMD(inputAdd9);
		
		String inputDel = "delete 1";
		//Task list now should have 8 items
		
		//output from test cases and remaining size of task list
		GuiCommand outDel1 = l.executeCMD(inputDel);
		int LD1 = l.getTaskList().size();
		
		l.executeCMD(inputDel);
		int LD2 = l.getTaskList().size();
		
		l.executeCMD(inputDel);
		int LD3 = l.getTaskList().size();
		
		l.executeCMD(inputDel);
		int LD4 = l.getTaskList().size();
		
		l.executeCMD(inputDel);
		int LD5 = l.getTaskList().size();
	
		l.executeCMD(inputDel);	
		int LD6 = l.getTaskList().size();
		
		l.executeCMD(inputDel);
		int LD7 = l.getTaskList().size();
		
		l.executeCMD(inputDel);
		int LD8 = l.getTaskList().size();
		
		l.executeCMD(inputDel);
		int LD9 = l.getTaskList().size();
		
		
		//checking list size
		assertEquals(LD1, 8);
		assertEquals(LD2, 7);
		assertEquals(LD3, 6);
		assertEquals(LD4, 5);
		assertEquals(LD5, 4);
		assertEquals(LD6, 3);
		assertEquals(LD7, 2);
		assertEquals(LD8, 1);
		assertEquals(LD9, 0);
	}
	
	
	//*************************Testing Edit****************************************************
	@Test
	public void testEdit(){
		//add back the tasks for editting, tested in testAdd
		String Add1 = "add Floating task 1";
		String Add2 = "add Floating task 2 with details -i detail";
		String Add3 = "add Spee-Do floating task -i -i in details";
		String Add4 = "add First non floating task -d 31102015 2015 -i testing input details";
		String Add5 = "add details input before date -i this is input details -d 11-11-2013 16:12";
		String Add6 = "add Task date format1 -d 20-3-2016";
		String Add7 = "add Start date End date -d 02/11/1993 12:10 03/11/1993 16:30";
		String Add8 = "add End date before start date -d 03/11/1993 10:10 02/11/1993 10:10";
		
		l.executeCMD(Add1);
		l.executeCMD(Add2);
		l.executeCMD(Add3);
		l.executeCMD(Add4);
		l.executeCMD(Add5);
		l.executeCMD(Add6);
		l.executeCMD(Add7);
		l.executeCMD(Add8);
		
		String inputEdit1 = "edit 1 -i added details -d 10112015 2000";
		String inputEdit2 = "edit 1 New Name";
		String inputEdit3 = "edit 2 Changed name -d Hi";
		String inputEdit4 = "edit 4";

		GuiCommand editOut1 = l.executeCMD(inputEdit1);
		List<Task> LE1 = editOut1.getListOfTasks();
		GuiCommand editOut2 = l.executeCMD(inputEdit2);
		List<Task> LE2 = editOut1.getListOfTasks();
		GuiCommand editOut3 = l.executeCMD(inputEdit3);
		List<Task> LE3 = editOut1.getListOfTasks();
		GuiCommand editOut4 = l.executeCMD(inputEdit4);
		
		GuiCommand testEdit1 = new GuiCommand(COMMANDS.EDIT, "Edited \"Floating task 1\"", LE1);
		GuiCommand testEdit2 = new GuiCommand(COMMANDS.EDIT, "Edited \"Floating task 2 with details\"", LE2);
		GuiCommand testEdit3 = new GuiCommand(COMMANDS.EDIT, "Edited \"Spee-Do floating task\"", LE3);
		GuiCommand testEdit4 = new GuiCommand(COMMANDS.INVALID, "Task not edited");
		
		
		assertEquals(editOut1.getCmd(), testEdit1.getCmd());
		System.out.println("Hi " +editOut1.getMsg());
		assertEquals(editOut1.getMsg(), testEdit1.getMsg());
		
		assertEquals(editOut2.getCmd(), testEdit2.getCmd());
		System.out.println("Hi " +editOut2.getMsg());
		assertEquals(editOut2.getMsg(), testEdit2.getMsg());
		
		assertEquals(editOut3.getCmd(), testEdit3.getCmd());
		assertEquals(editOut3.getMsg(), testEdit3.getMsg());
		
		assertEquals(editOut4.getCmd(), testEdit4.getCmd());
		assertEquals(editOut4.getMsg(), testEdit4.getMsg());
	}
	
	@Test
	public void testSearch(){
		
	}
}
