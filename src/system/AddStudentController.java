package system;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

public class AddStudentController {

	 @FXML
	    private JFXTextField Insertname;

	    @FXML
	    private JFXTextField Insertlastname;

	    @FXML
	    private JFXDatePicker Insertbirthday;

	    @FXML
	    private JFXRadioButton male;

	    @FXML
	    private ToggleGroup gendergroup;

	    @FXML
	    private JFXRadioButton female;

	    @FXML
	    private JFXComboBox<Student> InsertGroup;
}
