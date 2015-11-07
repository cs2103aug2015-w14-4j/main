package junitTests;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	GuiCommand testAdd1 = new GuiCommand(COMMANDS.ADD, "Added Floating task 1");
	GuiCommand testAdd2 = new GuiCommand(COMMANDS.ADD, "Added Floating task 2 with details");
	GuiCommand testAdd3 = new GuiCommand(COMMANDS.ADD, "Added Spee-Do floating task");
	GuiCommand testAdd4 = new GuiCommand(COMMANDS.ADD, "Added First non floating task");
	GuiCommand testAdd5 = new GuiCommand(COMMANDS.ADD, "Added details input before date");
	GuiCommand testAdd6 = new GuiCommand(COMMANDS.ADD, "Added Task date format1");
	GuiCommand testAdd7 = new GuiCommand(COMMANDS.ADD, "Added Start date End date");
	GuiCommand testAdd8 = new GuiCommand(COMMANDS.ADD, "Added End date before start date");
	
	@Test
	public void testAdd() {
		GuiCommand outAdd1 = l.executeCMD(inputAdd1);
		assertEquals(outAdd1.getCmd(), testAdd1.getCmd());
		assertEquals(outAdd1.getMsg(), testAdd1.getMsg());
		
		GuiCommand outAdd2 = l.executeCMD(inputAdd2); 
		System.out.println(outAdd2.getMsg());
		System.out.println(testAdd2.getMsg());
		
		assertEquals(outAdd2.getCmd(), testAdd2.getCmd());
		assertEquals(outAdd2.getMsg(), testAdd2.getMsg());
		
		GuiCommand outAdd3 = l.executeCMD(inputAdd3);
		assertEquals(outAdd3.getCmd(), testAdd3.getCmd());
		assertEquals(outAdd3.getMsg(), testAdd3.getMsg());
		
		GuiCommand outAdd4 = l.executeCMD(inputAdd4);
		assertEquals(outAdd4.getCmd(), testAdd4.getCmd());
		assertEquals(outAdd4.getMsg(), testAdd4.getMsg());
		
		GuiCommand outAdd5 = l.executeCMD(inputAdd5);
		assertEquals(outAdd5.getCmd(), testAdd5.getCmd());
		assertEquals(outAdd5.getMsg(), testAdd5.getMsg());
		
		GuiCommand outAdd6 = l.executeCMD(inputAdd6);
		assertEquals(outAdd6.getCmd(), testAdd6.getCmd());
		assertEquals(outAdd6.getMsg(), testAdd6.getMsg());
		
		GuiCommand outAdd7 = l.executeCMD(inputAdd7);
		assertEquals(outAdd7.getCmd(), testAdd7.getCmd());
		assertEquals(outAdd7.getMsg(), testAdd7.getMsg());
		
		GuiCommand outAdd8 = l.executeCMD(inputAdd8);
		assertEquals(outAdd8.getCmd(), testAdd8.getCmd());
		assertEquals(outAdd8.getMsg(), testAdd8.getMsg());

	}
	
//	@Test
//	public void testDelete() {
//
//	}
//	
		
	@Test
	public void testExecuteCmd() {
//		try{
//			date = dateVariant4.parse(" 20102015 1000");
//		} catch (Exception e){
//				
//		}
//		testT = new Task(name, details, date);
//		testGCommand = new GuiCommand(COMMANDS.ADD, "Added task1");
//		
//		GuiCommand get = l.executeCMD(input);
//		System.out.println(get.getMsg() + " " + get.getTask());
//		System.out.println("TestT: " + testT);
//		 assertEquals(testT.getName(), get.getTask().getName());
//		 assertEquals(testT.getDetails(), get.getTask().getDetails());
//		 assertEquals(testT.getStartDate(), get.getTask().getStartDate());
//		}
	}
}
