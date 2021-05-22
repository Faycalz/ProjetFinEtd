package admin;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

public class AddSessionController implements Initializable {

	
	
	  @FXML
	    private JFXTimePicker time;

	    @FXML
	    private JFXDatePicker dateS;

	    @FXML
	    private ComboBox<String> location;

	    @FXML
	    private ComboBox<String> grpSess;

	    @FXML
	    private JFXButton save;

	    @FXML
	    private JFXButton reset;

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	

}
