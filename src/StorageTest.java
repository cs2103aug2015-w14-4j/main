import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class StorageTest {

	public void test() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testAdd() throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm");
		String dateInString = "27-09-2015 08:00";
		Date date = sdf.parse(dateInString);
		String name = "test";
		assertEquals(true,Storage.add(name, date));
	}
	
	@Test
	public void testDelete(){
		assertEquals("test",Storage.delete(0));
	}
	
	@Test
	public void testSave() throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm");
		String dateInString = "28-09-2015 08:00";
		Date date = sdf.parse(dateInString);
		String name = "test2";
		Storage.add(name, date);
		assertEquals(true,Storage.saveFile());
	}
	
	@Test
	public void testRead(){
		Storage.readFile();
	}
}
