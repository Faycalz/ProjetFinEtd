package system;



import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import system.FxmlLoaderr;

public class ControllerSys {


    @FXML
    private BorderPane mainPane;
    
	@FXML
	void initialize() {
		 FxmlLoaderr object = new FxmlLoaderr();
	     Pane view = object.getPage("Dashboard");
	     mainPane.setCenter(view);
		
		}
	}
	


