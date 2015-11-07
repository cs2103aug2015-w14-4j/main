//@@author A0121823R
package junitTests;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import logic.GuiCommand;
import logic.Logic;
import processor.COMMANDS;
import storage.Task;

public class LogicTest {
	Logic l = new Logic(true);
	String input = "add \"task1\" 20102015 1000 'detail'";
	static String details = "detail";
	static String name = "task1";
	static Date date;
	static Task testT;
	static 	GuiCommand testGCommand;
	
	String inputAdd1 = "add Floating task 1";
	String inputAdd2 = "add Floating task 2 with details -i detail";
	String inputAdd3 = "add Spee-Do floating task -i -i in details";
	String inputAdd4 = "add First non floating task -d 31102015 2015 -i testing input details";
	String inputAdd5 = "add details input before date -i this is input details -d 11-11-2013 16:12";
	String inputAdd6 = "add Task date format1 -d 20-3-2016";
	String inputAdd7 = "add Start date End date -d 02/11/1993 12:10 03/11/1993 16:30";
	String inputAdd8 = "add End date before start date -d 03/11/1993 10:10 02/11/1993 10:10";
	
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
	
	
	
	GuiCommand testAdd1 = new GuiCommand(COMMANDS.ADD, "Added Floating task 1", LA1);
	GuiCommand testAdd2 = new GuiCommand(COMMANDS.ADD, "Added Floating task 2 with details", LA2);
	GuiCommand testAdd3 = new GuiCommand(COMMANDS.ADD, "Added Spee-Do floating task", LA3);
	GuiCommand testAdd4 = new GuiCommand(COMMANDS.ADD, "Added First non floating task", LA4);
	GuiCommand testAdd5 = new GuiCommand(COMMANDS.ADD, "Added details input before date", LA5);
	GuiCommand testAdd6 = new GuiCommand(COMMANDS.ADD, "Added Task date format1", LA6);
	GuiCommand testAdd7 = new GuiCommand(COMMANDS.ADD, "Added Start date End date", LA7);
	GuiCommand testAdd8 = new GuiCommand(COMMANDS.ADD, "Added End date before start date", LA8);
	
	@Test
	public void testAdd() {
		assertEquals(outAdd1.getCmd(), testAdd1.getCmd());
		assertEquals(outAdd1.getMsg(), testAdd1.getMsg());
		
//		assertEquals(outAdd2.getCmd(), testAdd2.getCmd());
//		assertEquals(outAdd2.getMsg(), testAdd2.getMsg());
		
		
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

	}
	
//	@Test
//	public void testDelete() {
//
//	}
//	
		

}
