package processor;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ErrorProcessor {

	private static final String ERROR_TITLE = "Error";
	private static final String ERROR_TRIGGERED = "Error Triggered";
	private static final String ERROR_TRIGGERED_AT = "Error Triggered at ";

	public static void alert(String error) {
		try {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle(ERROR_TITLE);
			alert.setHeaderText(ERROR_TRIGGERED);
			alert.setContentText(error);
			alert.showAndWait();
		} catch (java.lang.ExceptionInInitializerError e) {
			System.out.println(error);
		}
	}

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
