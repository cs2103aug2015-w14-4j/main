//@@author A0125369Y
package utilities;

import java.util.logging.Logger;

import storage.Storage;

/**
 * LogProcessor is a singleton class to handle logging.
 * 
 * @author Barnabas
 *
 */
public class LogProcessor {
	
	private static Logger logger ;
	private static LogProcessor logProcessor = new LogProcessor();
	
	private LogProcessor (){
		logger = Logger.getLogger(Storage.class.getName());
	}
	
	public static LogProcessor getInstance(){
		return logProcessor;
	}
	
	public void log(String input){
		logger.info(input);
	}
}
