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
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import system.FxmlLoaderr;

public class ControllerSys implements Initializable{


    @FXML
    private AnchorPane scenePane;
	@FXML
	private PieChart pieChart;

    @FXML
    private BorderPane mainPane;
    
    @FXML
    private JFXButton HomeBtn;

    @FXML
    private JFXButton LogouBtn;
    @FXML
    private JFXButton dash;

    @FXML
    private JFXButton studM;

    @FXML
    private JFXButton grpM;

    @FXML
    private JFXButton subP;

    @FXML
    private JFXButton sesP;

    @FXML
    private JFXButton teamP;

    @FXML
    private JFXButton Eval;

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
    
    @FXML
   	void EvaluationAction(ActionEvent event) {
   		
       	FxmlLoaderr object = new FxmlLoaderr();
   	     Pane view = object.getPage("Evaluation");
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
	


