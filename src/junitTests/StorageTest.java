//@@author A0125369Y
package junitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import storage.Storage;
import storage.Task;

public class StorageTest {
	//private static Storage store = new Storage(true);
	//private static Task task;

	@Test
	public void testAdd() {

		Storage store = new Storage(true);
		
		// Adding as usual
		Date date = new Date();
		String name = "test";
		String details = "task details";
		String taskName = store.add(name, details, null, date);
		assertEquals(name, taskName); 

		// Checking other variables
		List<Task> taskList = store.getTaskList();
		assertEquals(details, taskList.get(0).getDetails());
		assertEquals(date, taskList.get(0).getEndDate());
		
		// Duplicated task
		String duplicate = store.add(name, details, null, date);
		assertEquals(null, duplicate); 
	}

	@Test
	public void testDelete() {
		Storage store = new Storage(true);
		assert store.getTaskList().isEmpty();
		
		// Adding as usual
		Date date = new Date();
		String name = "test";
		String details = "task details";
		String taskName = store.add(name, details, null, date);
		
		assertEquals(taskName, store.delete(0));
	}

	@Test
	public void testUndoDelete() {
		Storage store = new Storage(true);
		assert store.getTaskList().isEmpty();
		
		// Adding as usual
		Date date = new Date();
		String name = "test";
		String details = "task details";
		
		// Assume that add and delete are working
		store.add(name, details, null, date);
		store.delete(0);
		
		assertEquals(name, store.undo());
	}

	@Test
	public void testEdit() {
		Storage store = new Storage(true);
		
		// Adding as usual
		Date date = new Date();
		String name = "test";
		String details = "task details";
		
		// Assume that add is working
		store.add(name, details, null, date);
		
		String newName = "new test";
		store.edit(0, newName, null, null, null);
		String editedName = store.getTaskList().get(0).getName();
		assertEquals(newName, editedName);
	}
	
	@Test
	public void testUndoEdit() {
		Storage store = new Storage(true);
		
		// Adding as usual
		Date date = new Date();
		String name = "test";
		String details = "task details";
		String taskName = store.add(name, details, null, date);
		String newName = "new test";
		
		// Assume that edit and undo are working
		store.edit(0, newName, null, null, null);
		store.undo();
		
		assertEquals(taskName, store.getTaskList().get(0).getName());
	}
	
	@Test
	public void testComplete() {
		Storage store = new Storage(true);
		
		// Adding as usual
		Date date = new Date();
		String name = "test";
		String details = "task details";
		
		// Assume that add is working
		String taskName = store.add(name, details, null, date);
		
		assertEquals(taskName, store.complete(0));
		assertEquals(true, store.getCompletedList().get(0).isCompleted());
	}

	@Test
	public void testSearch() throws ParseException {
		Storage store = new Storage(true);
		// Adding more entries
		String name;
		String details;
		Date date;

		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm");
		String dateInString = "28-09-2015 08:00";
		date = sdf.parse(dateInString);
		name = "test 2";
		details = "this is test 2";
		store.add(name, details, null, date);

		sdf = new SimpleDateFormat("dd-M-yyyy hh:mm");
		dateInString = "21-10-2015 02:00";
		date = sdf.parse(dateInString);
		name = "Sleep";
		details = "Get sleep to prepare for tutorial";
		store.add(name, details, null, date);

		sdf = new SimpleDateFormat("dd-M-yyyy hh:mm");
		dateInString = "24-10-2015 02:00";
		date = sdf.parse(dateInString);
		name = "2103 Meeting";
		details = "Get more coding done before 2010 written & online quiz";
		store.add(name, details, null, date);

		List<Task> searchList = store.search("2103");
		for (Task task : searchList) {
			System.out.println(task);
		}
		assertEquals(name, searchList.get(0).getName());

		searchList = store.search("Get");
		for (Task task : searchList) {
			System.out.println(task);
		}
		assertEquals("Sleep", searchList.get(0).getName());
		
		searchList = store.search("Apples");
		for (Task task : searchList) {
			System.out.println(task);
		}
		assertEquals(0, searchList.size());
		
		searchList = store.search("october");
		for (Task task : searchList) {
			System.out.println(task);
		}
		assertEquals(2, searchList.size());
	}



	@Test
	public void testValidIndex() {
		// Assumes that Add, Undo, Delete, Edit works 
		
		Storage store = new Storage(true);
		// Testing index via boundaries
		Date date = new Date();
		String name = "test 1";
		String details = "this is test 1";
		store.add(name, details, null, date);

		date = new Date();
		name = "test 2";
		details = "this is test 2";
		store.add(name, details, null, date);

		date = new Date();
		name = "test 3";
		details = "this is test 3";
		store.add(name, details, null, date);
		for (Task task : store.getTaskList()) {
			System.out.println(task);
		}
		assertEquals(null, store.delete(-1));
		assertNotEquals(null, store.delete(0));
		store.undo();
		assertNotEquals(null, store.delete(2));
		store.undo();
		assertEquals(null, store.delete(3));

		String newName = "new test";
		assertEquals(null, store.edit(-1, newName, null, null, null));
		assertNotEquals(null, store.edit(0, newName, null, null, null));
		assertNotEquals(null, store.edit(2, newName, null, null, null));
		assertEquals(null, store.edit(3, newName, null, null, null));
	}
}
