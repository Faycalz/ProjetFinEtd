package system;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class AddSubjectController implements Initializable {
	

    @FXML
    private JFXTextArea title;

    @FXML
    private JFXTextArea field;

    @FXML
    private JFXTextArea descrip;

    @FXML
    private JFXTextArea keyw;

    @FXML
    private JFXTextArea devtool;

    @FXML
    private JFXButton save;

    @FXML
    private JFXButton reset;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
    
    

}
