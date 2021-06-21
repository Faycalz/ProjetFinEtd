package system;

import java.io.IOException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EvaluationController {
	
	   @FXML
	    private JFXTextField filterField;

	    @FXML
	    private JFXButton AddEv;

	    @FXML
	    private JFXButton RefEv;

	    @FXML
	    private TableView<Evaluation> tableSub;

	    @FXML
	    private TableColumn<Evaluation, String> teach;

	    @FXML
	    private TableColumn<Evaluation, String> stud1;

	    @FXML
	    private TableColumn<Evaluation, String> stud2;

	    @FXML
	    private TableColumn<Evaluation, Date> DateEV;

	    @FXML
	    private TableColumn<Evaluation, Integer> mark;

	    @FXML
	    private TableColumn<Evaluation, String> ReppP;

	    @FXML
	    private TableColumn<Evaluation, String> AppP;

	    @FXML
	    private TableColumn<Evaluation, String> remark;
	    
	    ObservableList<Evaluation> EvaluationList = FXCollections.observableArrayList();
	    
	    
	    
	    
	    @FXML
	    public void getAddView(ActionEvent actionEvent) {
	        try {
	            Parent parent = FXMLLoader.load(getClass().getResource("AddEvaluation.fxml"));
	            Scene scene = new Scene(parent);
	            Stage stage = new Stage();
	            stage.setScene(scene);
	            stage.initStyle(StageStyle.UTILITY);
	            stage.setResizable(false);
	            stage.show();
	            //refreshView();
	        } catch (IOException ex) {
	            Logger.getLogger(EvaluationController.class.getName()).log(Level.SEVERE, null, ex);
	        }

	    }

}
