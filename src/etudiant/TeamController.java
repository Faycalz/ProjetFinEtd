package etudiant;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import system.Evaluation;
import system.EvaluationController;

public class TeamController implements Initializable {
	 @FXML
	    private Label list;

	    @FXML
	    private TableView<Team> tableGroup;

	    @FXML
	    private TableColumn<Team, Integer> teamc;

	    @FXML
	    private TableColumn<Team, String> stud1;

	    @FXML
	    private TableColumn<Team, String> stud11;

	    @FXML
	    private TableColumn<Team, String> stud2;

	    @FXML
	    private TableColumn<Team, String> stud22;

	    @FXML
	    private TableColumn<Team, String> subj;

	    @FXML
	    private TableColumn<Team, String> grpt;

	    @FXML
	    private JFXButton chooseStud;
	    
	    @FXML
	    private JFXButton RefT;
	    
ObservableList<Team> TeamList = FXCollections.observableArrayList();
	    
	    
	    
	    
	    @FXML
	    public void getAddView(ActionEvent actionEvent) {
	        try {
	            Parent parent = FXMLLoader.load(getClass().getResource("ChooseTeam.fxml"));
	            Scene scene = new Scene(parent);
	            Stage stage = new Stage();
	            stage.setScene(scene);
	            stage.initStyle(StageStyle.UTILITY);
	            stage.setResizable(false);
	            stage.show();
	            //refreshView();
	        } catch (IOException ex) {
	            Logger.getLogger(TeamController.class.getName()).log(Level.SEVERE, null, ex);
	        }
	    }

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			// TODO Auto-generated method stub
			
		}
  

}
