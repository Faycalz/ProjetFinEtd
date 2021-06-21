package etudiant;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;

public class ChooseSubjectController {
	  @FXML
	    private JFXTextField Insertname;

	    @FXML
	    private JFXTextField Insertlastname;

	    @FXML
	    private JFXComboBox<String> InsertSubject;

	    @FXML
	    private JFXComboBox<String> InsertTeam;

	    @FXML
	    private JFXButton save;

	    @FXML
	    private JFXButton reset;

}
