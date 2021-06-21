package system;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class AddEvaluationController implements Initializable {
	  @FXML
	    private JFXComboBox<String> InsertTeacher;

	    @FXML
	    private JFXComboBox<Integer> InsertTeam;

	    @FXML
	    private JFXDatePicker InsertDate;

	    @FXML
	    private JFXTextField InsertMark;

	    @FXML
	    private JFXTextField InsertRemark;

	    @FXML
	    private JFXTextField InsertRapp;

	    @FXML
	    private JFXTextField InsertApp;

	    @FXML
	    private JFXButton save;

	    @FXML
	    private JFXButton reset;

	    @FXML
	    private Label list;

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			
		}

}
