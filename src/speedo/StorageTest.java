package speedo;
import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class StorageTest {
	

	@Test
	public void testAdd() throws ParseException{
		Storage store = new Storage();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm");
		String dateInString = "30-09-2015 08:00";
		Date date = sdf.parse(dateInString);
		String name = "test";
		assertEquals(true,store.add(name, date));
		store.saveFile();
	}
	
	
	public void testDelete(){
		Storage store = new Storage();
		assertEquals("test",store.delete(0));
		store.saveFile();
	}
	
	@Test
	public void testSave() throws ParseException{
		Storage store = new Storage();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm");
		String dateInString = "28-09-2015 08:00";
		Date date = sdf.parse(dateInString);
		String name = "test2";
		store.add(name, date);
		assertEquals(true,store.saveFile());
	}
}
