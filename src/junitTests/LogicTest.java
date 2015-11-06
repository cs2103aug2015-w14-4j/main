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
	static SimpleDateFormat dateVariant4 = new SimpleDateFormat("ddMMyyyy hhmm");
	static Date date;
	static Task testT;
	static 	GuiCommand testGCommand;
	
	/*
	@Test
	public void testAdd() {
		
	}
	
	@Test
	public void testDelete() {

	}
	*/
		
	@Test
	public void testExecuteCmd() {
		try{
			date = dateVariant4.parse(" 20102015 1000");
		} catch (Exception e){
				
		}
		testT = new Task(name, details, date);
		testGCommand = new GuiCommand(COMMANDS.ADD, "Added task1", testT);
		
		GuiCommand get = l.executeCMD(input);
		System.out.println(get.getMsg() + " " + get.getTask());
		System.out.println("TestT: " + testT);
		 assertEquals(testT.getName(), get.getTask().getName());
		 assertEquals(testT.getDetails(), get.getTask().getDetails());
		 assertEquals(testT.getStartDate(), get.getTask().getStartDate());
		}
}
