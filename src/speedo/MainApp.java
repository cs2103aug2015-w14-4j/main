package speedo;
import java.io.IOException;
import java.util.ArrayList;

import controller.RootController;
import controller.TasksListController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class MainApp extends Application {
    
    private Stage primaryStage;
    private BorderPane rootLayout;
        
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("SpeeDo");

        initRootLayout();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
    
    /**
     * Constructor
     */
    public MainApp() {
    	
    }
    
    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();         
	        loader.setLocation(getClass().getResource("/view/RootView.fxml"));
	        
	        rootLayout = (BorderPane) loader.load();
	        // need to load before getting controller
	        RootController controller = loader.getController();
	        controller.setMainApp(this);
			Scene scene = new Scene(rootLayout,850,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
    
    
}
