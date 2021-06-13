package etudiant;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import etudiant.FxmlLoaderr;

public class ControllerEtd implements Initializable{

    @FXML
    private BorderPane mainPane;
	  private Stage stage ;
	    private Scene scene ;
	    private Parent root ;

	    @FXML
	    void logoutBTNAction(ActionEvent event) throws IOException {
	    	root=FXMLLoader.load(getClass().getResource("/application/MainScene.fxml"));
	    	stage =(Stage)((Node)event.getSource()).getScene().getWindow();
	    	scene = new Scene(root);
	    	stage.setScene(scene);
	    	stage.centerOnScreen();
	    	stage.show();
	    }
	    
	    @FXML
	   	void DashAction(ActionEvent event) {
	   		
	       	FxmlLoaderr object = new FxmlLoaderr();
	   	     Pane view = object.getPage("Dashboard");
	   	     mainPane.setCenter(view);
	   		
	   	}
	    @Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			
			
			
			 FxmlLoaderr object = new FxmlLoaderr();
		     Pane view = object.getPage("Dashboard");
		     mainPane.setCenter(view);
		     
		     
		    
		     
		}
}
