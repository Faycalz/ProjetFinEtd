package system;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import database.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class AddGroupController implements Initializable {


    @FXML
    private JFXTextField InsertID;

    @FXML
    private JFXTextField InsertName;

    @FXML
    private JFXComboBox<Integer> InsertTeacher;

    @FXML
    private JFXButton save;

    @FXML
    private JFXButton reset;
    
    
    
    

    Connection con ;
    Statement st ;
    ResultSet rs = null;
    PreparedStatement preparedStatement;
    String query;
    
    
    
    
    
    
    
    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
	
	