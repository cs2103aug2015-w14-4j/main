//@@author A0125369Y
package processor;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * ErrorProcessor is a class to generate error messages in GUI to alert the
 * user.
 * 
 * @author Barnabas
 *
 */
public class ErrorProcessor {

	private static final String ERROR_TITLE = "Error";
	private static final String ERROR_TRIGGERED = "Error Triggered";
	private static final String ERROR_TRIGGERED_AT = "Error Triggered at ";

	/**
	 * Method to display an error message.
	 * 
	 * @param error
	 *            the error message in String
	 */
	public static void alert(String error) {
		try {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle(ERROR_TITLE);
			alert.setHeaderText(ERROR_TRIGGERED);
			alert.setContentText(error);
			alert.showAndWait();
		} catch (java.lang.ExceptionInInitializerError e) {
			// Exception only thrown when javaFx 8 not properly initialised
			System.out.println(error);
		}
	}

	/**
	 * Method to display an error message.
	 * 
	 * @param errorLocation
	 *            the location of the error in String
	 * @param error
	 *            the error message in String
	 */
	public static void alert(String errorLocation, String error) {
		try {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle(ERROR_TITLE);
			alert.setHeaderText(ERROR_TRIGGERED_AT + errorLocation);
			alert.setContentText(error);
			alert.showAndWait();
		} catch (java.lang.ExceptionInInitializerError e) {
			System.out.println(ERROR_TRIGGERED_AT + errorLocation);
			System.out.println(error);
		}
	}
}
