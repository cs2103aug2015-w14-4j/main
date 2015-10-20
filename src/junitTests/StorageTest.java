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
	private static Storage store = new Storage(true);
	private static Task task;

	@Test
	public void testAdd() {

		// Adding as usual
		Date date = new Date();
		String name = "test";
		String details = "task details";
		task = store.add(name, details, date);
		assertEquals(name, task.getName()); 
		assertEquals(details, task.getDetails()); 
		assertEquals(date, task.getStartDate());

		// Duplicated task
		Task duplicate = store.add(name, details, date);
		assertEquals(null, duplicate); 
	}

	@Test
	public void testDelete() {
		assertEquals(task, store.delete(0));
	}

	@Test
	public void testUndoDelete() {
		assertEquals(true, store.undo());
		assertEquals(task, store.getTaskList().get(0));
	}

	@Test
	public void testEdit() {
		String newName = "new test";
		assertEquals(newName, store.edit(0, newName).getName());
	}
	
	@Test
	public void testUndoEdit() {
		assertEquals(true, store.undo());
		assertEquals(task, store.getTaskList().get(0));
	}

	@Test
	public void testSearch() throws ParseException {
		// Adding more entries
		String name;
		String details;
		Date date;

		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm");
		String dateInString = "28-09-2015 08:00";
		date = sdf.parse(dateInString);
		name = "test 2";
		details = "this is test 2";
		store.add(name, details, date);

		sdf = new SimpleDateFormat("dd-M-yyyy hh:mm");
		dateInString = "21-10-2015 02:00";
		date = sdf.parse(dateInString);
		name = "Sleep";
		details = "Get sleep to prepare for tutorial";
		store.add(name, details, date);

		sdf = new SimpleDateFormat("dd-M-yyyy hh:mm");
		dateInString = "24-10-2015 02:00";
		date = sdf.parse(dateInString);
		name = "2103 Meeting";
		details = "Get more coding done before 2010 written & online quiz";
		store.add(name, details, date);

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
	}

	@Test
	public void testComplete() {
		store.complete(0);
		assertEquals(true, store.getTaskList().get(0).isCompleted());
	}

	@Test
	public void testValidIndex() {
		// Testing index via boundaries
		Date date = new Date();
		String name = "test 1";
		String details = "this is test 1";
		store.add(name, details, date);

		date = new Date();
		name = "test 2";
		details = "this is test 2";
		store.add(name, details, date);

		date = new Date();
		name = "test 3";
		details = "this is test 3";
		store.add(name, details, date);

		assertEquals(null, store.delete(-1));
		assertNotEquals(null, store.delete(0));
		assertNotEquals(null, store.delete(2));
		assertEquals(null, store.delete(3));

		String newName = "new test";
		assertEquals(null, store.edit(-1, newName));
		assertNotEquals(null, store.edit(0, newName));
		assertNotEquals(null, store.edit(2, newName));
		assertEquals(null, store.edit(3, newName));
	}

	// @Test
	// public void testSave() throws ParseException{
	// //Storage store = new Storage();
	// SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm");
	// String dateInString = "28-09-2015 08:00";
	// Date date = sdf.parse(dateInString);
	// String name = "test2";
	// store.add(name,"test", date);
	// //assertEquals(true,store.saveFile());
	// }
}
