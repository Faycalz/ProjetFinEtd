package system;



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import system.FxmlLoaderr;

public class ControllerSys implements Initializable{


	@FXML
	private PieChart pieChart;

    @FXML
    private BorderPane mainPane;
    
    @FXML
    private JFXButton HomeBtn;

    @FXML
    private JFXButton LogouBtn;

   

    @FXML
    void logout(ActionEvent event) {
    	Parent root = null;
    	try {
			root = FXMLLoader.load(getClass().getResource("/application/MainScene.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	

   
    
    
    
    @FXML
	void studentAction(ActionEvent event) {
		
    	FxmlLoaderr object = new FxmlLoaderr();
	     Pane view = object.getPage("Student");
	     mainPane.setCenter(view);
		
	}
    
    @FXML
   	void DashAction(ActionEvent event) {
   		
       	FxmlLoaderr object = new FxmlLoaderr();
   	     Pane view = object.getPage("Dashboard");
   	     mainPane.setCenter(view);
   		
   	}
       
    @FXML
   	void GroupAction(ActionEvent event) {
   		
       	FxmlLoaderr object = new FxmlLoaderr();
   	     Pane view = object.getPage("GroupFxml");
   	     mainPane.setCenter(view);
   		
   	}
       
    @FXML
   	void SubjectAction(ActionEvent event) {
   		
       	FxmlLoaderr object = new FxmlLoaderr();
   	     Pane view = object.getPage("Subject");
   	     mainPane.setCenter(view);
   		
   	}
    
    @FXML
   	void SessionAction(ActionEvent event) {
   		
       	FxmlLoaderr object = new FxmlLoaderr();
   	     Pane view = object.getPage("Session");
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
	


