package system;



import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import system.FxmlLoaderr;

public class ControllerSys implements Initializable{


	@FXML
	private PieChart pieChart;

    @FXML
    private BorderPane mainPane;
    
	
	
	/*void initialize() {
		 FxmlLoaderr object = new FxmlLoaderr();
	     Pane view = object.getPage("Dashboard");
	     mainPane.setCenter(view);
		
	     
		}*/

    
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
   	     Pane view = object.getPage("Group");
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
	


